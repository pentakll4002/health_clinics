package com.healthclinics.repository;

import com.healthclinics.entity.QuiDinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuiDinhRepository extends JpaRepository<QuiDinh, Long> {
    
    Optional<QuiDinh> findByTenQuyDinh(String tenQuyDinh);
}
