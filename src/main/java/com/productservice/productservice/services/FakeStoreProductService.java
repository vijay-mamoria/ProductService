package com.productservice.productservice.services;

import com.productservice.productservice.assemblers.ProductDTOAssembler;
import com.productservice.productservice.dtos.FakeStoreProductDTO;
import com.productservice.productservice.dtos.GenericProductDTO;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO Error handling required for Update and Delete methods if product doesnt exist.
@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    //TODO  Replace with WebClient as RestTemplate is deprecated.
    private RestTemplateBuilder restTemplateBuilder;
    private final static String genericProductUrl = "https://fakestoreapi.com/products";
    private final static String specificProductUrl = "https://fakestoreapi.com/products/{id}";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        RestTemplate restTemplate = new RestTemplate(); //Autowire?
//        RestTemplate restTemplate = restTemplateBuilder
//                .setConnectTimeout(Duration.ofSeconds(10))
//                .setReadTimeout(Duration.ofSeconds(10))
//                .build();

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
    public GenericProductDTO getProductById(Long id) throws ProductNotFoundException {
        //Integrate the FakeStore API.
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO.class , id);

        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();

        if(fakeStoreProductDTO == null){
            throw new ProductNotFoundException("Product with id : " + id + " doesn't exist.");
        }

        //can we use generic response mappers for conversion?
        return ProductDTOAssembler.convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = new RestTemplate();
        /**
         * Since GenericProductDTO fields are same as FakeStoreProductDTO.
         */
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(genericProductUrl, genericProductDTO, FakeStoreProductDTO.class);
        return ProductDTOAssembler.convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));
    }

    //TODO - How to Handle exception in Delete and Update when the product doesn’t exist?
    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<GenericProductDTO> request = new HttpEntity<GenericProductDTO>(genericProductDTO);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange(specificProductUrl, HttpMethod.PUT, request,
                FakeStoreProductDTO.class,
                id
        );

        return ProductDTOAssembler.convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));
    }

    /**
     * Fetch product from DB and replace NOT Null values from input DTO.
     */
    @Override
    public GenericProductDTO patchProductById(Long id, GenericProductDTO genericProductDTO) throws ProductNotFoundException {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<GenericProductDTO> request = new HttpEntity<GenericProductDTO>(genericProductDTO);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange(specificProductUrl, HttpMethod.PUT, request,
                FakeStoreProductDTO.class,
                id
        );
        return ProductDTOAssembler.convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));
    }

    /**
     * Return type of DELETE should ideally be just a boolean or a message for successful delete.
     * Since FakeStoreApi is returning the deleted product itself we are also returning the DTO.
     * @param id
     * @return
     */
    @Override
    public GenericProductDTO deleteProductById(Long id) throws ProductNotFoundException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange(specificProductUrl, HttpMethod.DELETE, null,
                FakeStoreProductDTO.class,
                id
        );
        if(responseEntity.getBody() == null){
            throw new ProductNotFoundException("Product with id : " + id + " doesn't exist.");
        }
        return ProductDTOAssembler.convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));

    }
}
