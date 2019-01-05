package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.PointMask;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PointMask.
 */
public interface PointMaskService {

    /**
     * Save a pointMask.
     *
     * @param pointMask the entity to save
     * @return the persisted entity
     */
    PointMask save(PointMask pointMask);

    /**
     * Get all the pointMasks.
     *
     * @return the list of entities
     */
    List<PointMask> findAll();


    /**
     * Get the "id" pointMask.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PointMask> findOne(Long id);

    /**
     * Delete the "id" pointMask.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<PointMask> findAllByDeviceIdAndUserId(int deviceId, int userId);
}
