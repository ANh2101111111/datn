package datn.example.datn.service;

import datn.example.datn.entity.PasswordResetRequest;
import datn.example.datn.entity.User;
import datn.example.datn.repository.PasswordResetRequestRepository;
import datn.example.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetRequestRepository passwordResetRequestRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void requestPasswordReset(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) { // Check if the user exists
            User user = optionalUser.get(); // Get the User object
            String token = UUID.randomUUID().toString();
            PasswordResetRequest request = new PasswordResetRequest();
            request.setEmail(email);
            request.setToken(token);
            request.setCreatedAt(new Date());
            request.setExpiresAt(new Date(System.currentTimeMillis() + 3600000)); // Expires in 1 hour
            passwordResetRequestRepository.save(request);
            sendEmail(email, token);
        }
    }

    private void sendEmail(String email, String token) {
        String resetLink = "http://localhost:8080/reset-password?token=" + token; // Adjust for production
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + resetLink);
        mailSender.send(message);
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetRequest request = passwordResetRequestRepository.findByToken(token);
        if (request != null && new Date().before(request.getExpiresAt())) {
            Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
            if (optionalUser.isPresent()) { // Check if the user exists
                User user = optionalUser.get(); // Get the User object
                user.setPassword(newPassword); // Encrypt the password before saving
                userRepository.save(user);
                passwordResetRequestRepository.delete(request);
            }
        }
    }
}