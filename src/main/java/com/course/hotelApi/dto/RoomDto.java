package com.course.hotelApi.dto;

import com.course.hotelApi.entity.Available;
import com.course.hotelApi.entity.Type;
import com.course.hotelApi.entity.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Integer id;

    private Type type;

    private Available tv;

    private Available balcony;

    private Available airConditioner;

    private View view;

    private Integer hotelId;
}
