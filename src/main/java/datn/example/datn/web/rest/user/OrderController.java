package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.OrderRequestDto;
import datn.example.datn.dto.response.OrderResponseDto;
import datn.example.datn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/user/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{orderId}/details/{orderDetailId}/quantity")
    public ResponseEntity<Void> updateOrderDetailQuantity(@PathVariable Long orderId,
                                                          @PathVariable Long orderDetailId,
                                                          @RequestParam int newQuantity) {
        orderService.updateOrderDetailQuantity(orderId, orderDetailId, newQuantity);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}

