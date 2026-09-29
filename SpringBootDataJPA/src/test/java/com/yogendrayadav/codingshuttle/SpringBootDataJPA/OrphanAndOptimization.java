package com.yogendrayadav.codingshuttle.SpringBootDataJPA;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.InsuranceEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.PatientEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories.PatientRepository;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Service.AppointmentService;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class OrphanAndOptimization {

    @Autowired
    private PatientService patientService ;

    @Autowired
    private PatientRepository patientRepository ;

    @Test
    public void testUpdateInsuranceProcess() {
        InsuranceEntity insuranceEntity = InsuranceEntity.builder()
                .policyNumber("POL-199234")
                .provider("Max Bupa")
                .createdAt(LocalDateTime.now())
                .validUntil(LocalDate.of(2099, 3, 22))
                .build();
        patientService.updateInsuranceOfPatient(1L, insuranceEntity);
    }

    @Test
    public void testRemoveInsuranceProcess() {
        patientService.removeInsuranceOfPatient(5l);
    }

    @Test
    public void testJoinOperation() {

//        Patient with insurance and appointment
        List<PatientEntity> patientEntityList1 = patientRepository.getAllAppointmentsOfPatientWithInsuranceDetail() ;
        for (PatientEntity patientEntity : patientEntityList1) {
            System.out.println(patientEntity);
        }

//        Patient with appointment
        List<PatientEntity> patientEntityList2 = patientRepository.getAllPatientsWithAppointments() ;
        for (PatientEntity patientEntity : patientEntityList2) {
            System.out.println(patientEntity);
        }
    }
}
