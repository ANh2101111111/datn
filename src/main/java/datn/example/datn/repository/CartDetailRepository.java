package datn.example.datn.repository;

import datn.example.datn.entity.Cart;
import datn.example.datn.entity.CartDetail;
import datn.example.datn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartDetail cd WHERE cd.id = :cartDetailId AND cd.cart.user.userId = :userId")
    int deleteByCartDetailIdAndUserId(@Param("cartDetailId") Long cartDetailId, @Param("userId") Long userId);
}
