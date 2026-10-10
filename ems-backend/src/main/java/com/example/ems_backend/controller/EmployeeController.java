package com.example.ems_backend.controller;
import com.example.ems_backend.dto.EmployeeRequest;
import com.example.ems_backend.entity.Employee;
import com.example.ems_backend.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeRequest req) {
        Employee emp = employeeService.create(req);
        return ResponseEntity.status(201).body(emp);
    }
    @GetMapping
    public ResponseEntity<Page<Employee>> getAll(
            @RequestParam(defaultValue = "") String keyword,
            Pageable pageable) {
        Page<Employee> result = employeeService.search(keyword, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        Employee emp = employeeService.getById(id);
        return ResponseEntity.ok(emp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest req) {
        Employee emp = employeeService.update(id, req);
        return ResponseEntity.ok(emp);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Employee> updateStatus(@PathVariable Long id, @RequestBody String status) {
        Employee emp = employeeService.updateStatus(id, status);
        return ResponseEntity.ok(emp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}