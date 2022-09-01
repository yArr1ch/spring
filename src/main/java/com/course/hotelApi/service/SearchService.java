package com.course.hotelApi.service;

import com.course.hotelApi.entity.Booking;
import com.course.hotelApi.entity.Room;
import com.course.hotelApi.repository.BookingRepository;
import com.course.hotelApi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public List<Room> findRooms(Integer id, String type, String tv, String balcony, String condi, String view) {
        List<Room> roomList = roomRepository.findAll();
        List<Room> rooms = roomList.stream().filter(r -> r.getHotelId().equals(id)).toList();
        List<Room> rooms1 = rooms.stream().filter(r -> r.getType().name().toLowerCase().equals(type)).toList();
        List<Room> rooms2 = rooms1.stream().filter(r -> r.getTv().name().toLowerCase().equals(tv)).toList();
        List<Room> rooms3 = rooms2.stream().filter(r -> r.getBalcony().name().toLowerCase().equals(balcony)).toList();
        List<Room> rooms4 = rooms3.stream().filter(r -> r.getAirConditioner().name().toLowerCase().equals(condi)).toList();
        return rooms4.stream().filter(r -> r.getView().name().toLowerCase().equals(view)).toList();
    }

    public List<Booking> findAvailableForDates(LocalDate start, LocalDate end) {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().filter(b -> start.isEqual(b.getStartBooking()) && end.isEqual(b.getEndBooking())
                || start.isAfter(b.getStartBooking()) && end.isBefore(b.getEndBooking())
                || start.isEqual(b.getStartBooking()) && end.isBefore(b.getEndBooking())
                || start.isAfter(b.getStartBooking()) && end.isEqual(b.getEndBooking())).toList();
    }
}
