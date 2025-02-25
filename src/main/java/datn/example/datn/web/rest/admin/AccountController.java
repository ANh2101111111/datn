package datn.example.datn.web.rest.admin;

import datn.example.datn.config.security.JwtService;
import datn.example.datn.dto.request.LoginRequest;
import datn.example.datn.dto.request.RegisterRequest;
import datn.example.datn.dto.response.AuthResponse;
import datn.example.datn.entity.User;
import datn.example.datn.service.AuthService;
import datn.example.datn.service.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value ="/admin")
@RestController("adminAccountController")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class AccountController {
    @Autowired
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> adminregister(@Validated @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<String> adminlogin(@Validated @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
