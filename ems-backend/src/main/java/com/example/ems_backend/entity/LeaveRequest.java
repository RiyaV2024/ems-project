package com.example.ems_backend.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Entity
@Table(name ="leave_requests")
@Data
public class LeaveRequest {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@ManyToOne
@JoinColumn(name = "employee_id")
private Employee employee;
private LocalDate fromDate;
private LocalDate toDate;
private String reason;
private String status; // PENDING / APPROVED / REJECTED
}
