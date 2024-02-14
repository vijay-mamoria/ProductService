package com.productservice.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Generic Response DTO for all execptions.
 */
@Getter
@Setter
public class ExceptionDTO {

    private String message;
    private HttpStatus httpStatus;
}
