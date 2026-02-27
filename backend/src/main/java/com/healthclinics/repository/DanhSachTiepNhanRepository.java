package com.healthclinics.repository;

import com.healthclinics.entity.DanhSachTiepNhan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DanhSachTiepNhanRepository extends JpaRepository<DanhSachTiepNhan, Long> {
    
    List<DanhSachTiepNhan> findByIsDeletedFalse();
    
    Page<DanhSachTiepNhan> findByIsDeletedFalse(Pageable pageable);
    
    List<DanhSachTiepNhan> findByIdBenhNhanAndIsDeletedFalse(Long idBenhNhan);
    
    @Query("SELECT dstn FROM DanhSachTiepNhan dstn WHERE dstn.isDeleted = false AND " +
           "DATE(dstn.ngayTN) = DATE(:date)")
    List<DanhSachTiepNhan> findByDate(@Param("date") LocalDateTime date);
    
    @Query("SELECT dstn FROM DanhSachTiepNhan dstn WHERE dstn.isDeleted = false AND " +
           "dstn.ngayTN BETWEEN :startDate AND :endDate")
    List<DanhSachTiepNhan> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                           @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT dstn FROM DanhSachTiepNhan dstn JOIN FETCH dstn.benhNhan WHERE dstn.idTiepNhan = :id")
    DanhSachTiepNhan findByIdWithBenhNhan(@Param("id") Long id);
}
