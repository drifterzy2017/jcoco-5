package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.CoreEventSeverity;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CoreEventSeverity.
 */
public interface CoreEventSeverityService {

    /**
     * Save a coreEventSeverity.
     *
     * @param coreEventSeverity the entity to save
     * @return the persisted entity
     */
    CoreEventSeverity save(CoreEventSeverity coreEventSeverity);

    /**
     * Get all the coreEventSeverities.
     *
     * @return the list of entities
     */
    List<CoreEventSeverity> findAll();


    /**
     * Get the "id" coreEventSeverity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CoreEventSeverity> findOne(Long id);

    /**
     * Delete the "id" coreEventSeverity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
