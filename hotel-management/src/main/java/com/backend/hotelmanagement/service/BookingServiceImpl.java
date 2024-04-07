package com.backend.hotelmanagement.service;

import com.backend.hotelmanagement.exceptions.InvalidBookingRequestException;
import com.backend.hotelmanagement.exceptions.ResourceNotFoundException;
import com.backend.hotelmanagement.model.BookedRoom;
import com.backend.hotelmanagement.model.Room;
import com.backend.hotelmanagement.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository = null;
    private final RoomService roomService;
    public List<BookedRoom> getAllBookingsByRoomId(Long roomId){
        return bookingRepository.findByRoomId(roomId);


    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
        

    }

    @Override
    public List<BookedRoom> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public BookedRoom findByConfirmationCode(String confirmationCode) {
        return null;
    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
            throw new InvalidBookingRequestException("check-in date must come before check-out date !");

        }
        Room room = roomService.getRoomById(roomId).get();
        List<BookedRoom> existingBookings = room.getBookings();
        boolean roomIsAvailable = roomIsAvailable(bookingRequest, existingBookings);
        if(roomIsAvailable){
            room.addBooking(bookingRequest);
            bookingRepository.save(bookingRequest);
        }else{
            throw new  InvalidBookingRequestException("sorry, this room is not available for the selected dates ");
        }
        return bookingRequest.getBookingConfirmationCode() ;
    }



    @Override
    public BookedRoom findByBookingConfirmationCode(String confirmationCode) {
        return bookingRepository.findByBookingConfirmationCode(confirmationCode)
                .orElseThrow(() -> new ResourceNotFoundException("No booking found with booking code :"+confirmationCode));

    }

    private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
                );
    }
    }
}
