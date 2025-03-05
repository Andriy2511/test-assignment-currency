package org.example.currency.service.implementation;

import org.example.currency.dto.CategoryDTO;
import org.example.currency.exception.CategoryCycleDependencyException;
import org.example.currency.model.Category;
import org.example.currency.repository.CategoryRepository;
import org.example.currency.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = CategoryDTO.toEntity(categoryDTO);
        if (categoryDTO.getParentCategoryName() != null) {
            Category parentCategory = getCategoryByName(categoryDTO.getParentCategoryName());

            category.setParentCategory(parentCategory);
        }

        saveCategory(category);
    }

    @Override
    public CategoryDTO getCategoryDTOByName(String name) {
        return CategoryDTO.toDTO(categoryRepository.findByName(name).orElseThrow());
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElseThrow();
    }

    @Override
    public List<CategoryDTO> getAllParentCategories(String categoryName) {
        Category category = getCategoryByName(categoryName);

        return getParentCategories(category);
    }

    @Override
    public void changeCategoryName(String categoryName, String newName) {
        categoryRepository.updateCategoryName(categoryName, newName);
    }

    @Override
    @Transactional
    public void changeParentCategory(String categoryName, String newParentName) {
        Category newParentCategory = getCategoryByName(newParentName);
        Category currentCategory = getCategoryByName(categoryName);

        currentCategory.setParentCategory(newParentCategory);

        saveCategory(currentCategory);
    }

    @Override
    public void deleteCategoryByName(String name) {
        categoryRepository.deleteByName(name);
    }

    private void saveCategory(Category category) {
        if (isValidCategoryDependency(category))
            categoryRepository.save(category);
        else
            throw new CategoryCycleDependencyException("Category has cycled dependency");
    }

    private boolean isValidCategoryDependency(Category category){
        return !hasCyclicDependency(category) && !isCategoryEqualParentCategory(category);
    }

    private boolean hasCyclicDependency(Category category) {
        Set<Category> visitedCategories = new HashSet<>();
        while (category != null) {
            if (!visitedCategories.add(category)) {
                return true;
            }
            category = category.getParentCategory();
        }
        return false;
    }

    private boolean isCategoryEqualParentCategory(Category category) {
        return category.getParentCategory() != null && category.equals(category.getParentCategory());
    }

    private List<CategoryDTO> getParentCategories(Category category) {
        List<Category> parentCategories = new LinkedList<>();

        while (category.getParentCategory() != null) {
            parentCategories.add(category.getParentCategory());
            category = category.getParentCategory();
        }

        return parentCategories.stream().map(CategoryDTO::toDTO).toList();
    }
}
