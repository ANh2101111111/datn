package datn.example.datn.web.rest.user;

import datn.example.datn.dto.response.ProductResponseDto;
import datn.example.datn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/user/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{bicycleId}")
    public ResponseEntity<ProductResponseDto> getByBicycleId(@PathVariable Long bicycleId) {
        return ResponseEntity.ok(productService.getByBicycleId(bicycleId));
    }

    @GetMapping("/by-type/{type}")
    public ResponseEntity<List<ProductResponseDto>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(productService.getByType(type));
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<ProductResponseDto>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getByCategory(categoryId));
    }

    @GetMapping("/by-price-range")
    public ResponseEntity<List<ProductResponseDto>> getByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(productService.getByPriceRange(minPrice, maxPrice));
    }
}
