package com.healthclinics.repository;

import com.healthclinics.entity.BaoCaoSuDungThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaoCaoSuDungThuocRepository extends JpaRepository<BaoCaoSuDungThuoc, Long> {
    
    List<BaoCaoSuDungThuoc> findByThangAndNam(Integer thang, Integer nam);
    
    Optional<BaoCaoSuDungThuoc> findByThangAndNamAndIdThuoc(Integer thang, Integer nam, Long idThuoc);
    
    void deleteByThangAndNam(Integer thang, Integer nam);
}
