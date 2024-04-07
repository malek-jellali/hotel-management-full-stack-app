package com.backend.hotelmanagement.Response;

import com.backend.hotelmanagement.model.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private Long bookingId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate ;

    private String guestName;

    private String guestEmail;


    private int nbAdults ;

    private int nbChildren;

    private int totalNbGuests ;

    private String bookingConfirmationCode;

    private RoomResponse room;

    public BookingResponse(Long bookingId , LocalDate checkInDate ,  LocalDate checkOutDate , String bookingConfirmationCode) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingConfirmationCode = bookingConfirmationCode;

    }

}
