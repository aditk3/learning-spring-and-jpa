package com.aditkapoor.hospitalManagement;

import com.aditkapoor.hospitalManagement.entity.Appointment;
import com.aditkapoor.hospitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTests {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testCreateAppointment() {
        Appointment appointment = Appointment.builder()
                                             .appointmentTime(LocalDateTime.of(2026, 2, 1, 12, 0))
                                             .reason("Cancer")
                                             .build();

        var newApt = appointmentService.createNewAppointment(appointment, 1L, 2L);
        System.out.println(newApt);

        var updatedApt = appointmentService.reassignAppointmentToNewDoctor(newApt.getId(), 3L);
        System.out.println(updatedApt);
    }
}
