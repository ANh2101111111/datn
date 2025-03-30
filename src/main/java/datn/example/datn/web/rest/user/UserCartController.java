package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.CartDetailRequest;
import datn.example.datn.dto.request.CartRequest;
import datn.example.datn.dto.response.CartResponse;
import datn.example.datn.service.CartService;
import org.springframework.http.ResponseEntity;
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
    @PutMapping("/update")
    public ResponseEntity<CartResponse> updateCartDetail(@RequestParam Long userId, @RequestBody CartDetailRequest request) {
        CartResponse cartResponse = cartService.updateCartDetail(userId, request);
        return ResponseEntity.ok(cartResponse);
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
