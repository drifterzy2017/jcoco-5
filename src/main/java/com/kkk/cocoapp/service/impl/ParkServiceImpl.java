package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.ParkService;
import com.kkk.cocoapp.domain.Park;
import com.kkk.cocoapp.repository.ParkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Park.
 */
@Service
@Transactional
public class ParkServiceImpl implements ParkService {

    private final Logger log = LoggerFactory.getLogger(ParkServiceImpl.class);

    private final ParkRepository parkRepository;

    public ParkServiceImpl(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    /**
     * Save a park.
     *
     * @param park the entity to save
     * @return the persisted entity
     */
    @Override
    public Park save(Park park) {
        log.debug("Request to save Park : {}", park);
        return parkRepository.save(park);
    }

    /**
     * Get all the parks.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Park> findAll() {
        log.debug("Request to get all Parks");
        return parkRepository.findAll();
    }


    /**
     * Get one park by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Park> findOne(Long id) {
        log.debug("Request to get Park : {}", id);
        return parkRepository.findById(id);
    }

    /**
     * Delete the park by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Park : {}", id);
        parkRepository.deleteById(id);
    }
}
