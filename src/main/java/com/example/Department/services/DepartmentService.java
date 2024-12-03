package com.example.Department.services;

import com.example.Department.advice.ApiResponse;
import com.example.Department.dto.DepartmentDto;
import com.example.Department.entity.Department;
import com.example.Department.exception.ResourceNotFoundException;
import com.example.Department.repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.util.ReflectionUtils;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDto> getDepartmentList() {
        List<Department> dep = departmentRepository.findAll();
        if(dep.isEmpty()) throw new ResourceNotFoundException("No data in Department ");
        return dep.stream()
                .map((department) -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }


    public DepartmentDto getDepartmentById(Long id) {
        if(!departmentRepository.existsById(id)) throw new ResourceNotFoundException("No such Department with Id : "+id);
        return modelMapper.map(departmentRepository.findById(id), DepartmentDto.class);
    }

    public DepartmentDto createDepartment(DepartmentDto newDepartmentDto) {
        newDepartmentDto.setCreatedAt(LocalDateTime.now());
        Department addedDepartment  = departmentRepository.save(modelMapper.map(newDepartmentDto, Department.class));
        return modelMapper.map(addedDepartment,DepartmentDto.class);
    }

    public DepartmentDto updateDepartment(@Valid DepartmentDto newDepartment, Long id) {
        if (departmentRepository.existsById(id)){
           Department updateDepartment =  modelMapper.map(newDepartment, Department.class);
           updateDepartment.setId(id);
           return modelMapper.map(departmentRepository.save(updateDepartment), DepartmentDto.class);
        }else{
            throw new ResourceNotFoundException("No such Department with this Id : "+id);
        }
    }

    public DepartmentDto updatePartialDepartment(Map<String, Object> departmentdto, Long id) {
        if(!departmentRepository.existsById(id)) throw new ResourceNotFoundException("No such Department with Id : "+id);
        Department existDepartment = departmentRepository.findById(id).get();
        departmentdto.forEach((field, value) -> {
            Field fieldToUpdate = ReflectionUtils.findRequiredField(Department.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate,existDepartment,value);
        });

        return modelMapper.map(departmentRepository.save(existDepartment), DepartmentDto.class);
    }

    public ApiResponse<String> deleteDepartment(Long id){
        if(!departmentRepository.existsById(id)) throw new ResourceNotFoundException("No such Department with Id :"+id);
        departmentRepository.deleteById(id);
        return new ApiResponse<>("Department "+id+" has removed from the record");
    }
}
