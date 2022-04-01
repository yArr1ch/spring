package com.dataart.intern.logista.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class City {
    private Integer id;

    @NotEmpty(message = "Please enter the name of city")
    private String name;
}
