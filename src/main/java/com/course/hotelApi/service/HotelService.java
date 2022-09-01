package com.course.hotelApi.service;

import com.course.hotelApi.dto.HotelRoomsDto;
import com.course.hotelApi.entity.Hotel;
import com.course.hotelApi.dto.HotelDto;
import com.course.hotelApi.exception.NotFoundException;
import com.course.hotelApi.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RoomService roomService;

    public List<HotelDto> findAllHotels() {
        return map(hotelRepository.findAll());
    }

    public HotelDto findBy(Integer id) {
        return map(getById(id));
    }

    public HotelDto create(Hotel hotel) {
        hotelRepository.save(hotel);
        return map(hotel);
    }

    public HotelDto update(Integer id, Hotel hotel) {
        getById(id);
        hotel.setId(id);
        hotelRepository.save(hotel);
        return map(hotel);
    }

    public void delete(Integer id) {
        hotelRepository.deleteById(id);
    }

    public Hotel getById(Integer id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel not found with id = ", id));
    }

    public HotelDto map(Hotel hotel) {
        HotelDto h = new HotelDto();
        h.setHotelId(hotel.getId());
        h.setName(hotel.getName());
        h.setPools(hotel.getPools());
        h.setWaterSlides(hotel.getWaterSlides());
        h.setTennisCourt(hotel.getTennisCourt());
        return h;
    }

    public HotelRoomsDto mapWithRooms(Hotel hotel) {
        HotelRoomsDto h = new HotelRoomsDto();
        h.setHotelId(hotel.getId());
        h.setName(hotel.getName());
        h.setPools(hotel.getPools());
        h.setWaterSlides(hotel.getWaterSlides());
        h.setTennisCourt(hotel.getTennisCourt());
        return h;
    }

    private List<HotelDto> map(List<Hotel> hotels) {
        return hotels.stream().map(h -> new HotelDto(h.getId(), h.getName(), h.getPools(),
                h.getTennisCourt(), h.getWaterSlides())).collect(Collectors.toList());
    }

    private List<HotelRoomsDto> mapList(List<Hotel> hotels) {
        return hotels.stream().map(h -> new HotelRoomsDto(h.getId(), h.getName(), h.getPools(),
                h.getTennisCourt(), h.getWaterSlides())).collect(Collectors.toList());
    }

    public List<HotelRoomsDto> allRoomsInHotel() {
        List<HotelRoomsDto> hotelRoomsDto = mapList(hotelRepository.findAll());
        hotelRoomsDto.forEach(h -> {
            List<Integer> ids = hotelRepository.findAll().stream().map(Hotel::getId).toList();
            h.setRooms(roomService.findAllRooms().stream().filter(r -> ids.stream()
                            .anyMatch(id -> id.equals(r.getHotelId()))).collect(Collectors.toList()));
        });
        return hotelRoomsDto;
    }

    public HotelRoomsDto findByIdWithRooms(Integer id) {
        HotelRoomsDto hotelRoomsDto = mapWithRooms(getById(id));

        List<Integer> ids = hotelRepository.findAll().stream().map(Hotel::getId).toList();
        hotelRoomsDto.setRooms(roomService.findAllRooms().stream().filter(r -> ids.stream()
                .anyMatch(i -> i.equals(r.getHotelId()))).collect(Collectors.toList()));
        return hotelRoomsDto;
    }
}
