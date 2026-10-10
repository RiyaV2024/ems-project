package com.example.ems_backend.service;
import com.example.ems_backend.entity.Attendance;
import com.example.ems_backend.entity.Employee;
import com.example.ems_backend.exception.DuplicateException;
import com.example.ems_backend.exception.ResourceNotFoundException;
import com.example.ems_backend.repository.AttendanceRepository;
import com.example.ems_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepo;
    @Autowired
    private EmployeeRepository employeeRepo;
    // CHECK-IN
    public Attendance checkIn(Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        LocalDate today = LocalDate.now();
        Optional<Attendance> existing = attendanceRepo.findByEmployeeAndDate(employee, today);
        if (existing.isPresent()) {
            throw new DuplicateException(("Already checked in today"));
        }
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setDate(today);
        attendance.setCheckInTime(LocalDateTime.now());

        return attendanceRepo.save(attendance);
    }

    // CHECK-OUT
    public Attendance checkOut(Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepo.findByEmployeeAndDate(employee, today)
                .orElseThrow(() -> new ResourceNotFoundException("No check-in found for today"));
        if (attendance.getCheckOutTime() != null) {
            throw new DuplicateException("Already checked out today");
        }
        attendance.setCheckOutTime(LocalDateTime.now());
        return attendanceRepo.save(attendance);
    }
    // REPORT
    public List<Attendance> getReport(Long employeeId, LocalDate startDate, LocalDate endDate) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return attendanceRepo.findByEmployeeAndDateBetween(employee, startDate, endDate);
    }
}