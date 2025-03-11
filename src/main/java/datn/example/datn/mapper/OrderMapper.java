package datn.example.datn.mapper;

import datn.example.datn.dto.request.OrderRequestDto;
import datn.example.datn.dto.response.OrderDetailResponseDto;
import datn.example.datn.dto.response.OrderResponseDto;
import datn.example.datn.entity.Order;
import datn.example.datn.entity.OrderDetail;
import datn.example.datn.entity.OrderStatus;
import datn.example.datn.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order toEntity(OrderRequestDto dto) {
        Order order = new Order();
        User user = new User();
        user.setUserId(dto.getUserId());
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDING); // ✅ Sử dụng enum đúng cách
        return order;
    }

    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderId(order.getOrderId());
        dto.setUserId(order.getUser().getUserId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getOrderStatus()); // ✅ Chuyển enum thành String
        dto.setCreatedAt(order.getCreatedAt());
        List<OrderDetailResponseDto> orderDetails = order.getOrderDetails().stream()
                .map(this::toOrderDetailResponseDto)
                .collect(Collectors.toList());
        dto.setOrderDetails(orderDetails);
        return dto;
    }

    private OrderDetailResponseDto toOrderDetailResponseDto(OrderDetail orderDetail) {
        OrderDetailResponseDto dto = new OrderDetailResponseDto();
        dto.setOrderDetailId(orderDetail.getOrderDetailId());
        dto.setBicycleId(orderDetail.getProduct().getBicycleId());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setPrice(orderDetail.getPrice());
        return dto;
    }
}
