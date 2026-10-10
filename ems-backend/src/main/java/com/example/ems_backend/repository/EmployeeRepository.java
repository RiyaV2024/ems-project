package com.example.ems_backend.repository;
import com.example.ems_backend.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    Page<Employee> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}