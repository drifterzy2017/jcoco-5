package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.DeviceMask;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DeviceMask.
 */
public interface DeviceMaskService {

    /**
     * Save a deviceMask.
     *
     * @param deviceMask the entity to save
     * @return the persisted entity
     */
    DeviceMask save(DeviceMask deviceMask);

    /**
     * Get all the deviceMasks.
     *
     * @return the list of entities
     */
    List<DeviceMask> findAll();


    /**
     * Get the "id" deviceMask.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DeviceMask> findOne(Long id);

    /**
     * Delete the "id" deviceMask.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    DeviceMask findById(Long id);
    List<DeviceMask> findAllByDeviceIdAndUserId(int deviceId, int userId);
}
