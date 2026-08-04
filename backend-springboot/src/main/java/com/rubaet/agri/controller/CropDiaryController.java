package com.rubaet.agri.controller;

import com.rubaet.agri.entity.ActivityLog;
import com.rubaet.agri.entity.CropCycle;
import com.rubaet.agri.entity.User;
import com.rubaet.agri.repository.ActivityLogRepository;
import com.rubaet.agri.repository.CropCycleRepository;
import com.rubaet.agri.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${app.api.base-path}/diary")
public class CropDiaryController {

    private final CropCycleRepository cycleRepo;
    private final ActivityLogRepository activityRepo;
    private final UserRepository userRepo;

    public CropDiaryController(CropCycleRepository cycleRepo, ActivityLogRepository activityRepo, UserRepository userRepo) {
        this.cycleRepo = cycleRepo;
        this.activityRepo = activityRepo;
        this.userRepo = userRepo;
    }

    // ── Crop Cycles ─────────────────────────────────────────────

    @PostMapping("/cycles")
    public ResponseEntity<?> createCycle(@Valid @RequestBody CycleRequest req, Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        CropCycle cycle = new CropCycle();
        cycle.setUserId(user.getId());
        cycle.setCropName(req.getCropName().trim());
        cycle.setSeason(req.getSeason());
        cycle.setStartDate(req.getStartDate());
        cycle.setNotes(req.getNotes());
        cycle.setStatus("ACTIVE");

        return ResponseEntity.ok(cycleRepo.save(cycle));
    }

    @GetMapping("/cycles")
    public ResponseEntity<?> getCycles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        Page<CropCycle> result = cycleRepo.findByUserIdOrderByCreatedAtDesc(
            user.getId(), PageRequest.of(page, size));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("cycles", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalPages", result.getTotalPages());
        response.put("totalItems", result.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cycles/{id}/complete")
    public ResponseEntity<?> completeCycle(@PathVariable Long id, Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        return cycleRepo.findById(id)
            .filter(c -> c.getUserId().equals(user.getId()))
            .map(c -> { c.setStatus("COMPLETED"); return ResponseEntity.ok(cycleRepo.save(c)); })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/cycles/{id}")
    public ResponseEntity<?> deleteCycle(@PathVariable Long id, Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        return cycleRepo.findById(id)
            .filter(c -> c.getUserId().equals(user.getId()))
            .map(c -> { cycleRepo.delete(c); return ResponseEntity.noContent().build(); })
            .orElse(ResponseEntity.notFound().build());
    }

    // ── Activity Logs ───────────────────────────────────────────

    @PostMapping("/cycles/{cycleId}/activities")
    public ResponseEntity<?> logActivity(@PathVariable Long cycleId, @Valid @RequestBody ActivityRequest req, Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        return cycleRepo.findById(cycleId)
            .filter(c -> c.getUserId().equals(user.getId()))
            .map(cycle -> {
                ActivityLog log = new ActivityLog();
                log.setCropCycle(cycle);
                log.setActivityType(req.getActivityType().trim());
                log.setDescription(req.getDescription());
                log.setCost(req.getCost() != null ? req.getCost() : 0.0);
                return ResponseEntity.ok(activityRepo.save(log));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cycles/{cycleId}/activities")
    public ResponseEntity<?> getActivities(@PathVariable Long cycleId, Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        return cycleRepo.findById(cycleId)
            .filter(c -> c.getUserId().equals(user.getId()))
            .map(c -> ResponseEntity.ok(activityRepo.findByCropCycleIdOrderByLoggedAtDesc(cycleId)))
            .orElse(ResponseEntity.notFound().build());
    }

    private User resolveUser(Authentication auth) {
        if (auth == null) return null;
        return userRepo.findByEmail(auth.getName()).orElse(null);
    }
}

// ── DTOs ─────────────────────────────────────────────────────────

class CycleRequest {
    @NotBlank(message = "Crop name is required") private String cropName;
    private String season;
    @NotNull(message = "Start date is required") private LocalDate startDate;
    @Size(max = 500) private String notes;

    public String getCropName() { return cropName; }
    public void setCropName(String cropName) { this.cropName = cropName; }
    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}

class ActivityRequest {
    @NotBlank(message = "Activity type is required") private String activityType;
    @Size(max = 500) private String description;
    private Double cost;

    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
}
