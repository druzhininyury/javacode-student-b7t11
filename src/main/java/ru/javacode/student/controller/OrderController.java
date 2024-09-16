package ru.javacode.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.javacode.student.model.dto.*;
import ru.javacode.student.service.OrderService;
import ru.javacode.student.util.RequestBodyReader;

import java.io.IOException;

@Controller
@RequestMapping("/orders")
@Validated
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final ObjectMapper objectMapper;
    private final Validator validator;

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String getOrder(@PathVariable Long orderId) throws IOException {
        OrderDtoFull orderDtoFull = orderService.getOrder(orderId);
        return objectMapper.writeValueAsString(orderDtoFull);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addOrder(HttpServletRequest request) throws IOException {
        String requestBody = RequestBodyReader.getBody(request.getReader());
        OrderDtoNew orderDtoNew = objectMapper.readValue(requestBody, OrderDtoNew.class);
        if (!validator.validate(orderDtoNew).isEmpty()) {
            throw new ValidationException("Validation exception.");
        }

        OrderDtoShort orderDtoShort = orderService.addOrder(orderDtoNew);
        return objectMapper.writeValueAsString(orderDtoShort);
    }
}
