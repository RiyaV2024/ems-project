package com.example.ems_backend.controller;
import com.example.ems_backend.entity.LeaveRequest;
import com.example.ems_backend.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
    @Autowired
    private LeaveRequestService leaveService;
    @PostMapping("/apply")
    public ResponseEntity<LeaveRequest> apply(
            @RequestParam Long employeeId,
            @RequestBody LeaveRequest req) {
        LeaveRequest leave = leaveService.apply(employeeId, req);
        return ResponseEntity.status(201).body(leave);
    }
    @GetMapping("/pending")
    public ResponseEntity<List<LeaveRequest>> getPending() {
        List<LeaveRequest> pending = leaveService.getPending();
        return ResponseEntity.ok(pending);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approve(@PathVariable Long id) {
        LeaveRequest leave = leaveService.approve(id);
        return ResponseEntity.ok(leave);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> reject(@PathVariable Long id) {
        LeaveRequest leave = leaveService.reject(id);
        return ResponseEntity.ok(leave);
    }
}