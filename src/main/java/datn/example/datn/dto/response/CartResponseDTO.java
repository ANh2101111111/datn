package datn.example.datn.dto.response;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
@Data
public class CartResponseDTO {
    private Long cartId;
    private Long userId;
    private List<CartItemResponseDTO> items;
    private BigDecimal totalPrice;
}
