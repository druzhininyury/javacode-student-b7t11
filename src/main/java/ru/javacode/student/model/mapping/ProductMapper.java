package ru.javacode.student.model.mapping;

import org.mapstruct.*;
import ru.javacode.student.model.Product;
import ru.javacode.student.model.dto.ProductDtoNew;
import ru.javacode.student.model.dto.ProductDtoUpdate;
import ru.javacode.student.model.dto.ProductDtoViewFull;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDtoNew productDtoNew);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateProduct(@MappingTarget Product product, ProductDtoUpdate productDtoUpdate);

    ProductDtoViewFull toProductDtoFull(Product product);

    List<ProductDtoViewFull> toProductDtoFull(List<Product> products);

}
