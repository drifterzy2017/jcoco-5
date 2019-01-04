package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.CoreSource;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CoreSource.
 */
public interface CoreSourceService {

    /**
     * Save a coreSource.
     *
     * @param coreSource the entity to save
     * @return the persisted entity
     */
    CoreSource save(CoreSource coreSource);

    /**
     * Get all the coreSources.
     *
     * @return the list of entities
     */
    List<CoreSource> findAll();


    /**
     * Get the "id" coreSource.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CoreSource> findOne(Long id);

    /**
     * Delete the "id" coreSource.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
