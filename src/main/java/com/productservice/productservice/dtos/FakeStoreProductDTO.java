package com.productservice.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {

    //DTO -> Data Transfer Objects.
    private Long id;
    private String title;
    private int price;
    //Not Keeping as enum as values are huge and Fakestore has in string format.
    private String category;
    private String description;
    private String image;
}
