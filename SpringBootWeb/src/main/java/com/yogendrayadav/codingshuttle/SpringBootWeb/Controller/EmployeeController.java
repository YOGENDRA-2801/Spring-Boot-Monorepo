package com.yogendrayadav.codingshuttle.SpringBootWeb.Controller;

import com.yogendrayadav.codingshuttle.SpringBootWeb.CustomException.ResourceNotFoundException;
import com.yogendrayadav.codingshuttle.SpringBootWeb.DTO.EmployeeDTO;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("yogendrayadav")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> localExceptionHandler(NoSuchElementException noSuchElementException) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(noSuchElementException.getMessage()) ;
//    }

    @GetMapping(path = "employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "id") Long employeeId) {
        return ResponseEntity.ok(employeeService.findById(employeeId)) ;
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
        return ResponseEntity.ok(updatedEmployeeDTO) ;
    }

    @PatchMapping(path = "employee/{id}")
    public ResponseEntity<EmployeeDTO> patchEmployeeById(@PathVariable(name = "id") Long employeeId, @RequestBody Map<String, Object> patchDetail) {
        EmployeeDTO patchedEmployeeDTO = employeeService.patchEmployeeById(employeeId, patchDetail) ;
        return ResponseEntity.ok(patchedEmployeeDTO) ;
    }

    @DeleteMapping(path = "employee/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployeeById(@PathVariable(name = "id") Long employeeId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(Map.of("message", employeeService.deleteEmployeeById(employeeId)));
    }
}