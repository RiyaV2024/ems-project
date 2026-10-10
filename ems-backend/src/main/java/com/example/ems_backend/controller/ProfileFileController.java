package com.example.ems_backend.controller;

import com.example.ems_backend.entity.ProfileFile;
import com.example.ems_backend.service.ProfileFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
public class ProfileFileController {

    @Autowired
    private ProfileFileService profileFileService;

    @PostMapping("/upload")
    public ResponseEntity<ProfileFile> upload(
            @RequestParam Long employeeId,
            @RequestParam("file") MultipartFile file) {
        ProfileFile saved = profileFileService.upload(employeeId, file);
        return ResponseEntity.status(201).body(saved);
    }
}