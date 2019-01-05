package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.CorePoint;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CorePoint.
 */
public interface CorePointService {

    /**
     * Save a corePoint.
     *
     * @param corePoint the entity to save
     * @return the persisted entity
     */
    CorePoint save(CorePoint corePoint);

    /**
     * Get all the corePoints.
     *
     * @return the list of entities
     */
    List<CorePoint> findAll();


    /**
     * Get the "id" corePoint.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CorePoint> findOne(Long id);

    /**
     * Delete the "id" corePoint.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
