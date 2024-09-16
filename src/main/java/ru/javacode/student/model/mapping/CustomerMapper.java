package ru.javacode.student.model.mapping;

import org.mapstruct.Mapper;
import ru.javacode.student.model.Customer;
import ru.javacode.student.model.dto.CustomerDtoFull;
import ru.javacode.student.model.dto.CustomerDtoNew;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerDtoNew customerDtoNew);

    CustomerDtoFull toCustomerDtoFull(Customer customer);
}
