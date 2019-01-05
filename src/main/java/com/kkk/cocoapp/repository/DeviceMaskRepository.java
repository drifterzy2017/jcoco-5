package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.DeviceMask;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the DeviceMask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeviceMaskRepository extends JpaRepository<DeviceMask, Long> {

    List<DeviceMask> findAllByDeviceIdAndUserId(int deviceId, int userId);
}
