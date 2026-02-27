package com.healthclinics.repository;

import com.healthclinics.entity.ToaThuoc;
import com.healthclinics.entity.ToaThuocId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToaThuocRepository extends JpaRepository<ToaThuoc, ToaThuocId> {
    
    List<ToaThuoc> findByIdPhieuKham(Long idPhieuKham);
    
    void deleteByIdPhieuKhamAndIdThuoc(Long idPhieuKham, Long idThuoc);
}
