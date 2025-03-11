package datn.example.datn.dto.request;

import lombok.Data;

@Data
public class ReviewRequestDTO {
    private Long bicycleId; // ID sản phẩm
    private Long userId; // ID người dùng
    private int rating;
    private String comment;
}
