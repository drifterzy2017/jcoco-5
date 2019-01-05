package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.LibQuestion;
import com.kkk.cocoapp.domain.Room;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Room entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByFloorId(Long floorId);
}
