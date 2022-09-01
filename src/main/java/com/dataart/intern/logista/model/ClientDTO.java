package com.dataart.intern.logista.model;

import lombok.Getter;
import lombok.Setter;

import java.util.OptionalInt;

@Getter
@Setter
public class ClientDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Integer parcelNumber;
}
