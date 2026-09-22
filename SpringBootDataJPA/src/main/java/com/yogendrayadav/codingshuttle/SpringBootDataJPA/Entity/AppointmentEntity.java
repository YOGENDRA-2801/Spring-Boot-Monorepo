package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private LocalDateTime appointmentTime ;

    @Column(length = 500)
    private String reason ;

    private String Status ;

    @ManyToOne
    @JoinColumn(name = "doctor", nullable = false)
    private DoctorEntity doctorEntity ;

    @ManyToOne
    @JoinColumn(name = "patient", nullable = false)
    private PatientEntity patientEntity ;
}
