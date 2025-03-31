package datn.example.datn.mapper;

import datn.example.datn.dto.response.CartDetailResponse;
import datn.example.datn.entity.CartDetail;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class CartDetailMapper {
    public CartDetailResponse toResponse(CartDetail cartDetail) {
        CartDetailResponse response = new CartDetailResponse();
        response.setCartDetailId(cartDetail.getId());
        response.setBicycleId(cartDetail.getProduct().getBicycleId());
        response.setProductName(cartDetail.getProduct().getName());
        response.setPrice(cartDetail.getPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
        response.setQuantity(cartDetail.getQuantity());
        response.setImage(cartDetail.getProduct().getImage());
        return response;
    }
}
