package com.course.hotelApi.controller;

import com.course.hotelApi.dto.BookingDto;
import com.course.hotelApi.entity.Booking;
import com.course.hotelApi.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<BookingDto> findAll() {
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public BookingDto find(@PathVariable Integer id) {
        return bookingService.findById(id);
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public BookingDto create(@Valid @RequestBody Booking booking) {
        return bookingService.create(booking);
    }

    @PutMapping("/{id}")
    @PreAuthorize("permitAll()")
    public BookingDto update(@PathVariable Integer id, @Valid @RequestBody Booking booking) {
        return bookingService.update(id, booking);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Integer delete(@PathVariable Integer id) {
        return bookingService.delete(id);
    }
}
