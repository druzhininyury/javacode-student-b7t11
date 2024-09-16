package ru.javacode.student.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    public enum Status {
        NEW,
        APPROVED,
        SENT,
        DELIVERED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "orders_products",
               joinColumns = @JoinColumn(name = "order_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @Column(name = "created_date")
    private LocalDate orderDate;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status orderStatus = Status.NEW;

    public Long getTotalPrice() {
        return products.stream().mapToLong(Product::getPrice).sum();
    }

}
