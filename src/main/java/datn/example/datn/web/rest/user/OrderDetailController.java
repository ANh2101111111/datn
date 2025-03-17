package datn.example.datn.web.rest.user;
import datn.example.datn.dto.request.OrderDetailRequest;
import datn.example.datn.service.OrderDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/{orderId}")
    public ResponseEntity<Void> addProductToOrder(@PathVariable Long orderId, @RequestBody OrderDetailRequest request) {
        orderDetailService.addProductToOrder(orderId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderDetailId}")
    public ResponseEntity<Void> updateProductQuantity(@PathVariable Long orderDetailId, @RequestParam int quantity) {
        orderDetailService.updateProductQuantity(orderDetailId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{orderDetailId}")
    public ResponseEntity<Void> removeProductFromOrder(@PathVariable Long orderDetailId) {
        orderDetailService.removeProductFromOrder(orderDetailId);
        return ResponseEntity.ok().build();
    }
}