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
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "rating" ,nullable = false)
    private Double rating;
    @Column(name = "type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @Column(name = "original_price")
    private BigDecimal originalPrice;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Promotion> promotions;
    @Column(nullable = false)
    private int quantity;
    public boolean isOutOfStock() {
        return this.quantity <= 0;
    }
    public void reduceQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
        } else {
            throw new RuntimeException("Insufficient stock");
        }
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