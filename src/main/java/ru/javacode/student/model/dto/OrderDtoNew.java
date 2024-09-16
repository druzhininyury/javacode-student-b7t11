package ru.javacode.student.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDtoNew {

    @NotNull
    private Long customerId;

    @NotNull
    private List<Long> productsIds;

    @NotNull
    private String shippingAddress;

}
