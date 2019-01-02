package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.QuizService;
import com.kkk.cocoapp.domain.Quiz;
import com.kkk.cocoapp.repository.QuizRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Quiz.
 */
@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    private final Logger log = LoggerFactory.getLogger(QuizServiceImpl.class);

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    /**
     * Save a quiz.
     *
     * @param quiz the entity to save
     * @return the persisted entity
     */
    @Override
    public Quiz save(Quiz quiz) {
        log.debug("Request to save Quiz : {}", quiz);
        return quizRepository.save(quiz);
    }

    /**
     * Get all the quizzes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Quiz> findAll() {
        log.debug("Request to get all Quizzes");
        return quizRepository.findAll();
    }


    /**
     * Get one quiz by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Quiz> findOne(Long id) {
        log.debug("Request to get Quiz : {}", id);
        return quizRepository.findById(id);
    }

    /**
     * Delete the quiz by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Quiz : {}", id);
        quizRepository.deleteById(id);
    }
}
