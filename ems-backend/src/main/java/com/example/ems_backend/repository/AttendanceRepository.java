package com.example.ems_backend.repository;
import com.example.ems_backend.entity.Attendance;
import com.example.ems_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date);
    List<Attendance> findByEmployeeAndDateBetween(Employee employee, LocalDate startDate, LocalDate endDate);
}