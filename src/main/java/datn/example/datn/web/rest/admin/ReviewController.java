package datn.example.datn.web.rest.admin;

import datn.example.datn.dto.request.ReviewRequestDTO;
import datn.example.datn.dto.response.ReviewResponseDTO;
import datn.example.datn.entity.Review;
import datn.example.datn.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminReviewController")
@RequestMapping("/api/admin/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
