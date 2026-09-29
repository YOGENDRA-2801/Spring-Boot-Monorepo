package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.CBloodGroupAndCount;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.INameAndDateOfBirthProjection;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.PatientEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>
{
    List<INameAndDateOfBirthProjection> findBy() ;

    @Query("SELECT new com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.CBloodGroupAndCount(p.bloodGroup, COUNT(p)) FROM PatientEntity p GROUP BY p.bloodGroup")
    List<CBloodGroupAndCount> aggregateBloodGroupCount() ;

    @Modifying
    @Transactional
    @Query("UPDATE PatientEntity p SET p.name=:name , p.bloodGroup=:bloodGroup WHERE p.id=:id")
    Integer updateGroupAndName(@Param("name") String name, @Param("bloodGroup") BloodGroupType bloodGroupType, @Param("id") Long id) ;

    @Transactional
    @Query("SELECT DISTINCT p FROM PatientEntity p " +
            "LEFT JOIN FETCH p.insuranceEntity i " +
            "JOIN FETCH p.appointmentEntity a")
    List<PatientEntity> getAllAppointmentsOfPatientWithInsuranceDetail() ;

    @Query("SELECT p FROM PatientEntity p LEFT JOIN FETCH p.appointmentEntity")
    List<PatientEntity> getAllPatientsWithAppointments() ;

}
