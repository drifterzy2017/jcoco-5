package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.Cov;
import com.kkk.cocoapp.service.CovService;
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
 * REST controller for managing Cov.
 */
@RestController
@RequestMapping("/api")
public class CovResource {

    private final Logger log = LoggerFactory.getLogger(CovResource.class);

    private static final String ENTITY_NAME = "cov";

    private final CovService covService;

    public CovResource(CovService covService) {
        this.covService = covService;
    }

    /**
     * POST  /covs : Create a new cov.
     *
     * @param cov the cov to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cov, or with status 400 (Bad Request) if the cov has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/covs")
    @Timed
    public ResponseEntity<Cov> createCov(@RequestBody Cov cov) throws URISyntaxException {
        log.debug("REST request to save Cov : {}", cov);
        if (cov.getId() != null) {
            throw new BadRequestAlertException("A new cov cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cov result = covService.save(cov);
        return ResponseEntity.created(new URI("/api/covs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /covs : Updates an existing cov.
     *
     * @param cov the cov to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cov,
     * or with status 400 (Bad Request) if the cov is not valid,
     * or with status 500 (Internal Server Error) if the cov couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/covs")
    @Timed
    public ResponseEntity<Cov> updateCov(@RequestBody Cov cov) throws URISyntaxException {
        log.debug("REST request to update Cov : {}", cov);
        if (cov.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Cov result = covService.save(cov);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cov.getId().toString()))
            .body(result);
    }

    /**
     * GET  /covs : get all the covs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of covs in body
     */
    @GetMapping("/covs")
    @Timed
    public List<Cov> getAllCovs() {
        log.debug("REST request to get all Covs");
        return covService.findAll();
    }

    /**
     * GET  /covs/:id : get the "id" cov.
     *
     * @param id the id of the cov to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cov, or with status 404 (Not Found)
     */
    @GetMapping("/covs/{id}")
    @Timed
    public ResponseEntity<Cov> getCov(@PathVariable Long id) {
        log.debug("REST request to get Cov : {}", id);
        Optional<Cov> cov = covService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cov);
    }

    /**
     * DELETE  /covs/:id : delete the "id" cov.
     *
     * @param id the id of the cov to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/covs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCov(@PathVariable Long id) {
        log.debug("REST request to delete Cov : {}", id);
        covService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
