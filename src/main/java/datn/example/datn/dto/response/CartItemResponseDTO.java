package datn.example.datn.dto.response;
import java.math.BigDecimal;
import lombok.Data;
@Data
public class CartItemResponseDTO {
    private Long cartItemId;
    private Long productId;
    private String productName;
    private String image;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal totalPrice;
}
