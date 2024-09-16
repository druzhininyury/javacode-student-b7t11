package ru.javacode.student.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javacode.student.model.Product;
import ru.javacode.student.model.dto.ProductDtoNew;
import ru.javacode.student.model.dto.ProductDtoUpdate;
import ru.javacode.student.model.dto.ProductDtoViewFull;
import ru.javacode.student.model.mapping.ProductMapper;
import ru.javacode.student.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public ProductDtoViewFull getProduct(long productId) {
        Optional<Product> productOptional = productRepository.findByIdAndIsDeleted(productId, false);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product not found.");
        }
        Product dbProduct = productOptional.get();

        return productMapper.toProductDtoFull(dbProduct);
    }

    @Override
    public List<ProductDtoViewFull> getAllProducts() {
        List<Product> dbProducts = productRepository.findAllByIsDeleted(false);
        return productMapper.toProductDtoFull(dbProducts);
    }

    @Override
    @Transactional
    public ProductDtoViewFull addProduct(ProductDtoNew productDtoNew) {
        Product product = productMapper.toProduct(productDtoNew);
        Product dbProduct = productRepository.save(product);
        return productMapper.toProductDtoFull(dbProduct);
    }

    @Override
    @Transactional
    public ProductDtoViewFull updateProduct(ProductDtoUpdate productDtoUpdate) {
        Optional<Product> productOptional = productRepository.findByIdAndIsDeleted(productDtoUpdate.getId(), false);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product not found.");
        }
        Product dbProduct = productOptional.get();

        productMapper.updateProduct(dbProduct, productDtoUpdate);

        dbProduct = productRepository.save(dbProduct);
        return productMapper.toProductDtoFull(dbProduct);
    }

    @Override
    @Transactional
    public ProductDtoViewFull replenishProductStock(long productId, long amount) {
        Optional<Product> productOptional = productRepository.findByIdAndIsDeleted(productId, false);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product not found.");
        }
        Product dbProduct = productOptional.get();

        dbProduct.setQuantityInStock(dbProduct.getQuantityInStock() + amount);

        dbProduct = productRepository.save(dbProduct);
        return productMapper.toProductDtoFull(dbProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(long productId) {
        Optional<Product> productOptional = productRepository.findByIdAndIsDeleted(productId, false);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product not found.");
        }
        Product dbProduct = productOptional.get();

        dbProduct.setIsDeleted(true);
        productRepository.save(dbProduct);
    }
}
