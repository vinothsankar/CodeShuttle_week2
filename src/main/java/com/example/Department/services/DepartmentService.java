package com.example.Department.services;

import com.example.Department.dto.DepartmentDto;
import com.example.Department.entity.Department;
import com.example.Department.exception.ResourceNotFoundException;
import com.example.Department.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
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
}
