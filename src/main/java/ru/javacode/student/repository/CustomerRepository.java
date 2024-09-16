package ru.javacode.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javacode.student.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {



}
