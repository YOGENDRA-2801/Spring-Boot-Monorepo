package com.yogendrayadav.codingshuttle.SpringBootWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWebApplication {

	static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
//		modelMapper.map(employeeDTO, existingEntity) → Existing object ko UPDATE karo
//		modelMapper.map(employeeDTO, EmployeeEntity.class) → Naya object banao
	}

}
