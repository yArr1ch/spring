package com.course.hotelApi.service;

import com.course.hotelApi.dto.BookingDto;
import com.course.hotelApi.entity.Booking;
import com.course.hotelApi.entity.Room;
import com.course.hotelApi.exception.NotFoundException;
import com.course.hotelApi.repository.BookingRepository;
import com.course.hotelApi.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final PriceRepository priceRepository;

    public List<BookingDto> findAll() {
        return map(bookingRepository.findAll());
    }

    public BookingDto findById(Integer id) {
        return map(bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking not found with id = ", id)));
    }

    public BookingDto create(Booking booking) {
        equalDatesCheck(booking.getStartBooking(), booking.getEndBooking());

        if (bookingRepository.isAvailable(booking.getStartBooking(), booking.getEndBooking(), booking.getRoomNumber())) {
            priceFormation(booking, booking.getRoomNumber());
            bookingRepository.save(booking);
            return map(booking);
        }
        throw new NotFoundException("Room is booked, please choose other dates");
    }

    public BookingDto update(Integer id, Booking booking) {
        findById(id);
        booking.setId(id);
        //equalDatesCheck(booking.getStartBooking(), booking.getEndBooking());

        if (bookingRepository.isAvailable(booking.getStartBooking(), booking.getEndBooking(), booking.getRoomNumber())) {
            priceFormation(booking, booking.getRoomNumber());
            bookingRepository.save(booking);
            return map(booking);
        }
        throw new NotFoundException("Room is booked, please choose other dates");
    }

    public Integer delete(Integer id) {
        findById(id);
        bookingRepository.deleteById(id);
        return id;
    }

    private void equalDatesCheck(LocalDate start, LocalDate end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("The start date cannot be an end date");
        }
    }
    private BookingDto map(Booking booking) {
        BookingDto b = new BookingDto();
        b.setId(booking.getId());
        b.setUsername(booking.getUsername());
        b.setPhoneNumber(booking.getPhoneNumber());
        b.setStartBooking(booking.getStartBooking());
        b.setEndBooking(booking.getEndBooking());
        b.setRoomNumber(booking.getRoomNumber());
        b.setPrice(booking.getPrice());
        return b;
    }

    private void priceFormation(Booking booking, Integer roomNum) {
        Room room = roomService.getById(roomNum);
        long days = Duration.between(booking.getEndBooking(), booking.getStartBooking()).toDays();

        int price = priceRepository.priceForRoomPerDay(room.getType(), room.getTv(), room.getAirConditioner(),
                room.getBalcony(), room.getView());

        int monthStart = booking.getStartBooking().getMonthValue();
        int monthEnd = booking.getEndBooking().getMonthValue();

        if (monthStart > 5 && monthStart < 9) {
            booking.setPrice(price * days * 1.5);
        } else if (monthStart < 6 && (monthEnd > 5 && monthEnd < 9)) {
            booking.setPrice(price * days * 1.2);
        } else {
            booking.setPrice(price * days);
        }
    }

    private List<BookingDto> map(List<Booking> bookings) {
        return bookings.stream().map(b -> new BookingDto(b.getId(), b.getUsername(), b.getPhoneNumber(),
               b.getStartBooking(), b.getEndBooking(), b.getRoomNumber())).collect(Collectors.toList());
    }
}
//price count