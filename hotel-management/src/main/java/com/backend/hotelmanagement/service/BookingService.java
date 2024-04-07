package com.backend.hotelmanagement.service;

import com.backend.hotelmanagement.Response.BookingResponse;
import com.backend.hotelmanagement.model.BookedRoom;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    void cancelBooking(Long bookingId);

    List<BookedRoom> getAllBookings();

    BookedRoom  findByConfirmationCode(String confirmationCode);

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    BookedRoom findByBookingConfirmationCode(String confirmationCode);
}


