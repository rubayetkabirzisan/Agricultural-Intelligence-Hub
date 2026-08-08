package com.rubaet.agri.repository;

import com.rubaet.agri.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByCropCycleIdOrderByLoggedAtDesc(Long cycleId);
}
