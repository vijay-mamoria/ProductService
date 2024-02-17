package com.productservice.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass //Table Not to be created for this Class. Superclass of other entities.
public class BaseModel {

    @Id
    @GeneratedValue(generator = "custom_generator") //Configure a Custom UUID Generator.
    @GenericGenerator(name = "custom_generator", strategy = "uuid2") //Definition of Generator used above with strategy.
    @Column(name = "ID", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID id;
    /**
     * TODO Audit Fields
     */
}
