package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.EventStaticByDay;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EventStaticByDay.
 */
public interface EventStaticByDayService {

    /**
     * Save a eventStaticByDay.
     *
     * @param eventStaticByDay the entity to save
     * @return the persisted entity
     */
    EventStaticByDay save(EventStaticByDay eventStaticByDay);

    /**
     * Get all the eventStaticByDays.
     *
     * @return the list of entities
     */
    List<EventStaticByDay> findAll();


    /**
     * Get the "id" eventStaticByDay.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EventStaticByDay> findOne(Long id);

    /**
     * Delete the "id" eventStaticByDay.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<EventStaticByDay> FindEventStaticLast(int n);
}
