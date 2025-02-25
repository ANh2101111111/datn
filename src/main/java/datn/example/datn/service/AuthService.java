package datn.example.datn.service;

import datn.example.datn.config.security.JwtService;
import datn.example.datn.dto.request.*;
import datn.example.datn.dto.response.AuthResponse;
import datn.example.datn.entity.User;
import datn.example.datn.repository.UserDetailsImpl;
import datn.example.datn.repository.UserRepository;
import datn.example.datn.utils.Constants;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setCreatedAt(LocalDateTime.now());  // Sử dụng LocalDateTime
        user.setUpdatedAt(LocalDateTime.now());  // Sử dụng LocalDateTime

        userRepository.save(user);

        // Chuyển đổi LocalDateTime thành Date khi trả về
        Date createdAt = Date.from(user.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
        Date updatedAt = Date.from(user.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant());
        String token = jwtService.generateToken(new UserDetailsImpl(user));

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        return response;
    }


    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Generate token but do not return it
        UserDetails userDetails = new UserDetailsImpl(user);
        String token = jwtService.generateToken(userDetails);
        return "Login successful";
    }


}
