package datn.example.datn.config;

import datn.example.datn.entity.Category;
import datn.example.datn.entity.Product;
import datn.example.datn.repository.CategoryRepository;
import datn.example.datn.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.*;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {

        return args -> {
            // ✅ Fetch existing categories from DB
            List<Category> existingCategories = categoryRepository.findAll();
            Map<String, Category> categoryMap = new HashMap<>();

            // ✅ Store categories in a Map to prevent duplicates
            for (Category c : existingCategories) {
                categoryMap.put(c.getName(), c);
            }

            // ✅ Define new categories
            List<Category> newCategories = List.of(
                    new Category("Mountain Bikes", "High-quality mountain bikes for tough terrains."),
                    new Category("Kids Bikes", "Safe and easy-to-use bikes for children."),
                    new Category("Sports Bikes", "Bikes designed specifically for sports."),
                    new Category("Folding Bikes", "Convenient folding bikes for city travel."),
                    new Category("Electric Bikes", "E-bikes that help save effort."),
                    new Category("Road Racing Bikes", "Bikes designed for competitive racing."),
                    new Category("Classic Bikes", "Elegant bikes with a classic style.")
            );

            // ✅ Add categories only if they don’t exist
            for (Category category : newCategories) {
                if (!categoryMap.containsKey(category.getName())) {
                    categoryRepository.save(category);
                    categoryMap.put(category.getName(), category); // Update Map
                    System.out.println("✅ Added category: " + category.getName());
                } else {
                    System.out.println("⏩ Skipped existing category: " + category.getName());
                }
            }

            // ✅ Refresh category list after insertion
            existingCategories = categoryRepository.findAll();
            categoryMap.clear();
            for (Category c : existingCategories) {
                categoryMap.put(c.getName(), c);
            }

            // ✅ Fetch existing products
            List<Product> existingProducts = productRepository.findAll();
            Set<String> existingProductNames = new HashSet<>();

            for (Product p : existingProducts) {
                existingProductNames.add(p.getName());
            }

            // ✅ Generate new products
            List<String> bikeNames = List.of("X-Trial", "Speedster", "UrbanRide", "MountainMax", "StreetFlow");
            List<String> bikeImages = List.of("bike1.jpg", "bike2.jpg", "bike3.jpg", "bike4.jpg", "bike5.jpg");
            Random random = new Random();

            List<Product> newProducts = new ArrayList<>();
            for (int i = 1; i <= 50; i++) {
                String productName = "Bike " + bikeNames.get(random.nextInt(bikeNames.size())) + " " + (2020 + random.nextInt(6));

                if (!existingProductNames.contains(productName)) {
                    Product product = new Product();
                    product.setName(productName);
                    product.setDescription("Model " + i + " with a modern design and high durability.");
                    product.setImage("/img_bicycle/" + bikeImages.get(i % bikeImages.size()));
                    product.setRating(3.5 + (random.nextDouble() * 1.5));
                    product.setOriginalPrice(new BigDecimal(500 + random.nextInt(1000)));
                    product.setQuantity(random.nextInt(20) + 1);
                    product.setCategory(new ArrayList<>(categoryMap.values()).get(random.nextInt(categoryMap.size())));

                    String[] types = {"HOT", "NEW", "SALE"};
                    product.setType(types[random.nextInt(types.length)]);

                    newProducts.add(product);
                } else {
                    System.out.println("⏩ Skipped existing product: " + productName);
                }
            }

            // ✅ Save new products if any
            if (!newProducts.isEmpty()) {
                productRepository.saveAll(newProducts);
                System.out.println("✅ Added " + newProducts.size() + " new products!");
            } else {
                System.out.println("⏩ No new products to add!");
            }
        };
    }
}
