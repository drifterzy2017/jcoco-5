package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.DesiredCov;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DesiredCov.
 */
public interface DesiredCovService {

    /**
     * Save a desiredCov.
     *
     * @param desiredCov the entity to save
     * @return the persisted entity
     */
    DesiredCov save(DesiredCov desiredCov);

    /**
     * Get all the desiredCovs.
     *
     * @return the list of entities
     */
    List<DesiredCov> findAll();


    /**
     * Get the "id" desiredCov.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DesiredCov> findOne(Long id);

    /**
     * Delete the "id" desiredCov.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
