package datn.example.datn.mapper;

import datn.example.datn.dto.request.OrderRequest;
import datn.example.datn.dto.response.OrderResponse;
import datn.example.datn.entity.Order;
import datn.example.datn.entity.PaymentMethod;
import datn.example.datn.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final OrderDetailMapper orderDetailMapper;

    public OrderMapper(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

    public Order toEntity(OrderRequest request, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setFullName(request.getFullName());       // Ánh xạ fullName
        order.setPhoneNumber(request.getPhoneNumber()); // Ánh xạ phoneNumber
        order.setEmail(request.getEmail());             // Ánh xạ email
        order.setAddress(request.getAddress());         // Ánh xạ address
        order.setNote(request.getNote());               // Ánh xạ note
        order.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod())); // Ánh xạ paymentMethod
        order.setOrderDetails(null);                     // Để xử lý ở OrderDetailMapper
        return order;
    }

    public OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setUserId(order.getUser().getUserId());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());
        response.setCancel(order.getCancel());
        response.setOrderStatus(order.getOrderStatus().name());
        response.setOrderDetails(order.getOrderDetails().stream()
                .map(orderDetailMapper::toResponse)
                .collect(Collectors.toList()));
        response.setFullName(order.getFullName());                // Ánh xạ fullName
        response.setPhoneNumber(order.getPhoneNumber());          // Ánh xạ phoneNumber
        response.setEmail(order.getEmail());                      // Ánh xạ email
        response.setAddress(order.getAddress());                  // Ánh xạ address
        response.setNote(order.getNote());                        // Ánh xạ note
        response.setPaymentMethod(order.getPaymentMethod().name()); // Ánh xạ paymentMethod
        return response;
    }
}