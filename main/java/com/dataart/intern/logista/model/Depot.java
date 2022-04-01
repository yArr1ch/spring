package com.dataart.intern.logista.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Depot {

    private Integer id;

    @NotNull(message = "Please enter the number")
    private Integer number;

    @NotEmpty(message = "Please enter the address")
    private String address;

    @NotNull(message = "Please select the city")
    private Integer cityId;
}
