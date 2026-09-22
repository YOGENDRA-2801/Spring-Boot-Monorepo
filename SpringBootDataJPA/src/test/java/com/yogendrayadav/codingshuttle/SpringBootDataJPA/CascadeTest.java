package com.yogendrayadav.codingshuttle.SpringBootDataJPA;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.AppointmentEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.InsuranceEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Service.AppointmentService;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class CascadeTest {

    @Autowired
    private PatientService patientService ;

    @Autowired
    private AppointmentService appointmentService ;

    @Test
    public void testAddOfInsuranceToPatient() {
        InsuranceEntity insuranceEntity = InsuranceEntity.builder()
//                .id(1L)
                .provider("Star Health")
                .validUntil(LocalDate.of(2030, 12, 31))
                .policyNumber("7506562343")
                .createdAt(LocalDateTime.now())
                .build() ;
        patientService.assignInsuranceToPatient(2L, insuranceEntity);
    }

    @Test
    public void deletePatientWithCascade() {
        patientService.deletePatient(2L);
    }

    @Test
    public void bookAppointment() {
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .reason("Heart Attack")
                .Status("Booked")
                .appointmentTime(LocalDateTime.now())
                .build();
        appointmentService.addAppointment(appointmentEntity, 1L, 1L) ;
    }

}
