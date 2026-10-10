package com.example.ems_backend.service;
import com.example.ems_backend.dto.EmployeeRequest;
import com.example.ems_backend.entity.Department;
import com.example.ems_backend.entity.Employee;
import com.example.ems_backend.exception.DuplicateException;
import com.example.ems_backend.exception.ResourceNotFoundException;
import com.example.ems_backend.repository.DepartmentRepository;
import com.example.ems_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private DepartmentRepository departmentRepo;
    // CREATE
    public Employee create(EmployeeRequest req) {
        if (employeeRepo.existsByEmail(req.getEmail())) {
            throw new DuplicateException("Email already exists");
        }
        Department dept = departmentRepo.findById(req.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        Employee emp = new Employee();
        emp.setName(req.getName());
        emp.setEmail(req.getEmail());
        emp.setPhone(req.getPhone());
        emp.setSalary(req.getSalary());
        emp.setStatus("ACTIVE");
        emp.setDepartment(dept);

        return employeeRepo.save(emp);
    }

    // READ - by id
    public Employee getById(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    // READ - search + pagination
    public Page<Employee> search(String keyword, Pageable pageable) {
        return employeeRepo.findByNameContainingIgnoreCase(keyword, pageable);
    }

    // UPDATE - full update
    public Employee update(Long id, EmployeeRequest req) {
        Employee emp = getById(id);

        Department dept = departmentRepo.findById(req.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        emp.setName(req.getName());
        emp.setEmail(req.getEmail());
        emp.setPhone(req.getPhone());
        emp.setSalary(req.getSalary());
        emp.setDepartment(dept);

        return employeeRepo.save(emp);
    }

    // PATCH - status only
    public Employee updateStatus(Long id, String status) {
        Employee emp = getById(id);
        emp.setStatus(status);
        return employeeRepo.save(emp);
    }

    // DELETE
    public void delete(Long id) {
        Employee emp = getById(id);
        employeeRepo.delete(emp);
    }
}