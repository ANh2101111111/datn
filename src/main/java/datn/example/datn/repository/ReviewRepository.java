package datn.example.datn.repository;

import datn.example.datn.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProduct_bicycleId(Long bicycleId);
}