package datn.example.datn.service;

import datn.example.datn.dto.request.ApplyPromotionRequest;
import datn.example.datn.dto.request.PromotionRequest;
import datn.example.datn.dto.response.PromotionResponse;
import datn.example.datn.dto.response.ApplyPromotionResponse;

import java.util.List;

public interface PromotionService {
    PromotionResponse createPromotion(PromotionRequest request);
    PromotionResponse updatePromotion(Long id, PromotionRequest request);
    void deletePromotion(Long id);
    List<PromotionResponse> getActivePromotions();
    ApplyPromotionResponse applyPromotion(Long promotionId, ApplyPromotionRequest request);
}
