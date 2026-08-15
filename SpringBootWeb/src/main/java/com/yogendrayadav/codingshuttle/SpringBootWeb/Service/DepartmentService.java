package com.yogendrayadav.codingshuttle.SpringBootWeb.Service;

import com.yogendrayadav.codingshuttle.SpringBootWeb.CustomException.DepartmentNotFoundException;
import com.yogendrayadav.codingshuttle.SpringBootWeb.DTO.DepartmentDTO;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Entity.DepartmentEntity;
import com.yogendrayadav.codingshuttle.SpringBootWeb.Repository.DepartmentRepository;
import org.jspecify.annotations.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class DepartmentService {

    private final DepartmentRepository departmentRepository ;
    private final ModelMapper modelMapper ;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentDTO getDepartmentById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department Not Found for delete operation for id " + id)) ;
        return modelMapper.map(departmentEntity, DepartmentDTO.class) ;
    }

    public List<DepartmentDTO> getAllDepartment() {
        List<DepartmentEntity> departmentEntity = departmentRepository.findAll() ;
        return departmentEntity
                .stream()
                .map( departmentEntityInstance -> modelMapper.map(departmentEntityInstance, DepartmentDTO.class) )
                .collect(Collectors.toList());
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = departmentRepository.save(modelMapper.map(departmentDTO, DepartmentEntity.class));
        return modelMapper.map(departmentEntity, DepartmentDTO.class) ;
    }

    public String deleteDepartmentById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id)
                .orElseThrow( () -> new DepartmentNotFoundException("Department Not Found for delete operation for id "+ id) )  ;
        departmentRepository.delete(departmentEntity);
        return "Department with id " + id + " has been successfully deleted" ;
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id)
                .orElseThrow( ()-> new DepartmentNotFoundException("Department Not Found for delete operation for id "+ id) ) ;
        modelMapper.map(departmentDTO, departmentEntity) ;
        departmentEntity.setId(id);
        return  modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class) ;
    }

    public DepartmentDTO partialUpdateDepartment(Long id, @NonNull Map<String, Object> departmentDTO) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department Not Found for delete operation for id " + id));
        departmentDTO.forEach( (key, value) -> {
            Field field = ReflectionUtils.findField(DepartmentEntity.class, key)  ;
            field.setAccessible(true);
            ReflectionUtils.setField(field, departmentEntity, value);
            }   );
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class) ;
    }
}














