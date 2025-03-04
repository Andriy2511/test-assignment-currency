package org.example.currency.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.currency.model.Product;

@Getter
@ToString
@Builder(toBuilder = true)
public class ProductDTO {

    private Long id;

    @NotBlank(message = "The field cannot be void")
    private String name;

    @NotNull(message = "The field cannot be void")
    private Double price;

    private String description;

    @NotBlank(message = "The field cannot be void")
    private String categoryName;

    private String currencyCode;

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .name(product.getDescription())
                .name(product.getCategory().getName())
                .name(product.getCategory().getName())
                .build();
    }

    public static Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        return product;
    }
}
