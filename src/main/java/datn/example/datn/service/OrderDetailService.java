package datn.example.datn.service;

import datn.example.datn.dto.request.OrderDetailRequest;
import datn.example.datn.entity.Order;
import datn.example.datn.entity.OrderDetail;
import datn.example.datn.entity.Product;
import datn.example.datn.entity.OrderStatus;
import datn.example.datn.mapper.OrderDetailMapper;
import datn.example.datn.repository.OrderDetailRepository;
import datn.example.datn.repository.OrderRepository;
import datn.example.datn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Transactional
    public void addProductToOrder(Long orderId, OrderDetailRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Product product = productRepository.findById(request.getBicycleId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        OrderDetail orderDetail = orderDetailMapper.toEntity(request, order, product);
        orderDetailRepository.save(orderDetail);
    }

    @Transactional
    public void updateProductQuantity(Long orderDetailId, int quantity) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new RuntimeException("Order detail not found"));

        orderDetail.setQuantity(quantity);
        orderDetailRepository.save(orderDetail);
    }

    @Transactional
    public void removeProductFromOrder(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new RuntimeException("Order detail not found"));

        if (orderDetail.getOrder().getOrderStatus() == OrderStatus.PENDING) {
            orderDetailRepository.delete(orderDetail);
        } else {
            throw new RuntimeException("Cannot remove product from order in current status");
        }
    }
}
