package datn.example.datn.mapper;

import datn.example.datn.dto.request.CartRequest;
import datn.example.datn.dto.response.CartDetailResponse;
import datn.example.datn.dto.response.CartResponse;
import datn.example.datn.entity.Cart;
import datn.example.datn.entity.CartDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public CartResponse toResponse(Cart cart) {
        List<CartDetailResponse> details = cart.getCartDetails().stream().map(detail -> {
            CartDetailResponse response = new CartDetailResponse();
            response.setCartDetailId(detail.getId());
            response.setBicycleId(detail.getProduct().getBicycleId());
            response.setProductName(detail.getProduct().getName());
            response.setQuantity(detail.getQuantity());
            return response;
        }).collect(Collectors.toList());

        CartResponse response = new CartResponse();
        response.setCartId(cart.getId());
        response.setUserId(cart.getUser().getUserId());
        response.setCartDetails(details);
        return response;
    }
}
