package org.example.currency.controller.implementation;

import org.example.currency.controller.ProductController;
import org.example.currency.dto.ProductDTO;
import org.example.currency.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @PostMapping
    public void addProduct(@RequestBody ProductDTO productDTO){
        productService.addProduct(productDTO);
    }

    @Override
    @GetMapping
    public ProductDTO getProductByName(@RequestBody String name){
        return productService.getProductDTOByName(name);
    }

    @Override
    @PutMapping
    public void updateProduct(@RequestBody ProductDTO productDTO){
        productService.updateProduct(productDTO);
    }

    @Override
    @PatchMapping
    public void renameProduct(@RequestBody String oldName, @RequestBody String newName){
        productService.renameProduct(oldName, newName);
    }

    @Override
    @DeleteMapping
    public void deleteProduct(@RequestBody String name){
        productService.deleteProductByName(name);
    }
}
