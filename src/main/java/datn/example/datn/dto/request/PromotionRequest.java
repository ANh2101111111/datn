package datn.example.datn.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PromotionRequest {
    private BigDecimal discount;
    private Date startDate;
    private Date endDate;
}