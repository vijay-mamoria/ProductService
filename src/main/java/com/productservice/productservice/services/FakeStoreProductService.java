package com.productservice.productservice.services;

import org.springframework.stereotype.Service;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    @Override
    public void getAllProducts() {

    }

    @Override
    public String getProductById(Long id) {
        //Integrate the FakeStore API.
        return "Product is :- " + id;
    }

    @Override
    public void createProduct() {

    }

    @Override
    public void updateProductById(Long id) {

    }

    @Override
    public void deleteProductById(Long id) {

    }
}
