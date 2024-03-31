package com.backend.hotelmanagement.controller;

import com.backend.hotelmanagement.Response.RoomResponse;
import com.backend.hotelmanagement.model.Room;
import com.backend.hotelmanagement.service.RoomService;
import com.backend.hotelmanagement.service.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
private final RoomServiceImpl roomService ;


@PostMapping("/add/new-room")
    public ResponseEntity<RoomResponse> addNewRoom(
            @RequestParam("photo") MultipartFile photo ,
            @RequestParam("roomType") String roomType ,
            @RequestParam("roomPrice") BigDecimal roomPrice ) throws SQLException, IOException {
        Room savedRoom = roomService.addNewRoom(photo, roomType , roomPrice);
        RoomResponse response= new RoomResponse(savedRoom.getId(), savedRoom.getPrice(), savedRoom.getType());


        return ResponseEntity.ok(response);
    }
@GetMapping("/room/types")
    public List<String> getRoomTypes(){
    return roomService.getAllRoomTypes();

    }

}
