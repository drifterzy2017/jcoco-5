package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.LibQuestion;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing LibQuestion.
 */
public interface LibQuestionService {

    /**
     * Save a libQuestion.
     *
     * @param libQuestion the entity to save
     * @return the persisted entity
     */
    LibQuestion save(LibQuestion libQuestion);

    void flushAll();
    /**
     * Get all the libQuestions.
     *
     * @return the list of entities
     */
    List<LibQuestion> findAll();


    /**
     * Get the "id" libQuestion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LibQuestion> findOne(Long id);

    /**
     * Delete the "id" libQuestion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<LibQuestion> findAllByLibName(String libName);
}
