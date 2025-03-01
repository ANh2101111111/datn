package datn.example.datn.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private Long userId;
    private List<OrderDetailRequestDto> orderDetails;
    private String paymentMethod; // "COD" or "VNPay"
}