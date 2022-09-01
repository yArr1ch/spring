package com.course.hotelApi.controller;

import com.course.hotelApi.entity.Booking;
import com.course.hotelApi.entity.Room;
import com.course.hotelApi.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/hotel")
    @PreAuthorize("permitAll()")
    public List<Room> findRooms(@RequestParam Integer id, @RequestParam String type, @RequestParam String tv,
                                @RequestParam String balcony, @RequestParam String condi, @RequestParam String view) {
        return searchService.findRooms(id, type, tv, balcony, condi, view);
    }

    @GetMapping("/date")
    @PreAuthorize("permitAll()")
    public List<Booking> findAvailable(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        return searchService.findAvailableForDates(start, end);
    }
}
