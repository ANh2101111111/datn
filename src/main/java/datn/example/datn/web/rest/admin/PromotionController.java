package datn.example.datn.web.rest.admin;
import datn.example.datn.dto.request.PromotionRequest;
import datn.example.datn.dto.response.PromotionResponse;
import datn.example.datn.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController ("amdinPromotionsController")
@RequestMapping("/api/admin/promotions")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public PromotionResponse createPromotion(@RequestBody PromotionRequest request) {
        return promotionService.createPromotion(request);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{promotionId}")
    public PromotionResponse updatePromotion(@PathVariable Long promotionId, @RequestBody PromotionRequest request) {
        return promotionService.updatePromotion(promotionId, request);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{promotionId}")
    public void deletePromotion(@PathVariable Long promotionId) {
        promotionService.deletePromotion(promotionId);
    }
}
