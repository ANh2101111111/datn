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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    @PostMapping("/{orderId}/vnpay")
    public ResponseEntity<String> createVNPayPayment(@PathVariable Long orderId) {
        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            String totalAmountStr = order.getTotalAmount().toString().replaceAll(",", ""); // Loại bỏ dấu phẩy
            BigDecimal totalAmount = new BigDecimal(totalAmountStr);
            double exchangeRate = 25000; // Tỷ giá VND/USD
            long vndAmount = Math.round(totalAmount.doubleValue() * exchangeRate);

            // Kiểm tra số tiền hợp lệ
            if (vndAmount <= 0) {
                return ResponseEntity.badRequest().body("Số tiền không hợp lệ.");
            }

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", "2.1.0");
            vnp_Params.put("vnp_Command", "pay");
            vnp_Params.put("vnp_TmnCode", VNPayConfig.vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(vndAmount));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", String.valueOf(orderId));
            vnp_Params.put("vnp_OrderInfo", "Thanh toán đơn hàng " + orderId);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
            vnp_Params.put("vnp_CreateDate", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

            // Ghi nhận các tham số để kiểm tra
            System.out.println("vnp_Params: " + vnp_Params);

            String queryString = VNPayConfig.getQueryString(vnp_Params, VNPayConfig.vnp_HashSecret);
            return ResponseEntity.ok(VNPayConfig.vnp_PayUrl + "?" + queryString);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi xử lý thanh toán VNPay: " + e.getMessage());
        }
    }
    @GetMapping("/vnpay_return")
    public ResponseEntity<String> handleVNPayReturn(@RequestParam Map<String, String> vnp_Params) {
        if ("00".equals(vnp_Params.get("vnp_ResponseCode"))) {
            long vndAmount = Long.parseLong(vnp_Params.get("vnp_Amount"));

            double exchangeRate = 25000; // Tỷ giá USD -> VND
            double amountInUSD = (vndAmount / 100.0) / exchangeRate;

            orderService.processVNPayPayment(Long.valueOf(vnp_Params.get("vnp_TxnRef")));

            return ResponseEntity.ok("Payment successful. Amount paid: $" + amountInUSD);
        }
        return ResponseEntity.badRequest().body("Payment via VNPAY failed.");
    }
}