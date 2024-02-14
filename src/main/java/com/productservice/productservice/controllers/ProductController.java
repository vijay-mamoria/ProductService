package com.productservice.productservice.controllers;

import com.productservice.productservice.dtos.GenericProductDTO;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<GenericProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO){
        return productService.createProduct(genericProductDTO);
    }

    @PutMapping("/{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") Long id, GenericProductDTO genericProductDTO){
        return productService.updateProductById(id, genericProductDTO);
    }

    @PatchMapping("/{id}")
    public GenericProductDTO patchProductById(@PathVariable("id") Long id, GenericProductDTO genericProductDTO) throws ProductNotFoundException {
        return productService.patchProductById(id, genericProductDTO);
    }

    /**
     * @ResponseStatus(HttpStatus.NO_CONTENT) - Use with void return type.
     * @param id
     */
    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }

    /**
     * Moved to GlobalExceptionHandler for Global exception handling for all controllers.
     */
//    @ExceptionHandler(ProductNotFoundException.class)
//    private ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
//        ExceptionDTO exceptionDTO = new ExceptionDTO();
//        exceptionDTO.setMessage(productNotFoundException.getMessage());
//        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
//        return new ResponseEntity(exceptionDTO, HttpStatus.NOT_FOUND);
//    }
}

/*
3 ways of Dependency Injection :-
1. Constructor Injection
2. Field Injection
3. Setter Injection.
 */
