package ru.javacode.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javacode.student.model.Customer;
import ru.javacode.student.model.dto.CustomerDtoFull;
import ru.javacode.student.model.dto.CustomerDtoNew;
import ru.javacode.student.model.mapping.CustomerMapper;
import ru.javacode.student.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerDtoFull addCustomer(CustomerDtoNew customerDtoNew) {
        Customer customer = customerMapper.toCustomer(customerDtoNew);
        Customer dbCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerDtoFull(dbCustomer);
    }
}
