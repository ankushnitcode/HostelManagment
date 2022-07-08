package com.example.BedManagement.Repository;

import com.example.BedManagement.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {
}
