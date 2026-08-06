package com.rubaet.agri.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "activity_logs")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cycle_id", nullable = false)
    @JsonIgnore
    private CropCycle cropCycle;

    @Column(name = "activity_type", nullable = false, length = 60)
    private String activityType;

    @Column(length = 500)
    private String description;

    @Column
    private Double cost = 0.0;

    @Column(name = "logged_at", nullable = false, updatable = false)
    private Instant loggedAt;

    @PrePersist
    void onCreate() { this.loggedAt = Instant.now(); }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public CropCycle getCropCycle() { return cropCycle; }
    public void setCropCycle(CropCycle cropCycle) { this.cropCycle = cropCycle; }
    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    public Instant getLoggedAt() { return loggedAt; }
}
