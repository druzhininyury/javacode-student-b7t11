package ru.javacode.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javacode.student.model.dto.CustomerDtoFull;
import ru.javacode.student.model.dto.CustomerDtoNew;
import ru.javacode.student.service.CustomerService;
import ru.javacode.student.util.RequestBodyReader;

import java.io.IOException;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final ObjectMapper objectMapper;
    private final Validator validator;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addCustomer(HttpServletRequest request) throws IOException {
        String requestBody = RequestBodyReader.getBody(request.getReader());
        CustomerDtoNew customerDtoNew = objectMapper.readValue(requestBody, CustomerDtoNew.class);
        if (!validator.validate(customerDtoNew).isEmpty()) {
            throw new ValidationException("Validation exception.");
        }

        CustomerDtoFull customerDtoFull = customerService.addCustomer(customerDtoNew);
        return objectMapper.writeValueAsString(customerDtoFull);
    }

}
