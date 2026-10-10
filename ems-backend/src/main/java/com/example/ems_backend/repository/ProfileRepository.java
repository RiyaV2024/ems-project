package com.example.ems_backend.repository;
import com.example.ems_backend.entity.Employee;
import com.example.ems_backend.entity.ProfileFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ProfileRepository extends JpaRepository<ProfileFile, Long> {
    Optional<ProfileFile> findByEmployee(Employee employee);
}