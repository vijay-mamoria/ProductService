package com.productservice.productservice.services;

import org.springframework.web.bind.annotation.*;

public interface ProductService {

    void getAllProducts();

    String getProductById(Long id);

    void createProduct();

    void updateProductById(Long id);

    void deleteProductById(Long id);
}
