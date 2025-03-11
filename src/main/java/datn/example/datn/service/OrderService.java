package datn.example.datn.service;

import datn.example.datn.dto.request.OrderDetailRequestDto;
import datn.example.datn.dto.request.OrderRequestDto;
import datn.example.datn.dto.response.OrderResponseDto;
import datn.example.datn.entity.*;
import datn.example.datn.mapper.OrderMapper;
import datn.example.datn.repository.OrderRepository;
import datn.example.datn.repository.ProductRepository;
import datn.example.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderMapper orderMapper;

    public OrderResponseDto createOrder(OrderRequestDto request) {
        Order order = orderMapper.toEntity(request);
        order.setCreatedAt(new Date());
        order.setOrderStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);

        for (OrderDetailRequestDto detailDto : request.getOrderDetails()) {
            addProductToCart(order.getUser().getUserId(), detailDto.getBicycleId(), detailDto.getQuantity());
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
        order.setOrderStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
    }

    public void handleVNPayPayment(Order order) {
        order.setOrderStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
    }

    public void addProductToCart(Long userId, Long bicycleId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order cart = orderRepository.findByUserAndOrderStatus(user, OrderStatus.CART)
                .orElseGet(() -> createNewCart(user));

        Product product = productRepository.findById(bicycleId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (quantity > product.getQuantity()) {
            throw new RuntimeException("Insufficient stock for product");
        }

        Optional<OrderDetail> existingOrderDetail = cart.getOrderDetails().stream()
                .filter(od -> od.getProduct().getBicycleId().equals(bicycleId))
                .findFirst();

        if (existingOrderDetail.isPresent()) {
            OrderDetail orderDetail = existingOrderDetail.get();
            orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
            orderDetail.setPrice(calculatePrice(product, orderDetail.getQuantity()));
        } else {
            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setOrder(cart);
            newOrderDetail.setProduct(product);
            newOrderDetail.setQuantity(quantity);
            newOrderDetail.setPrice(calculatePrice(product, quantity));
            cart.getOrderDetails().add(newOrderDetail);
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        cart.calculateTotalAmount();
        orderRepository.save(cart);
    }

    public void updateOrderDetailQuantity(Long orderId, Long orderDetailId, int newQuantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDetail orderDetail = order.getOrderDetails().stream()
                .filter(od -> od.getOrderDetailId().equals(orderDetailId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order detail not found"));

        Product product = orderDetail.getProduct();

        // Kiểm tra nếu số lượng mới hợp lệ
        int quantityDifference = newQuantity - orderDetail.getQuantity();
        if (quantityDifference > 0 && quantityDifference > product.getQuantity()) {
            throw new RuntimeException("Insufficient stock for product");
        }

        // Cập nhật số lượng sản phẩm trong kho
        product.setQuantity(product.getQuantity() - quantityDifference);
        productRepository.save(product);

        // Cập nhật số lượng và giá trong chi tiết đơn hàng
        orderDetail.setQuantity(newQuantity);
        orderDetail.setPrice(calculatePrice(product, newQuantity));

        order.calculateTotalAmount();
        orderRepository.save(order);
    }

    private Order createNewCart(User user) {
        Order newCart = new Order();
        newCart.setUser(user);
        newCart.setOrderStatus(OrderStatus.CART);
        newCart.setOrderDetails(new ArrayList<>());
        return orderRepository.save(newCart);
    }

    private BigDecimal calculatePrice(Product product, int quantity) {
        return product.getOriginalPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
