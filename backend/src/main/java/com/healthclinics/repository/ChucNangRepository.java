package com.healthclinics.repository;

import com.healthclinics.entity.ChucNang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChucNangRepository extends JpaRepository<ChucNang, Long> {
    
    Optional<ChucNang> findByTenManHinhTuongUong(String tenManHinhTuongUong);
}
