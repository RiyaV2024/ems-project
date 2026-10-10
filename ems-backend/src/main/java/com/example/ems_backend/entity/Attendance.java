package com.example.ems_backend.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name="attendance")
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private LocalDate date;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}

