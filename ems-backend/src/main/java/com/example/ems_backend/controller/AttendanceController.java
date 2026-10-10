package com.example.ems_backend.controller;
import com.example.ems_backend.entity.Attendance;
import com.example.ems_backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @PostMapping("/check-in")
    public ResponseEntity<Attendance> checkIn(@RequestParam Long employeeId) {
        Attendance attendance = attendanceService.checkIn(employeeId);
        return ResponseEntity.status(201).body(attendance);
    }
    @PostMapping("/check-out")
    public ResponseEntity<Attendance> checkOut(@RequestParam Long employeeId) {
        Attendance attendance = attendanceService.checkOut(employeeId);
        return ResponseEntity.ok(attendance);
    }
    @GetMapping("/report")
    public ResponseEntity<List<Attendance>> getReport(
            @RequestParam Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Attendance> report = attendanceService.getReport(employeeId, startDate, endDate);
        return ResponseEntity.ok(report);
    }
}