package com.healthclinics.repository;

import com.healthclinics.entity.PhanQuyen;
import com.healthclinics.entity.PhanQuyenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhanQuyenRepository extends JpaRepository<PhanQuyen, PhanQuyenId> {
    
    List<PhanQuyen> findByIdNhom(Long idNhom);
    
    List<PhanQuyen> findByIdChucNang(Long idChucNang);
    
    @Query("SELECT pq FROM PhanQuyen pq JOIN FETCH pq.chucNang WHERE pq.idNhom = :idNhom")
    List<PhanQuyen> findByIdNhomWithChucNang(@Param("idNhom") Long idNhom);
    
    void deleteByIdNhomAndIdChucNang(Long idNhom, Long idChucNang);
    
    @Query("SELECT cn.tenManHinhTuongUong FROM PhanQuyen pq " +
           "JOIN pq.chucNang cn " +
           "JOIN pq.nhomNguoiDung nnd " +
           "JOIN nnd.nhanViens nv " +
           "JOIN nv.user u " +
           "WHERE u.email = :email")
    List<String> findPermissionsByUserEmail(@Param("email") String email);
}
