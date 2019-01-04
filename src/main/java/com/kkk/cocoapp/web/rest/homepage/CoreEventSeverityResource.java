package com.kkk.cocoapp.web.rest.homepage;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.CoreEventSeverity;
import com.kkk.cocoapp.service.CoreEventSeverityService;
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
 * REST controller for managing CoreEventSeverity.
 */
@RestController
@RequestMapping("/api")
public class CoreEventSeverityResource {

    private final Logger log = LoggerFactory.getLogger(CoreEventSeverityResource.class);

    private static final String ENTITY_NAME = "coreEventSeverity";

    private final CoreEventSeverityService coreEventSeverityService;

    public CoreEventSeverityResource(CoreEventSeverityService coreEventSeverityService) {
        this.coreEventSeverityService = coreEventSeverityService;
    }

    /**
     * POST  /core-event-severities : Create a new coreEventSeverity.
     *
     * @param coreEventSeverity the coreEventSeverity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coreEventSeverity, or with status 400 (Bad Request) if the coreEventSeverity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/core-event-severities")
    @Timed
    public ResponseEntity<CoreEventSeverity> createCoreEventSeverity(@RequestBody CoreEventSeverity coreEventSeverity) throws URISyntaxException {
        log.debug("REST request to save CoreEventSeverity : {}", coreEventSeverity);
        if (coreEventSeverity.getId() != null) {
            throw new BadRequestAlertException("A new coreEventSeverity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoreEventSeverity result = coreEventSeverityService.save(coreEventSeverity);
        return ResponseEntity.created(new URI("/api/core-event-severities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /core-event-severities : Updates an existing coreEventSeverity.
     *
     * @param coreEventSeverity the coreEventSeverity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coreEventSeverity,
     * or with status 400 (Bad Request) if the coreEventSeverity is not valid,
     * or with status 500 (Internal Server Error) if the coreEventSeverity couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/core-event-severities")
    @Timed
    public ResponseEntity<CoreEventSeverity> updateCoreEventSeverity(@RequestBody CoreEventSeverity coreEventSeverity) throws URISyntaxException {
        log.debug("REST request to update CoreEventSeverity : {}", coreEventSeverity);
        if (coreEventSeverity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CoreEventSeverity result = coreEventSeverityService.save(coreEventSeverity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coreEventSeverity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /core-event-severities : get all the coreEventSeverities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of coreEventSeverities in body
     */
    @GetMapping("/core-event-severities")
    @Timed
    public List<CoreEventSeverity> getAllCoreEventSeverities() {
        log.debug("REST request to get all CoreEventSeverities");
        return coreEventSeverityService.findAll();
    }

    /**
     * GET  /core-event-severities/:id : get the "id" coreEventSeverity.
     *
     * @param id the id of the coreEventSeverity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coreEventSeverity, or with status 404 (Not Found)
     */
    @GetMapping("/core-event-severities/{id}")
    @Timed
    public ResponseEntity<CoreEventSeverity> getCoreEventSeverity(@PathVariable Long id) {
        log.debug("REST request to get CoreEventSeverity : {}", id);
        Optional<CoreEventSeverity> coreEventSeverity = coreEventSeverityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coreEventSeverity);
    }

    /**
     * DELETE  /core-event-severities/:id : delete the "id" coreEventSeverity.
     *
     * @param id the id of the coreEventSeverity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/core-event-severities/{id}")
    @Timed
    public ResponseEntity<Void> deleteCoreEventSeverity(@PathVariable Long id) {
        log.debug("REST request to delete CoreEventSeverity : {}", id);
        coreEventSeverityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
