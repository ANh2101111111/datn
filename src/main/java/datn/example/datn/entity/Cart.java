package datn.example.datn.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> items = new ArrayList<>();
}

