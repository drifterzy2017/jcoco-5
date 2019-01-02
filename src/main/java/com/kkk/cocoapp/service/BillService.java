package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.Bill;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Bill.
 */
public interface BillService {

    /**
     * Save a bill.
     *
     * @param bill the entity to save
     * @return the persisted entity
     */
    Bill save(Bill bill);

    /**
     * Get all the bills.
     *
     * @return the list of entities
     */
    List<Bill> findAll();


    /**
     * Get the "id" bill.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Bill> findOne(Long id);

    /**
     * Delete the "id" bill.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
