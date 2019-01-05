package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.LivePoint;
import com.kkk.cocoapp.service.LivePointService;
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
 * REST controller for managing LivePoint.
 */
@RestController
@RequestMapping("/api")
public class LivePointResource {

    private final Logger log = LoggerFactory.getLogger(LivePointResource.class);

    private static final String ENTITY_NAME = "livePoint";

    private final LivePointService livePointService;

    public LivePointResource(LivePointService livePointService) {
        this.livePointService = livePointService;
    }

    /**
     * POST  /live-points : Create a new livePoint.
     *
     * @param livePoint the livePoint to create
     * @return the ResponseEntity with status 201 (Created) and with body the new livePoint, or with status 400 (Bad Request) if the livePoint has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/live-points")
    @Timed
    public ResponseEntity<LivePoint> createLivePoint(@RequestBody LivePoint livePoint) throws URISyntaxException {
        log.debug("REST request to save LivePoint : {}", livePoint);
        if (livePoint.getId() != null) {
            throw new BadRequestAlertException("A new livePoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LivePoint result = livePointService.save(livePoint);
        return ResponseEntity.created(new URI("/api/live-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /live-points : Updates an existing livePoint.
     *
     * @param livePoint the livePoint to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated livePoint,
     * or with status 400 (Bad Request) if the livePoint is not valid,
     * or with status 500 (Internal Server Error) if the livePoint couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/live-points")
    @Timed
    public ResponseEntity<LivePoint> updateLivePoint(@RequestBody LivePoint livePoint) throws URISyntaxException {
        log.debug("REST request to update LivePoint : {}", livePoint);
        if (livePoint.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LivePoint result = livePointService.save(livePoint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, livePoint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /live-points : get all the livePoints.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of livePoints in body
     */
    @GetMapping("/live-points")
    @Timed
    public List<LivePoint> getAllLivePoints() {
        log.debug("REST request to get all LivePoints");
        return livePointService.findAll();
    }

    /**
     * GET  /live-points/:id : get the "id" livePoint.
     *
     * @param id the id of the livePoint to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the livePoint, or with status 404 (Not Found)
     */
    @GetMapping("/live-points/{id}")
    @Timed
    public ResponseEntity<LivePoint> getLivePoint(@PathVariable Long id) {
        log.debug("REST request to get LivePoint : {}", id);
        Optional<LivePoint> livePoint = livePointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(livePoint);
    }

    /**
     * DELETE  /live-points/:id : delete the "id" livePoint.
     *
     * @param id the id of the livePoint to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/live-points/{id}")
    @Timed
    public ResponseEntity<Void> deleteLivePoint(@PathVariable Long id) {
        log.debug("REST request to delete LivePoint : {}", id);
        livePointService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
