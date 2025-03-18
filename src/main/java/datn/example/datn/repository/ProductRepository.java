package datn.example.datn.repository;

import datn.example.datn.entity.Category;
import datn.example.datn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTypeAndIsDeletedFalse(String type);
    List<Product> findByCategory_CategoryIdAndIsDeletedFalse(Long categoryId);
    Optional<Product> findByBicycleIdAndIsDeletedFalse(Long bicycleId);
    List<Product> findByOriginalPriceBetweenAndIsDeletedFalse(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> findByIsDeletedFalse();

}