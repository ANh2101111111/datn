package datn.example.datn.service;

import datn.example.datn.config.security.JwtService;
import datn.example.datn.dto.request.*;
import datn.example.datn.dto.response.AuthResponse;
import datn.example.datn.entity.Role;
import datn.example.datn.entity.User;
import datn.example.datn.repository.RoleRepository;
import datn.example.datn.repository.UserDetailsImpl;
import datn.example.datn.repository.UserRepository;
import datn.example.datn.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager =authenticationManager;
        this.customUserDetailsService =customUserDetailsService;
    }

    public AuthResponse register(RegisterRequest request) {
        // Kiểm tra Role "USER" đã tồn tại chưa
        Role defaultRole = roleRepository.findByRoleName("USER")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRoleName("USER");
                    return roleRepository.save(newRole); // Lưu Role nếu chưa có
                });

        // Tạo User mới
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setStatus(true);
        user.setRole(defaultRole); // Gán Role đã lưu vào User

        userRepository.save(user); // Lưu User

        // Tạo JWT Token
        String token = jwtService.generateToken(new UserDetailsImpl(user));

        // Tạo response
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setRole(defaultRole.getRoleName());

        return response;
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        // Lấy user từ database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Chuyển đổi User thành UserDetails
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole() != null ? user.getRole().getRoleName() : "USER")
                .build();

        // Sinh token từ UserDetails
        return jwtService.generateToken(userDetails);
    }
}

