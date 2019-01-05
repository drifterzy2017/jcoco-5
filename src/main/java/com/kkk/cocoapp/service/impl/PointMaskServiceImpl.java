package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.PointMaskService;
import com.kkk.cocoapp.domain.PointMask;
import com.kkk.cocoapp.repository.PointMaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing PointMask.
 */
@Service
@Transactional
public class PointMaskServiceImpl implements PointMaskService {

    private final Logger log = LoggerFactory.getLogger(PointMaskServiceImpl.class);

    private final PointMaskRepository pointMaskRepository;

    public PointMaskServiceImpl(PointMaskRepository pointMaskRepository) {
        this.pointMaskRepository = pointMaskRepository;
    }

    /**
     * Save a pointMask.
     *
     * @param pointMask the entity to save
     * @return the persisted entity
     */
    @Override
    public PointMask save(PointMask pointMask) {
        log.debug("Request to save PointMask : {}", pointMask);
        return pointMaskRepository.save(pointMask);
    }

    /**
     * Get all the pointMasks.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PointMask> findAll() {
        log.debug("Request to get all PointMasks");
        return pointMaskRepository.findAll();
    }


    /**
     * Get one pointMask by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PointMask> findOne(Long id) {
        log.debug("Request to get PointMask : {}", id);
        return pointMaskRepository.findById(id);
    }

    /**
     * Delete the pointMask by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PointMask : {}", id);
        pointMaskRepository.deleteById(id);
    }

    @Override
    public List<PointMask> findAllByDeviceIdAndUserId(int userId, int deviceId){
        return  pointMaskRepository.findAllByDeviceIdAndUserId(userId,deviceId);
    }
}
