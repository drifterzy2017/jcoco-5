package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.CorePointMeaning;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CorePointMeaning.
 */
public interface CorePointMeaningService {

    /**
     * Save a corePointMeaning.
     *
     * @param corePointMeaning the entity to save
     * @return the persisted entity
     */
    CorePointMeaning save(CorePointMeaning corePointMeaning);

    /**
     * Get all the corePointMeanings.
     *
     * @return the list of entities
     */
    List<CorePointMeaning> findAll();


    /**
     * Get the "id" corePointMeaning.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CorePointMeaning> findOne(Long id);

    /**
     * Delete the "id" corePointMeaning.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
