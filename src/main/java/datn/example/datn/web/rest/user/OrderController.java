package datn.example.datn.web.rest.user;

import datn.example.datn.config.VNPayConfig;
import datn.example.datn.dto.request.OrderRequest;
import datn.example.datn.dto.response.OrderResponse;
import datn.example.datn.dto.response.UserProfileResponse;
import datn.example.datn.entity.Cart;
import datn.example.datn.entity.Order;
import datn.example.datn.entity.OrderStatus;
import datn.example.datn.entity.User;
import datn.example.datn.repository.CartRepository;
import datn.example.datn.repository.OrderRepository;
import datn.example.datn.repository.UserRepository;
import datn.example.datn.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("userOderController")
@RequestMapping("/api/user/orders")
public class OrderController {


    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    @Autowired
    public OrderController(OrderService orderService, OrderRepository orderRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        Cart cart = cartRepository.findByUser_UserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getCartDetails().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        OrderResponse orderResponse = orderService.createOrder(request);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable Long userId) {
        List<OrderResponse> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }


    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest request) {
        OrderResponse updatedOrder = orderService.updateOrder(orderId, request);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{orderId}/pay/cod")
    public String payOrderWithCOD(@PathVariable Long orderId) {
        orderService.processCOD(orderId);
        return "Payment via COD successful. Order status set to PENDING.";
    }

    @PostMapping("/{orderId}/pay/vnpay")
    public ResponseEntity<String> createVNPayPayment(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", VNPayConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(order.getTotalAmount()));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", String.valueOf(orderId));
        vnp_Params.put("vnp_OrderInfo", "Thanh toán đơn hàng " + orderId);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_CreateDate", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

        try {
            String queryString = VNPayConfig.getQueryString(vnp_Params, VNPayConfig.vnp_HashSecret);
            return ResponseEntity.ok(VNPayConfig.vnp_PayUrl + "?" + queryString);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating payment URL.");
        }
    }

    @GetMapping("/payment/vnpay_return")
    public ResponseEntity<String> handleVNPayReturn(@RequestParam Map<String, String> vnp_Params) {
        if ("00".equals(vnp_Params.get("vnp_ResponseCode"))) {
            orderService.processVNPayPayment(Long.valueOf(vnp_Params.get("vnp_TxnRef")));
            return ResponseEntity.ok("Payment via VNPAY successful. Order status updated to PAID.");
        }
        return ResponseEntity.badRequest().body("Payment via VNPAY failed.");
    }
}

