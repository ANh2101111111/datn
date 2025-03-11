package datn.example.datn.web.rest.admin;

import datn.example.datn.dto.request.CategoryRequestDto;
import datn.example.datn.dto.response.CategoryResponseDto;
import datn.example.datn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminCategoryController")
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequestDto));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequestDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }


}
