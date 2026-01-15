package com.aditkapoor.hospitalManagement.service;

import com.aditkapoor.hospitalManagement.dto.AppointmentResponseDto;
import com.aditkapoor.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.aditkapoor.hospitalManagement.entity.Appointment;
import com.aditkapoor.hospitalManagement.entity.Doctor;
import com.aditkapoor.hospitalManagement.entity.Patient;
import com.aditkapoor.hospitalManagement.repository.AppointmentRepository;
import com.aditkapoor.hospitalManagement.repository.DoctorRepository;
import com.aditkapoor.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto) {
        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Doctor doctor = doctorRepository.findById(doctorId)
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                "Doctor with ID " + doctorId + " not found!"));
        Patient patient = patientRepository.findById(patientId)
                                           .orElseThrow(() -> new EntityNotFoundException(
                                                   "Patient with ID " + patientId + " not found!"));

        Appointment appointment = Appointment.builder()
                                             .reason(createAppointmentRequestDto.getReason())
                                             .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
                                             .build();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);  // To maintain bidirectional consistency
//        doctor.getAppointments().add(appointment);  // To maintain bidirectional consistency

        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDto.class);
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

    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                "Doctor with ID " + doctorId + " not found!"));

        return doctor.getAppointments()
                     .stream()
                     .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                     .collect(Collectors.toList());
    }
}
