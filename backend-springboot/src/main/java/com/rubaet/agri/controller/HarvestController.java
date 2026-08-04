package com.rubaet.agri.controller;

import com.rubaet.agri.entity.HarvestRecord;
import com.rubaet.agri.entity.User;
import com.rubaet.agri.repository.HarvestRecordRepository;
import com.rubaet.agri.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${app.api.base-path}/harvest")
public class HarvestController {

    private final HarvestRecordRepository harvestRepo;
    private final UserRepository userRepo;

    public HarvestController(HarvestRecordRepository harvestRepo, UserRepository userRepo) {
        this.harvestRepo = harvestRepo;
        this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<?> recordHarvest(@Valid @RequestBody HarvestRequest req, Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        HarvestRecord record = new HarvestRecord();
        record.setUserId(user.getId());
        record.setCropName(req.getCropName().trim());
        record.setSeason(req.getSeason());
        record.setYear(req.getYear());
        record.setActualYieldKg(req.getActualYieldKg());
        record.setRevenueUsd(req.getRevenueUsd() != null ? req.getRevenueUsd() : 0.0);
        record.setNotes(req.getNotes());

        return ResponseEntity.ok(harvestRepo.save(record));
    }

    @GetMapping
    public ResponseEntity<?> getRecords(Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
        return ResponseEntity.ok(harvestRepo.findByUserIdOrderByYearDescSeasonDesc(user.getId()));
    }

    /** Aggregate summary: average yield per crop, total revenue by year */
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        List<HarvestRecord> records = harvestRepo.findByUserIdOrderByYearDescSeasonDesc(user.getId());

        // Average yield per crop
        Map<String, Double> avgYieldByCrop = records.stream()
            .collect(Collectors.groupingBy(HarvestRecord::getCropName,
                     Collectors.averagingDouble(HarvestRecord::getActualYieldKg)));

        // Total revenue by year
        Map<Integer, Double> revenueByYear = records.stream()
            .collect(Collectors.groupingBy(HarvestRecord::getYear,
                     Collectors.summingDouble(HarvestRecord::getRevenueUsd)));

        // Yield trend (year → total yield)
        Map<Integer, Double> yieldByYear = records.stream()
            .collect(Collectors.groupingBy(HarvestRecord::getYear,
                     Collectors.summingDouble(HarvestRecord::getActualYieldKg)));

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("totalRecords", records.size());
        summary.put("avgYieldByCrop", avgYieldByCrop);
        summary.put("revenueByYear", revenueByYear);
        summary.put("yieldByYear", yieldByYear);

        return ResponseEntity.ok(summary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable Long id, Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        return harvestRepo.findById(id)
            .filter(r -> r.getUserId().equals(user.getId()))
            .map(r -> { harvestRepo.delete(r); return ResponseEntity.noContent().build(); })
            .orElse(ResponseEntity.notFound().build());
    }

    private User resolveUser(Authentication auth) {
        if (auth == null) return null;
        return userRepo.findByEmail(auth.getName()).orElse(null);
    }
}

// ── DTO ──────────────────────────────────────────────────────────
class HarvestRequest {
    @NotBlank(message = "Crop name is required") private String cropName;
    private String season;
    @Min(value = 2000, message = "Year must be 2000 or later") private int year;
    @Min(value = 0, message = "Yield must be positive") private double actualYieldKg;
    private Double revenueUsd;
    private String notes;

    public String getCropName() { return cropName; }
    public void setCropName(String cropName) { this.cropName = cropName; }
    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getActualYieldKg() { return actualYieldKg; }
    public void setActualYieldKg(double actualYieldKg) { this.actualYieldKg = actualYieldKg; }
    public Double getRevenueUsd() { return revenueUsd; }
    public void setRevenueUsd(Double revenueUsd) { this.revenueUsd = revenueUsd; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
