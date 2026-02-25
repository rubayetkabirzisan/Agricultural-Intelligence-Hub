package com.rubaet.agri.repository;

import com.rubaet.agri.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, Long> {
    boolean existsByNameIgnoreCase(String name);
}
