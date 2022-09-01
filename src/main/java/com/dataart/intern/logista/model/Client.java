package com.dataart.intern.logista.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
@Table(name = "clients")
public class Client extends Base {

    @Column(name = "first_name")
    @NotEmpty(message = "Please enter the first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Please enter the last name")
    private String lastName;

    @Column(name = "phone_number")
    @NotEmpty(message = "Please enter the phone number")
    @Size(min = 10, max = 10)
    private String phoneNumber;
}
