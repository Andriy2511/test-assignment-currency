package org.example.currency.controller.implementation;

import jakarta.validation.Valid;
import org.example.currency.controller.CategoryController;
import org.example.currency.dto.CategoryDTO;
import org.example.currency.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryControllerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @PostMapping
    public void addCategory(@RequestBody @Valid CategoryDTO category) {
        categoryService.addCategory(category);
    }

    @Override
    @GetMapping("/{categoryName}")
    public CategoryDTO getCategoryByName(@PathVariable String categoryName){
        return categoryService.getCategoryDTOByName(categoryName);
    }

    @Override
    @GetMapping("/{categoryName}/parents")
    public List<CategoryDTO> getAllParentCategories(@PathVariable String categoryName){
        return categoryService.getAllParentCategories(categoryName);
    }

    @Override
    @PutMapping("/{categoryName}/rename")
    public void updateCategoryName(@PathVariable String categoryName, @RequestBody String newName){
        categoryService.changeCategoryName(categoryName, newName);
    }

    @Override
    @PutMapping("/{categoryName}/parent")
    public void updateParentCategory(@PathVariable String categoryName, @RequestBody String newParentName){
        categoryService.changeParentCategory(categoryName, newParentName);
    }

    @Override
    @DeleteMapping("/{categoryName}")
    public void deleteCategoryByName(@PathVariable String categoryName){
        categoryService.deleteCategoryByName(categoryName);
    }
}
