package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.CoreSourceService;
import com.kkk.cocoapp.domain.CoreSource;
import com.kkk.cocoapp.repository.CoreSourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing CoreSource.
 */
@Service
@Transactional
public class CoreSourceServiceImpl implements CoreSourceService {

    private final Logger log = LoggerFactory.getLogger(CoreSourceServiceImpl.class);

    private final CoreSourceRepository coreSourceRepository;

    public CoreSourceServiceImpl(CoreSourceRepository coreSourceRepository) {
        this.coreSourceRepository = coreSourceRepository;
    }

    /**
     * Save a coreSource.
     *
     * @param coreSource the entity to save
     * @return the persisted entity
     */
    @Override
    public CoreSource save(CoreSource coreSource) {
        log.debug("Request to save CoreSource : {}", coreSource);
        return coreSourceRepository.save(coreSource);
    }

    /**
     * Get all the coreSources.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CoreSource> findAll() {
        log.debug("Request to get all CoreSources");
        return coreSourceRepository.findAll();
    }


    /**
     * Get one coreSource by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CoreSource> findOne(Long id) {
        log.debug("Request to get CoreSource : {}", id);
        return coreSourceRepository.findById(id);
    }

    /**
     * Delete the coreSource by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CoreSource : {}", id);
        coreSourceRepository.deleteById(id);
    }
}
