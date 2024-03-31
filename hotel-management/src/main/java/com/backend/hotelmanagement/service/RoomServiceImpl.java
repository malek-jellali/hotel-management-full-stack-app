package com.backend.hotelmanagement.service;

import com.backend.hotelmanagement.model.Room;
import com.backend.hotelmanagement.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository  ;
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws SQLException, IOException {
        Room room = new Room();
        room.setType(roomType);
        room.setPrice(roomPrice);
        if (!file.isEmpty()){
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }
        return roomRepository.save(room) ;
    }

    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();


    }
}
