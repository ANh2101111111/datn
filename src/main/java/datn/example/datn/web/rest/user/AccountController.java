package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.LoginRequest;
import datn.example.datn.dto.request.RegisterRequest;
import datn.example.datn.dto.response.AuthResponse;
import datn.example.datn.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value ="/user")
@RestController("userAccountController")
@SecurityRequirement(name = "Bearer Authentication")
public class AccountController {
    @Autowired
    private final AuthService authService;

    public AccountController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Validated @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
