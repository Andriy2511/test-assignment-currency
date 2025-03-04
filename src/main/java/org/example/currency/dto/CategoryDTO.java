package org.example.currency.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.example.currency.model.Category;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "The field cannot be void")
    private String name;

    private String parentCategoryName;

    private List<String> subcategories;

    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }
}
