package ru.javacode.student.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javacode.student.model.Customer;
import ru.javacode.student.model.Order;
import ru.javacode.student.model.Product;
import ru.javacode.student.model.dto.OrderDtoFull;
import ru.javacode.student.model.dto.OrderDtoNew;
import ru.javacode.student.model.dto.OrderDtoShort;
import ru.javacode.student.model.mapping.OrderMapper;
import ru.javacode.student.repository.CustomerRepository;
import ru.javacode.student.repository.OrderRepository;
import ru.javacode.student.repository.ProductRepository;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;

    @Override
    public OrderDtoFull getOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new EntityNotFoundException("Order hasn't been found.");
        }
        Order dbOrder = orderOptional.get();

        return orderMapper.toOrderDtoFull(dbOrder);
    }

    @Override
    @Transactional
    public OrderDtoShort addOrder(OrderDtoNew orderDtoNew) {
        Optional<Customer> customerOptional = customerRepository.findById(orderDtoNew.getCustomerId());
        if (customerOptional.isEmpty()) {
            throw new EntityNotFoundException("Customer hasn't been found.");
        }
        Customer dbCustomer = customerOptional.get();

        Set<Long> uniqueProductsIds = new HashSet<>(orderDtoNew.getProductsIds());
        List<Product> dbUniqueProducts = productRepository.findAllByIdInAndIsDeleted(uniqueProductsIds, false);
        Map<Long, Product> mapIdToProduct = getMapIdToOrder(dbUniqueProducts);
        if (mapIdToProduct.size() < uniqueProductsIds.size()) {
            throw new EntityNotFoundException("Not all products have been found.");
        }
        List<Product> dbProducts = orderDtoNew.getProductsIds().stream().map(mapIdToProduct::get).toList();

        Order order = new Order();
        order.setCustomer(dbCustomer);
        order.setProducts(dbProducts);
        order.setOrderDate(LocalDate.now());
        order.setShippingAddress(orderDtoNew.getShippingAddress());

        Order dbOrder = orderRepository.save(order);

        return orderMapper.toOrderDtoShort(dbOrder);
    }

    private static Map<Long, Product> getMapIdToOrder(Collection<Product> collection) {
        Map<Long, Product> map = new HashMap<>();
        for (Product product : collection) {
            map.put(product.getId(), product);
        }
        return map;
    }
}
