package datn.example.datn.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDto {
    private Long bicycleId;
    private String name;
    private String description;
    private String image;
    private Double rating;
    private String type;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private int quantity;
    private int stock;
    private Long categoryId;
    private CategoryResponseDto category;
}
