package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.CartDetailRequest;
import datn.example.datn.dto.request.CartRequest;
import datn.example.datn.dto.request.OrderRequest;
import datn.example.datn.dto.response.CartResponse;
import datn.example.datn.dto.response.OrderResponse;
import datn.example.datn.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/cart")
public class UserCartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public void addProductToCart(@RequestBody CartRequest request) {
        cartService.addProductToCart(request.getUserId(), request.getCartDetails().get(0)); // Simplified
    }

    @GetMapping("/{userId}")
    public CartResponse getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }
    @PutMapping("/{userId}/update")
    public ResponseEntity<CartResponse> updateCart(@PathVariable Long userId, @RequestBody List<CartDetailRequest> cartDetails) {
        CartResponse updatedCart = cartService.updateCart(userId, cartDetails);
        return ResponseEntity.ok(updatedCart); // Trả về cart đã cập nhật
    }
    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok().build();
    }
}