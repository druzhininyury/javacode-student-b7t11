package ru.javacode.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javacode.student.model.Product;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndIsDeleted(long productId, boolean isDeleted);

    List<Product> findAllByIsDeleted(boolean isDeleted);

    List<Product> findAllByIdInAndIsDeleted(Collection<Long> ids, boolean isDeleted);

}
