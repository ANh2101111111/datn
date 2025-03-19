package datn.example.datn.repository;

import datn.example.datn.entity.Cart;
import datn.example.datn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser_UserId(Long userId);


    Optional<Cart> findByUser(User user);
}
