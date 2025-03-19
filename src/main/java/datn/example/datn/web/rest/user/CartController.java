package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.CartRequestDTO;
import datn.example.datn.dto.response.CartResponseDTO;
import datn.example.datn.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/carts")
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addToCart(@RequestBody CartRequestDTO cartRequestDTO) {
        return ResponseEntity.ok(cartService.addToCart(cartRequestDTO));
    }

    // 2. Cập nhật số lượng sản phẩm trong giỏ hàng
    @PutMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> updateCart(@PathVariable Long userId, @RequestBody CartRequestDTO cartRequestDTO) {
        return ResponseEntity.ok(cartService.updateCart(userId, cartRequestDTO));
    }
    // 3. Lấy danh sách sản phẩm trong giỏ hàng
    @GetMapping
    public ResponseEntity<CartResponseDTO> getCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    // 4. Xóa một sản phẩm khỏi giỏ hàng
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<CartResponseDTO> removeCartItem(@PathVariable Long cartItemId, @RequestParam Long userId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, cartItemId));
    }

    // 5. Xóa toàn bộ giỏ hàng
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Giỏ hàng đã được xóa.");
    }
}
