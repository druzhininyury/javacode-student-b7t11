package ru.javacode.student.service;

import ru.javacode.student.model.dto.CustomerDtoFull;
import ru.javacode.student.model.dto.CustomerDtoNew;

public interface CustomerService {

    CustomerDtoFull addCustomer(CustomerDtoNew customerDtoNew);

}
