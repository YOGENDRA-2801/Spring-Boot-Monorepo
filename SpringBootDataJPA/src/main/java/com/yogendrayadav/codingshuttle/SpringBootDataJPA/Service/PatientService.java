package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Service;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.InsuranceEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.PatientEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository ;

    @Transactional
    public void assignInsuranceToPatient(Long patientId, InsuranceEntity newInsurance) {
        PatientEntity patientEntity = patientRepository.findById(patientId)
                .orElseThrow( () -> new RuntimeException("Patient Not Found") );
        patientEntity.setInsuranceEntity(newInsurance);
        patientRepository.save(patientEntity) ;
        System.out.println( "Patient Name - " +  patientEntity.getName() +
                " , Patient Insurance Provider - " + patientEntity.getInsuranceEntity().getProvider());
    }

    @Transactional
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

}
