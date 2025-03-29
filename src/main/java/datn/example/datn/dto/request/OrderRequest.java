package datn.example.datn.dto.request;

import datn.example.datn.entity.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private List<OrderDetailRequest> orderDetails;
    private String paymentMethod;
    private OrderStatus orderStatus;
}