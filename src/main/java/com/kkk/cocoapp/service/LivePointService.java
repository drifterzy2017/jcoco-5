package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.LivePoint;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing LivePoint.
 */
public interface LivePointService {

    /**
     * Save a livePoint.
     *
     * @param livePoint the entity to save
     * @return the persisted entity
     */
    LivePoint save(LivePoint livePoint);

    /**
     * Get all the livePoints.
     *
     * @return the list of entities
     */
    List<LivePoint> findAll();


    /**
     * Get the "id" livePoint.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LivePoint> findOne(Long id);

    /**
     * Delete the "id" livePoint.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
