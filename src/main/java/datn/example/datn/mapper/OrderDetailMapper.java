package datn.example.datn.mapper;

import datn.example.datn.dto.request.OrderDetailRequest;
import datn.example.datn.dto.response.OrderDetailResponse;
import datn.example.datn.entity.OrderDetail;

public class OrderDetailMapper {
    public static OrderDetail toEntity(OrderDetailRequest request) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductName(request.getProductName());
        orderDetail.setPrice(request.getPrice());
        orderDetail.setQuantity(request.getQuantity());
        return orderDetail;
    }

    public static OrderDetailResponse toResponse(OrderDetail orderDetail) {
        OrderDetailResponse response = new OrderDetailResponse();
        response.setProductName(orderDetail.getProductName());
        response.setPrice(orderDetail.getPrice());
        response.setQuantity(orderDetail.getQuantity());
        return response;
    }
}