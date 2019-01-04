package com.kkk.cocoapp.service.impl.phx;

import com.kkk.cocoapp.domain.DeviceState;
import com.kkk.cocoapp.repository.DeviceStateRepository;
import com.kkk.cocoapp.service.DeviceService;
import com.kkk.cocoapp.domain.Device;
import com.kkk.cocoapp.repository.DeviceRepository;
import com.kkk.cocoapp.service.dto.DeviceStatusStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import lombok.val;
import lombok.Data;
/**
 * Service Implementation for managing Device.
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

    private final DeviceRepository deviceRepository;

    private final DeviceStateRepository deviceStateRepo;


    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceStateRepository deviceStateRepo) {
        this.deviceRepository = deviceRepository;
        this.deviceStateRepo = deviceStateRepo;
    }

    /**
     * Save a device.
     *
     * @param device the entity to save
     * @return the persisted entity
     */
    @Override
    public Device save(Device device) {
        log.debug("Request to save Device : {}", device);
        return deviceRepository.save(device);
    }

    /**
     * Get all the devices.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Device> findAll() {
        log.debug("Request to get all Devices");
        return deviceRepository.findAll();
    }


    /**
     * Get one device by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Device> findOne(Long id) {
        log.debug("Request to get Device : {}", id);
        return deviceRepository.findById(id);
    }

    /**
     * Delete the device by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Device : {}", id);
        deviceRepository.deleteById(id);
    }

    /**
     *
     * 实现PH
     * @return
     */
    @Override
    public ArrayList<DeviceStatusStatistics> getDeviceStatusStatistics(){
        List<Device> devices = deviceRepository.findAll();
        Map<Integer, Long> staticResult = devices.stream().collect(
            Collectors.groupingBy(
                Device::getStatus, Collectors.counting()
            )
        );
        ConcurrentHashMap<Integer,DeviceStatusStatistics> result = constructBasicDeviceStatusStatistic();

        for (Integer key : staticResult.keySet()) {
            if(result.containsKey(key)){
                val deviceStatusStatistics = new DeviceStatusStatistics(key,staticResult.get(key));
                result.put(key,deviceStatusStatistics);
            }
        }
        return  new ArrayList<>(result.values());

    }

    private ConcurrentHashMap<Integer,DeviceStatusStatistics> constructBasicDeviceStatusStatistic(){
        ConcurrentHashMap<Integer,DeviceStatusStatistics>   result = new ConcurrentHashMap<Integer,DeviceStatusStatistics>(0);

        //todo
        List<DeviceState> stateList = deviceStateRepo.findAll();
        for(DeviceState state:stateList){
            DeviceStatusStatistics deviceStatusStatistics = new DeviceStatusStatistics(state.getStateId(),0);
            result.put(deviceStatusStatistics.getStateId(),deviceStatusStatistics);
        }

        return  result;
    }
}
