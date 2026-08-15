package com.yogendrayadav.codingshuttle.SpringBootWeb.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;
    private String title ;
    @JsonProperty("isActive")
    private Boolean isActive ;
    private LocalDate createdAt ;
    private Integer empId ;
    private String deptPassCode ;
}
