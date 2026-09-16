package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false, length = 100)
    private String name ;

    @Column(length = 100)
    private String specialization ;

    @Column(nullable = false, length = 100, unique = true)
    private String email ;

    @Column(updatable = false)
    private LocalDateTime createdAt ;

    @OneToMany(mappedBy = "doctorEntity")
    private Set<AppointmentEntity> appointmentEntities = new HashSet<>() ;

    @ManyToMany
    @JoinTable(
            name = "doctor_department",
            joinColumns = @JoinColumn(name = "department")
    )
    private Set<DepartmentEntity> departmentEntities = new HashSet<>() ;
}
