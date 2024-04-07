package com.backend.hotelmanagement.exceptions;

public class InvalidBookingRequestException extends RuntimeException{
public InvalidBookingRequestException(String message){
    super(message);
}
}
