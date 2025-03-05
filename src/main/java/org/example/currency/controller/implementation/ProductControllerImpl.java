package org.example.currency.controller.implementation;

import org.example.currency.controller.ProductController;
import org.example.currency.dto.ProductDTO;
import org.example.currency.dto.RenameProductDTO;
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
    @GetMapping("/{name}")
    public ProductDTO getProductByName(@PathVariable String name){
        return productService.getProductDTOByName(name);
    }

    @Override
    @PutMapping
    public void updateProduct(@RequestBody ProductDTO productDTO){
        productService.updateProduct(productDTO);
    }

    @Override
    @PatchMapping
    public void renameProduct(@RequestBody RenameProductDTO productDTO){
        productService.renameProduct(productDTO.getOldName(), productDTO.getNewName());
    }

    @Override
    @DeleteMapping("/{name}")
    public void deleteProduct(@PathVariable String name){
        productService.deleteProductByName(name);
    }
}
