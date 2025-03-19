package datn.example.datn.mapper;

import datn.example.datn.dto.request.CartItemRequestDTO;
import datn.example.datn.dto.request.CartRequestDTO;
import datn.example.datn.dto.response.CartItemResponseDTO;
import datn.example.datn.dto.response.CartResponseDTO;
import datn.example.datn.entity.Cart;
import datn.example.datn.entity.CartItem;
import datn.example.datn.entity.Product;
import datn.example.datn.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public Cart toEntity(CartRequestDTO request, User user, List<CartItem> items) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setItems(items);
        return cart;
    }

    public CartResponseDTO toCartResponseDTO(Cart cart) {
        CartResponseDTO dto = new CartResponseDTO();
        dto.setCartId(cart.getCartId());
        dto.setUserId(cart.getUser().getUserId());

        List<CartItemResponseDTO> items = cart.getItems().stream()
                .map(this::toCartItemResponseDTO)
                .collect(Collectors.toList());
        dto.setItems(items);
        dto.setTotalPrice(calculateTotalPrice(cart.getItems()));

        return dto;
    }

    public CartItem toCartItemEntity(CartItemRequestDTO request, Product product, Cart cart) {
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setCart(cart);
        item.setQuantity(request.getQuantity());
        item.setUnitPrice(product.getOriginalPrice());
        return item;
    }

    public CartItemResponseDTO toCartItemResponseDTO(CartItem item) {
        CartItemResponseDTO dto = new CartItemResponseDTO();
        dto.setCartItemId(item.getCartItemId());
        dto.setProductId(item.getProduct().getBicycleId());
        dto.setProductName(item.getProduct().getName());
        dto.setImage(item.getProduct().getImage());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setQuantity(item.getQuantity());
        dto.setTotalPrice(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        return dto;
    }

    private BigDecimal calculateTotalPrice(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}


//@Mapper(componentModel = "spring")
//public interface CartMapper {
//
//    // Chuyển từ Entity -> DTO
//    @Mapping(source = "cart.cartId", target = "cartId")
//    @Mapping(source = "cart.user.userId", target = "userId")
//    @Mapping(source = "cart.items", target = "items")
//    @Mapping(target = "totalPrice", expression = "java(calculateTotalPrice(cart.getItems()))")
//    CartResponseDTO toCartResponseDTO(Cart cart);
//
//    // Chuyển từ Entity -> DTO (CartItem)
//    @Mapping(source = "cartItem.cartItemId", target = "cartItemId")
//    @Mapping(source = "cartItem.product.bicycleId", target = "productId")
//    @Mapping(source = "cartItem.product.name", target = "productName")
//    @Mapping(source = "cartItem.product.image", target = "image")
//    @Mapping(source = "cartItem.unitPrice", target = "unitPrice")
//    @Mapping(source = "cartItem.quantity", target = "quantity")
//    @Mapping(target = "totalPrice", expression = "java(cartItem.getUnitPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))")
//    CartItemResponseDTO toCartItemResponseDTO(CartItem cartItem);
//
//    List<CartItemResponseDTO> toCartItemResponseDTOs(List<CartItem> cartItems);
//
//    // Chuyển từ DTO -> Entity (Cart)
//    @Mapping(source = "request.userId", target = "user.userId")
//    @Mapping(target = "cartId", ignore = true) // ID tự động tạo, không map từ request
//    @Mapping(target = "items", ignore = true)  // Items được set từ service, không map trực tiếp
//    Cart toCartEntity(CartRequestDTO request);
//
//    Cart toCartEntity(CartRequestDTO request, List<CartItem> items);
//
//    // Chuyển từ DTO -> Entity (CartItem)
//    @Mapping(source = "request.productId", target = "product.bicycleId")
//    @Mapping(source = "request.quantity", target = "quantity")
//    @Mapping(target = "cartItemId", ignore = true) // ID tự động tạo
//    @Mapping(target = "cart", ignore = true) // Cart sẽ được set từ service
//    CartItem toCartItemEntity(CartItemRequestDTO request);
//
//    List<CartItem> toCartItemEntities(List<CartItemRequestDTO> requestItems);
//
//    // 🛠 Hàm tính tổng giá tiền của giỏ hàng
//    default BigDecimal calculateTotalPrice(List<CartItem> cartItems) {
//        if (cartItems == null || cartItems.isEmpty()) {
//            return BigDecimal.ZERO;
//        }
//        return cartItems.stream()
//                .filter(item -> item.getUnitPrice() != null)
//                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
//}
