package com.example.ems_backend.controller;
import com.example.ems_backend.dto.DepartmentRequest;
import com.example.ems_backend.entity.Department;
import com.example.ems_backend.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")

    @PostMapping
    public ResponseEntity<Department> create(@Valid @RequestBody DepartmentRequest req) {
        Department dept = departmentService.create(req);
        return ResponseEntity.status(201).body(dept);
    }
    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        List<Department> departments = departmentService.getAll();
        return ResponseEntity.ok(departments);
    }
}