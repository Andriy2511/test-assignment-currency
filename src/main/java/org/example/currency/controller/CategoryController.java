package org.example.currency.controller;

import jakarta.validation.Valid;
import org.example.currency.dto.CategoryDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CategoryController {
    @PostMapping
    void addCategory(@RequestBody @Valid CategoryDTO category);

    @GetMapping("/{categoryName}")
    CategoryDTO getCategoryByName(@PathVariable String categoryName);

    @GetMapping("/{categoryName}/parents")
    List<CategoryDTO> getAllParentCategories(@PathVariable String categoryName);

    @PutMapping("/{categoryName}/rename")
    void updateCategoryName(@PathVariable String categoryName, @RequestBody String newName);

    @PutMapping("/{categoryName}/parent")
    void updateParentCategory(@PathVariable String categoryName, @RequestBody String newParentName);

    @DeleteMapping("/{categoryName}")
    void deleteCategoryByName(@PathVariable String categoryName);
}
