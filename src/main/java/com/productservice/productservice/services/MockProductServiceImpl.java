package com.productservice.productservice.services;

import com.productservice.productservice.dtos.GenericProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockProductServiceImpl implements ProductService{
    @Override
    public List<GenericProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDTO getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {

        return null;
    }

    @Override
    public void updateProductById(Long id) {

    }

    @Override
    public void deleteProductById(Long id) {

    }
}
