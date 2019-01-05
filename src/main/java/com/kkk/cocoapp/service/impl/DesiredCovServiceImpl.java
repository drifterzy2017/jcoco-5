package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.DesiredCovService;
import com.kkk.cocoapp.domain.DesiredCov;
import com.kkk.cocoapp.repository.DesiredCovRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing DesiredCov.
 */
@Service
@Transactional
public class DesiredCovServiceImpl implements DesiredCovService {

    private final Logger log = LoggerFactory.getLogger(DesiredCovServiceImpl.class);

    private final DesiredCovRepository desiredCovRepository;

    public DesiredCovServiceImpl(DesiredCovRepository desiredCovRepository) {
        this.desiredCovRepository = desiredCovRepository;
    }

    /**
     * Save a desiredCov.
     *
     * @param desiredCov the entity to save
     * @return the persisted entity
     */
    @Override
    public DesiredCov save(DesiredCov desiredCov) {
        log.debug("Request to save DesiredCov : {}", desiredCov);
        return desiredCovRepository.save(desiredCov);
    }

    /**
     * Get all the desiredCovs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DesiredCov> findAll() {
        log.debug("Request to get all DesiredCovs");
        return desiredCovRepository.findAll();
    }


    /**
     * Get one desiredCov by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DesiredCov> findOne(Long id) {
        log.debug("Request to get DesiredCov : {}", id);
        return desiredCovRepository.findById(id);
    }

    /**
     * Delete the desiredCov by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DesiredCov : {}", id);
        desiredCovRepository.deleteById(id);
    }
}
