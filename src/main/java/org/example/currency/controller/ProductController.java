package org.example.currency.controller;

import org.example.currency.dto.ProductDTO;
import org.example.currency.dto.RenameProductDTO;
import org.springframework.web.bind.annotation.*;

public interface ProductController {
    @PostMapping
    void addProduct(@RequestBody ProductDTO productDTO);

    @GetMapping("/{name}")
    ProductDTO getProductByName(@PathVariable String name);

    @PutMapping
    void updateProduct(@RequestBody ProductDTO productDTO);

    @PatchMapping
    void renameProduct(@RequestBody RenameProductDTO productDTO);

    @DeleteMapping("/{name}")
    void deleteProduct(@PathVariable String name);
}
