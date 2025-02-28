package datn.example.datn.service;

import datn.example.datn.config.exception.ResourceNotFoundException;
import datn.example.datn.dto.request.CategoryRequestDto;
import datn.example.datn.dto.response.CategoryResponseDto;
import datn.example.datn.entity.Category;
import datn.example.datn.mapper.CategoryMapper;
import datn.example.datn.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public CategoryResponseDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findCategoryByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
        return CategoryMapper.toDto(category);
    }

    public String createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = CategoryMapper.toEntity(categoryRequestDto);
        categoryRepository.save(category);
        return "CreateCategory Successful";
    }

    public String updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findCategoryByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));

        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        categoryRepository.save(category);

        return "UpdateCategory Successful";
    }

    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findCategoryByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
        categoryRepository.delete(category);
        return "DeletedCategory Successful";
    }
}
