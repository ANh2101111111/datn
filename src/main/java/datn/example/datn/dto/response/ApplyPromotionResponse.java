package datn.example.datn.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ApplyPromotionResponse {
    private Long promotionId;
    private BigDecimal originalAmount;
    private double discountAmount;
    private double finalAmount;
}