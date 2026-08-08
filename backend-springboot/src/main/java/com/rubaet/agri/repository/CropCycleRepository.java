package com.rubaet.agri.repository;

import com.rubaet.agri.entity.CropCycle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropCycleRepository extends JpaRepository<CropCycle, Long> {
    Page<CropCycle> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
    List<CropCycle> findByUserIdAndStatus(Long userId, String status);
}
