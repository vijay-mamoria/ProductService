package com.productservice.productservice.services;

import com.productservice.productservice.dtos.GenericProductDTO;
import com.productservice.productservice.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    List<GenericProductDTO> getAllProducts();

    GenericProductDTO getProductById(Long id) throws ProductNotFoundException;

    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);

    GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO);

    GenericProductDTO patchProductById(Long id, GenericProductDTO genericProductDTO) throws ProductNotFoundException;

    GenericProductDTO deleteProductById(Long id) throws ProductNotFoundException;
}
