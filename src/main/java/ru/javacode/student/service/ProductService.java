package ru.javacode.student.service;

import ru.javacode.student.model.dto.ProductDtoNew;
import ru.javacode.student.model.dto.ProductDtoUpdate;
import ru.javacode.student.model.dto.ProductDtoViewFull;

import java.util.List;

public interface ProductService {

    ProductDtoViewFull getProduct(long productId);

    List<ProductDtoViewFull> getAllProducts();

    ProductDtoViewFull addProduct(ProductDtoNew productDtoNew);

    ProductDtoViewFull updateProduct(ProductDtoUpdate productDtoUpdate);

    ProductDtoViewFull replenishProductStock(long productId, long amount);

    void deleteProduct(long productId);
}
