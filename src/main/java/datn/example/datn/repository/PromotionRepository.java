package datn.example.datn.repository;

import datn.example.datn.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByEndDateAfter(Date date);
}
