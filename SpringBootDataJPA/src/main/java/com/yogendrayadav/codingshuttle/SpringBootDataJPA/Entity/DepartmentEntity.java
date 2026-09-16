package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;

    @CreationTimestamp
    private LocalDateTime createdAt ;

    @OneToOne
    @JoinColumn(name = "DeptDocHead", nullable = false)
    private DoctorEntity headDoctor ;

    @ManyToMany(mappedBy = "departmentEntities")
    private Set<DoctorEntity> doctorEntities = new HashSet<>() ;
}
