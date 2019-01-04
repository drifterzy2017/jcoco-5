package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.Park;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Park.
 */
public interface ParkService {

    /**
     * Save a park.
     *
     * @param park the entity to save
     * @return the persisted entity
     */
    Park save(Park park);

    /**
     * Get all the parks.
     *
     * @return the list of entities
     */
    List<Park> findAll();


    /**
     * Get the "id" park.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Park> findOne(Long id);

    /**
     * Delete the "id" park.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
