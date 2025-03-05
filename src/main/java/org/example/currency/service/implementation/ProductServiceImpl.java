package org.example.currency.service.implementation;

import jakarta.transaction.Transactional;
import org.example.currency.dto.ProductDTO;
import org.example.currency.exception.CurrencyNotFoundException;
import org.example.currency.exception.ProductNotFoundException;
import org.example.currency.model.Currency;
import org.example.currency.model.Product;
import org.example.currency.repository.ProductRepository;
import org.example.currency.service.CategoryService;
import org.example.currency.service.CurrencyService;
import org.example.currency.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CurrencyService currencyService;
    private final String defaultCurrency;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, CurrencyService currencyService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.currencyService = currencyService;
        this.defaultCurrency = "EUR";
    }

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
        Product product = ProductDTO.toEntity(productDTO);
        setCategoryAndCurrencyForProduct(productDTO, product);

        productRepository.save(product);
    }

    @Override
    public ProductDTO getProductDTOByName(String name) {
        return ProductDTO.toDTO(getProductByName(name));
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository
                .findByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Product doesn't exist"));
    }

    @Override
    @Transactional
    public void updateProduct(ProductDTO productDTO) {
        Product product = getProductByName(productDTO.getName());

        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        setCategoryAndCurrencyForProduct(productDTO, product);
        product.setDescription(productDTO.getDescription());

        productRepository.save(product);
    }

    @Override
    @Transactional
    public void renameProduct(String oldName, String newName) {
        Product product = getProductByName(oldName);
        product.setName(newName);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProductByName(String name) {
        productRepository.deleteByName(name);
    }

    private void setCategoryAndCurrencyForProduct(ProductDTO productDTO, Product product) {
        product.setCategory(categoryService.getCategoryByName(productDTO.getCategoryName()));
        product.setCurrency(getCurrencyFromProductDTO(productDTO));
    }

    private ProductDTO setDefaultCurrencyIfAbsent(ProductDTO productDTO){
        return productDTO.getCurrencyCode() == null
                ? productDTO.toBuilder().currencyCode(defaultCurrency).build()
                : productDTO;
    }

    private Currency getCurrencyFromProductDTO(ProductDTO productDTO) {
        productDTO = setDefaultCurrencyIfAbsent(productDTO);

        try {
            return currencyService.getCurrencyByCode(productDTO.getCurrencyCode());
        } catch (CurrencyNotFoundException e){
            currencyService.addCurrencyByCode(productDTO.getCurrencyCode());
            return currencyService.getCurrencyByCode(productDTO.getCurrencyCode());
        }
    }
}
