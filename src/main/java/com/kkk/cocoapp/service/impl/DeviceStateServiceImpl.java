package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.DeviceStateService;
import com.kkk.cocoapp.domain.DeviceState;
import com.kkk.cocoapp.repository.DeviceStateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing DeviceState.
 */
@Service
@Transactional
public class DeviceStateServiceImpl implements DeviceStateService {

    private final Logger log = LoggerFactory.getLogger(DeviceStateServiceImpl.class);

    private final DeviceStateRepository deviceStateRepository;

    public DeviceStateServiceImpl(DeviceStateRepository deviceStateRepository) {
        this.deviceStateRepository = deviceStateRepository;
    }

    /**
     * Save a deviceState.
     *
     * @param deviceState the entity to save
     * @return the persisted entity
     */
    @Override
    public DeviceState save(DeviceState deviceState) {
        log.debug("Request to save DeviceState : {}", deviceState);
        return deviceStateRepository.save(deviceState);
    }

    /**
     * Get all the deviceStates.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DeviceState> findAll() {
        log.debug("Request to get all DeviceStates");
        return deviceStateRepository.findAll();
    }


    /**
     * Get one deviceState by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DeviceState> findOne(Long id) {
        log.debug("Request to get DeviceState : {}", id);
        return deviceStateRepository.findById(id);
    }

    /**
     * Delete the deviceState by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeviceState : {}", id);
        deviceStateRepository.deleteById(id);
    }
}
