package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.DeviceState;
import com.kkk.cocoapp.service.DeviceStateService;
import com.kkk.cocoapp.web.rest.errors.BadRequestAlertException;
import com.kkk.cocoapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DeviceState.
 */
@RestController
@RequestMapping("/api")
public class DeviceStateResource {

    private final Logger log = LoggerFactory.getLogger(DeviceStateResource.class);

    private static final String ENTITY_NAME = "deviceState";

    private final DeviceStateService deviceStateService;

    public DeviceStateResource(DeviceStateService deviceStateService) {
        this.deviceStateService = deviceStateService;
    }

    /**
     * POST  /device-states : Create a new deviceState.
     *
     * @param deviceState the deviceState to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deviceState, or with status 400 (Bad Request) if the deviceState has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/device-states")
    @Timed
    public ResponseEntity<DeviceState> createDeviceState(@RequestBody DeviceState deviceState) throws URISyntaxException {
        log.debug("REST request to save DeviceState : {}", deviceState);
        if (deviceState.getId() != null) {
            throw new BadRequestAlertException("A new deviceState cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeviceState result = deviceStateService.save(deviceState);
        return ResponseEntity.created(new URI("/api/device-states/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /device-states : Updates an existing deviceState.
     *
     * @param deviceState the deviceState to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deviceState,
     * or with status 400 (Bad Request) if the deviceState is not valid,
     * or with status 500 (Internal Server Error) if the deviceState couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/device-states")
    @Timed
    public ResponseEntity<DeviceState> updateDeviceState(@RequestBody DeviceState deviceState) throws URISyntaxException {
        log.debug("REST request to update DeviceState : {}", deviceState);
        if (deviceState.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeviceState result = deviceStateService.save(deviceState);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deviceState.getId().toString()))
            .body(result);
    }

    /**
     * GET  /device-states : get all the deviceStates.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of deviceStates in body
     */
    @GetMapping("/device-states")
    @Timed
    public List<DeviceState> getAllDeviceStates() {
        log.debug("REST request to get all DeviceStates");
        return deviceStateService.findAll();
    }

    /**
     * GET  /device-states/:id : get the "id" deviceState.
     *
     * @param id the id of the deviceState to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deviceState, or with status 404 (Not Found)
     */
    @GetMapping("/device-states/{id}")
    @Timed
    public ResponseEntity<DeviceState> getDeviceState(@PathVariable Long id) {
        log.debug("REST request to get DeviceState : {}", id);
        Optional<DeviceState> deviceState = deviceStateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deviceState);
    }

    /**
     * DELETE  /device-states/:id : delete the "id" deviceState.
     *
     * @param id the id of the deviceState to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/device-states/{id}")
    @Timed
    public ResponseEntity<Void> deleteDeviceState(@PathVariable Long id) {
        log.debug("REST request to delete DeviceState : {}", id);
        deviceStateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
