package com.example.ems_backend.controller;
import com.example.ems_backend.dto.LoginRequest;
import com.example.ems_backend.dto.RegisterRequest;
import com.example.ems_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest req) {
        authService.register(req);
        return ResponseEntity.status(201).body("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest req) {
        String token = authService.login(req);
        return ResponseEntity.ok(token);
    }
}