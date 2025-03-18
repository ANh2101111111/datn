package datn.example.datn.repository;

import datn.example.datn.entity.Order;
import datn.example.datn.entity.OrderStatus;
import datn.example.datn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_UserId(Long userId);
}
