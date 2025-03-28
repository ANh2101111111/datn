package datn.example.datn.repository;

import datn.example.datn.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
 Optional<Category> findCategoryByCategoryId(Long categoryId);
 Optional<Category> findCategoryByName(String name);
}
