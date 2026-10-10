package com.example.ems_backend.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "departments")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
}