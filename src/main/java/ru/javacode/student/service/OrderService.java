package ru.javacode.student.service;

import ru.javacode.student.model.dto.OrderDtoFull;
import ru.javacode.student.model.dto.OrderDtoNew;
import ru.javacode.student.model.dto.OrderDtoShort;

public interface OrderService {

    OrderDtoFull getOrder(Long orderId);

    OrderDtoShort addOrder(OrderDtoNew orderDtoNew);
}
