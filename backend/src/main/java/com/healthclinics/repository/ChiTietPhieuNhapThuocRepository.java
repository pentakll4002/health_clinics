package com.healthclinics.repository;

import com.healthclinics.entity.ChiTietPhieuNhapThuoc;
import com.healthclinics.entity.ChiTietPhieuNhapThuocId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietPhieuNhapThuocRepository extends JpaRepository<ChiTietPhieuNhapThuoc, ChiTietPhieuNhapThuocId> {
    
    List<ChiTietPhieuNhapThuoc> findByIdPhieuNhapThuoc(Long idPhieuNhapThuoc);
}
