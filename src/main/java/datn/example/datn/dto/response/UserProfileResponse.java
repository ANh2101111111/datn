package datn.example.datn.dto.response;

import lombok.Data;

@Data
public class UserProfileResponse {
    private Long id;
    private String phone;
    private String avatar;
    private String address;
    private String fullName;
    private String username;
}
