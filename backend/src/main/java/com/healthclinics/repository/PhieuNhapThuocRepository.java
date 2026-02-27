package com.healthclinics.repository;

import com.healthclinics.entity.PhieuNhapThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhieuNhapThuocRepository extends JpaRepository<PhieuNhapThuoc, Long> {
    
    List<PhieuNhapThuoc> findByNgayNhapBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<PhieuNhapThuoc> findByIdNhanVien(Long idNhanVien);
    
    @Query("SELECT pnt FROM PhieuNhapThuoc pnt JOIN FETCH pnt.chiTiets WHERE pnt.idPhieuNhapThuoc = :id")
    Optional<PhieuNhapThuoc> findByIdWithChiTiet(@Param("id") Long id);
}
