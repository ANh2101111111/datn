package datn.example.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
    public class ChatbotResponseDto {
//    private String message;
    private  List<Long> bicycleId;
    private List<String> names;
    private List<String> descriptions;
    private List<String> images;
    private List<BigDecimal> originalPrice;
    private List<BigDecimal> discountedPrices;

}
