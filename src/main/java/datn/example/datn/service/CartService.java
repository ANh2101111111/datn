package datn.example.datn.service;

import datn.example.datn.dto.request.CartRequest;
import datn.example.datn.dto.request.CartDetailRequest;
import datn.example.datn.dto.response.CartResponse;
import datn.example.datn.entity.Cart;
import datn.example.datn.entity.CartDetail;
import datn.example.datn.entity.Product;
import datn.example.datn.entity.User;
import datn.example.datn.mapper.CartMapper;
import datn.example.datn.mapper.ProductMapper;
import datn.example.datn.repository.CartRepository;
import datn.example.datn.repository.CartDetailRepository;
import datn.example.datn.repository.ProductRepository;
import datn.example.datn.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    public CartService( ProductMapper productMapper,CartRepository cartRepository, CartDetailRepository cartDetailRepository,
                       ProductRepository productRepository, UserRepository userRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartMapper = cartMapper;
        this.productMapper = productMapper;
    }

    public CartResponse getCartByUser(Long userId) {
        return cartRepository.findByUser_UserId(userId)
                .map(cartMapper::toResponse)
                .orElse(null);
    }
//    @Transactional
//    public CartResponse updateCartDetail(Long userId, CartDetailRequest request) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Cart cart = cartRepository.findByUser_UserId(userId)
//                .orElseThrow(() -> new RuntimeException("Cart not found"));
//
//        Product product = productRepository.findByBicycleIdAndIsDeletedFalse(request.getBicycleId())
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        CartDetail detail = cartDetailRepository.findByCartAndProduct(cart, product)
//                .orElseThrow(() -> new RuntimeException("Product not found in cart"));
//
//        if (request.getQuantity() <= 0) {
//            cartDetailRepository.delete(detail);
//        } else {
//            detail.setQuantity(request.getQuantity());
//            cartDetailRepository.save(detail);
//        }
//
//        cart.calculateTotalAmount(); // Cập nhật lại tổng giá trị giỏ hàng
//        cartRepository.save(cart);
//
//        return cartMapper.toResponse(cart);
//    }

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
            cartDetailRepository.delete(detail);  // Xóa sản phẩm khi số lượng <= 0
        } else {
            detail.setQuantity(request.getQuantity());  // Cập nhật số lượng
            detail.setPrice(product.getOriginalPrice().multiply(BigDecimal.valueOf(request.getQuantity())));  // Cập nhật giá trị tổng
            cartDetailRepository.save(detail);  // Lưu lại thông tin giỏ hàng
        }

        // Cập nhật tổng giá trị giỏ hàng
        cart.calculateTotalAmount();
        cartRepository.save(cart);  // Lưu lại giỏ hàng sau khi tính toán lại tổng

        return cartMapper.toResponse(cart);  // Trả về thông tin giỏ hàng
    }


//    @Transactional
//    public CartResponse addToCart(Long userId, CartRequest request) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Cart cart = cartRepository.findByUser_UserId(userId).orElseGet(() -> {
//            Cart newCart = new Cart();
//            newCart.setUser(user);
//            newCart.setTotalAmount(BigDecimal.ZERO);
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
//                        newDetail.setQuantity(0);
//                        return newDetail;
//                    });
//
//            // Đảm bảo price luôn có giá trị
//            detail.setPrice(productMapper.calculateDiscountedPrice(product));
//            detail.setQuantity(detail.getQuantity() + item.getQuantity());
//            cartDetailRepository.save(detail);
//        }
//
//        cart.calculateTotalAmount();
//        cartRepository.save(cart);
//
//        return cartMapper.toResponse(cart);
//    }

    @Transactional
    public CartResponse addToCart(Long userId, CartRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Tìm giỏ hàng của người dùng, nếu không có thì tạo mới
        Cart cart = cartRepository.findByUser_UserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setTotalAmount(BigDecimal.ZERO);
            return cartRepository.save(newCart);
        });

        for (CartDetailRequest item : request.getCartDetails()) {
            // Tìm sản phẩm trong cơ sở dữ liệu
            Product product = productRepository.findByBicycleIdAndIsDeletedFalse(item.getBicycleId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Tìm CartDetail tương ứng với sản phẩm trong giỏ
            CartDetail detail = cartDetailRepository.findByCartAndProduct(cart, product)
                    .orElseGet(() -> {
                        // Nếu chưa có CartDetail, tạo mới và lưu giá sản phẩm lần đầu
                        CartDetail newDetail = new CartDetail();
                        newDetail.setCart(cart);
                        newDetail.setProduct(product);
                        newDetail.setQuantity(0);
                        newDetail.setPrice(productMapper.calculateDiscountedPrice(product)); // Lưu giá sản phẩm lần đầu
                        return newDetail;
                    });

            // Cập nhật số lượng sản phẩm trong giỏ
            detail.setQuantity(detail.getQuantity() + item.getQuantity());
            cartDetailRepository.save(detail);
        }

        // Tính lại tổng giá trị giỏ hàng sau khi thay đổi số lượng
        cart.calculateTotalAmount();
        cartRepository.save(cart);

        return cartMapper.toResponse(cart);
    }

//    @Transactional
//    public void clearCart(Long userId) {
//        cartRepository.findByUser_UserId(userId).ifPresent(cart -> {
//            cartDetailRepository.deleteAll(cart.getCartDetails());
//            cartRepository.delete(cart);
//        });
//    }

    @Transactional
    public boolean removeItemFromCart(Long userId, Long cartDetailId) {
        return cartDetailRepository.deleteByCartDetailIdAndUserId(cartDetailId, userId) > 0;
    }

}