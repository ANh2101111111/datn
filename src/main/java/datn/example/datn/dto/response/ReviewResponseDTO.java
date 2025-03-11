package datn.example.datn.dto.response;
import lombok.Data;

@Data
public class ReviewResponseDTO {
    private Long reviewId;
    private String username; // Tên người dùng
    private int rating;
    private String comment;
}