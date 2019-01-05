package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.DeviceMaskService;
import com.kkk.cocoapp.domain.DeviceMask;
import com.kkk.cocoapp.repository.DeviceMaskRepository;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing DeviceMask.
 */
@Service
@Transactional
public class DeviceMaskServiceImpl implements DeviceMaskService {

    private final Logger log = LoggerFactory.getLogger(DeviceMaskServiceImpl.class);

    private final DeviceMaskRepository deviceMaskRepository;

    public DeviceMaskServiceImpl(DeviceMaskRepository deviceMaskRepository) {
        this.deviceMaskRepository = deviceMaskRepository;
    }

    /**
     * Save a deviceMask.
     *
     * @param deviceMask the entity to save
     * @return the persisted entity
     */
    @Override
    public DeviceMask save(DeviceMask deviceMask) {
        log.debug("Request to save DeviceMask : {}", deviceMask);
        return deviceMaskRepository.save(deviceMask);
    }

    /**
     * Get all the deviceMasks.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DeviceMask> findAll() {
        log.debug("Request to get all DeviceMasks");
        return deviceMaskRepository.findAll();
    }


    /**
     * Get one deviceMask by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DeviceMask> findOne(Long id) {
        log.debug("Request to get DeviceMask : {}", id);
        return deviceMaskRepository.findById(id);
    }

    /**
     * Delete the deviceMask by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeviceMask : {}", id);
        deviceMaskRepository.deleteById(id);
    }

    @Override
    public List<DeviceMask> findAllByDeviceIdAndUserId(int deviceId, int userId){
        return deviceMaskRepository.findAllByDeviceIdAndUserId(deviceId,userId);
    }

    @Override
    public DeviceMask findById(Long id) {
//        return deviceMaskRepository.findOne(id);
        val rtn =  deviceMaskRepository.findById(id);
        return rtn.orElse(null);

    }

}
