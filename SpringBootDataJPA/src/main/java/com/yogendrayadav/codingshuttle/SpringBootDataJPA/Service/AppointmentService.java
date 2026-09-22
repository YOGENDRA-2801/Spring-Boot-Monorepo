package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Service;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.AppointmentEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.DoctorEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.PatientEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories.AppointmentRepository;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories.DoctorRepository;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository ;
    private final DoctorRepository doctorRepository ;
    private final PatientRepository patientRepository ;

    @Transactional
    public void addAppointment(@NonNull AppointmentEntity appointmentEntity, Long patientId, Long doctorId) {
        DoctorEntity doctorEntity = doctorRepository
                .findById(doctorId)
                .orElseThrow( () -> new RuntimeException("Doctor Not Found") ) ;
        PatientEntity patientEntity = patientRepository
                .findById(patientId)
                .orElseThrow( () -> new RuntimeException("Doctor Not Found") ) ;
        appointmentEntity.setDoctorEntity(doctorEntity);
        appointmentEntity.setPatientEntity(patientEntity);
        AppointmentEntity appointmentDetail = appointmentRepository.save(appointmentEntity) ;
        System.out.println(
                appointmentDetail.getReason() + " , " + appointmentDetail.getStatus() + " , " + appointmentDetail.getAppointmentTime()
        );
        System.out.println(
                "Doctor : " + doctorEntity.getName() + " , Patient : " + patientEntity.getName()
        );
    }

}
