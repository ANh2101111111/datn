package datn.example.datn.entity;
import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId; // Đổi từ id -> cartItemId

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "bicycleId", nullable = false)
    private Product product;

    private int quantity;
    private BigDecimal unitPrice;
}
