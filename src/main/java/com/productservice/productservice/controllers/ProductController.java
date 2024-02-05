package com.productservice.productservice.controllers;

import com.productservice.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService  productService;

    /**
     * if the Service is not annotated then error :- Could not autowire. No beans of 'ProductService' type found.
     * @param productService
     */
    // @Autowired OPTIONAL - in newer Spring Boot releases.
    //Constructor Injection.
    public ProductController(@Qualifier("FakeStoreProductService") ProductService  productService){
        this.productService = productService;
    }

    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id){
        //return "Scaler || Product fetched with id: " + id;
        return productService.getProductById(id);
    }

    @PostMapping
    public void createProduct(){

    }

    @PutMapping("/{id}")
    public void updateProductById(@PathVariable("id") Long id){

    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id){

    }
}

/*
3 ways of Dependency Injection :-
1. Constructor Injection
2. Field Injection
3. Setter Injection.
 */
