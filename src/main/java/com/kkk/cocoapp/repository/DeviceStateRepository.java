package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.DeviceState;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DeviceState entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeviceStateRepository extends JpaRepository<DeviceState, Long> {

}
