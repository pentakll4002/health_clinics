package com.healthclinics.repository;

import com.healthclinics.entity.LoaiBenh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiBenhRepository extends JpaRepository<LoaiBenh, Long> {
    
    @Query("SELECT lb FROM LoaiBenh lb WHERE LOWER(lb.tenLoaiBenh) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<LoaiBenh> searchByKeyword(@Param("keyword") String keyword);
}
