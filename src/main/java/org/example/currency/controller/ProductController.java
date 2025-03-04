package org.example.currency.controller;

import org.example.currency.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

public interface ProductController {
    @PostMapping
    void addProduct(@RequestBody ProductDTO productDTO);

    @GetMapping
    ProductDTO getProductByName(@RequestBody String name);

    @PutMapping
    void updateProduct(@RequestBody ProductDTO productDTO);

    @PatchMapping
    void renameProduct(@RequestBody String oldName, @RequestBody String newName);

    @DeleteMapping
    void deleteProduct(@RequestBody String name);
}
