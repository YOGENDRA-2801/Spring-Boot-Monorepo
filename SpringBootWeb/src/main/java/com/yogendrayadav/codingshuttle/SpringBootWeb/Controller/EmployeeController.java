package com.yogendrayadav.codingshuttle.SpringBootWeb.Controller;

import com.yogendrayadav.codingshuttle.SpringBootWeb.DTO.EmployeeDTO;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("yogendrayadav")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "employee/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "id") Long employeeId) {
        return employeeService.findById(employeeId) ;
    }

    @GetMapping(path = "employee")
    public List<EmployeeDTO> getEmployeeInformation() {
        return employeeService.findAll() ;
    }

    @PostMapping(path = "employee")
    public EmployeeDTO postNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.save(employeeDTO) ;
    }
}
