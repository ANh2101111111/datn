package datn.example.datn.dto.request;

import lombok.Data;

@Data
public class UserProfileRequest {
    private String phone;
    private String avatar;
    private String address;
    private String fullName;
}
