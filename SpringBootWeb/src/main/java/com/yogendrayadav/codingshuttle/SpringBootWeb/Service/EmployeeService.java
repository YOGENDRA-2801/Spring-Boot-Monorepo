package com.yogendrayadav.codingshuttle.SpringBootWeb.Service;

import com.yogendrayadav.codingshuttle.SpringBootWeb.DTO.EmployeeDTO;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Entity.EmployeeEntity;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Repository.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository ;
    private final ModelMapper modelMapper ;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> findById(Long employeeId) {
//        return modelMapper.map(employeeRepository.findById(employeeId).orElse(null), EmployeeDTO.class) ; // modelMapper cannot handle null
        Optional<EmployeeEntity> employeeEntity1 = employeeRepository.findById(employeeId) ;  // Ager yah null nikla to map chalega hi nahi
        return employeeEntity1.map( employeeEntity2 -> modelMapper.map(employeeEntity2, EmployeeDTO.class) ) ;
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
        boolean exist = employeeRepository.existsById(employeeId) ;
        if (exist) {
            EmployeeEntity existingEntity = employeeRepository.findById(employeeId).get() ; // If a value is present, returns the value, otherwise throws NoSuchElementException.
            modelMapper.map(employeeDTO, existingEntity) ; // Existing entity ko update karta hai yah
            existingEntity.setId(employeeId);
            return modelMapper.map(employeeRepository.save(existingEntity), EmployeeDTO.class) ;
        }
        return null;
    }

    public EmployeeDTO patchEmployeeById(Long employeeId, Map<String, Object> patchDetail) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null) ;
        if (employeeEntity != null) {
            patchDetail.forEach( (key, value) -> {
                Field field = ReflectionUtils.findField(EmployeeEntity.class, key) ;
                field.setAccessible(true);
                ReflectionUtils.setField(field, employeeEntity, value);
            } );
            return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class) ;
        }
        return null;
    }

    public Boolean deleteEmployeeById(Long employeeId) {
        boolean exist = employeeRepository.existsById(employeeId) ;
        if (exist) {
            employeeRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }
}