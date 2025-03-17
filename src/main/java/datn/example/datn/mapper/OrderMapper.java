package datn.example.datn.mapper;

import datn.example.datn.dto.request.OrderRequest;
import datn.example.datn.dto.response.OrderDetailResponse;
import datn.example.datn.dto.response.OrderResponse;
import datn.example.datn.entity.Order;
import datn.example.datn.entity.OrderDetail;
import datn.example.datn.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static Order toEntity(OrderRequest request) {
        Order order = new Order();
        // Giả định rằng bạn có một phương thức để tìm User theo ID
        User user = new User();
        user.setUserId(request.getUserId()); // Thay đổi để khởi tạo User
        order.setUser(user);
        order.setOrderDetails(request.getOrderDetails().stream()
                .map(detail -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProductName(detail.getProductName());
                    orderDetail.setPrice(detail.getPrice());
                    orderDetail.setQuantity(detail.getQuantity());
                    return orderDetail;
                })
                .collect(Collectors.toList()));
        return order;
    }

    public static OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setUserId(order.getUser().getUserId());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());
        response.setOrderStatus(order.getOrderStatus().name());
        response.setOrderDetails(order.getOrderDetails().stream()
                .map(detail -> {
                    OrderDetailResponse detailResponse = new OrderDetailResponse();
                    detailResponse.setProductName(detail.getProductName());
                    detailResponse.setPrice(detail.getPrice());
                    detailResponse.setQuantity(detail.getQuantity());
                    return detailResponse;
                })
                .collect(Collectors.toList()));
        return response;
    }
}