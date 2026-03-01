package com.healthclinics.repository;

import com.healthclinics.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.nhanVien nv LEFT JOIN FETCH nv.nhomNguoiDung WHERE u.email = :email")
    Optional<User> findByEmailWithNhanVien(@Param("email") String email);
    
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.benhNhan WHERE u.email = :email")
    Optional<User> findByEmailWithBenhNhan(@Param("email") String email);
    
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.benhNhan LEFT JOIN FETCH u.nhanVien nv LEFT JOIN FETCH nv.nhomNguoiDung WHERE u.email = :email")
    Optional<User> findByEmailWithAllRelations(@Param("email") String email);
}
