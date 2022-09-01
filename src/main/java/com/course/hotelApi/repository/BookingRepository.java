package com.course.hotelApi.repository;

import com.course.hotelApi.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT COUNT(b) = 0 FROM Booking b WHERE " +
            "(:startDate BETWEEN b.startBooking AND b.endBooking AND :endDate BETWEEN b.startBooking AND b.endBooking)" +
            "OR (:startDate <= b.startBooking AND :endDate BETWEEN b.startBooking AND b.endBooking) " +
            "OR (:startDate BETWEEN b.startBooking AND b.endBooking AND :endDate >= b.endBooking)" +
            "OR (:startDate <= b.startBooking AND :endDate >= b.endBooking)" +
            "AND b.roomNumber = :number")
    Boolean isAvailable(LocalDate startDate, LocalDate endDate, Integer number);
}
