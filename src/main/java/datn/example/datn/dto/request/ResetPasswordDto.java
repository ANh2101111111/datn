package datn.example.datn.dto.request;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String token;
    private String newPassword;
}