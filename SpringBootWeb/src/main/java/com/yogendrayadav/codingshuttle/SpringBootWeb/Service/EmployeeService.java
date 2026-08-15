package com.yogendrayadav.codingshuttle.SpringBootWeb.Service;

import com.yogendrayadav.codingshuttle.SpringBootWeb.CustomException.ResourceNotFoundException;
import com.yogendrayadav.codingshuttle.SpringBootWeb.DTO.EmployeeDTO;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Entity.EmployeeEntity;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Repository.EmployeeRepository;
import org.jspecify.annotations.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
        return employeeRepository.findById(employeeId)
                .map( employeeEntity2 -> modelMapper.map(employeeEntity2, EmployeeDTO.class) ) // will run iff entity is found
                .orElseThrow(() -> new NoSuchElementException("Employee Not Found"))  ; // will run iff null is found
    }

    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll() ; // ager data nahi raha to empty list return hogi 0 records and if records are 0 stream shuru hi nahi hoga and null wala scenario hi nahi banega kabhi model mapper ke ander)
        return employeeEntityList
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList()) ;
    }

    public EmployeeDTO hireEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity toSaveEntity = modelMapper.map(employeeDTO, EmployeeEntity.class) ;
        EmployeeEntity toPerformSave = employeeRepository.save(toSaveEntity) ;
        return modelMapper.map(toPerformSave, EmployeeDTO.class) ;
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity existingEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id "+ employeeId +" to be updated is not found"));
        modelMapper.map(employeeDTO, existingEntity) ;
        existingEntity.setId(employeeId);
        return modelMapper.map(employeeRepository.save(existingEntity), EmployeeDTO.class) ;
    }

    public EmployeeDTO patchEmployeeById(Long employeeId, @NonNull Map<String, Object> patchDetail) {
        EmployeeEntity employeeEntity = employeeRepository
                .findById(employeeId)
                .orElseThrow( () -> new ResourceNotFoundException("Employee with id "+ employeeId + " is not found for patch operation")) ;
        patchDetail.forEach( (key, value) -> {
            Field field = ReflectionUtils.findField(EmployeeEntity.class, key) ;
            field.setAccessible(true);
            ReflectionUtils.setField(field, employeeEntity, value);
        } );
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class) ;
    }

    public void deleteEmployeeById(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id "+ employeeId +" to be deleted is not found"));
        employeeRepository.delete(employeeEntity);
    }
}