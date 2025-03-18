package datn.example.datn.service;

import datn.example.datn.dto.request.ProductRequestDto;
import datn.example.datn.dto.response.ProductResponseDto;
import datn.example.datn.entity.Category;
import datn.example.datn.entity.Product;
import datn.example.datn.mapper.ProductMapper;
import datn.example.datn.repository.CategoryRepository;
import datn.example.datn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = productMapper.toEntity(requestDto, category);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findByIsDeletedFalse().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDto> getByType(String type) {
        return productRepository.findByTypeAndIsDeletedFalse(type).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDto> getByCategory(Long categoryId) {
        return productRepository.findByCategory_CategoryIdAndIsDeletedFalse(categoryId).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto getByBicycleId(Long bicycleId) {
        Product product = productRepository.findByBicycleIdAndIsDeletedFalse(bicycleId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toDto(product);
    }

    public List<ProductResponseDto> getByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByOriginalPriceBetweenAndIsDeletedFalse(minPrice, maxPrice).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto updateProduct(Long bicycleId, ProductRequestDto request) {
        Product product = productRepository.findByBicycleIdAndIsDeletedFalse(bicycleId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        product.setRating(request.getRating());
        product.setType(request.getType());
        product.setOriginalPrice(request.getOriginalPrice());
        product.setQuantity(request.getQuantity());

        Product updatedProduct = productRepository.save(product);
        return productMapper.toDto(updatedProduct);
    }

    public void deleteProduct(Long bicycleId) {
        Product product = productRepository.findByBicycleIdAndIsDeletedFalse(bicycleId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDeleted(true);
        productRepository.save(product);
    }
}
