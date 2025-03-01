package datn.example.datn.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ApplyPromotionRequest {
    private BigDecimal totalAmount;
}
