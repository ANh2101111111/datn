package datn.example.datn.service;

import datn.example.datn.dto.request.ReviewRequestDTO;
import datn.example.datn.dto.response.ReviewResponseDTO;
import datn.example.datn.entity.Review;
import datn.example.datn.entity.Product;
import datn.example.datn.entity.User;
import datn.example.datn.repository.ReviewRepository;
import datn.example.datn.repository.ProductRepository;
import datn.example.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO) {
        Review review = new Review();
        review.setRating(reviewRequestDTO.getRating());
        review.setComment(reviewRequestDTO.getComment());

        Product product = productRepository.findById(reviewRequestDTO.getBicycleId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        review.setProduct(product);

        User user = userRepository.findById(reviewRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        review.setUser(user);

        Review savedReview = reviewRepository.save(review);

        // Tạo ResponseDTO
        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        responseDTO.setReviewId(savedReview.getReviewId());
        responseDTO.setUsername(user.getUsername()); // Giả sử User có phương thức getUsername()
        responseDTO.setRating(savedReview.getRating());
        responseDTO.setComment(savedReview.getComment());

        return responseDTO;
    }

    public ReviewResponseDTO getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        responseDTO.setReviewId(review.getReviewId());
        responseDTO.setUsername(review.getUser().getUsername()); // Giả sử User có phương thức getUsername()
        responseDTO.setRating(review.getRating());
        responseDTO.setComment(review.getComment());

        return responseDTO;
    }

    public List<ReviewResponseDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(review -> {
            ReviewResponseDTO responseDTO = new ReviewResponseDTO();
            responseDTO.setReviewId(review.getReviewId());
            responseDTO.setUsername(review.getUser().getUsername()); // Giả sử User có phương thức getUsername()
            responseDTO.setRating(review.getRating());
            responseDTO.setComment(review.getComment());
            return responseDTO;
        }).collect(Collectors.toList());
    }

    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(reviewId);
    }
}