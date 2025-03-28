package datn.example.datn.service;

import datn.example.datn.dto.request.CartDetailRequest;
import datn.example.datn.dto.response.CartResponse;
import datn.example.datn.entity.Cart;
import datn.example.datn.entity.CartDetail;
import datn.example.datn.entity.Product;
import datn.example.datn.repository.CartRepository;
import datn.example.datn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void addProductToCart(Long userId, CartDetailRequest request) {
        Cart cart = cartRepository.findByUser_UserId(userId);
        if (cart == null) {
            cart = new Cart();
            // Set user and save cart
        }

        Product product = productRepository.findById(request.getBicycleId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setQuantity(request.getQuantity());

        cart.getCartDetails().add(cartDetail);
        cartRepository.save(cart);
    }

    public CartResponse updateCart(Long userId, List<CartDetailRequest> cartDetails) {
        Cart cart = cartRepository.findByUser_UserId(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found for user ID: " + userId);
        }

        cart.getCartDetails().clear(); // Xóa tất cả các chi tiết giỏ hàng hiện tại

        for (CartDetailRequest detail : cartDetails) {
            Product product = productRepository.findById(detail.getBicycleId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + detail.getBicycleId()));

            if (product.getStock() < detail.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setQuantity(detail.getQuantity());
            cart.getCartDetails().add(cartDetail);
        }

        cartRepository.save(cart); // Lưu giỏ hàng đã cập nhật
        return null;
    }

    @Transactional
    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUser_UserId(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found for user ID: " + userId);
        }

        cart.getCartDetails().removeIf(detail -> detail.getProduct().getBicycleId().equals(productId));
        cartRepository.save(cart); // Lưu giỏ hàng sau khi xóa sản phẩm
    }

    @Transactional(readOnly = true)
    public CartResponse getCart(Long userId) {
        Cart cart = cartRepository.findByUser_UserId(userId);
        // Convert cart to CartResponse and return
        return new CartResponse(); // Placeholder
    }
}