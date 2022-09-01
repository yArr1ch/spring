package com.course.hotelApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.course.hotelApi.entity.ValidationMessages.*;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = USERNAME)
    @Column(name = "username")
    private String username;

    @NotBlank(message = NUMBER)
    @Column(name = "phone_number")
    private String phoneNumber;

    @FutureOrPresent
    @NotNull(message = START_DATE)
    @Column(name = "start_booking")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startBooking;

    @FutureOrPresent
    @NotNull(message = END_DATE)
    @Column(name = "end_booking")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endBooking;

    @NotNull(message = ROOM)
    @Column(name = "room_reserved")
    private Integer roomNumber;

    @Column(name = "price", insertable = false, updatable = false)
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_reserved", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private Room room;
}
