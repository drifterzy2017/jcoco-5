package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.CorePoint;
import com.kkk.cocoapp.service.CorePointService;
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
 * REST controller for managing CorePoint.
 */
@RestController
@RequestMapping("/api")
public class CorePointResource {

    private final Logger log = LoggerFactory.getLogger(CorePointResource.class);

    private static final String ENTITY_NAME = "corePoint";

    private final CorePointService corePointService;

    public CorePointResource(CorePointService corePointService) {
        this.corePointService = corePointService;
    }

    /**
     * POST  /core-points : Create a new corePoint.
     *
     * @param corePoint the corePoint to create
     * @return the ResponseEntity with status 201 (Created) and with body the new corePoint, or with status 400 (Bad Request) if the corePoint has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/core-points")
    @Timed
    public ResponseEntity<CorePoint> createCorePoint(@RequestBody CorePoint corePoint) throws URISyntaxException {
        log.debug("REST request to save CorePoint : {}", corePoint);
        if (corePoint.getId() != null) {
            throw new BadRequestAlertException("A new corePoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CorePoint result = corePointService.save(corePoint);
        return ResponseEntity.created(new URI("/api/core-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /core-points : Updates an existing corePoint.
     *
     * @param corePoint the corePoint to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated corePoint,
     * or with status 400 (Bad Request) if the corePoint is not valid,
     * or with status 500 (Internal Server Error) if the corePoint couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/core-points")
    @Timed
    public ResponseEntity<CorePoint> updateCorePoint(@RequestBody CorePoint corePoint) throws URISyntaxException {
        log.debug("REST request to update CorePoint : {}", corePoint);
        if (corePoint.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CorePoint result = corePointService.save(corePoint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, corePoint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /core-points : get all the corePoints.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of corePoints in body
     */
    @GetMapping("/core-points")
    @Timed
    public List<CorePoint> getAllCorePoints() {
        log.debug("REST request to get all CorePoints");
        return corePointService.findAll();
    }

    /**
     * GET  /core-points/:id : get the "id" corePoint.
     *
     * @param id the id of the corePoint to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the corePoint, or with status 404 (Not Found)
     */
    @GetMapping("/core-points/{id}")
    @Timed
    public ResponseEntity<CorePoint> getCorePoint(@PathVariable Long id) {
        log.debug("REST request to get CorePoint : {}", id);
        Optional<CorePoint> corePoint = corePointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(corePoint);
    }

    /**
     * DELETE  /core-points/:id : delete the "id" corePoint.
     *
     * @param id the id of the corePoint to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/core-points/{id}")
    @Timed
    public ResponseEntity<Void> deleteCorePoint(@PathVariable Long id) {
        log.debug("REST request to delete CorePoint : {}", id);
        corePointService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
