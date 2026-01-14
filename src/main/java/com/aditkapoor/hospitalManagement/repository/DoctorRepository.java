package com.aditkapoor.hospitalManagement.repository;

import com.aditkapoor.hospitalManagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}