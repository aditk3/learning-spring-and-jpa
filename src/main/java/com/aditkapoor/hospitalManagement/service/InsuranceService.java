package com.aditkapoor.hospitalManagement.service;

import com.aditkapoor.hospitalManagement.entity.Insurance;
import com.aditkapoor.hospitalManagement.entity.Patient;
import com.aditkapoor.hospitalManagement.repository.InsuranceRepository;
import com.aditkapoor.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                                           .orElseThrow(() -> new EntityNotFoundException(
                                                   "Patient with ID " + patientId + " not found!"));
        patient.setInsurance(insurance);
        insurance.setPatient(patient);  // only put for bidirectional consistency maintenance. can remove

        return patient;
    }

    @Transactional
    public Patient removeInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                                           .orElseThrow(() -> new EntityNotFoundException(
                                                   "Patient with ID " + patientId + " not found!"));

        patient.setInsurance(null);
        return patient;
    }
}
