package com.healthclinics.repository;

import com.healthclinics.entity.DVT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVTRepository extends JpaRepository<DVT, Long> {
}
