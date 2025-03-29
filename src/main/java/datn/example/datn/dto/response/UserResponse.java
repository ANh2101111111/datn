package datn.example.datn.dto.response;

import java.util.Set;
import lombok.Data;
import java.util.Set;

@Data
public class UserResponse {
    private Long userId;
    private String username;
    private String email;
    private boolean isActive;
}
