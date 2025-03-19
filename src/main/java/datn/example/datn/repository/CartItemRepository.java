package datn.example.datn.repository;

import datn.example.datn.entity.Cart;
import datn.example.datn.entity.CartItem;
import datn.example.datn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
