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
    private final GeminiAIService geminiAIService;

    public ChatbotService(GeminiAIService geminiAIService,ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper, MessageSource messageSource) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.messageSource = messageSource;
        this.geminiAIService = geminiAIService;
    }

    public ChatbotResponseDto getChatbotResponse(String message) {
        // Gọi Gemini AI để lấy phản hồi (nếu cần)
        String geminiResponse = geminiAIService.askGeminiAI(message);

        // Kiểm tra sản phẩm
        ChatbotResponseDto response = null;

        // 1️⃣ Kiểm tra keyword "highest price"
        if (message.toLowerCase().contains("highest price")) {
            response = getHighestPricedProduct();
        }
        // 2️⃣ Kiểm tra keyword "lowest price"
        else if (message.toLowerCase().contains("lowest price")) {
            response = getLowestPricedProduct();
        }

        // Phân tích các từ khóa khác
        String name = extractKeyword(message, "(?i)(information)\\s*[:=]?\\s*([^,]+)");
        String type = extractKeyword(message, "(?i)(type)\\s*[:=]?\\s*([^,]+)");
        String categoryName = extractKeyword(message, "(?i)(category)\\s*[:=]?\\s*([^,]+)");
        String description = extractKeyword(message, "(?i)(carbon|steel|aluminum|titanium|magnesium)\\s*[:=]?\\s*([^,]+)");
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
                maxPrice,
                (description != null && !description.isBlank()) ? description.trim() : null  // Thêm mô tả vào tìm kiếm
        );

        if (name == null && type == null && categoryName == null && minPrice == null && maxPrice == null && description == null) {
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

    private ChatbotResponseDto getHighestPricedProduct() {
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

    private ChatbotResponseDto getLowestPricedProduct() {
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

//package datn.example.datn.service;
//
//import datn.example.datn.dto.response.ChatbotResponseDto;
//import datn.example.datn.dto.response.ProductResponseDto;
//import datn.example.datn.entity.Category;
//import datn.example.datn.entity.Product;
//import datn.example.datn.mapper.ProductMapper;
//import datn.example.datn.repository.CategoryRepository;
//import datn.example.datn.repository.ProductRepository;
//import org.springframework.context.MessageSource;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class ChatbotService {
//    private final ProductRepository productRepository;
//    private final CategoryRepository categoryRepository;
//    private final ProductMapper productMapper;
//    private final MessageSource messageSource;
//    private final GeminiAIService geminiAIService;
//
//    public ChatbotService(GeminiAIService geminiAIService, ProductRepository productRepository,
//                          CategoryRepository categoryRepository, ProductMapper productMapper,
//                          MessageSource messageSource) {
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//        this.productMapper = productMapper;
//        this.messageSource = messageSource;
//        this.geminiAIService = geminiAIService;
//    }
//
//    public ChatbotResponseDto getChatbotResponse(String message) {
//        // Gọi Gemini AI để lấy phản hồi (nếu cần)
//        String geminiResponse = geminiAIService.askGeminiAI(message);
//
//        // Kiểm tra sản phẩm
//        ChatbotResponseDto response = null;
//
//        // 1️⃣ Kiểm tra keyword "highest price"
//        if (message.toLowerCase().contains("highest price")) {
//            response = getHighestPricedProduct();
//        }
//        // 2️⃣ Kiểm tra keyword "lowest price"
//        else if (message.toLowerCase().contains("lowest price")) {
//            response = getLowestPricedProduct();
//        }
//
//        // Phân tích các từ khóa khác
//        String name = extractKeyword(message, "(?i)(information)\\s*[:=]?\\s*([^,]+)");
//        String type = extractKeyword(message, "(?i)(type)\\s*[:=]?\\s*([^,]+)");
//        String categoryName = extractKeyword(message, "(?i)(category)\\s*[:=]?\\s*([^,]+)");
//        String description = extractKeyword(message, "(?i)(carbon|steel|aluminum|titanium|magnesium)\\s*[:=]?\\s*([^,]+)");
//        BigDecimal minPrice = extractPrice(message, "(?i)(from|min)\\s*(\\d+)");
//        BigDecimal maxPrice = extractPrice(message, "(?i)(to|max)\\s*(\\d+)");
//
//        Long categoryId = null;
//        if (categoryName != null && !categoryName.isBlank()) {
//            Optional<Category> categoryOptional = categoryRepository.findCategoryByName(categoryName.trim());
//            categoryId = categoryOptional.map(Category::getCategoryId).orElse(null);
//        }
//
//        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
//            BigDecimal temp = minPrice;
//            minPrice = maxPrice;
//            maxPrice = temp;
//        }
//
//        List<Product> products = productRepository.searchProducts(
//                (name != null && !name.isBlank()) ? name.trim() : null,
//                (type != null && !type.isBlank()) ? type.trim() : null,
//                categoryId,
//                minPrice,
//                maxPrice,
//                (description != null && !description.isBlank()) ? description.trim() : null
//        );
//
//        if (products.isEmpty()) {
//            // Nếu không có sản phẩm, trả về phản hồi từ Gemini AI
//            return new ChatbotResponseDto(List.of(), List.of(), List.of(), List.of(), List.of(), List.of());
//        }
//
//        List<ProductResponseDto> productDtos = products.stream().map(productMapper::toDto).collect(Collectors.toList());
//
//        return new ChatbotResponseDto(
//                productDtos.stream().map(ProductResponseDto::getBicycleId).collect(Collectors.toList()),
//                productDtos.stream().map(ProductResponseDto::getName).collect(Collectors.toList()),
//                productDtos.stream().map(ProductResponseDto::getDescription).collect(Collectors.toList()),
//                productDtos.stream().map(ProductResponseDto::getImage).collect(Collectors.toList()),
//                productDtos.stream().map(ProductResponseDto::getOriginalPrice).collect(Collectors.toList()),
//                productDtos.stream().map(ProductResponseDto::getDiscountedPrice).collect(Collectors.toList())
//        );
//    }
//
//    private ChatbotResponseDto getHighestPricedProduct() {
//        Optional<Product> highestPricedProduct = productRepository.findTopByOrderByOriginalPriceDesc();
//        if (highestPricedProduct.isPresent()) {
//            ProductResponseDto dto = productMapper.toDto(highestPricedProduct.get());
//            return new ChatbotResponseDto(
//                    List.of(dto.getBicycleId()),
//                    List.of(dto.getName()),
//                    List.of(dto.getDescription()),
//                    List.of(dto.getImage()),
//                    List.of(dto.getOriginalPrice()),
//                    List.of(dto.getDiscountedPrice())
//            );
//        }
//        return new ChatbotResponseDto(List.of(), List.of(), List.of(), List.of(), List.of(), List.of()); // Thông báo nếu không tìm thấy
//    }
//
//    private ChatbotResponseDto getLowestPricedProduct() {
//        Optional<Product> lowestPricedProduct = productRepository.findTopByOrderByOriginalPriceAsc();
//        if (lowestPricedProduct.isPresent()) {
//            ProductResponseDto dto = productMapper.toDto(lowestPricedProduct.get());
//            return new ChatbotResponseDto(
//                    List.of(dto.getBicycleId()),
//                    List.of(dto.getName()),
//                    List.of(dto.getDescription()),
//                    List.of(dto.getImage()),
//                    List.of(dto.getOriginalPrice()),
//                    List.of(dto.getDiscountedPrice())
//            );
//        }
//        return new ChatbotResponseDto(List.of(), List.of(), List.of(), List.of(), List.of(), List.of()); // Thông báo nếu không tìm thấy
//    }
//
//    private String extractKeyword(String message, String regex) {
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(message);
//        return matcher.find() ? matcher.group(2).trim() : null;
//    }
//
//    private BigDecimal extractPrice(String message, String regex) {
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(message);
//        return matcher.find() ? new BigDecimal(matcher.group(2)) : null;
//    }
//}