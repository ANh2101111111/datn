package datn.example.datn.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailResponseDto {
    private Long orderDetailId;
    private Long bicycleId;
    private int quantity;
    private BigDecimal price;
}
