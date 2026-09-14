package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient")
@Data
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private String gender ;
    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup ;
    private LocalDate dateOfBirth ;
    @CreationTimestamp
    private LocalDateTime createdAt ;
}