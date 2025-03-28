package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.LoginRequest;
import datn.example.datn.dto.request.PasswordResetRequestDto;
import datn.example.datn.dto.request.RegisterRequest;
import datn.example.datn.dto.request.ResetPasswordDto;
import datn.example.datn.dto.response.AuthResponse;
import datn.example.datn.dto.response.PasswordResetResponseDto;
import datn.example.datn.service.AuthService;
import datn.example.datn.service.PasswordResetService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequestMapping(value ="/api/user")
@RestController("userAccountController")
@SecurityRequirement(name = "Bearer Authentication")
public class AccountController {
    @Autowired
    private final AuthService authService;
    @Autowired
    private PasswordResetService passwordResetService;

    public AccountController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Validated @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Map<String, Object> response = authService.login(request.get("username"), request.get("password"));
        return ResponseEntity.ok(response);
    }
    @PostMapping("/reset-password/request")
    public PasswordResetResponseDto requestPasswordReset(@RequestBody PasswordResetRequestDto requestDto) {
        passwordResetService.requestPasswordReset(requestDto.getEmail());
        PasswordResetResponseDto response = new PasswordResetResponseDto();
        response.setMessage("Password reset link has been sent to your email.");
        return response;
    }

    @PostMapping("/reset-password")
    public PasswordResetResponseDto resetPassword(@RequestBody ResetPasswordDto requestDto) {
        passwordResetService.resetPassword(requestDto.getToken(), requestDto.getNewPassword());
        PasswordResetResponseDto response = new PasswordResetResponseDto();
        response.setMessage("Your password has been reset successfully.");
        return response;
    }

}
