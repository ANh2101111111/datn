package datn.example.datn.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private String image;
    private Double rating;
    private String type;
    private BigDecimal originalPrice;
    private int quantity;
    private Long categoryId;
}