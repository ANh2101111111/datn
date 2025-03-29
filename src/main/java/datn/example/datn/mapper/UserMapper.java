package datn.example.datn.mapper;

import datn.example.datn.dto.response.UserResponse;
import datn.example.datn.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        // Khởi tạo UserResponse với các giá trị từ User
        UserResponse dto = new UserResponse();
        dto.setUserId(user.getUserId());          // Gán ID người dùng
        dto.setUsername(user.getUsername());       // Gán tên người dùng
        dto.setEmail(user.getEmail());             // Gán email
        dto.setActive(user.isActive());             // Gán trạng thái hoạt động

        return dto;  // Trả về đối tượng UserResponse đã được thiết lập
    }
}