package datn.example.datn.service;

import datn.example.datn.dto.response.ChatbotResponseDto;
import datn.example.datn.dto.response.ProductResponseDto;
import datn.example.datn.entity.Category;
import datn.example.datn.entity.Product;
import datn.example.datn.mapper.ProductMapper;
import datn.example.datn.repository.CategoryRepository;
import datn.example.datn.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ChatbotService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ChatbotService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public ChatbotResponseDto getChatbotResponse(String message) {
        // Trích xuất thông tin tìm kiếm từ tin nhắn
        String name = extractKeyword(message, "(?i)(tên|name)\\s*[:=]?\\s*([^,]+)");
        String type = extractKeyword(message, "(?i)(loại|type)\\s*[:=]?\\s*([^,]+)");
        String categoryName = extractKeyword(message, "(?i)(danh mục|category)\\s*[:=]?\\s*([^,]+)");
        BigDecimal minPrice = extractPrice(message, "(?i)(từ|min)\\s*(\\d+)");
        BigDecimal maxPrice = extractPrice(message, "(?i)(đến|max)\\s*(\\d+)");

        // Kiểm tra dữ liệu đã trích xuất
        System.out.println("🔍 Từ khóa tìm kiếm:");
        System.out.println("📌 Name: '" + name + "'");
        System.out.println("📌 Type: '" + type + "'");
        System.out.println("📌 Category: '" + categoryName + "'");
        System.out.println("📌 MinPrice: " + minPrice);
        System.out.println("📌 MaxPrice: " + maxPrice);

        // Lấy categoryId nếu có categoryName
        Long categoryId = null;
        if (categoryName != null && !categoryName.isBlank()) {
            Optional<Category> categoryOptional = categoryRepository.findCategoryByName(categoryName.trim());
            if (categoryOptional.isPresent()) {
                categoryId = categoryOptional.get().getCategoryId();
            } else {
                System.out.println("⚠️ Không tìm thấy category có tên: " + categoryName);
            }
        }

        // Nếu minPrice > maxPrice, đổi lại
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            BigDecimal temp = minPrice;
            minPrice = maxPrice;
            maxPrice = temp;
        }

        // Kiểm tra lại trước khi gọi repo
        System.out.println("📌 Thực hiện tìm kiếm với:");
        System.out.println("   - Name: " + (name != null ? "'" + name + "'" : "NULL"));
        System.out.println("   - Type: " + (type != null ? "'" + type + "'" : "NULL"));
        System.out.println("   - Category ID: " + (categoryId != null ? categoryId : "NULL"));
        System.out.println("   - MinPrice: " + (minPrice != null ? minPrice : "NULL"));
        System.out.println("   - MaxPrice: " + (maxPrice != null ? maxPrice : "NULL"));

        // Tìm kiếm sản phẩm với tiêu chí đã lấy được
        List<Product> products = productRepository.searchProducts(
                (name != null && !name.isBlank()) ? name.trim() : null,
                (type != null && !type.isBlank()) ? type.trim() : null,
                categoryId,
                minPrice,
                maxPrice
        );

        // Nếu không tìm thấy sản phẩm
        if (products.isEmpty()) {
            return new ChatbotResponseDto(
                    List.of("Không tìm thấy sản phẩm nào phù hợp."),
                    List.of(),
                    List.of(),
                    List.of(),
                    List.of()
            );
        }

        // Chuyển đổi danh sách sản phẩm thành DTO
        List<ProductResponseDto> productDtos = products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());

        return new ChatbotResponseDto(
                productDtos.stream().map(ProductResponseDto::getName).collect(Collectors.toList()),
                productDtos.stream().map(ProductResponseDto::getDescription).collect(Collectors.toList()),
                productDtos.stream().map(ProductResponseDto::getImage).collect(Collectors.toList()),
                productDtos.stream().map(ProductResponseDto::getOriginalPrice).collect(Collectors.toList()),
                productDtos.stream().map(ProductResponseDto::getDiscountedPrice).collect(Collectors.toList())
        );
    }

    private String extractKeyword(String message, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(message);
        return matcher.find() ? matcher.group(2).trim() : null;
    }

    private BigDecimal extractPrice(String message, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        return matcher.find() ? new BigDecimal(matcher.group(2)) : null;
    }
}
