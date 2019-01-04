package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.Floor;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Floor.
 */
public interface FloorService {

    /**
     * Save a floor.
     *
     * @param floor the entity to save
     * @return the persisted entity
     */
    Floor save(Floor floor);

    /**
     * Get all the floors.
     *
     * @return the list of entities
     */
    List<Floor> findAll();


    /**
     * Get the "id" floor.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Floor> findOne(Long id);

    /**
     * Delete the "id" floor.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
