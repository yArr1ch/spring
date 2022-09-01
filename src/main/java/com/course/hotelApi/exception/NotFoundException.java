package com.course.hotelApi.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private int id;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, int id) {
        super(message);
        this.id = id;
    }
}
