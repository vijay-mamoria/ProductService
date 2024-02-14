package com.productservice.productservice.controllers;

import com.productservice.productservice.dtos.ExceptionDTO;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(productNotFoundException.getMessage());
        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
