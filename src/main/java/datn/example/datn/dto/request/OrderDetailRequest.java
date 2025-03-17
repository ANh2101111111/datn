package datn.example.datn.dto.request;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailRequest {
    private String productName;
    private BigDecimal price;
    private int quantity;
}
