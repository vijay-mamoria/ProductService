package com.productservice.productservice.services;

import com.productservice.productservice.assemblers.ProductDTOAssembler;
import com.productservice.productservice.dtos.FakeStoreProductDTO;
import com.productservice.productservice.dtos.GenericProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private final static String genericProductUrl = "https://fakestoreapi.com/products";
    private final static String specificProductUrl = "https://fakestoreapi.com/products/{id}";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        /**
         * Due to Generics Type Erasure we can't use Lise<FakeStoreProductDTO>.class as Response Type.
         * Type information is erased at compile time. This means that at runtime, the JVM does not have access to the generic type parameters due to type erasure.
         * So, RestTemplate won't be able to convert the JSON response to correct Type.
         * Arrays Don't suffer from this problem.
         */
        FakeStoreProductDTO[] responseEntity = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDTO[].class).getBody();
        List<GenericProductDTO> genericProductDTOsList = new ArrayList<GenericProductDTO>();
        if(responseEntity != null) {
            for (FakeStoreProductDTO fakeStoreProductDTO : responseEntity) {
                genericProductDTOsList.add(ProductDTOAssembler.convertToGenericProductDTO(fakeStoreProductDTO));
            }
        }
        return genericProductDTOsList;
    }

    @Override
    public GenericProductDTO getProductById(Long id) {
        //Integrate the FakeStore API.
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO.class , id);

        //can we use generic response mappers for conversion?
        return ProductDTOAssembler.convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        /**
         * Since GenericProductDTO fields are same as FakeStoreProductDTO.
         */
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(genericProductUrl, genericProductDTO, FakeStoreProductDTO.class);
        return ProductDTOAssembler.convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public void updateProductById(Long id) {

    }

    @Override
    public void deleteProductById(Long id) {

    }
}
