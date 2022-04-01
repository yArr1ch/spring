package com.dataart.intern.logista.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class Client {
    private Integer id;

    @NotEmpty(message = "Please enter the first name")
    private String firstName;

    @NotEmpty(message = "Please enter the last name")
    private String lastName;

    @NotEmpty(message = "Please enter the phone number")
    @Size(min = 10, max = 10)
    private String phoneNumber;
}
