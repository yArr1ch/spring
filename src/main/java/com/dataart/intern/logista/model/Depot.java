package com.dataart.intern.logista.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "depots")
public class Depot extends Base {

    @Column(name = "number")
    @NotNull(message = "Please enter the number")
    private Integer number;

    @Column(name = "address")
    @NotEmpty(message = "Please enter the address")
    private String address;

    @Column(name = "city_id")
    @NotNull(message = "Please select the city")
    private Integer cityId;
}
