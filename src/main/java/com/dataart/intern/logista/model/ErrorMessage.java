package com.dataart.intern.logista.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
