package datn.example.datn.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {
    private Long cartId;
    private Long userId;
    private List<CartDetailResponse> cartDetails;
}