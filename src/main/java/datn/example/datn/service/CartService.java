package datn.example.datn.service;

import datn.example.datn.dto.request.CartRequest;
import datn.example.datn.dto.request.CartDetailRequest;
import datn.example.datn.dto.response.CartResponse;
import datn.example.datn.entity.Cart;
import datn.example.datn.entity.CartDetail;
import datn.example.datn.entity.Product;
import datn.example.datn.entity.User;
import datn.example.datn.mapper.CartMapper;
import datn.example.datn.repository.CartRepository;
import datn.example.datn.repository.CartDetailRepository;
import datn.example.datn.repository.ProductRepository;
import datn.example.datn.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository, CartDetailRepository cartDetailRepository,
                       ProductRepository productRepository, UserRepository userRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartMapper = cartMapper;
    }

    public CartResponse getCartByUser(Long userId) {
        return cartRepository.findByUser_UserId(userId)
                .map(cartMapper::toResponse)
                .orElse(null);
    }
    @Transactional
    public CartResponse updateCartDetail(Long userId, CartDetailRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productRepository.findByBicycleIdAndIsDeletedFalse(request.getBicycleId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartDetail detail = cartDetailRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        if (request.getQuantity() <= 0) {
            cartDetailRepository.delete(detail);
        } else {
            detail.setQuantity(request.getQuantity());
            cartDetailRepository.save(detail);
        }

        return cartMapper.toResponse(cart);
    }

//    @Transactional
//    public CartResponse addToCart(Long userId, CartRequest request) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Cart cart = cartRepository.findByUser_UserId(userId).orElseGet(() -> {
//            Cart newCart = new Cart();
//            newCart.setUser(user);
//            return cartRepository.save(newCart);
//        });
//
//        for (CartDetailRequest item : request.getCartDetails()) {
//            Product product = productRepository.findByBicycleIdAndIsDeletedFalse(item.getBicycleId())
//                    .orElseThrow(() -> new RuntimeException("Product not found"));
//
//            CartDetail detail = cartDetailRepository.findByCartAndProduct(cart, product)
//                    .orElseGet(() -> {
//                        CartDetail newDetail = new CartDetail();
//                        newDetail.setCart(cart);
//                        newDetail.setProduct(product);
//                        newDetail.setQuantity(0); // Khởi tạo số lượng ban đầu
//                        return newDetail;
//                    });
//
//            detail.setQuantity(detail.getQuantity() + item.getQuantity());
//            cartDetailRepository.save(detail);
//
//        }
//
//        return cartMapper.toResponse(cart);
//    }

    @Transactional
    public CartResponse addToCart(Long userId, CartRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser_UserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        for (CartDetailRequest item : request.getCartDetails()) {
            Product product = productRepository.findByBicycleIdAndIsDeletedFalse(item.getBicycleId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            CartDetail detail = cartDetailRepository.findByCartAndProduct(cart, product)
                    .orElseGet(() -> {
                        CartDetail newDetail = new CartDetail();
                        newDetail.setCart(cart);
                        newDetail.setProduct(product);
                        newDetail.setQuantity(0); // Khởi tạo số lượng ban đầu
                        return newDetail;
                    });

            detail.setQuantity(detail.getQuantity() + item.getQuantity());
            cartDetailRepository.save(detail);
        }

        // Trả về giỏ hàng với cartDetails
        return cartMapper.toResponse(cart); // Đảm bảo cartMapper đã bao gồm cartDetails
    }

    @Transactional
    public void clearCart(Long userId) {
        cartRepository.findByUser_UserId(userId).ifPresent(cart -> {
            cartDetailRepository.deleteAll(cart.getCartDetails());
            cartRepository.delete(cart);
        });
    }
}