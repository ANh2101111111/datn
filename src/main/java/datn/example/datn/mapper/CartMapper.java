package datn.example.datn.mapper;

import datn.example.datn.dto.request.CartRequest;
import datn.example.datn.dto.response.CartResponse;
import datn.example.datn.dto.response.CartDetailResponse;
import datn.example.datn.entity.Cart;
import datn.example.datn.entity.CartDetail;
import datn.example.datn.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    private final CartDetailMapper cartDetailMapper;

    public CartMapper(CartDetailMapper cartDetailMapper) {
        this.cartDetailMapper = cartDetailMapper;
    }

    public Cart toEntity(CartRequest request, User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCartDetails(null); // Để xử lý ở CartDetailMapper
        return cart;
    }

    public CartResponse toResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setCartId(cart.getId());
        response.setUserId(cart.getUser().getUserId());
        response.setTotalAmount(cart.getTotalAmount());
        response.setCartDetails(cart.getCartDetails().stream()
                .map(cartDetailMapper::toResponse)
                .collect(Collectors.toList()));
        return response;
    }
}
