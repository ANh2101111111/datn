package datn.example.datn.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PromotionResponse {
    private Long promotionId;
    private BigDecimal discount;
    private Date startDate;
    private Date endDate;
    private boolean active;
}
