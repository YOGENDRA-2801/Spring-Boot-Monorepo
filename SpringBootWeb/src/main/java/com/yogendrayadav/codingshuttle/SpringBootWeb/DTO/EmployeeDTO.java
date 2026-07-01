package com.yogendrayadav.codingshuttle.SpringBootWeb.DTO;

import java.time.LocalDate;

public class EmployeeDTO {
    private Long id ;
    private String name ;
    private int age ;
    private LocalDate joiningDate ;
    private Boolean isEmployee ;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name, int age, LocalDate joiningDate, Boolean isEmployee) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.joiningDate = joiningDate;
        this.isEmployee = isEmployee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }
}
