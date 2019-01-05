package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.CorePointMeaningService;
import com.kkk.cocoapp.domain.CorePointMeaning;
import com.kkk.cocoapp.repository.CorePointMeaningRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing CorePointMeaning.
 */
@Service
@Transactional
public class CorePointMeaningServiceImpl implements CorePointMeaningService {

    private final Logger log = LoggerFactory.getLogger(CorePointMeaningServiceImpl.class);

    private final CorePointMeaningRepository corePointMeaningRepository;

    public CorePointMeaningServiceImpl(CorePointMeaningRepository corePointMeaningRepository) {
        this.corePointMeaningRepository = corePointMeaningRepository;
    }

    /**
     * Save a corePointMeaning.
     *
     * @param corePointMeaning the entity to save
     * @return the persisted entity
     */
    @Override
    public CorePointMeaning save(CorePointMeaning corePointMeaning) {
        log.debug("Request to save CorePointMeaning : {}", corePointMeaning);
        return corePointMeaningRepository.save(corePointMeaning);
    }

    /**
     * Get all the corePointMeanings.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CorePointMeaning> findAll() {
        log.debug("Request to get all CorePointMeanings");
        return corePointMeaningRepository.findAll();
    }


    /**
     * Get one corePointMeaning by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CorePointMeaning> findOne(Long id) {
        log.debug("Request to get CorePointMeaning : {}", id);
        return corePointMeaningRepository.findById(id);
    }

    /**
     * Delete the corePointMeaning by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CorePointMeaning : {}", id);
        corePointMeaningRepository.deleteById(id);
    }
}
