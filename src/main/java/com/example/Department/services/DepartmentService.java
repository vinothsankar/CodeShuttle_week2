package com.example.Department.services;

import com.example.Department.dto.DepartmentDto;
import com.example.Department.entity.Department;
import com.example.Department.exception.ResourceNotFoundException;
import com.example.Department.repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
        if(departmentRepository.existsById(id)) throw new ResourceNotFoundException("No such Departmen with Id : "+id);
        return modelMapper.map(departmentRepository.findById(id), DepartmentDto.class);
    }

    public DepartmentDto createDepartment(DepartmentDto newDepartmentDto) {
        newDepartmentDto.setCreatedAt(LocalDate.now());
        Department addedDepartment  = departmentRepository.save(modelMapper.map(newDepartmentDto, Department.class));
        return modelMapper.map(addedDepartment,DepartmentDto.class);
    }
}
