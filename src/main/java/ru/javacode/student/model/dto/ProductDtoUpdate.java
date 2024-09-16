package ru.javacode.student.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDtoUpdate {

    @NotNull
    private Long id;

    @Size(min = 3)
    private String name;

    @Size(min = 5)
    private String description;

    @PositiveOrZero
    private Long price;

}
