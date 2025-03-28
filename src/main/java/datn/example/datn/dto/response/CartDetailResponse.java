package datn.example.datn.dto.response;

import lombok.Data;

@Data
public class CartDetailResponse {
    private Long cartDetailId;
    private Long bicycleId;
    private int quantity;
}