package datn.example.datn.mapper;

import datn.example.datn.dto.request.OrderDetailRequest;
import datn.example.datn.dto.response.OrderDetailResponse;
import datn.example.datn.entity.Order;
import datn.example.datn.entity.OrderDetail;
import datn.example.datn.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderDetailMapper {

    private final ProductMapper productMapper;

    @Autowired
    public OrderDetailMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public OrderDetail toEntity(OrderDetailRequest request, Order order, Product product) {
        OrderDetail detail = new OrderDetail();
        detail.setOrder(order);
        detail.setProduct(product); // Gán product thay vì name

        // Lấy giá đã giảm thay vì giá gốc
        BigDecimal discountedPrice = productMapper.calculateDiscountedPrice(product);
        detail.setPrice(discountedPrice);

        detail.setQuantity(request.getQuantity());
        return detail;
    }

    public OrderDetailResponse toResponse(OrderDetail orderDetail) {
        OrderDetailResponse response = new OrderDetailResponse();
        response.setOrderDetailId(orderDetail.getId());
        response.setOrderId(orderDetail.getOrder().getOrderId());
        response.setProductName(orderDetail.getProduct().getName()); // Lấy name từ product

        // Lấy giá đã giảm
        response.setPrice(orderDetail.getPrice());

        response.setQuantity(orderDetail.getQuantity());
        response.setImage(orderDetail.getProduct().getImage());
        return response;
    }
}
