package com.example.ems_backend.repository;
import com.example.ems_backend.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStatus(String status);
}