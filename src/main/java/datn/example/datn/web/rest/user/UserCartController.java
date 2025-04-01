package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.CartDetailRequest;
import datn.example.datn.dto.request.CartRequest;
import datn.example.datn.dto.response.CartResponse;
import datn.example.datn.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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
    @PutMapping("/update/{userId}")
    public ResponseEntity<CartResponse> updateCartDetail(
            @PathVariable Long userId,
            @RequestBody CartDetailRequest request) {
        CartResponse cartResponse = cartService.updateCartDetail(userId, request);
        return ResponseEntity.ok(cartResponse);
    }

    @PostMapping("/add/{userId}")
    public CartResponse addToCart(@PathVariable Long userId, @RequestBody CartRequest request) {
        return cartService.addToCart(userId, request);
    }

//    @DeleteMapping("/{userId}/clear")
//    public void clearCart(@PathVariable Long userId) {
//        cartService.clearCart(userId);
//    }
@DeleteMapping("/remove-items/{userId}/{cartDetailId}")
public ResponseEntity<?> removeItemFromCart(@PathVariable Long userId, @PathVariable Long cartDetailId) {
    return cartService.removeItemFromCart(userId, cartDetailId)
            ? ResponseEntity.ok().build()
            : ResponseEntity.notFound().build();
}
}
