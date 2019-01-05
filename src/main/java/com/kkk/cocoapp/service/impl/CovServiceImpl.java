package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.CovService;
import com.kkk.cocoapp.domain.Cov;
import com.kkk.cocoapp.repository.CovRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Cov.
 */
@Service
@Transactional
public class CovServiceImpl implements CovService {

    private final Logger log = LoggerFactory.getLogger(CovServiceImpl.class);

    private final CovRepository covRepository;

    public CovServiceImpl(CovRepository covRepository) {
        this.covRepository = covRepository;
    }

    /**
     * Save a cov.
     *
     * @param cov the entity to save
     * @return the persisted entity
     */
    @Override
    public Cov save(Cov cov) {
        log.debug("Request to save Cov : {}", cov);
        return covRepository.save(cov);
    }

    /**
     * Get all the covs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Cov> findAll() {
        log.debug("Request to get all Covs");
        return covRepository.findAll();
    }


    /**
     * Get one cov by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Cov> findOne(Long id) {
        log.debug("Request to get Cov : {}", id);
        return covRepository.findById(id);
    }

    /**
     * Delete the cov by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cov : {}", id);
        covRepository.deleteById(id);
    }
}
