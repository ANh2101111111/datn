package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.OrderDetailRequestDto;
import datn.example.datn.dto.response.ProductResponseDto;
import datn.example.datn.service.OrderService;
import datn.example.datn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/by-type/{type}")
    public ResponseEntity<List<ProductResponseDto>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(productService.getByType(type));
    }
    @GetMapping("/{bicycleId}")
    public ResponseEntity<List<ProductResponseDto>> getById(@PathVariable Long bicycleId) {
        return ResponseEntity.ok(productService.getBybicycleId(bicycleId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
