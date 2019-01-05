package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.DesiredCov;
import com.kkk.cocoapp.service.DesiredCovService;
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
 * REST controller for managing DesiredCov.
 */
@RestController
@RequestMapping("/api")
public class DesiredCovResource {

    private final Logger log = LoggerFactory.getLogger(DesiredCovResource.class);

    private static final String ENTITY_NAME = "desiredCov";

    private final DesiredCovService desiredCovService;

    public DesiredCovResource(DesiredCovService desiredCovService) {
        this.desiredCovService = desiredCovService;
    }

    /**
     * POST  /desired-covs : Create a new desiredCov.
     *
     * @param desiredCov the desiredCov to create
     * @return the ResponseEntity with status 201 (Created) and with body the new desiredCov, or with status 400 (Bad Request) if the desiredCov has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/desired-covs")
    @Timed
    public ResponseEntity<DesiredCov> createDesiredCov(@RequestBody DesiredCov desiredCov) throws URISyntaxException {
        log.debug("REST request to save DesiredCov : {}", desiredCov);
        if (desiredCov.getId() != null) {
            throw new BadRequestAlertException("A new desiredCov cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DesiredCov result = desiredCovService.save(desiredCov);
        return ResponseEntity.created(new URI("/api/desired-covs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /desired-covs : Updates an existing desiredCov.
     *
     * @param desiredCov the desiredCov to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated desiredCov,
     * or with status 400 (Bad Request) if the desiredCov is not valid,
     * or with status 500 (Internal Server Error) if the desiredCov couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/desired-covs")
    @Timed
    public ResponseEntity<DesiredCov> updateDesiredCov(@RequestBody DesiredCov desiredCov) throws URISyntaxException {
        log.debug("REST request to update DesiredCov : {}", desiredCov);
        if (desiredCov.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DesiredCov result = desiredCovService.save(desiredCov);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, desiredCov.getId().toString()))
            .body(result);
    }

    /**
     * GET  /desired-covs : get all the desiredCovs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of desiredCovs in body
     */
    @GetMapping("/desired-covs")
    @Timed
    public List<DesiredCov> getAllDesiredCovs() {
        log.debug("REST request to get all DesiredCovs");
        return desiredCovService.findAll();
    }

    /**
     * GET  /desired-covs/:id : get the "id" desiredCov.
     *
     * @param id the id of the desiredCov to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the desiredCov, or with status 404 (Not Found)
     */
    @GetMapping("/desired-covs/{id}")
    @Timed
    public ResponseEntity<DesiredCov> getDesiredCov(@PathVariable Long id) {
        log.debug("REST request to get DesiredCov : {}", id);
        Optional<DesiredCov> desiredCov = desiredCovService.findOne(id);
        return ResponseUtil.wrapOrNotFound(desiredCov);
    }

    /**
     * DELETE  /desired-covs/:id : delete the "id" desiredCov.
     *
     * @param id the id of the desiredCov to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/desired-covs/{id}")
    @Timed
    public ResponseEntity<Void> deleteDesiredCov(@PathVariable Long id) {
        log.debug("REST request to delete DesiredCov : {}", id);
        desiredCovService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
