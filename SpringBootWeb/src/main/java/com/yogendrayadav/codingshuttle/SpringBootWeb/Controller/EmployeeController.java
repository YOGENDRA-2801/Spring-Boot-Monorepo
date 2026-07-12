package com.yogendrayadav.codingshuttle.SpringBootWeb.Controller;

import com.yogendrayadav.codingshuttle.SpringBootWeb.DTO.EmployeeDTO;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("yogendrayadav")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "id") Long employeeId) {
        Optional<EmployeeDTO> employeeDTO1 = employeeService.findById(employeeId) ;
        return employeeDTO1
                .map(employeeDTO2 -> ResponseEntity.ok(employeeDTO2))
                .orElse(ResponseEntity.notFound().build()) ;
    }

    @GetMapping(path = "employee")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeInformation() {
        return ResponseEntity.ok(employeeService.findAll()) ; // If not found then empty JSON will be shown hence no need for not found scenario
    }

    @PostMapping(path = "employee")
    public ResponseEntity<EmployeeDTO> postNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.hireEmployee(employeeDTO)) ;
    }

    @PutMapping(path = "employee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable(name = "id") Long employeeId, @RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployeeById(employeeId, employeeDTO) ;
        if(updatedEmployeeDTO != null) return ResponseEntity.ok(updatedEmployeeDTO) ;
        else return ResponseEntity.notFound().build() ;
    }

    @PatchMapping(path = "employee/{id}")
    public ResponseEntity<EmployeeDTO> patchEmployeeById(@PathVariable(name = "id") Long employeeId, @RequestBody Map<String, Object> patchDetail) {
        EmployeeDTO patchedEmployeeDTO = employeeService.patchEmployeeById(employeeId, patchDetail) ;
        if (patchedEmployeeDTO != null) return ResponseEntity.ok(patchedEmployeeDTO) ;
        else return ResponseEntity.notFound().build() ;
    }

    @DeleteMapping(path = "employee/{id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "id") Long employeeId) {
        Boolean employeeDeleted = employeeService.deleteEmployeeById(employeeId) ;
        if (employeeDeleted) return ResponseEntity.ok(true) ;
        else return ResponseEntity.notFound().build() ;
    }
}