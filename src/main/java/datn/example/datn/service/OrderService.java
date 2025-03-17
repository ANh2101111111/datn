package datn.example.datn.service;

import datn.example.datn.config.VNPayConfig;
import datn.example.datn.dto.request.OrderRequest;
import datn.example.datn.dto.response.OrderResponse;
import datn.example.datn.entity.Order;
import datn.example.datn.entity.OrderStatus;
import datn.example.datn.mapper.OrderMapper;
import datn.example.datn.repository.OrderRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderResponse createOrder(OrderRequest request) {
        Order order = OrderMapper.toEntity(request);
        orderRepository.save(order);
        return OrderMapper.toResponse(order);
    }

    public OrderResponse updateOrder(Long orderId, OrderRequest request) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        // Thay đổi thông tin order
        orderRepository.save(order);
        return OrderMapper.toResponse(order);
    }
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapper::toResponse).collect(Collectors.toList());
    }

    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getOrderStatus() == OrderStatus.PENDING) {
            orderRepository.delete(order);
        } else {
            throw new RuntimeException("Cannot delete order in current status");
        }
    }

    public List<OrderResponse> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUser_UserId(userId);
        return orders.stream().map(OrderMapper::toResponse).collect(Collectors.toList());
    }
    public void processCOD(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        // Cập nhật trạng thái đơn hàng về PENDING
        order.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(order);

        // Thực hiện thêm các công việc cần thiết khác cho thanh toán COD ở đây
    }

}
