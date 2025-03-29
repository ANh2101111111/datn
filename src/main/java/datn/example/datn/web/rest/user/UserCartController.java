package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.CartRequest;
import datn.example.datn.dto.response.CartResponse;
import datn.example.datn.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/cart")
public class UserCartController {
    private final CartService cartService;

    public UserCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public CartResponse getCart(@PathVariable Long userId) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/{userId}/add")
    public CartResponse addToCart(@PathVariable Long userId, @RequestBody CartRequest request) {
        return cartService.addToCart(userId, request);
    }

    @DeleteMapping("/{userId}/clear")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}
