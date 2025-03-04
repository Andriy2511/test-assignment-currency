package org.example.currency.service;

import org.example.currency.dto.CategoryDTO;
import org.example.currency.model.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(CategoryDTO category);

    CategoryDTO getCategoryDTOByName(String name);

    Category getCategoryByName(String name);

    List<CategoryDTO> getAllParentCategories(String categoryName);

    void changeCategoryName(String categoryName, String newName);

    void changeParentCategory(String categoryName, String newParentName);

    void deleteCategoryByName(String name);
}
