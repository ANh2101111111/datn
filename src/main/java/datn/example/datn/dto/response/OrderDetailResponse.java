package datn.example.datn.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
    public class OrderDetailResponse {
    private Long orderDetailId;
    private Long bicycleId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private String image;
    }
