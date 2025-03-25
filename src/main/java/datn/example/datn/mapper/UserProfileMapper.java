package datn.example.datn.mapper;


import datn.example.datn.dto.request.UserProfileRequest;
import datn.example.datn.dto.response.UserProfileResponse;
import datn.example.datn.entity.User;
import datn.example.datn.entity.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    // Chuyển từ Entity -> Response DTO
    public UserProfileResponse toResponseDTO(UserProfile userProfile) {
        if (userProfile == null) {
            return null;
        }
        UserProfileResponse dto = new UserProfileResponse();
        dto.setId(userProfile.getId());
        dto.setFullName(userProfile.getFullName());
        dto.setPhone(userProfile.getPhone());
        dto.setAvatar(userProfile.getAvatar());
        dto.setAddress(userProfile.getAddress());
        dto.setUsername(userProfile.getUser().getUsername());
        return dto;
    }

    // Chuyển từ Request DTO -> Entity
    public UserProfile toEntity(UserProfileRequest requestDTO, User user) {
        if (requestDTO == null || user == null) {
            return null;
        }
        UserProfile userProfile = new UserProfile();
        userProfile.setFullName(requestDTO.getFullName());
        userProfile.setPhone(requestDTO.getPhone());
        userProfile.setAvatar(requestDTO.getAvatar());
        userProfile.setAddress(requestDTO.getAddress());
        userProfile.setUser(user);
        return userProfile;
    }

    // Cập nhật dữ liệu từ Request DTO vào Entity
    public void updateEntityFromDTO(UserProfileRequest requestDTO, UserProfile userProfile) {
        if (requestDTO == null || userProfile == null) {
            return;
        }
        userProfile.setFullName(requestDTO.getFullName());
        userProfile.setPhone(requestDTO.getPhone());
        userProfile.setAvatar(requestDTO.getAvatar());
        userProfile.setAddress(requestDTO.getAddress());
    }
}
