package datn.example.datn.service;

import datn.example.datn.dto.request.OrderDetailRequest;
import datn.example.datn.entity.OrderDetail;
import datn.example.datn.entity.OrderStatus;
import datn.example.datn.mapper.OrderDetailMapper;
import datn.example.datn.repository.OrderDetailRepository;
import datn.example.datn.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void addProductToOrder(Long orderId, OrderDetailRequest request) {
        OrderDetail orderDetail = OrderDetailMapper.toEntity(request);
        orderDetail.setOrder(orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found")));
        orderDetailRepository.save(orderDetail);
    }

    public void updateProductQuantity(Long orderDetailId, int quantity) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(() -> new RuntimeException("Order detail not found"));
        orderDetail.setQuantity(quantity);
        orderDetailRepository.save(orderDetail);
    }

    public void removeProductFromOrder(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(() -> new RuntimeException("Order detail not found"));
        if (orderDetail.getOrder().getOrderStatus() == OrderStatus.PENDING) {
            orderDetailRepository.delete(orderDetail);
        } else {
            throw new RuntimeException("Cannot remove product from order in current status");
        }
    }
}