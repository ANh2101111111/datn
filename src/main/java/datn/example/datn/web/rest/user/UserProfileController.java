package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.UserProfileRequest;
import datn.example.datn.dto.response.UserProfileResponse;
import datn.example.datn.entity.User;
import datn.example.datn.repository.UserRepository;
import datn.example.datn.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController("userUserProfileController")
@RequestMapping("/api/user/profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<UserProfileResponse> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        return ResponseEntity.ok(userProfileService.getUserProfile(user.getUserId()));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> createUserProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileRequest requestDTO) {
        return ResponseEntity.ok(userProfileService.createUserProfile(userId, requestDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileRequest requestDTO) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(userId, requestDTO));
    }
}
