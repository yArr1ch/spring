package com.course.hotelApi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Type type;

    @Enumerated(EnumType.STRING)
    private Available tv;

    @Enumerated(EnumType.STRING)
    private Available balcony;

    @Enumerated(EnumType.STRING)
    private Available airConditioner;

    @Enumerated(EnumType.STRING)
    private View view;

    private Integer price;
}
