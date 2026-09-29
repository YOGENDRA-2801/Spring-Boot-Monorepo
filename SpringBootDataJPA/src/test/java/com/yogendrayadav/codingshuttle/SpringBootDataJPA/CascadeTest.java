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
    public void testCreateInsuranceForPatient() {
        InsuranceEntity insuranceEntity = InsuranceEntity.builder()
                .provider("Star Health")
                .validUntil(LocalDate.of(2030, 12, 31))
                .policyNumber("7506562343")
                .createdAt(LocalDateTime.now())
                .build() ;
        patientService.createInsuranceForPatient(2L, insuranceEntity);
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

    @Test
    public void testAddInsuranceToPatient(){
        patientService.assignInsuranceToPatient(9l, 4l);
        patientService.assignInsuranceToPatient(8l, 8l);
        patientService.assignInsuranceToPatient(7l, 9l);
        patientService.assignInsuranceToPatient(6l, 6l);
        patientService.assignInsuranceToPatient(5l, 7l);
        patientService.assignInsuranceToPatient(4l, 5l);
    }

}
