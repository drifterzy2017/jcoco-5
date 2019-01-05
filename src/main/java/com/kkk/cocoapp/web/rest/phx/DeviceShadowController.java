package com.kkk.cocoapp.web.rest.phx;


import com.kkk.cocoapp.service.DeviceService;
import com.kkk.cocoapp.service.shadow.ShadowService;
import com.kkk.cocoapp.service.shadow.DeviceShadow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DeviceShadowController {
    private final Logger log = LoggerFactory.getLogger(DeviceShadowController.class);
    @Autowired
    private ShadowService shadowService;
    @Autowired
    private DeviceService deviceService;


    /**
     * GET  /deviceshadows/{deviceId} : get the "deviceId" dto.
     *
     * @param deviceId the id of the dto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the DeviceShadow, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/deviceshadows/{deviceId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceShadow> getDeviceShadowById(@PathVariable Integer deviceId) {
        log.debug("REST request to get DeviceShadow : {}", deviceId);
        DeviceShadow deviceShadow = shadowService.findDeviceShadowByDeviceId(new Long((long)deviceId));
        return Optional.ofNullable(deviceShadow)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
