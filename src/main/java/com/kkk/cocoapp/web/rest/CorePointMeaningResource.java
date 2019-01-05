package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.CorePointMeaning;
import com.kkk.cocoapp.service.CorePointMeaningService;
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
 * REST controller for managing CorePointMeaning.
 */
@RestController
@RequestMapping("/api")
public class CorePointMeaningResource {

    private final Logger log = LoggerFactory.getLogger(CorePointMeaningResource.class);

    private static final String ENTITY_NAME = "corePointMeaning";

    private final CorePointMeaningService corePointMeaningService;

    public CorePointMeaningResource(CorePointMeaningService corePointMeaningService) {
        this.corePointMeaningService = corePointMeaningService;
    }

    /**
     * POST  /core-point-meanings : Create a new corePointMeaning.
     *
     * @param corePointMeaning the corePointMeaning to create
     * @return the ResponseEntity with status 201 (Created) and with body the new corePointMeaning, or with status 400 (Bad Request) if the corePointMeaning has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/core-point-meanings")
    @Timed
    public ResponseEntity<CorePointMeaning> createCorePointMeaning(@RequestBody CorePointMeaning corePointMeaning) throws URISyntaxException {
        log.debug("REST request to save CorePointMeaning : {}", corePointMeaning);
        if (corePointMeaning.getId() != null) {
            throw new BadRequestAlertException("A new corePointMeaning cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CorePointMeaning result = corePointMeaningService.save(corePointMeaning);
        return ResponseEntity.created(new URI("/api/core-point-meanings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /core-point-meanings : Updates an existing corePointMeaning.
     *
     * @param corePointMeaning the corePointMeaning to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated corePointMeaning,
     * or with status 400 (Bad Request) if the corePointMeaning is not valid,
     * or with status 500 (Internal Server Error) if the corePointMeaning couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/core-point-meanings")
    @Timed
    public ResponseEntity<CorePointMeaning> updateCorePointMeaning(@RequestBody CorePointMeaning corePointMeaning) throws URISyntaxException {
        log.debug("REST request to update CorePointMeaning : {}", corePointMeaning);
        if (corePointMeaning.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CorePointMeaning result = corePointMeaningService.save(corePointMeaning);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, corePointMeaning.getId().toString()))
            .body(result);
    }

    /**
     * GET  /core-point-meanings : get all the corePointMeanings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of corePointMeanings in body
     */
    @GetMapping("/core-point-meanings")
    @Timed
    public List<CorePointMeaning> getAllCorePointMeanings() {
        log.debug("REST request to get all CorePointMeanings");
        return corePointMeaningService.findAll();
    }

    /**
     * GET  /core-point-meanings/:id : get the "id" corePointMeaning.
     *
     * @param id the id of the corePointMeaning to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the corePointMeaning, or with status 404 (Not Found)
     */
    @GetMapping("/core-point-meanings/{id}")
    @Timed
    public ResponseEntity<CorePointMeaning> getCorePointMeaning(@PathVariable Long id) {
        log.debug("REST request to get CorePointMeaning : {}", id);
        Optional<CorePointMeaning> corePointMeaning = corePointMeaningService.findOne(id);
        return ResponseUtil.wrapOrNotFound(corePointMeaning);
    }

    /**
     * DELETE  /core-point-meanings/:id : delete the "id" corePointMeaning.
     *
     * @param id the id of the corePointMeaning to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/core-point-meanings/{id}")
    @Timed
    public ResponseEntity<Void> deleteCorePointMeaning(@PathVariable Long id) {
        log.debug("REST request to delete CorePointMeaning : {}", id);
        corePointMeaningService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
