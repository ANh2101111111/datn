package datn.example.datn.service;

import datn.example.datn.dto.request.OrderRequest;
import datn.example.datn.dto.response.OrderResponse;
import datn.example.datn.entity.*;
import datn.example.datn.mapper.OrderMapper;
import datn.example.datn.repository.CartDetailRepository;
import datn.example.datn.repository.CartRepository;
import datn.example.datn.repository.OrderRepository;

import datn.example.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
@Autowired
    private  UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository, CartDetailRepository cartDetailRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.cartRepository = cartRepository;
        this.cartDetailRepository =cartDetailRepository;
    }


    @Transactional
    public OrderResponse updateOrder(Long orderId, OrderRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.save(order);
        return orderMapper.toResponse(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getOrderStatus() == OrderStatus.PENDING) {
            orderRepository.delete(order);
        } else {
            throw new RuntimeException("Cannot delete order in current status: " + order.getOrderStatus());
        }
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getUserOrders(Long userId) {
        return orderRepository.findByUser_UserId(userId)
                .stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse createOrder(OrderRequest request) {
        Cart cart = cartRepository.findByUser_UserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getCartDetails().isEmpty()) {
            throw new RuntimeException("Cart is empty, cannot create order.");
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDetails(cart.getCartDetails().stream().map(detail -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(detail.getProduct());
            orderDetail.setQuantity(detail.getQuantity());
            orderDetail.setPrice(detail.getProduct().getOriginalPrice());
            return orderDetail;
        }).collect(Collectors.toList()));

        order.calculateTotalAmount(); // Tính tổng tiền
        orderRepository.save(order);

        // Sau khi tạo đơn hàng, xoá giỏ hàng
        cartDetailRepository.deleteAll(cart.getCartDetails());
        cartRepository.delete(cart);

        return orderMapper.toResponse(order);
    }


    @Transactional
    public OrderResponse updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getOrderStatus() == OrderStatus.COMPLETED || order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("Cannot update status of completed or cancelled orders");
        }
        if (!isValidStatusTransition(order.getOrderStatus(), newStatus)) {
            throw new RuntimeException("Invalid status transition from " + order.getOrderStatus() + " to " + newStatus);
        }
        order.setOrderStatus(newStatus);
        orderRepository.save(order);
        return orderMapper.toResponse(order);
    }
    private boolean isValidStatusTransition(OrderStatus current, OrderStatus next) {
        return switch (current) {
            case PENDING -> next == OrderStatus.CONFIRMED || next == OrderStatus.CANCELLED;
            case CONFIRMED -> next == OrderStatus.PAID || next == OrderStatus.CANCELLED;
            case PAID -> next == OrderStatus.COMPLETED;
            case CANCELLED, COMPLETED -> false;
        };
    }

    @Transactional
    public void processCOD(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(order);
    }

    @Transactional
    public void processVNPayPayment(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(order);
    }
}

