package com.aditkapoor.hospitalManagement;

import com.aditkapoor.hospitalManagement.entity.Insurance;
import com.aditkapoor.hospitalManagement.entity.Patient;
import com.aditkapoor.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
                                       .policyNumber("HDFC1234")
                                       .provider("HDFC Bank")
                                       .validUntil(LocalDate.of(2030, 12, 12))
                                       .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);

        var updatedPatient = insuranceService.removeInsuranceFromPatient(patient.getId());
        System.out.println(updatedPatient);
    }
}
