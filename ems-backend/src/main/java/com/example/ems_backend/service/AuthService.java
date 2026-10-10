package com.example.ems_backend.service;
import com.example.ems_backend.dto.LoginRequest;
import com.example.ems_backend.dto.RegisterRequest;
import com.example.ems_backend.entity.Role;
import com.example.ems_backend.entity.User;
import com.example.ems_backend.repository.UserRepository;
import com.example.ems_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(Role.EMPLOYEE);
        userRepository.save(user);
    }
    public String login (LoginRequest req){
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());    }
}