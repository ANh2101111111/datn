package datn.example.datn.dto.response;
import lombok.Data;

@Data
public class ReviewResponseDTO {
    private Long reviewId;
    private String username;
    private int rating;
    private String comment;
}