package datn.example.datn.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long orderId;
    private Long userId;
    private BigDecimal totalAmount;
    private String status;
    private Date createdAt;
    private List<OrderDetailResponseDto> orderDetails;
}
