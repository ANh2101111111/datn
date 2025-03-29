package datn.example.datn.service;

import datn.example.datn.entity.User;
import datn.example.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
    }
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false); // Set is_active to false for soft delete
        userRepository.save(user);
    }
}
