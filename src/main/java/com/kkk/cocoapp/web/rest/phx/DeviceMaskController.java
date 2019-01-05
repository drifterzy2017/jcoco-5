package com.kkk.cocoapp.web.rest.phx;


import com.kkk.cocoapp.domain.DeviceMask;
import com.kkk.cocoapp.service.DeviceMaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DeviceMaskController {
    private final Logger log = LoggerFactory.getLogger(DeviceMaskController.class);
    @Autowired
    private DeviceMaskService deviceMaskService;

    /**
     * POST  /devicemasks : Create a new deviceMask.
     *
     * @param deviceMask the deviceMask to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deviceMask, or with status 400 (Bad Request) if the deviceMask has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/devicemasks",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceMask> createDeviceMask(@RequestBody @Valid DeviceMask deviceMask) throws URISyntaxException {
        log.debug("REST request to save DeviceMask : {}", deviceMask);
        if (deviceMask.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("deviceMask", "idexists", "A new DeviceMask cannot already have an ID")).body(null);
        }
        DeviceMask result = deviceMaskService.save(deviceMask);
        return ResponseEntity.created(new URI("/api/devicemasks/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("DeviceMask", String.valueOf( result.getId())))
                .body(result);
    }

    /**
     * PUT  /devicemasks : Updates an existing devicemasks.
     *
     * @param deviceMask the device to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated devicemasks,
     * or with status 400 (Bad Request) if the device is not valid,
     * or with status 500 (Internal Server Error) if the device couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/devicemasks",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceMask> updateDeviceMask(@Valid @RequestBody DeviceMask deviceMask) throws URISyntaxException {
        log.debug("REST request to update DeviceMask : {}", deviceMask);
        if (deviceMask.getId() == null) {
            return createDeviceMask(deviceMask);
        }
        DeviceMask result = deviceMaskService.save(deviceMask);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("DeviceMask", String.valueOf( result.getId())))
                .body(result);
    }

    /**
     * GET  /devicemasks : get all the deviceMasks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of deviceMasks in body
     */
    @RequestMapping(value = "/devicemasks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceMask> getAllDeviceMasks() {
        log.debug("REST request to get all DeviceMask");
        List<DeviceMask> deviceMasks = deviceMaskService.findAll();
        return deviceMasks;
    }

    /**
     * GET  /devicemasks/:id : get the "id" devicemask.
     *
     * @param id the id of the devicemask to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the device, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/devicemasks/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceMask> getDeviceMask(@PathVariable Long id) {
        log.debug("REST request to get DeviceMask : {}", id);
        DeviceMask deviceMask = deviceMaskService.findById(id);
        return Optional.ofNullable(deviceMask)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /devicemasks/:id : delete the "id" devicemask.
     *
     * @param id the id of the devicemask to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/devicemasks/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteDeviceMask(@PathVariable Long id) {
        log.debug("REST request to delete DeviceMask : {}", id);
        deviceMaskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("DeviceMask", id.toString())).build();
    }
    /**
     * GET  /devicemasks : get all the deviceMasks by userid and deviceid.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of deviceMasks in body
     */
    @RequestMapping(value = "/devicemasks/query",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceMask> getDeviceMasksByUserIdAndDeviceId( int userId,
                                                                int deviceId) {
        log.debug("REST request to get all devicemasks by userid and deviceid");
        List<DeviceMask> deviceMasks = deviceMaskService.findAllByDeviceIdAndUserId(deviceId,userId);
        return deviceMasks;
    }
}
