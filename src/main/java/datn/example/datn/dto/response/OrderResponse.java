package datn.example.datn.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private BigDecimal totalAmount;
    private Date createdAt;
    private String orderStatus;
    private List<OrderDetailResponse> orderDetails;
}