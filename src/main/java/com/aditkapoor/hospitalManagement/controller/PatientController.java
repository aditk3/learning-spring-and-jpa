package com.aditkapoor.hospitalManagement.controller;

import com.aditkapoor.hospitalManagement.dto.AppointmentResponseDto;
import com.aditkapoor.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.aditkapoor.hospitalManagement.dto.PatientResponseDto;
import com.aditkapoor.hospitalManagement.service.AppointmentService;
import com.aditkapoor.hospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @GetMapping("/profile")
    public ResponseEntity<PatientResponseDto> getPatientProfile() {
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> post(@RequestBody CreateAppointmentRequestDto appointmentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(appointmentDto));
    }
}
