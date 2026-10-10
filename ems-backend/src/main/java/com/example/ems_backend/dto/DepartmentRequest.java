package com.example.ems_backend.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class DepartmentRequest {
    @NotBlank
    private String name;
}