package ru.javacode.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.javacode.student.model.dto.ProductDtoNew;
import ru.javacode.student.model.dto.ProductDtoUpdate;
import ru.javacode.student.model.dto.ProductDtoViewFull;
import ru.javacode.student.service.ProductService;
import ru.javacode.student.util.RequestBodyReader;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
@Validated
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ObjectMapper objectMapper;
    private final Validator validator;

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String getProduct(@PathVariable Long productId) throws IOException {
        ProductDtoViewFull productDtoViewFull = productService.getProduct(productId);
        return objectMapper.writeValueAsString(productDtoViewFull);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String getAllProduct() throws IOException {
        List<ProductDtoViewFull> productsDtoViewFull = productService.getAllProducts();
        return objectMapper.writeValueAsString(productsDtoViewFull);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addProduct(HttpServletRequest request) throws IOException {
        String requestBody = RequestBodyReader.getBody(request.getReader());
        ProductDtoNew productDtoNew = objectMapper.readValue(requestBody, ProductDtoNew.class);
        if (!validator.validate(productDtoNew).isEmpty()) {
            throw new ValidationException("Validation exception.");
        }

        ProductDtoViewFull productDtoViewFull = productService.addProduct(productDtoNew);
        return objectMapper.writeValueAsString(productDtoViewFull);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String updateProduct(HttpServletRequest request) throws IOException {
        String requestBody = RequestBodyReader.getBody(request.getReader());
        ProductDtoUpdate productDtoUpdate = objectMapper.readValue(requestBody, ProductDtoUpdate.class);
        if (!validator.validate(productDtoUpdate).isEmpty()) {
            throw new ValidationException("Validation exception.");
        }

        ProductDtoViewFull productDtoViewFull = productService.updateProduct(productDtoUpdate);
        return objectMapper.writeValueAsString(productDtoViewFull);
    }

    @PatchMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String replenishProductStock(@PathVariable Long productId,
                                                      @RequestParam(name = "amount") @PositiveOrZero Long amount) throws IOException {
        ProductDtoViewFull productDtoViewFull = productService.replenishProductStock(productId, amount);
        return objectMapper.writeValueAsString(productDtoViewFull);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String deleteProduct(@PathVariable Long productId) throws IOException {
        productService.deleteProduct(productId);
        return "Deleted.";
    }

}
