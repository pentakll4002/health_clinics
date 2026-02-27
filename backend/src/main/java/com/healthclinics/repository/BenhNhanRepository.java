package com.healthclinics.repository;

import com.healthclinics.entity.BenhNhan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan, Long> {
    
    List<BenhNhan> findByIsDeletedFalse();
    
    Page<BenhNhan> findByIsDeletedFalse(Pageable pageable);
    
    Optional<BenhNhan> findByIdBenhNhanAndIsDeletedFalse(Long id);
    
    @Query("SELECT bn FROM BenhNhan bn WHERE bn.isDeleted = false AND " +
           "(LOWER(bn.hoTenBN) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "bn.dienThoai LIKE CONCAT('%', :keyword, '%') OR " +
           "bn.cccd LIKE CONCAT('%', :keyword, '%'))")
    List<BenhNhan> searchByKeyword(@Param("keyword") String keyword);
    
    Optional<BenhNhan> findByUserId(Long userId);
    
    Boolean existsByCccd(String cccd);
    
    Boolean existsByDienThoai(String dienThoai);
}
