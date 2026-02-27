package com.healthclinics.repository;

import com.healthclinics.entity.BaoCaoDoanhThu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaoCaoDoanhThuRepository extends JpaRepository<BaoCaoDoanhThu, Long> {
    
    Optional<BaoCaoDoanhThu> findByThangAndNam(Integer thang, Integer nam);
}
