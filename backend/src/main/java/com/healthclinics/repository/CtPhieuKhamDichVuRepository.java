package com.healthclinics.repository;

import com.healthclinics.entity.CtPhieuKhamDichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CtPhieuKhamDichVuRepository extends JpaRepository<CtPhieuKhamDichVu, Long> {
    
    List<CtPhieuKhamDichVu> findByIdPhieuKhamAndIsDeletedFalse(Long idPhieuKham);
    
    void deleteByIdPhieuKhamAndIdDichVu(Long idPhieuKham, Long idDichVu);
}
