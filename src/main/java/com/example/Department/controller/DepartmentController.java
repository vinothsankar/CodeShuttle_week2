package com.example.Department.controller;

import com.example.Department.advice.ApiResponse;
import com.example.Department.dto.DepartmentDto;
import com.example.Department.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/update/put/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody @Valid DepartmentDto newDepartment, @PathVariable Long id){
        return ResponseEntity.ok(departmentService.updateDepartment(newDepartment, id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<DepartmentDto> updateParticalDepartment(@RequestBody Map<String, Object> departmentdto, @PathVariable Long id){
        DepartmentDto udpatedDepartment = departmentService.updatePartialDepartment(departmentdto, id);
        return ResponseEntity.ok(udpatedDepartment);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }
}
