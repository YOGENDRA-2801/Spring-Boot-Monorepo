package com.yogendrayadav.codingshuttle.SpringBootDataJPA;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.CBloodGroupAndCount;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.INameAndDateOfBirthProjection;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.PatientEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.type.BloodGroupType;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientServiceTest
{
    @Autowired
    private PatientRepository patientRepository ;

    @Test
    public void toCheckWorking() {
        List<PatientEntity> patientEntityList = patientRepository.findAll() ;
        for(PatientEntity patientEntity : patientEntityList) {
            System.out.println(patientEntity);
        }
    }

    @Test
    public void interfaceProjection() {
        List<INameAndDateOfBirthProjection> nameAndDateOfBirthProjections = patientRepository.findBy() ;
        for (INameAndDateOfBirthProjection nameAndDob: nameAndDateOfBirthProjections) {
            System.out.print(nameAndDob.getDateOfBirth() + " , ");
            System.out.println(nameAndDob.getName());
        }
    }

    @Test
    public void concreteProjection() {
        List<CBloodGroupAndCount> bloodGroupAndCounts = patientRepository.aggregateBloodGroupCount();
        for (CBloodGroupAndCount bgc : bloodGroupAndCounts) {
            System.out.println(bgc);
        }
    }

    @Test
    public void modifyingQuery() {
        System.out.println(
                patientRepository.updateGroupAndName("Vishnu", BloodGroupType.AB_POSITIVE, 1L)
        );
    }
}
