package com.productservice.productservice.services;

import com.productservice.productservice.dtos.GenericProductDTO;

import java.util.List;

public interface ProductService {

    List<GenericProductDTO> getAllProducts();

    GenericProductDTO getProductById(Long id);

    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);

    void updateProductById(Long id);

    void deleteProductById(Long id);
}
