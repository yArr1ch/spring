package com.course.hotelApi.controller;

import com.course.hotelApi.entity.Room;
import com.course.hotelApi.dto.RoomDto;
import com.course.hotelApi.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    /*@GetMapping
    @PreAuthorize("permitAll()")
    public List<Room> findAll() {
        return roomService.findAll();
    }*/

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<RoomDto> findAllRooms() {
        return roomService.findAllRooms();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public RoomDto findById(@PathVariable Integer id) {
        return roomService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoomDto create(@RequestBody Room room) {
        return roomService.create(room);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoomDto update(@PathVariable Integer id, @RequestBody Room room) {
        return roomService.update(id, room);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Integer id) {
        roomService.delete(id);
    }
}
