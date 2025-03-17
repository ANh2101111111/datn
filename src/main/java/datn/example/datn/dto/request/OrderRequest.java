package datn.example.datn.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private List<OrderDetailRequest> orderDetails;
    private String paymentMethod;
}