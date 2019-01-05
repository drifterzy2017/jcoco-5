package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.DeviceMask;
import com.kkk.cocoapp.service.DeviceMaskService;
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
 * REST controller for managing DeviceMask.
 */
@RestController
@RequestMapping("/api")
public class DeviceMaskResource {

    private final Logger log = LoggerFactory.getLogger(DeviceMaskResource.class);

    private static final String ENTITY_NAME = "deviceMask";

    private final DeviceMaskService deviceMaskService;

    public DeviceMaskResource(DeviceMaskService deviceMaskService) {
        this.deviceMaskService = deviceMaskService;
    }

    /**
     * POST  /device-masks : Create a new deviceMask.
     *
     * @param deviceMask the deviceMask to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deviceMask, or with status 400 (Bad Request) if the deviceMask has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/device-masks")
    @Timed
    public ResponseEntity<DeviceMask> createDeviceMask(@RequestBody DeviceMask deviceMask) throws URISyntaxException {
        log.debug("REST request to save DeviceMask : {}", deviceMask);
        if (deviceMask.getId() != null) {
            throw new BadRequestAlertException("A new deviceMask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeviceMask result = deviceMaskService.save(deviceMask);
        return ResponseEntity.created(new URI("/api/device-masks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /device-masks : Updates an existing deviceMask.
     *
     * @param deviceMask the deviceMask to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deviceMask,
     * or with status 400 (Bad Request) if the deviceMask is not valid,
     * or with status 500 (Internal Server Error) if the deviceMask couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/device-masks")
    @Timed
    public ResponseEntity<DeviceMask> updateDeviceMask(@RequestBody DeviceMask deviceMask) throws URISyntaxException {
        log.debug("REST request to update DeviceMask : {}", deviceMask);
        if (deviceMask.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeviceMask result = deviceMaskService.save(deviceMask);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deviceMask.getId().toString()))
            .body(result);
    }

    /**
     * GET  /device-masks : get all the deviceMasks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of deviceMasks in body
     */
    @GetMapping("/device-masks")
    @Timed
    public List<DeviceMask> getAllDeviceMasks() {
        log.debug("REST request to get all DeviceMasks");
        return deviceMaskService.findAll();
    }

    /**
     * GET  /device-masks/:id : get the "id" deviceMask.
     *
     * @param id the id of the deviceMask to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deviceMask, or with status 404 (Not Found)
     */
    @GetMapping("/device-masks/{id}")
    @Timed
    public ResponseEntity<DeviceMask> getDeviceMask(@PathVariable Long id) {
        log.debug("REST request to get DeviceMask : {}", id);
        Optional<DeviceMask> deviceMask = deviceMaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deviceMask);
    }

    /**
     * DELETE  /device-masks/:id : delete the "id" deviceMask.
     *
     * @param id the id of the deviceMask to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/device-masks/{id}")
    @Timed
    public ResponseEntity<Void> deleteDeviceMask(@PathVariable Long id) {
        log.debug("REST request to delete DeviceMask : {}", id);
        deviceMaskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
