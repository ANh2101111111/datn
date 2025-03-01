package datn.example.datn.mapper;

import datn.example.datn.dto.request.ProductRequestDto;
import datn.example.datn.dto.response.ProductResponseDto;
import datn.example.datn.entity.Product;
import datn.example.datn.entity.Category; // Import the Category entity if needed
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {
    public Product toEntity(ProductRequestDto dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
        product.setRating(dto.getRating());
        product.setType(dto.getType());
        product.setOriginalPrice(dto.getOriginalPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(category); // Set the category from the request
        return product;
    }

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setBicycleId(product.getBicycleId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setRating(product.getRating());
        dto.setType(product.getType());
        dto.setOriginalPrice(product.getOriginalPrice());
        dto.setDiscountedPrice(calculateDiscountedPrice(product));
        dto.setQuantity(product.getQuantity());
        return dto;
    }

    private BigDecimal calculateDiscountedPrice(Product product) {
        // Implement your discount logic here
        BigDecimal discountRate = BigDecimal.valueOf(0.1); // Example: 10% discount
        return product.getOriginalPrice().multiply(BigDecimal.ONE.subtract(discountRate));
    }
}