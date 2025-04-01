package datn.example.datn.mapper;

import datn.example.datn.dto.response.CartDetailResponse;
import datn.example.datn.entity.CartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartDetailMapper {

    private final ProductMapper productMapper;

    @Autowired
    public CartDetailMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public CartDetailResponse toResponse(CartDetail cartDetail) {
        CartDetailResponse response = new CartDetailResponse();
        response.setCartDetailId(cartDetail.getId());
        response.setBicycleId(cartDetail.getProduct().getBicycleId());
        response.setProductName(cartDetail.getProduct().getName());

        // Lấy giá đã áp dụng giảm giá từ ProductMapper
        BigDecimal discountedPrice = productMapper.calculateDiscountedPrice(cartDetail.getProduct());
        response.setPrice(discountedPrice);

        response.setQuantity(cartDetail.getQuantity());
        response.setImage(cartDetail.getProduct().getImage());
        return response;
    }
}
