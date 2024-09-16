package ru.javacode.student.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDtoViewFull {

    private Long id;

    private String name;

    private String description;

    private Long price;

    private Long quantityInStock;

}
