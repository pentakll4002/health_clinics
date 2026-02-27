package com.healthclinics.repository;

import com.healthclinics.entity.DichVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu, Long> {
    
    List<DichVu> findByIsDeletedFalse();
    
    Page<DichVu> findByIsDeletedFalse(Pageable pageable);
    
    Optional<DichVu> findByIdDichVuAndIsDeletedFalse(Long id);
    
    @Query("SELECT dv FROM DichVu dv WHERE dv.isDeleted = false AND LOWER(dv.tenDichVu) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<DichVu> searchByKeyword(@Param("keyword") String keyword);
}
