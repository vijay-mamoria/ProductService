package com.productservice.productservice.thirdpartyclients;

import com.productservice.productservice.dtos.GenericProductDTO;
import com.productservice.productservice.exceptions.ProductNotFoundException;

import java.util.List;

public class FlipkartClientAdapter implements ProductStoreAdapter {
    @Override
    public List<GenericProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDTO getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        return null;
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        return null;
    }

    @Override
    public GenericProductDTO patchProductById(Long id, GenericProductDTO genericProductDTO) throws ProductNotFoundException {
        return null;
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) throws ProductNotFoundException {
        return null;
    }
}
