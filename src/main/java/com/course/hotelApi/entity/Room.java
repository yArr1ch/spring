package com.course.hotelApi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Please enter the type")
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull(message = "Please specify tv")
    @Column(name = "tv")
    @Enumerated(EnumType.STRING)
    private Available tv;

    @NotNull(message = "Please specify balcony")
    @Column(name = "balcony")
    @Enumerated(EnumType.STRING)
    private Available balcony;

    @NotNull(message = "Please specify conditioner")
    @Column(name = "air_conditioner")
    @Enumerated(EnumType.STRING)
    private Available airConditioner;

    @NotNull(message = "Please enter the view")
    @Column(name = "view")
    @Enumerated(EnumType.STRING)
    private View view;

    @NotNull(message = "Please enter the hotel id")
    @Column(name = "hotel_id")
    private Integer hotelId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    private Hotel hotel;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Booking> bookings;
}
