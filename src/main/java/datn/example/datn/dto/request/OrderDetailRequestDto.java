package datn.example.datn.dto.request;
import lombok.Data;

@Data
public class OrderDetailRequestDto {
    private Long bicycleId;
    private int quantity;
}
