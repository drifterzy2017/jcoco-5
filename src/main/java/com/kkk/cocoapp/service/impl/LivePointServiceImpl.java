package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.LivePointService;
import com.kkk.cocoapp.domain.LivePoint;
import com.kkk.cocoapp.repository.LivePointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing LivePoint.
 */
@Service
@Transactional
public class LivePointServiceImpl implements LivePointService {

    private final Logger log = LoggerFactory.getLogger(LivePointServiceImpl.class);

    private final LivePointRepository livePointRepository;

    public LivePointServiceImpl(LivePointRepository livePointRepository) {
        this.livePointRepository = livePointRepository;
    }

    /**
     * Save a livePoint.
     *
     * @param livePoint the entity to save
     * @return the persisted entity
     */
    @Override
    public LivePoint save(LivePoint livePoint) {
        log.debug("Request to save LivePoint : {}", livePoint);
        return livePointRepository.save(livePoint);
    }

    /**
     * Get all the livePoints.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LivePoint> findAll() {
        log.debug("Request to get all LivePoints");
        return livePointRepository.findAll();
    }


    /**
     * Get one livePoint by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LivePoint> findOne(Long id) {
        log.debug("Request to get LivePoint : {}", id);
        return livePointRepository.findById(id);
    }

    /**
     * Delete the livePoint by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LivePoint : {}", id);
        livePointRepository.deleteById(id);
    }
}
