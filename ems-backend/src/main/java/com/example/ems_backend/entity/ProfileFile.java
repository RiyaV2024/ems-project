package com.example.ems_backend.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "profile_files")
@Data
public class ProfileFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private String fileName;
    private String filePath;
}