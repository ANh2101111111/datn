package datn.example.datn.service;


import datn.example.datn.dto.request.UserProfileRequest;
import datn.example.datn.dto.response.UserProfileResponse;
import datn.example.datn.entity.User;
import datn.example.datn.entity.UserProfile;
import datn.example.datn.mapper.UserProfileMapper;
import datn.example.datn.repository.UserProfileRepository;
import datn.example.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileMapper userProfileMapper;

    // Lấy UserProfile theo userId
    public UserProfileResponse getUserProfile(Long userId) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUser_UserId(userId);

        if (userProfileOptional.isPresent()) {
            return userProfileMapper.toResponseDTO(userProfileOptional.get());
        }
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userProfileMapper.toResponseFromUserOnly(userOptional.get());
        }
        return null;
    }


    // Tạo UserProfile mới
    public UserProfileResponse createUserProfile(Long userId, UserProfileRequest requestDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User không tồn tại!");
        }

        if (userProfileRepository.findByUser_UserId(userId).isPresent()) {
            throw new RuntimeException("UserProfile đã tồn tại! Vui lòng cập nhật thay vì tạo mới.");
        }

        UserProfile userProfile = userProfileMapper.toEntity(requestDTO, userOptional.get());
        UserProfile savedProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toResponseDTO(savedProfile);
    }

    // Cập nhật UserProfile
    public UserProfileResponse updateUserProfile(Long userId, UserProfileRequest requestDTO) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUser_UserId(userId);
        if (userProfileOptional.isEmpty()) {
            throw new RuntimeException("UserProfile chưa tồn tại! Hãy tạo mới trước.");
        }

        UserProfile userProfile = userProfileOptional.get();
        userProfileMapper.updateEntityFromDTO(requestDTO, userProfile);

        UserProfile updatedProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toResponseDTO(updatedProfile);
    }

    // Xóa UserProfile (Admin)
    public void deleteUserProfile(Long userId) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUser_UserId(userId);
        userProfileOptional.ifPresent(userProfileRepository::delete);
    }
    // Lấy toàn bộ danh sách UserProfile
    public List<UserProfileResponse> getAllUserProfiles() {
        List<UserProfile> profiles = userProfileRepository.findAll();
        return profiles.stream()
                .map(userProfileMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
