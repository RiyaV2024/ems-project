package com.example.ems_backend.service;
import com.example.ems_backend.entity.Employee;
import com.example.ems_backend.entity.LeaveRequest;
import com.example.ems_backend.exception.ResourceNotFoundException;
import com.example.ems_backend.repository.EmployeeRepository;
import com.example.ems_backend.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRepo;
    @Autowired
    private EmployeeRepository employeeRepo;
    // APPLY
    public LeaveRequest apply(Long employeeId, LeaveRequest req) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(employee);
        leave.setFromDate(req.getFromDate());
        leave.setToDate(req.getToDate());
        leave.setReason(req.getReason());
        leave.setStatus("PENDING");
        return leaveRepo.save(leave);
    }
    // PENDING LIST
    public List<LeaveRequest> getPending() {
        return leaveRepo.findByStatus("PENDING");
    }

    // APPROVE
    public LeaveRequest approve(Long id) {
        LeaveRequest leave = leaveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
        leave.setStatus("APPROVED");
        return leaveRepo.save(leave);
    }
    // REJECT
    public LeaveRequest reject(Long id) {
        LeaveRequest leave = leaveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
        leave.setStatus("REJECTED");
        return leaveRepo.save(leave);
    }
}