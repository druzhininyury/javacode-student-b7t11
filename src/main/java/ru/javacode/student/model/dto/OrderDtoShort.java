package ru.javacode.student.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.javacode.student.model.Order;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDtoShort {

    private Long id;

    private Long customerId;

    private List<Long> productsIds;

    private LocalDate orderDate;

    private String shippingAddress;

    private Long totalPrice;

    private Order.Status orderStatus;

}
