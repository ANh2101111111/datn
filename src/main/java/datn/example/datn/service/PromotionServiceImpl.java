package datn.example.datn.service;

import datn.example.datn.dto.request.ApplyPromotionRequest;
import datn.example.datn.dto.request.PromotionRequest;
import datn.example.datn.dto.response.PromotionResponse;
import datn.example.datn.dto.response.ApplyPromotionResponse;
import datn.example.datn.entity.Promotion;
import datn.example.datn.mapper.PromotionMapper;
import datn.example.datn.repository.PromotionRepository;
import datn.example.datn.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionMapper promotionMapper;

    @Override
    public PromotionResponse createPromotion(PromotionRequest request) {
        Promotion promotion = new Promotion();
        promotion.setDiscount(request.getDiscount());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion = promotionRepository.save(promotion);
        return promotionMapper.toResponse(promotion);
    }

    @Override
    public PromotionResponse updatePromotion(Long promotionId, PromotionRequest request) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));

        promotion.setDiscount(request.getDiscount());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion = promotionRepository.save(promotion);
        return promotionMapper.toResponse(promotion);
    }

    @Override
    public void deletePromotion(Long promotionId) {
        if (!promotionRepository.existsById(promotionId)) {
            throw new RuntimeException("Promotion not found");
        }
        promotionRepository.deleteById(promotionId);
    }

    @Override
    public List<PromotionResponse> getActivePromotions() {
        List<Promotion> promotions = promotionRepository.findByEndDateAfter(new Date());
        return promotions.stream().map(promotionMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ApplyPromotionResponse applyPromotion(Long promotionId, ApplyPromotionRequest request) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));

        if (!promotion.isActive()) {
            throw new RuntimeException("Promotion is not active");
        }

        double discountAmount = request.getTotalAmount().multiply(promotion.getDiscount()).doubleValue();
        double finalAmount = request.getTotalAmount().doubleValue() - discountAmount;

        ApplyPromotionResponse response = new ApplyPromotionResponse();
        response.setPromotionId(promotionId);
        response.setOriginalAmount(request.getTotalAmount());
        response.setDiscountAmount(discountAmount);
        response.setFinalAmount(finalAmount);

        return response;
    }
}