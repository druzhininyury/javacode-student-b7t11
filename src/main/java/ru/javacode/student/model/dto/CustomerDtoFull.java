package ru.javacode.student.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDtoFull {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long contactNumber;

}
