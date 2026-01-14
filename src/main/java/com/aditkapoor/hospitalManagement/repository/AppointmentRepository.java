package com.aditkapoor.hospitalManagement.repository;

import com.aditkapoor.hospitalManagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}