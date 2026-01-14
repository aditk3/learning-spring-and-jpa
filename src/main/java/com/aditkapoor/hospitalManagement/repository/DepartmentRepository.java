package com.aditkapoor.hospitalManagement.repository;

import com.aditkapoor.hospitalManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}