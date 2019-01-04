package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.LiveEvent;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing LiveEvent.
 */
public interface LiveEventService {

    /**
     * Save a liveEvent.
     *
     * @param liveEvent the entity to save
     * @return the persisted entity
     */
    LiveEvent save(LiveEvent liveEvent);

    /**
     * Get all the liveEvents.
     *
     * @return the list of entities
     */
    List<LiveEvent> findAll();


    /**
     * Get the "id" liveEvent.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LiveEvent> findOne(Long id);

    /**
     * Delete the "id" liveEvent.
     *
     * @param id the id of the entity
     */
    void delete(Long id);


    List<LiveEvent> getEventByLastest(int recordNumber);
}
