package com.example.ems_backend.service;
import com.example.ems_backend.dto.DepartmentRequest;
import com.example.ems_backend.entity.Department;
import com.example.ems_backend.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepo;
    public Department create(DepartmentRequest req) {
        Department dept = new Department();
        dept.setName(req.getName());
        return departmentRepo.save(dept);
    }
    public List<Department> getAll() {
        return departmentRepo.findAll();
    }
}