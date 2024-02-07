package com.productservice.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * So changes in External DTO don’t change our internal DTO directly.
 * Ex. Field, type change, add/delete fields.
 */
    @Getter
    @Setter
    public class GenericProductDTO {

        //DTO -> Data Transfer Objects.
        private Long id;
        private String title;
        private int price;
        //Not Keeping as enum as values are huge and Fakestore has in string format.
        private String category;
        private String description;
        private String image;
    }
