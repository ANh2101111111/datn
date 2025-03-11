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
        return productRepository.findByIsDeletedFalse().stream() // Lấy sản phẩm chưa bị xóa
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDto> getByType(String type) {
        return productRepository.findByTypeAndIsDeletedFalse(type).stream() // Tìm theo loại chưa bị xóa
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<ProductResponseDto> getBybicycleId(Long bicycleId) {
        return productRepository.getBybicycleId(bicycleId).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }


    public ProductResponseDto updateProduct(Long bicycleId, ProductRequestDto request) {
        Optional<Product> optionalProduct = productRepository.findById(bicycleId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setImage(request.getImage());
            product.setRating(request.getRating());
            product.setType(request.getType());
            product.setOriginalPrice(request.getOriginalPrice());
            product.setQuantity(request.getQuantity());
            Product updatedProduct = productRepository.save(product);
            return productMapper.toDto(updatedProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setDeleted(true); // Đánh dấu sản phẩm là đã xóa mềm
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}