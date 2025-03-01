package datn.example.datn.repository;

import datn.example.datn.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByEndDateAfter(Date date);
}
