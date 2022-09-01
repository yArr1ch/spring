package com.course.hotelApi.controller;

import com.course.hotelApi.dto.HotelDto;
import com.course.hotelApi.dto.HotelRoomsDto;
import com.course.hotelApi.entity.Hotel;
import com.course.hotelApi.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<HotelDto> findAllHotels() {
        return hotelService.findAllHotels();
    }

    @GetMapping("/rooms")
    @PreAuthorize("permitAll()")
    public List<HotelRoomsDto> allRoomsInHotel() {
        return hotelService.allRoomsInHotel();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public HotelDto findById(@PathVariable Integer id) {
        return hotelService.findBy(id);
    }

    @GetMapping("/{id}/rooms")
    @PreAuthorize("permitAll()")
    public HotelRoomsDto findByIdWithRooms(@PathVariable Integer id) {
        return hotelService.findByIdWithRooms(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HotelDto create(@RequestBody Hotel hotel) {
        return hotelService.create(hotel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HotelDto update(@PathVariable Integer id, @RequestBody Hotel hotel) {
        return hotelService.update(id, hotel);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Integer id) {
        hotelService.delete(id);
    }
}
