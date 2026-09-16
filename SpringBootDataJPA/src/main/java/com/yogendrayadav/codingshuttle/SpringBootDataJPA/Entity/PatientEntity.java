package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;

    private String gender ;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup ;

    private LocalDate dateOfBirth ;

    private String email ;

    @CreationTimestamp
    private LocalDateTime createdAt ;

    @OneToOne
    @JoinColumn(name = "insurance")
    private InsuranceEntity insuranceEntity ;

    @OneToMany(mappedBy = "patientEntity")
    private Set<AppointmentEntity> appointmentEntity = new HashSet<>();

}