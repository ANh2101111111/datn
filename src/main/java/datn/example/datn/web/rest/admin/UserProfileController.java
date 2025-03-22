package datn.example.datn.web.rest.admin;

import datn.example.datn.dto.response.UserProfileResponse;
import datn.example.datn.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminUserProfileController")
@RequestMapping("/api/admin/user-profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @GetMapping
    public List<UserProfileResponse> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long userId) {
        userProfileService.deleteUserProfile(userId);
        return ResponseEntity.noContent().build();
    }
}
