package datn.example.datn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bicycleId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "type")
    private String type;

    @Column(name = "original_price")
    private BigDecimal originalPrice;

    @Column(nullable = false)
    private int quantity; // Số lượng có

    @Column(name = "sold_quantity", nullable = false)
    private int soldQuantity = 0; // Số lượng đã bán

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public boolean isOutOfStock() {
        return getStock() <= 0; // Kiểm tra tồn kho
    }

    public void reduceQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount; // Giảm số lượng có
            this.soldQuantity += amount; // Tăng số lượng đã bán
        } else {
            throw new RuntimeException("Insufficient stock");
        }
    }

    public int getStock() {
        return this.quantity - this.soldQuantity; // Tính số lượng tồn kho
    }

    public void calculateAverageRating() {
        if (reviews != null && !reviews.isEmpty()) {
            double totalRating = 0;
            for (Review review : reviews) {
                totalRating += review.getRating();
            }
            this.rating = totalRating / reviews.size();
        } else {
            this.rating = 0.0;
        }
    }
}