package datn.example.datn.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bicycleId;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Promotion> promotions;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
