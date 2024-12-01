package com.example.Department.controller;

import com.example.Department.dto.DepartmentDto;
import com.example.Department.services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDto>> getDepartmentList(){
        return ResponseEntity.ok(departmentService.getDepartmentList());
    }
}
