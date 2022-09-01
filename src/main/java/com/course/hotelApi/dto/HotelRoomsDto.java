package com.course.hotelApi.dto;

import com.course.hotelApi.entity.Available;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelRoomsDto {

    private Integer hotelId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Available pools;

    @Enumerated(EnumType.STRING)
    private Available waterSlides;

    @Enumerated(EnumType.STRING)
    private Available tennisCourt;

    private List<RoomDto> rooms;

    public HotelRoomsDto(Integer hotelId, String name, Available pools, Available waterSlides, Available tennisCourt) {
        this.hotelId = hotelId;
        this.name = name;
        this.pools = pools;
        this.waterSlides = waterSlides;
        this.tennisCourt = tennisCourt;
    }
}
