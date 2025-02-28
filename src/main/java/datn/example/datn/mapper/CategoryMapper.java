package datn.example.datn.mapper;

import datn.example.datn.dto.request.CategoryRequestDto;
import datn.example.datn.dto.response.CategoryResponseDto;
import datn.example.datn.entity.Category;

public class CategoryMapper {
    public static Category toEntity(CategoryRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Category category = new Category();
        category.setName(requestDto.getName());
        category.setDescription(requestDto.getDescription());
        return category;
    }
    // Chuyển đổi từ Category sang CategoryResponseDto
    public static CategoryResponseDto toDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setCategoryId(category.getCategoryId());
        responseDto.setName(category.getName());
        responseDto.setDescription(category.getDescription());
        return responseDto;
    }
}
