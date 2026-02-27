package com.healthclinics.repository;

import com.healthclinics.entity.CachDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CachDungRepository extends JpaRepository<CachDung, Long> {
}
