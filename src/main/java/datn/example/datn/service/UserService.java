package datn.example.datn.service;

import datn.example.datn.dto.response.UserResponse;
import datn.example.datn.entity.User;
import datn.example.datn.mapper.UserMapper;
import datn.example.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
    }
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false); // Set is_active to false for soft delete
        userRepository.save(user);
    }
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse) // Sử dụng UserMapper để chuyển đổi
                .collect(Collectors.toList());
    }
}
