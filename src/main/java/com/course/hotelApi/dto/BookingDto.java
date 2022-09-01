package com.course.hotelApi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BookingDto {

    private Integer id;

    private String username;

    private String phoneNumber;

    private LocalDate startBooking;

    private LocalDate endBooking;

    private Integer roomNumber;

    private double price;

    public BookingDto(Integer id,
                      String username,
                      String phoneNumber,
                      LocalDate startBooking,
                      LocalDate endBooking,
                      Integer roomNumber) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.startBooking = startBooking;
        this.endBooking = endBooking;
        this.roomNumber = roomNumber;
    }
}
