package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.DeviceState;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DeviceState.
 */
public interface DeviceStateService {

    /**
     * Save a deviceState.
     *
     * @param deviceState the entity to save
     * @return the persisted entity
     */
    DeviceState save(DeviceState deviceState);

    /**
     * Get all the deviceStates.
     *
     * @return the list of entities
     */
    List<DeviceState> findAll();


    /**
     * Get the "id" deviceState.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DeviceState> findOne(Long id);

    /**
     * Delete the "id" deviceState.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
