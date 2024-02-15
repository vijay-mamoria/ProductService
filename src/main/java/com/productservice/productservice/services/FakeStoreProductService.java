package com.productservice.productservice.services;

import com.productservice.productservice.dtos.GenericProductDTO;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.thirdpartyclients.ProductStoreAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private ProductStoreAdapter productStoreAdapter;

    public FakeStoreProductService(@Qualifier("FakeStoreClientAdapter") ProductStoreAdapter productStoreAdapter){
        this.productStoreAdapter = productStoreAdapter;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        return productStoreAdapter.getAllProducts();
    }

    @Override
    public GenericProductDTO getProductById(Long id) throws ProductNotFoundException {
        return productStoreAdapter.getProductById(id);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        return productStoreAdapter.createProduct(genericProductDTO);
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        return productStoreAdapter.updateProductById(id, genericProductDTO);
    }

    @Override
    public GenericProductDTO patchProductById(Long id, GenericProductDTO genericProductDTO) throws ProductNotFoundException {
        return productStoreAdapter.patchProductById(id, genericProductDTO);
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) throws ProductNotFoundException {
        return productStoreAdapter.deleteProductById(id);
    }
}
