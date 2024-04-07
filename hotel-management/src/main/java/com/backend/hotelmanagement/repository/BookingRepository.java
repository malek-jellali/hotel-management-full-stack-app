package com.backend.hotelmanagement.repository;

import com.backend.hotelmanagement.model.BookedRoom;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    List<BookedRoom> findByRoomId(Long roomId);

    Optional<BookedRoom> findByBookingConfirmationCode(String confirmationCode);

    List<BookedRoom> findByGuestEmail(String email);

    void save(BookedRoom bookingRequest);

    void deleteById(Long bookingId);

    List<BookedRoom> findAll();
}
