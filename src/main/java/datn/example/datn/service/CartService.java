package datn.example.datn.service;

import datn.example.datn.dto.request.CartItemRequestDTO;
import datn.example.datn.dto.request.CartRequestDTO;
import datn.example.datn.dto.response.CartResponseDTO;
import datn.example.datn.entity.Cart;
import datn.example.datn.entity.CartItem;
import datn.example.datn.entity.Product;
import datn.example.datn.entity.User;
import datn.example.datn.mapper.CartMapper;
import datn.example.datn.repository.CartItemRepository;
import datn.example.datn.repository.CartRepository;
import datn.example.datn.repository.ProductRepository;
import datn.example.datn.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartMapper cartMapper;

    /**
     * Lấy giỏ hàng của user
     */
    public CartResponseDTO getCart(Long userId) {
        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseGet(() -> createNewCart(userId));
        return cartMapper.toCartResponseDTO(cart);
    }

    public CartResponseDTO addToCart(CartRequestDTO cartRequestDTO) {
        Long userId = cartRequestDTO.getUserId();

        // 🔥 Tìm User từ database trước khi set vào Cart
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        // Kiểm tra giỏ hàng của user
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user); // ✅ Gán trực tiếp User, không phải ID
            newCart.setItems(new ArrayList<>()); // 🔥 Tránh lỗi NullPointerException
            return cartRepository.save(newCart);
        });

        // Thêm sản phẩm vào giỏ hàng
        for (CartItemRequestDTO itemDTO : cartRequestDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(itemDTO.getQuantity());
//            if (product.getOriginalPrice() == null) {
//                throw new RuntimeException("Sản phẩm không có giá.");
//            }
            cartItem.setUnitPrice(product.getOriginalPrice());
            cartItem.setCart(cart);
            cart.getItems().add(cartItem);
        }

        // ✅ Lưu cart sau khi thêm sản phẩm
        cartRepository.save(cart);

        return cartMapper.toCartResponseDTO(cart);
    }


    /**
     * Cập nhật số lượng sản phẩm trong giỏ hàng
     */
    public CartResponseDTO updateCart(Long userId, CartRequestDTO cartRequestDTO) {
        // 🔥 Tìm User trước khi set vào Cart
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        // 🔥 Tìm giỏ hàng của User
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));

        // 🔥 Duyệt qua danh sách sản phẩm trong DTO để cập nhật
        for (CartItemRequestDTO itemDTO : cartRequestDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

            CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                    .orElseGet(() -> {
                        CartItem newItem = new CartItem();
                        newItem.setCart(cart);
                        newItem.setProduct(product);
                        return newItem;
                    });

            cartItem.setQuantity(itemDTO.getQuantity());
            cartItemRepository.save(cartItem);
        }

        return cartMapper.toCartResponseDTO(cart);
    }


    /**
     * Xóa một sản phẩm khỏi giỏ hàng
     */
    public CartResponseDTO removeItemFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getBicycleId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sản phẩm không có trong giỏ hàng"));

        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        return cartMapper.toCartResponseDTO(cart);
    }

    /**
     * Xóa toàn bộ giỏ hàng
     */
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));

        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    /**
     * Tạo giỏ hàng mới cho user nếu chưa có
     */
    private Cart createNewCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setItems(new ArrayList<>());
        return cartRepository.save(cart);
    }
}
