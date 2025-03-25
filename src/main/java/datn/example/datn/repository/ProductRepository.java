package datn.example.datn.repository;

import datn.example.datn.entity.Category;
import datn.example.datn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTypeAndIsDeletedFalse(String type);

    List<Product> findByCategory_CategoryIdAndIsDeletedFalse(Long categoryId);

    Optional<Product> findByBicycleIdAndIsDeletedFalse(Long bicycleId);


    List<Product> findByIsDeletedFalse();


    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByName(@Param("name") String name);

    @Query("SELECT p FROM Product p " +
            "WHERE (:name IS NULL OR p.name ILIKE CONCAT('%', CAST(:name AS text), '%')) " +
            "AND (:type IS NULL OR p.type ILIKE :type) " +
            "AND (:categoryId IS NULL OR p.category.id = :categoryId) " +
            "AND (:minPrice IS NULL OR p.originalPrice >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.originalPrice <= :maxPrice) " +
            "AND p.isDeleted = false " +
            "ORDER BY p.originalPrice DESC")
    List<Product> searchProducts(
            @Param("name") String name,
            @Param("type") String type,
            @Param("categoryId") Long categoryId,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);
    Optional<Product> findTopByOrderByOriginalPriceDesc();
    Optional<Product> findTopByOrderByOriginalPriceAsc();
}

