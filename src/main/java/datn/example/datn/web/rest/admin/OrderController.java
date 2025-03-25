package datn.example.datn.web.rest.admin;

import datn.example.datn.dto.request.OrderRequest;
import datn.example.datn.dto.response.OrderResponse;
import datn.example.datn.entity.OrderStatus;
import datn.example.datn.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminOderController")
@RequestMapping("/api/admin/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{orderId}/status")
    public String updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return "Order status updated to " + status;
    }
}
