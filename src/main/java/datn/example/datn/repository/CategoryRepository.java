package datn.example.datn.repository;

import datn.example.datn.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
 Optional<Category> findCategoryByCategoryId(Long categoryId); // Sửa chính tả
}
