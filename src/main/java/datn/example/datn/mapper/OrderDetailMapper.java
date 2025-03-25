package datn.example.datn.mapper;

import datn.example.datn.dto.request.OrderDetailRequest;
import datn.example.datn.dto.response.OrderDetailResponse;
import datn.example.datn.entity.Order;
import datn.example.datn.entity.OrderDetail;
import datn.example.datn.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailMapper {
    public OrderDetail toEntity(OrderDetailRequest request, Order order, Product product) {
        OrderDetail detail = new OrderDetail();
        detail.setOrder(order);
        detail.setProduct(product); // Gán product thay vì name
        detail.setPrice(product.getOriginalPrice());
        detail.setQuantity(request.getQuantity());
        return detail;
    }

    public OrderDetailResponse toResponse(OrderDetail orderDetail) {
        OrderDetailResponse response = new OrderDetailResponse();
        response.setOrderDetailId(orderDetail.getId());
        response.setOrderId(orderDetail.getOrder().getOrderId());
        response.setProductName(orderDetail.getProduct().getName()); // Lấy name từ product
        response.setPrice(orderDetail.getPrice());
        response.setQuantity(orderDetail.getQuantity());
        response.setImage(orderDetail.getProduct().getImage());
        return response;
    }
}
