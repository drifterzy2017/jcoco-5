package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.Device;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Device entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllByRoomId(Long roomId);
}
