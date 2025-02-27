package datn.example.datn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import datn.example.datn.entity.PasswordResetRequest;
@Repository
public interface PasswordResetRequestRepository extends JpaRepository<PasswordResetRequest, Long> {
    PasswordResetRequest findByToken(String token);
    PasswordResetRequest findByEmail(String email);
}