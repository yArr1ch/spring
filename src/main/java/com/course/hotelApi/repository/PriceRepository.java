package com.course.hotelApi.repository;

import com.course.hotelApi.entity.Available;
import com.course.hotelApi.entity.Price;
import com.course.hotelApi.entity.Type;
import com.course.hotelApi.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<Price, Integer> {

    @Query("SELECT p.price FROM Price p WHERE p.type = :type AND p.tv = :tv AND p.airConditioner = :conditioner " +
           "AND p.balcony = :balcony AND p.view = :view")
    Integer priceForRoomPerDay(Type type, Available tv, Available conditioner, Available balcony, View view);
}
