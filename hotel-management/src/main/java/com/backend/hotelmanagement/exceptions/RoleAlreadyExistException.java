package com.backend.hotelmanagement.exceptions;

public class RoleAlreadyExistException extends RuntimeException{
    public RoleAlreadyExistException(String message){
        super(message);
    }
}
