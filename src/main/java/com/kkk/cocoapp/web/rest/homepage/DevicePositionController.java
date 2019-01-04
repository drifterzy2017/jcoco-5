package com.kkk.cocoapp.web.rest.homepage;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.service.DevicePositionService;
import com.kkk.cocoapp.service.dto.overview.DevicePosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DevicePositionController {
    private final Logger log = LoggerFactory.getLogger(DevicePositionController.class);
    @Autowired
    DevicePositionService devicePositionService;
    /**
     * GET  :
     *
     * @return the ResponseEntity with status 201 (Created) and with body the new ParkInfo, or with status 400 (Bad Request) if the liveEvent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @GetMapping("/devicepostions")
    @Timed
    public List<DevicePosition> getDevicePositionInfo() throws URISyntaxException {
        log.debug("REST request get all device position overview");
        //获取当前所有告警设备的详细信息
        List<DevicePosition>  result = devicePositionService.getAll();
        return  result;
    }
}
