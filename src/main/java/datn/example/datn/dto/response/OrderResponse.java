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
    private Boolean cancel;
    private String orderStatus;
    private List<OrderDetailResponse> orderDetails;
    private String fullName;            // Thêm trường fullName
    private String phoneNumber;         // Thêm trường phoneNumber
    private String email;               // Thêm trường email
    private String address;             // Thêm trường address
    private String note;                // Thêm trường note
    private String paymentMethod;       // Thêm trường paymentMethod
}