package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.PointMask;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the PointMask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PointMaskRepository extends JpaRepository<PointMask, Long> {
    List<PointMask> findAllByDeviceIdAndUserId(int deviceId, int userId);
}
