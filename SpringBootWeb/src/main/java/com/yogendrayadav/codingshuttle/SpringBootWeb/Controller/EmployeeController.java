package com.yogendrayadav.codingshuttle.SpringBootWeb.Controller;

import com.yogendrayadav.codingshuttle.SpringBootWeb.DTO.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("yogendrayadav")
public class EmployeeController {

    @GetMapping("/welcome")
    public String greet() {
        return "Hello World" ;
    }

//    @GetMapping(path = "employee/{employeeId}")
//    public EmployeeDTO getEmployeeById(@PathVariable long employeeId) {
//        return new EmployeeDTO(employeeId, "Yogendra", 23, LocalDate.now(), true) ;
//    }

    @GetMapping(path = "employee/{naam}")
    public EmployeeDTO getEmployeeByName(@PathVariable(name = "naam") String employeename) {
        return new EmployeeDTO(1l, employeename, 23, LocalDate.now(), true) ;
    }

    @GetMapping(path = "employee")
    public String getEmployeeInformation(@RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "umar", required = false) Integer age) {
        return "The age of boss is " + age + " his name is " + name ;
    }

    @PostMapping(path = "employee")
    public String postSimpleMessage() {
        return "POST MAPPING IS USED" ;
    }

    @PutMapping(path = "employee")
    public String putSimpleMessage() {
        return "POST MAPPING IS USED" ;
    }

    @PostMapping(path = "hire")
    public EmployeeDTO postEmployeeDTO(@RequestBody EmployeeDTO employeeDTO){
        employeeDTO.setId(23l);
        return employeeDTO ;
    }
}
