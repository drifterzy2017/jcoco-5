package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.Cov;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Cov.
 */
public interface CovService {

    /**
     * Save a cov.
     *
     * @param cov the entity to save
     * @return the persisted entity
     */
    Cov save(Cov cov);

    /**
     * Get all the covs.
     *
     * @return the list of entities
     */
    List<Cov> findAll();


    /**
     * Get the "id" cov.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Cov> findOne(Long id);

    /**
     * Delete the "id" cov.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
