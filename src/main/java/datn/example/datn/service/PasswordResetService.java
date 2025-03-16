package datn.example.datn.service;

import datn.example.datn.entity.PasswordResetRequest;
import datn.example.datn.entity.User;
import datn.example.datn.repository.PasswordResetRequestRepository;
import datn.example.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PasswordResetService {

    private static final Logger LOGGER = Logger.getLogger(PasswordResetService.class.getName());

    private final UserRepository userRepository;
    private final PasswordResetRequestRepository passwordResetRequestRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordResetService(UserRepository userRepository,
                                PasswordResetRequestRepository passwordResetRequestRepository,
                                JavaMailSender mailSender,
                                PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordResetRequestRepository = passwordResetRequestRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Xử lý yêu cầu đặt lại mật khẩu
     */
    public boolean requestPasswordReset(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            String token = UUID.randomUUID().toString();
            PasswordResetRequest request = new PasswordResetRequest();
            request.setEmail(email);
            request.setToken(token);
            request.setCreatedAt(new Date());
            request.setExpiresAt(new Date(System.currentTimeMillis() + 3600000)); // Hết hạn sau 1 giờ

            passwordResetRequestRepository.save(request);
            return sendEmail(email, token);
        } else {
            LOGGER.log(Level.WARNING, "❌ Không tìm thấy người dùng với email: {0}", email);
            return false;
        }
    }

    /**
     * Gửi email đặt lại mật khẩu
     */
    private boolean sendEmail(String email, String token) {
        try {
            String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + token;
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Reset Request");
            message.setText("To reset your password, click the link below:\n" + resetLink);

            mailSender.send(message);
            LOGGER.log(Level.INFO, "✅ Email đặt lại mật khẩu đã gửi đến: {0}", email);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "❌ Lỗi khi gửi email: " + e.getMessage(), e);
            return false;
        }
    }

    /**
     * Xử lý đặt lại mật khẩu từ token
     */
    public boolean resetPassword(String token, String newPassword) {
        PasswordResetRequest request = passwordResetRequestRepository.findByToken(token);
        if (request != null && new Date().before(request.getExpiresAt())) {
            Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPassword(passwordEncoder.encode(newPassword)); // Mã hóa mật khẩu
                userRepository.save(user);
                passwordResetRequestRepository.delete(request);
                LOGGER.log(Level.INFO, "✅ Mật khẩu đã được đặt lại thành công cho email: {0}", request.getEmail());
                return true;
            } else {
                LOGGER.log(Level.WARNING, "❌ Không tìm thấy người dùng với email: {0}", request.getEmail());
            }
        } else {
            LOGGER.log(Level.WARNING, "❌ Token không hợp lệ hoặc đã hết hạn: {0}", token);
        }
        return false;
    }
}
