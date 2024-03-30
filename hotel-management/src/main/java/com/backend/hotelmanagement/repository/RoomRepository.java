package com.backend.hotelmanagement.repository;


import com.backend.hotelmanagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long > {
}
