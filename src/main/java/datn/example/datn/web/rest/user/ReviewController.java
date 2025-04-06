package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.ReviewRequestDTO;
import datn.example.datn.dto.response.ReviewResponseDTO;
import datn.example.datn.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userReviewController")
@RequestMapping("/api/user/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{reviewId}")
    @Operation(tags = "Get Reviews By ReviewId")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable Long reviewId) {
        ReviewResponseDTO reviewResponse = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(reviewResponse);
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseDTO reviewResponse = reviewService.createReview(reviewRequestDTO);
        return ResponseEntity.ok(reviewResponse);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews() {
        List<ReviewResponseDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }
    @GetMapping("product/{bicycleId}")
    @Operation(tags = "Get Reviews By BicycleId")
    public List<ReviewResponseDTO> getReviewsByBicycleId(@PathVariable Long bicycleId) {
        return reviewService.getReviewsByBicycleId(bicycleId);
    }
}
