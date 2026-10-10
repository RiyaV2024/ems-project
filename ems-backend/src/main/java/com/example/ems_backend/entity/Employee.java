package com.example.ems_backend.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private Double salary;
    @Column(nullable = false)
    private String status; // ACTIVE / INACTIVe
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
