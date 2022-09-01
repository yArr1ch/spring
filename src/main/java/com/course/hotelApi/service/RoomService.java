package com.course.hotelApi.service;

import com.course.hotelApi.entity.Room;
import com.course.hotelApi.dto.RoomDto;
import com.course.hotelApi.exception.NotFoundException;
import com.course.hotelApi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomDto> findAllRooms() {
        return map(roomRepository.findAll());
    }

    public RoomDto findById(Integer id) {
        return map(getById(id));
    }

    public RoomDto create(Room room) {
        return map(roomRepository.save(room));
    }

    public RoomDto update(Integer id, Room room) {
        getById(id);
        room.setId(id);
        return map(roomRepository.save(room));
    }

    public void delete(Integer id) {
        roomRepository.deleteById(id);
    }

    public List<RoomDto> map(List<Room> rooms) {
        return rooms.stream().map(r -> new RoomDto(r.getId(), r.getType(), r.getTv(), r.getBalcony(),
                r.getAirConditioner(), r.getView(), r.getHotelId())).collect(Collectors.toList());
    }

    public Room getById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room not found with id = ", id));
    }

    private RoomDto map(Room room) {
        RoomDto r = new RoomDto();
        r.setId(room.getId());
        r.setType(room.getType());
        r.setTv(room.getTv());
        r.setBalcony(room.getBalcony());
        r.setAirConditioner(room.getAirConditioner());
        r.setView(room.getView());
        r.setHotelId(room.getHotelId());
        return r;
    }
}
