package com.kkk.cocoapp.service.impl.phx;

import com.kkk.cocoapp.service.CoreEventSeverityService;
import com.kkk.cocoapp.domain.CoreEventSeverity;
import com.kkk.cocoapp.repository.CoreEventSeverityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing CoreEventSeverity.
 */
@Service
@Transactional
public class CoreEventSeverityServiceImpl implements CoreEventSeverityService {

    private final Logger log = LoggerFactory.getLogger(CoreEventSeverityServiceImpl.class);

    private final CoreEventSeverityRepository coreEventSeverityRepository;

    public CoreEventSeverityServiceImpl(CoreEventSeverityRepository coreEventSeverityRepository) {
        this.coreEventSeverityRepository = coreEventSeverityRepository;
    }

    /**
     * Save a coreEventSeverity.
     *
     * @param coreEventSeverity the entity to save
     * @return the persisted entity
     */
    @Override
    public CoreEventSeverity save(CoreEventSeverity coreEventSeverity) {
        log.debug("Request to save CoreEventSeverity : {}", coreEventSeverity);
        return coreEventSeverityRepository.save(coreEventSeverity);
    }

    /**
     * Get all the coreEventSeverities.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CoreEventSeverity> findAll() {
        log.debug("Request to get all CoreEventSeverities");
        return coreEventSeverityRepository.findAll();
    }


    /**
     * Get one coreEventSeverity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CoreEventSeverity> findOne(Long id) {
        log.debug("Request to get CoreEventSeverity : {}", id);
        return coreEventSeverityRepository.findById(id);
    }

    /**
     * Delete the coreEventSeverity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CoreEventSeverity : {}", id);
        coreEventSeverityRepository.deleteById(id);
    }
}
