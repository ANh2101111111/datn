package datn.example.datn.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class CartRequestDTO {
    private Long userId;
    private List<CartItemRequestDTO> items;
}
