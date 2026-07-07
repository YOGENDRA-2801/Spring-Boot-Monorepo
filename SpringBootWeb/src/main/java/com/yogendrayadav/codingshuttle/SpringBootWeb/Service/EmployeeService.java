package com.yogendrayadav.codingshuttle.SpringBootWeb.Service;

import com.yogendrayadav.codingshuttle.SpringBootWeb.DTO.EmployeeDTO;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Entity.EmployeeEntity;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository ;
    private final ModelMapper modelMapper ;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO findById(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null) ;
        return modelMapper.map(employeeEntity, EmployeeDTO.class) ;
    }

    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll() ;
        return employeeEntityList
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList()) ;
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        EmployeeEntity toSaveEntity = modelMapper.map(employeeDTO, EmployeeEntity.class) ;
        EmployeeEntity toPerformSave = employeeRepository.save(toSaveEntity) ;
        return modelMapper.map(toPerformSave, EmployeeDTO.class) ;
    }
}