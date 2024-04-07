package com.backend.hotelmanagement.service;

import com.backend.hotelmanagement.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomService {


    List<String> getAllRoomTypes();

    List<Room> getAllRooms();

    byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException;

    void deleteRoom(Long roomId);

    Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes);

    Optional<Room> getRoomById(Long roomId);

    List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType);


}
