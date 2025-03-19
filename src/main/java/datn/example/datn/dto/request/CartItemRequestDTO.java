package datn.example.datn.dto.request;
import lombok.Data;

@Data
public class CartItemRequestDTO {
    private Long productId;
    private int quantity;
}
