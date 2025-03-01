package datn.example.datn.service;

import datn.example.datn.dto.request.OrderDetailRequestDto;
import datn.example.datn.dto.request.OrderRequestDto;
import datn.example.datn.dto.response.OrderResponseDto;
import datn.example.datn.entity.Order;
import datn.example.datn.entity.OrderDetail;
import datn.example.datn.entity.Product;
import datn.example.datn.mapper.OrderMapper;
import datn.example.datn.repository.OrderRepository;
import datn.example.datn.repository.OrderDetailRepository;
import datn.example.datn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderMapper orderMapper;

    public OrderResponseDto createOrder(OrderRequestDto request) {
        Order order = orderMapper.toEntity(request);
        order.setCreatedAt(new Date());
        order.setStatus("PENDING");
        Order savedOrder = orderRepository.save(order);
        Long savedOrderId = savedOrder.getOrderId();
        for (OrderDetailRequestDto detailDto : request.getOrderDetails()) {
            addProductToOrder(savedOrderId, detailDto);
        }
        savedOrder.calculateTotalAmount();
        orderRepository.save(savedOrder);
        if ("COD".equalsIgnoreCase(request.getPaymentMethod())) {
            handleCODPayment(savedOrder);
        } else if ("VNPay".equalsIgnoreCase(request.getPaymentMethod())) {
            handleVNPayPayment(savedOrder);
        }
        return orderMapper.toDto(savedOrder);
    }
    public void deleteOrder(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new RuntimeException("Order not found");
        }
    }
    public void handleCODPayment(Order order) {
        order.setStatus("COMPLETED");
        orderRepository.save(order);
    }

    public void handleVNPayPayment(Order order) {
        String paymentUrl = "https://sandbox.vnpayment.vn/paymentv2/vnpay.vnp"; // Example URL
        order.setStatus("COMPLETED"); // Assuming payment successful
        orderRepository.save(order);
    }

    public void addProductToOrder(Long orderId, OrderDetailRequestDto detailDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);

        Product product = productRepository.findById(detailDto.getBicycleId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        orderDetail.setProduct(product);
        orderDetail.setQuantity(detailDto.getQuantity());
        orderDetail.setPrice(calculatePrice(product, detailDto.getQuantity()));

        orderDetailRepository.save(orderDetail);

        // Optionally, update the order's total amount here
        order.calculateTotalAmount(); // Make sure this method is implemented
        orderRepository.save(order);
    }
    public void updateOrderDetailQuantity(Long orderId, Long orderDetailId, int newQuantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new RuntimeException("Order detail not found"));

        Product product = orderDetail.getProduct();

        // Kiểm tra xem có đủ số lượng sản phẩm không
        if (newQuantity > product.getQuantity() + orderDetail.getQuantity()) {
            throw new RuntimeException("Insufficient stock for product");
        }

        // Cập nhật số lượng
        product.setQuantity(product.getQuantity() + orderDetail.getQuantity() - newQuantity); // Trả lại số lượng cũ và trừ đi số lượng mới
        orderDetail.setQuantity(newQuantity);
        orderDetail.setPrice(calculatePrice(product, newQuantity)); // Cập nhật giá
        orderDetailRepository.save(orderDetail);
        order.calculateTotalAmount(); // Cập nhật tổng tiền
        orderRepository.save(order);
    }
    private BigDecimal calculateTotalAmount(List<OrderDetailRequestDto> orderDetails) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderDetailRequestDto detail : orderDetails) {
            Product product = productRepository.findById(detail.getBicycleId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            total = total.add(calculatePrice(product, detail.getQuantity()));
        }
        return total;
    }

    private BigDecimal calculatePrice(Product product, int quantity) {
        return product.getOriginalPrice().multiply(BigDecimal.valueOf(quantity)); // Example
    }
}