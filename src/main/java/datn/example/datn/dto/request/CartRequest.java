package datn.example.datn.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CartRequest {
    private Long userId;
    private List<CartDetailRequest> cartDetails;
}
