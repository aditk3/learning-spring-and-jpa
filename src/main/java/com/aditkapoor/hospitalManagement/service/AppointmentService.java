package com.aditkapoor.hospitalManagement.service;

import com.aditkapoor.hospitalManagement.entity.Appointment;
import com.aditkapoor.hospitalManagement.entity.Doctor;
import com.aditkapoor.hospitalManagement.entity.Patient;
import com.aditkapoor.hospitalManagement.repository.AppointmentRepository;
import com.aditkapoor.hospitalManagement.repository.DoctorRepository;
import com.aditkapoor.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                "Doctor with ID " + doctorId + " not found!"));
        Patient patient = patientRepository.findById(patientId)
                                           .orElseThrow(() -> new EntityNotFoundException(
                                                   "Patient with ID " + patientId + " not found!"));

        if (appointment.getId() != null)
            throw new IllegalArgumentException("Appointment should not have an ID");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);  // To maintain bidirectional consistency
        doctor.getAppointments().add(appointment);  // To maintain bidirectional consistency

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reassignAppointmentToNewDoctor(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                                                       .orElseThrow(() -> new EntityNotFoundException(
                                                               "Appointment with ID " + appointmentId + " not found!"));
        Doctor doctor = doctorRepository.findById(doctorId)
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                "Doctor with ID " + doctorId + " not found!"));

        appointment.setDoctor(doctor);  // This will automatically call the update as it is DIRTY

        doctor.getAppointments().add(appointment);  // To maintain bidirectional consistency

        return appointment;
    }
}
