package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.CorePointService;
import com.kkk.cocoapp.domain.CorePoint;
import com.kkk.cocoapp.repository.CorePointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing CorePoint.
 */
@Service
@Transactional
public class CorePointServiceImpl implements CorePointService {

    private final Logger log = LoggerFactory.getLogger(CorePointServiceImpl.class);

    private final CorePointRepository corePointRepository;

    public CorePointServiceImpl(CorePointRepository corePointRepository) {
        this.corePointRepository = corePointRepository;
    }

    /**
     * Save a corePoint.
     *
     * @param corePoint the entity to save
     * @return the persisted entity
     */
    @Override
    public CorePoint save(CorePoint corePoint) {
        log.debug("Request to save CorePoint : {}", corePoint);
        return corePointRepository.save(corePoint);
    }

    /**
     * Get all the corePoints.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CorePoint> findAll() {
        log.debug("Request to get all CorePoints");
        return corePointRepository.findAll();
    }


    /**
     * Get one corePoint by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CorePoint> findOne(Long id) {
        log.debug("Request to get CorePoint : {}", id);
        return corePointRepository.findById(id);
    }

    /**
     * Delete the corePoint by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CorePoint : {}", id);
        corePointRepository.deleteById(id);
    }
}
