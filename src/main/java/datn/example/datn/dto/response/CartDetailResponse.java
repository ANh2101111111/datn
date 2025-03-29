package datn.example.datn.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDetailResponse {
    private Long cartDetailId;
    private Long bicycleId;
    private String productName;
    private int quantity;
}