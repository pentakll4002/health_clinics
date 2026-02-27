package com.healthclinics.repository;

import com.healthclinics.entity.PendingRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PendingRegisterRepository extends JpaRepository<PendingRegister, Long> {
    
    Optional<PendingRegister> findByEmail(String email);
    
    void deleteByEmail(String email);
}
