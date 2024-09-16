package ru.javacode.student.model.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.javacode.student.model.Order;
import ru.javacode.student.model.Product;
import ru.javacode.student.model.dto.OrderDtoFull;
import ru.javacode.student.model.dto.OrderDtoShort;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "productsIds", source = "products")
    @Mapping(target = "totalPrice", expression = "java(order.getTotalPrice())")
    OrderDtoShort toOrderDtoShort(Order order);

    default List<Long> productToIds(List<Product> products) {
        return products.stream().map(Product::getId).collect(Collectors.toList());
    }

    @Mapping(target = "totalPrice", expression = "java(order.getTotalPrice())")
    OrderDtoFull toOrderDtoFull(Order order);
}
