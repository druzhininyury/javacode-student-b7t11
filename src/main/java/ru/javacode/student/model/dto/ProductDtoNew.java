package ru.javacode.student.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDtoNew {

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Size(min = 5)
    private String description;

    @NotNull
    @PositiveOrZero
    private Long price;

    @NotNull
    @PositiveOrZero
    private Long quantityInStock;

}
