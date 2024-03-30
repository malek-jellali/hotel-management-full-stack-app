package com.backend.hotelmanagement.Response;

import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;

//enables us to select the information to show at the frontend


    @Data
    @NoArgsConstructor
    public class RoomResponse {
        private Long id ;
        private BigDecimal price;
        private String type;


        private Boolean isBooked = false;

        @Lob
        private String photo;

        private List<com.backend.hotelmanagement.Response.BookingResponse> bookings ;

        public RoomResponse(Long id ,BigDecimal price, String type ) {
            this.id=id;
            this.type=type;
            this.price=price;
        }

        public RoomResponse(Long id , String type , BigDecimal price,boolean isBooked , byte[] photoByte , List<BookingResponse> bookings) {
            this.id=id;
            this.type=type;
            this.price=price;
            this.photo = photoByte != null ? Base64.encodeBase64String(photoByte) : null ;
            this.isBooked=isBooked;
            this.bookings = bookings;
        }
    }
