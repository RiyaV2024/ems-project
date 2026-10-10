package com.example.ems_backend.service;

import com.example.ems_backend.entity.Employee;
import com.example.ems_backend.entity.ProfileFile;
import com.example.ems_backend.exception.ResourceNotFoundException;
import com.example.ems_backend.repository.EmployeeRepository;
import com.example.ems_backend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ProfileFileService {

    @Autowired
    private ProfileRepository profileRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProfileFile upload(Long employeeId, MultipartFile file) {

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String contentType = file.getContentType();
        if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
            throw new RuntimeException("Only JPG/PNG files are allowed");
        }

        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(uniqueFileName);
            file.transferTo(filePath);

            ProfileFile profileFile = profileRepo.findByEmployee(employee)
                    .orElse(new ProfileFile());

            profileFile.setEmployee(employee);
            profileFile.setFileName(file.getOriginalFilename());
            profileFile.setFilePath(filePath.toString());
            return profileRepo.save(profileFile);

        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }
}