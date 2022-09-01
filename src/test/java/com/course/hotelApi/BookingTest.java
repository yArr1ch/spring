/*
package com.course.hotelApi;

import com.course.hotelApi.entity.Booking;
import com.course.hotelApi.security.Jwt;
import com.course.hotelApi.service.BookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Booking.class)
public class BookingTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Jwt jwt;

    @MockBean
    private BookingService bookingService;

    @Test
    void createBooking() throws Exception {
        Booking b1 = new Booking();
        b1.setUsername("name");
        b1.setPhoneNumber("üöüöü");
        b1.setStartBooking(LocalDate.parse("2022-10-10"));
        b1.setEndBooking(LocalDate.parse("2022-10-15"));
        b1.setRoomNumber(1);
        Booking b2 = new Booking();
        b2.setUsername("name");
        b2.setPhoneNumber("üöüöü");
        b2.setStartBooking(LocalDate.parse("2022-09-15"));
        b2.setEndBooking(LocalDate.parse("2022-09-25"));
        b2.setRoomNumber(1);
        List<Booking> booked = List.of(b1, b2);

        Booking b = new Booking();
        b.setUsername("name");
        b.setPhoneNumber("üöüöü");
        b.setStartBooking(LocalDate.parse("2022-09-30"));
        b.setEndBooking(LocalDate.parse("2022-10-12"));
        b.setRoomNumber(1);

        when(bookingService.create(any())).thenReturn(new Booking());

        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/book"))
                .andExpect(status().isOk()).andReturn();

        Assertions.assertNotNull(res);
        verify(bookingService, times(1)).create(b);
    }

    @Test
    void findAll() throws Exception {
        Booking b1 = new Booking();
        b1.setUsername("name");
        b1.setPhoneNumber("üöüöü");
        b1.setStartBooking(LocalDate.parse("2022-10-10"));
        b1.setEndBooking(LocalDate.parse("2022-10-15"));
        b1.setRoomNumber(1);
        Booking b2 = new Booking();
        b2.setUsername("name");
        b2.setPhoneNumber("üöüöü");
        b2.setStartBooking(LocalDate.parse("2022-09-15"));
        b2.setEndBooking(LocalDate.parse("2022-09-25"));
        b2.setRoomNumber(1);
        List<Booking> booked = List.of(b1, b2);

        when(bookingService.findAll()).thenReturn(booked);

        mockMvc.perform(MockMvcRequestBuilders.get("/book"))
                .andExpect(status().isOk());

        verify(bookingService, times(1)).findAll();
    }
}
*/
