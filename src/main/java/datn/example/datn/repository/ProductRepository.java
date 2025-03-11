package datn.example.datn.repository;

import datn.example.datn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTypeAndIsDeletedFalse(String type);
    Optional<Product> findById(Long bicycleId);
    List<Product>findByIsDeletedFalse();
    List<Product> getBybicycleId(Long bicycleId);
    List<Product> findByCategory_CategoryIdAndIsDeletedFalse(Long categoryId);
}