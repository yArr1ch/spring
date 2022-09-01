package com.course.hotelApi.dto;

import com.course.hotelApi.entity.Available;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {

    private Integer hotelId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Available pools;

    @Enumerated(EnumType.STRING)
    private Available waterSlides;

    @Enumerated(EnumType.STRING)
    private Available tennisCourt;
}
