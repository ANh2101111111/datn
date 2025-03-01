package datn.example.datn.mapper;

import datn.example.datn.dto.response.PromotionResponse;
import datn.example.datn.entity.Promotion;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapper {
    public PromotionResponse toResponse(Promotion promotion) {
        PromotionResponse response = new PromotionResponse();
        response.setPromotionId(promotion.getPromotionId());
        response.setDiscount(promotion.getDiscount());
        response.setStartDate(promotion.getStartDate());
        response.setEndDate(promotion.getEndDate());
        response.setActive(promotion.isActive());
        return response;
    }
}
