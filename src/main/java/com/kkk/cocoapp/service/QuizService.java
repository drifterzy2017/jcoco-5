package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.Quiz;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Quiz.
 */
public interface QuizService {

    /**
     * Save a quiz.
     *
     * @param quiz the entity to save
     * @return the persisted entity
     */
    Quiz save(Quiz quiz);

    /**
     * Get all the quizzes.
     *
     * @return the list of entities
     */
    List<Quiz> findAll();


    /**
     * Get the "id" quiz.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Quiz> findOne(Long id);

    /**
     * Delete the "id" quiz.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
