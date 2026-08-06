package com.rubaet.agri.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "crop_cycles")
public class CropCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "crop_name", nullable = false, length = 120)
    private String cropName;

    @Column(length = 60)
    private String season;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(nullable = false, length = 30)
    private String status = "ACTIVE";

    @Column(length = 500)
    private String notes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "cropCycle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityLog> activities = new ArrayList<>();

    @PrePersist
    void onCreate() { this.createdAt = Instant.now(); }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getCropName() { return cropName; }
    public void setCropName(String cropName) { this.cropName = cropName; }
    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Instant getCreatedAt() { return createdAt; }
    public List<ActivityLog> getActivities() { return activities; }
    public void setActivities(List<ActivityLog> activities) { this.activities = activities; }
}
