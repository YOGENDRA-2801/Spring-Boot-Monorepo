package com.yogendrayadav.codingshuttle.SpringBootWeb.Controller;

import com.yogendrayadav.codingshuttle.SpringBootWeb.DTO.EmployeeDTO;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Entity.EmployeeEntity;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("yogendrayadav")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "employee/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "id") Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null) ;
    }

    @GetMapping(path = "employee")
    public List<EmployeeEntity> getEmployeeInformation() {
        return employeeRepository.findAll() ;
    }

    @PostMapping(path = "employee")
    public EmployeeEntity postNewEmployee(@RequestBody EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity) ;
    }
}
