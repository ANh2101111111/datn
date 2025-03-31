package datn.example.datn.dto.request;

import datn.example.datn.entity.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private List<OrderDetailRequest> orderDetails;
    private String fullName;            // Thêm trường fullName
    private String phoneNumber;         // Thêm trường phoneNumber
    private String email;               // Thêm trường email
    private String address;             // Thêm trường address
    private String note;                // Thêm trường note
    private String paymentMethod;
    private OrderStatus orderStatus;// Giữ nguyên cho paymentMethod
}