package com.backend.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.Lazy;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
@Column(name="check_In")
    private LocalDate checkInDate;
@Column(name="check_Out")
    private LocalDate checkOutDate ;

    @Column(name="guest_name")
    private String guestName;

    @Column(name="guest_email")
    private String guestEmail;

    @Column(name="adults")
    private int nbAdults ;

    @Column(name="children")
    private int nbChildren;

    @Column(name="total_guests")
    private int totalNbGuests ;

    @Column(name="confirmation_code")
    private String bookingConfirmationCode;

    @ManyToOne (fetch= FetchType.LAZY)
    @JoinColumn (name="room_Id")
     private Room room;

    public void setTotalnbGuests() {
        this.totalNbGuests = this.nbAdults + this.nbChildren;
    }

    public void setNbChildren(int nbChildren) {
        this.nbChildren = nbChildren;
        setTotalnbGuests();
    }

    public void setNbAdults(int nbAdults) {
        this.nbAdults = nbAdults;
        setTotalnbGuests();
    }

    public BookedRoom(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
