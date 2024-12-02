package com.example.Department.controller;

import com.example.Department.dto.DepartmentDto;
import com.example.Department.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/get/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id){
            return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PostMapping("add/")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto newDepartmentDto){
        return newDepartmentDto == null ? ResponseEntity.unprocessableEntity().body(null) : new ResponseEntity<>(departmentService.createDepartment(newDepartmentDto), HttpStatus.CREATED);
    }
}
