package datn.example.datn.web.rest.user;
import datn.example.datn.dto.request.ApplyPromotionRequest;
import datn.example.datn.dto.response.PromotionResponse;
import datn.example.datn.dto.response.ApplyPromotionResponse;
import datn.example.datn.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController ("userPromotionscontroller")
@RequestMapping("/api/user/promotions")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/active")
    public List<PromotionResponse> getActivePromotions() {
        return promotionService.getActivePromotions();
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{promotionId}/apply")
    public ApplyPromotionResponse applyPromotion(@PathVariable Long promotionId, @RequestBody ApplyPromotionRequest request) {
        return promotionService.applyPromotion(promotionId, request);
    }
}
