package com.rubaet.agri.controller;

import com.rubaet.agri.entity.Alert;
import com.rubaet.agri.entity.User;
import com.rubaet.agri.repository.AlertRepository;
import com.rubaet.agri.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("${app.api.base-path}/alerts")
public class AlertController {

    private final AlertRepository alertRepo;
    private final UserRepository userRepo;

    public AlertController(AlertRepository alertRepo, UserRepository userRepo) {
        this.alertRepo = alertRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public ResponseEntity<?> getAlerts(Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        var alerts = alertRepo.findByUserIdOrderByCreatedAtDesc(user.getId());
        long unread = alertRepo.countByUserIdAndReadFalse(user.getId());

        return ResponseEntity.ok(Map.of("alerts", alerts, "unreadCount", unread));
    }

    @GetMapping("/unread-count")
    public ResponseEntity<?> unreadCount(Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
        return ResponseEntity.ok(Map.of("unreadCount", alertRepo.countByUserIdAndReadFalse(user.getId())));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long id, Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        return alertRepo.findById(id)
            .filter(a -> a.getUserId().equals(user.getId()))
            .map(a -> { a.setRead(true); return ResponseEntity.ok(alertRepo.save(a)); })
            .orElse(ResponseEntity.notFound().build());
    }

    /** Manual trigger — creates a sample weather alert for testing */
    @PostMapping("/test")
    public ResponseEntity<?> createTestAlert(Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        Alert alert = new Alert();
        alert.setUserId(user.getId());
        alert.setType("WEATHER");
        alert.setMessage("⚠️ Heavy rainfall expected in your area in the next 24 hours. Consider covering vulnerable crops.");
        alert.setSeverity("WARNING");
        return ResponseEntity.ok(alertRepo.save(alert));
    }

    private User resolveUser(Authentication auth) {
        if (auth == null) return null;
        return userRepo.findByEmail(auth.getName()).orElse(null);
    }
}
