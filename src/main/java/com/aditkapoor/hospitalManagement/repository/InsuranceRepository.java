package com.aditkapoor.hospitalManagement.repository;

import com.aditkapoor.hospitalManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}