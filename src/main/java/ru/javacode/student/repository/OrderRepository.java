package ru.javacode.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javacode.student.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {



}
