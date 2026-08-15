package com.yogendrayadav.codingshuttle.SpringBootWeb.Controller;

import com.yogendrayadav.codingshuttle.SpringBootWeb.DTO.DepartmentDTO;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService ;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "department")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.getAllDepartment()) ;
    }

    @PostMapping(path = "department")
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentService.createDepartment(departmentDTO));
    }

    @PutMapping(path = "department/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentDTO departmentDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.updateDepartment(id, departmentDTO)) ;
    }

    @PatchMapping(path = "department/{id}")
    public ResponseEntity<DepartmentDTO> partialUpdateDepartment(@PathVariable Long id, Map<String, Object> departmentDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.partialUpdateDepartment(id, departmentDTO)) ;
    }

    @DeleteMapping(path = "department/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.deleteDepartmentById(id));
    }

    @GetMapping(path = "department/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.getDepartmentById(id)) ;
    }


}
