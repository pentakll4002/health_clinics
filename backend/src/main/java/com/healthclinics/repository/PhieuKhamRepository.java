package com.healthclinics.repository;

import com.healthclinics.entity.PhieuKham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhieuKhamRepository extends JpaRepository<PhieuKham, Long> {
    
    List<PhieuKham> findByIsDeletedFalse();
    
    Page<PhieuKham> findByIsDeletedFalse(Pageable pageable);
    
    List<PhieuKham> findByIdTiepNhan(Long idTiepNhan);
    
    List<PhieuKham> findByIdBacSi(Long idBacSi);
    
    List<PhieuKham> findByTrangThai(String trangThai);
    
    @Query("SELECT pk FROM PhieuKham pk WHERE pk.isDeleted = false AND pk.createdAt BETWEEN :startDate AND :endDate")
    List<PhieuKham> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                     @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT pk FROM PhieuKham pk JOIN FETCH pk.toaThuocs WHERE pk.idPhieuKham = :id")
    Optional<PhieuKham> findByIdWithToaThuoc(@Param("id") Long id);
    
    @Query("SELECT pk FROM PhieuKham pk JOIN FETCH pk.ctDichVuPhus WHERE pk.idPhieuKham = :id")
    Optional<PhieuKham> findByIdWithDichVuPhu(@Param("id") Long id);
    
    @Query("SELECT pk FROM PhieuKham pk " +
           "JOIN FETCH pk.tiepNhan tn " +
           "JOIN FETCH tn.benhNhan " +
           "WHERE pk.idPhieuKham = :id")
    Optional<PhieuKham> findByIdWithDetails(@Param("id") Long id);
}
