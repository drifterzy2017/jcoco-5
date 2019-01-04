package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.FloorService;
import com.kkk.cocoapp.domain.Floor;
import com.kkk.cocoapp.repository.FloorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Floor.
 */
@Service
@Transactional
public class FloorServiceImpl implements FloorService {

    private final Logger log = LoggerFactory.getLogger(FloorServiceImpl.class);

    private final FloorRepository floorRepository;

    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    /**
     * Save a floor.
     *
     * @param floor the entity to save
     * @return the persisted entity
     */
    @Override
    public Floor save(Floor floor) {
        log.debug("Request to save Floor : {}", floor);
        return floorRepository.save(floor);
    }

    /**
     * Get all the floors.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Floor> findAll() {
        log.debug("Request to get all Floors");
        return floorRepository.findAll();
    }


    /**
     * Get one floor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Floor> findOne(Long id) {
        log.debug("Request to get Floor : {}", id);
        return floorRepository.findById(id);
    }

    /**
     * Delete the floor by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Floor : {}", id);
        floorRepository.deleteById(id);
    }
}
