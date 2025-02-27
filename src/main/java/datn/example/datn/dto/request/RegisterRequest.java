package datn.example.datn.dto.request;

import datn.example.datn.dto.response.RoleResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RegisterRequest {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private Boolean status;
    private List<RoleResponse> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
