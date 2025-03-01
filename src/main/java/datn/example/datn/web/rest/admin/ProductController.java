package datn.example.datn.web.rest.admin;


import datn.example.datn.dto.request.ProductRequestDto;
import datn.example.datn.dto.response.ProductResponseDto;
import datn.example.datn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminProductController")
@RequestMapping("/api/admin/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{bicycleId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long bicycleId, @RequestBody ProductRequestDto request) {
        return ResponseEntity.ok(productService.updateProduct(bicycleId, request));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{bicycleId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long bicycleId) {
        productService.deleteProduct(bicycleId);
        return ResponseEntity.noContent().build();
    }
}
