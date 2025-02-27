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

    @Column(nullable = false)
    private String name;

    private String description;
    private String imageUrl;

    private Double rating; // Rating trung bình, có thể cập nhật
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // Đặt khóa ngoại
    private Category category;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "original_price")
    private BigDecimal originalPrice;

    @Column(name = "discounted_price")
    private BigDecimal discountedPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Promotion> promotions;

    // Phương thức để tính toán rating trung bình
    public void calculateAverageRating() {
        if (reviews != null && !reviews.isEmpty()) {
            double totalRating = 0;
            for (Review review : reviews) {
                totalRating += review.getRating();
            }
            this.rating = totalRating / reviews.size();
        } else {
            this.rating = 0.0; // Hoặc null nếu không có đánh giá
        }
    }

}