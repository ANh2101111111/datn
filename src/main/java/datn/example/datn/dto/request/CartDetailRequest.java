package datn.example.datn.dto.request;


import lombok.Data;

@Data
public class CartDetailRequest {
    private Long id;
    private Long bicycleId;
    private int quantity;
}
