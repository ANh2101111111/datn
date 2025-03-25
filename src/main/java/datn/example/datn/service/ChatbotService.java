package datn.example.datn.service;

import datn.example.datn.dto.response.ChatbotResponseDto;
import datn.example.datn.dto.response.ProductResponseDto;
import datn.example.datn.entity.Category;
import datn.example.datn.entity.Product;
import datn.example.datn.mapper.ProductMapper;
import datn.example.datn.repository.CategoryRepository;
import datn.example.datn.repository.ProductRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Locale;
import java.util.Optional;


@Service
public class ChatbotService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final MessageSource messageSource;

    public ChatbotService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper, MessageSource messageSource) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.messageSource = messageSource;
    }

    public ChatbotResponseDto getChatbotResponse(String message, String lang) {
        Locale locale = getLocale(lang);
        String notFoundMessage = messageSource.getMessage("chatbot.response.not_found", null, locale);
        if (message.toLowerCase().contains(
                "highest price")) {
            return getHighestPricedProduct(locale, notFoundMessage);
        } else if (message.toLowerCase().contains("lowest price")) {
            return getLowestPricedProduct(locale, notFoundMessage);
        }

        // Phân tích các từ khóa khác
        String name = extractKeyword(message, "(?i)(information)\\s*[:=]?\\s*([^,]+)");
        String type = extractKeyword(message, "(?i)(type)\\s*[:=]?\\s*([^,]+)");
        String categoryName = extractKeyword(message, "(?i)(category)\\s*[:=]?\\s*([^,]+)");
        BigDecimal minPrice = extractPrice(message, "(?i)(from|min)\\s*(\\d+)");
        BigDecimal maxPrice = extractPrice(message, "(?i)(to|max)\\s*(\\d+)");

        Long categoryId = null;
        if (categoryName != null && !categoryName.isBlank()) {
            Optional<Category> categoryOptional = categoryRepository.findCategoryByName(categoryName.trim());
            categoryId = categoryOptional.map(Category::getCategoryId).orElse(null);
        }

        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            BigDecimal temp = minPrice;
            minPrice = maxPrice;
            maxPrice = temp;
        }

        List<Product> products = productRepository.searchProducts(
                (name != null && !name.isBlank()) ? name.trim() : null,
                (type != null && !type.isBlank()) ? type.trim() : null,
                categoryId,
                minPrice,
                maxPrice
        );
            if (name == null && type == null && categoryName == null && minPrice == null && maxPrice == null) {
                return null;
            }

        List<ProductResponseDto> productDtos = products.stream().map(productMapper::toDto).collect(Collectors.toList());

        return new ChatbotResponseDto(
                productDtos.stream().map(ProductResponseDto::getBicycleId).collect(Collectors.toList()),
                productDtos.stream().map(ProductResponseDto::getName).collect(Collectors.toList()),
                productDtos.stream().map(ProductResponseDto::getDescription).collect(Collectors.toList()),
                productDtos.stream().map(ProductResponseDto::getImage).collect(Collectors.toList()),
                productDtos.stream().map(ProductResponseDto::getOriginalPrice).collect(Collectors.toList()),
                productDtos.stream().map(ProductResponseDto::getDiscountedPrice).collect(Collectors.toList())
        );
    }

    private ChatbotResponseDto getHighestPricedProduct(Locale locale, String notFoundMessage) {
        Optional<Product> highestPricedProduct = productRepository.findTopByOrderByOriginalPriceDesc();
        if (highestPricedProduct.isPresent()) {
            ProductResponseDto dto = productMapper.toDto(highestPricedProduct.get());
            return new ChatbotResponseDto(
                    List.of(dto.getBicycleId()),
                    List.of(dto.getName()),
                    List.of(dto.getDescription()),
                    List.of(dto.getImage()),
                    List.of(dto.getOriginalPrice()),
                    List.of(dto.getDiscountedPrice())
            );
        }
        return null;
    }

    private ChatbotResponseDto getLowestPricedProduct(Locale locale, String notFoundMessage) {
        Optional<Product> lowestPricedProduct = productRepository.findTopByOrderByOriginalPriceAsc();
        if (lowestPricedProduct.isPresent()) {
            ProductResponseDto dto = productMapper.toDto(lowestPricedProduct.get());
            return new ChatbotResponseDto(
                    List.of(dto.getBicycleId()),
                    List.of(dto.getName()),
                    List.of(dto.getDescription()),
                    List.of(dto.getImage()),
                    List.of(dto.getOriginalPrice()),
                    List.of(dto.getDiscountedPrice())
            );
        }
        return null;
    }

    private Locale getLocale(String lang) {
        if (lang == null || lang.isBlank()) return Locale.ENGLISH;
        return switch (lang.toLowerCase()) {
            case "vi" -> new Locale("vi");
            case "fr" -> new Locale("fr");
            default -> Locale.ENGLISH;
        };
    }

    private String extractKeyword(String message, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        return matcher.find() ? matcher.group(2).trim() : null;
    }

    private BigDecimal extractPrice(String message, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        return matcher.find() ? new BigDecimal(matcher.group(2)) : null;
    }
}
