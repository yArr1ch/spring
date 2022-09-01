package com.course.hotelApi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

    private int code;
    private String message;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.message = message;
    }
}
