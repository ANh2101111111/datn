package datn.example.datn.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponse {
    private Long cartId;
    private Long userId;
    private List<CartDetailResponse> cartDetails;
    private BigDecimal totalAmount;
}