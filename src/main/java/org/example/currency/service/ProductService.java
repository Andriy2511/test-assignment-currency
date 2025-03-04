package org.example.currency.service;

import org.example.currency.dto.ProductDTO;
import org.example.currency.model.Product;

public interface ProductService {
    void addProduct(ProductDTO productDTO);

    Product getProductByName(String name);

    ProductDTO getProductDTOByName(String name);

    void updateProduct(ProductDTO productDTO);

    void renameProduct(String oldName, String newName);

    void deleteProductByName(String name);
}
