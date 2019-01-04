package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.CoreSource;
import com.kkk.cocoapp.service.CoreSourceService;
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
 * REST controller for managing CoreSource.
 */
@RestController
@RequestMapping("/api")
public class CoreSourceResource {

    private final Logger log = LoggerFactory.getLogger(CoreSourceResource.class);

    private static final String ENTITY_NAME = "coreSource";

    private final CoreSourceService coreSourceService;

    public CoreSourceResource(CoreSourceService coreSourceService) {
        this.coreSourceService = coreSourceService;
    }

    /**
     * POST  /core-sources : Create a new coreSource.
     *
     * @param coreSource the coreSource to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coreSource, or with status 400 (Bad Request) if the coreSource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/core-sources")
    @Timed
    public ResponseEntity<CoreSource> createCoreSource(@RequestBody CoreSource coreSource) throws URISyntaxException {
        log.debug("REST request to save CoreSource : {}", coreSource);
        if (coreSource.getId() != null) {
            throw new BadRequestAlertException("A new coreSource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoreSource result = coreSourceService.save(coreSource);
        return ResponseEntity.created(new URI("/api/core-sources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /core-sources : Updates an existing coreSource.
     *
     * @param coreSource the coreSource to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coreSource,
     * or with status 400 (Bad Request) if the coreSource is not valid,
     * or with status 500 (Internal Server Error) if the coreSource couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/core-sources")
    @Timed
    public ResponseEntity<CoreSource> updateCoreSource(@RequestBody CoreSource coreSource) throws URISyntaxException {
        log.debug("REST request to update CoreSource : {}", coreSource);
        if (coreSource.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CoreSource result = coreSourceService.save(coreSource);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coreSource.getId().toString()))
            .body(result);
    }

    /**
     * GET  /core-sources : get all the coreSources.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of coreSources in body
     */
    @GetMapping("/core-sources")
    @Timed
    public List<CoreSource> getAllCoreSources() {
        log.debug("REST request to get all CoreSources");
        return coreSourceService.findAll();
    }

    /**
     * GET  /core-sources/:id : get the "id" coreSource.
     *
     * @param id the id of the coreSource to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coreSource, or with status 404 (Not Found)
     */
    @GetMapping("/core-sources/{id}")
    @Timed
    public ResponseEntity<CoreSource> getCoreSource(@PathVariable Long id) {
        log.debug("REST request to get CoreSource : {}", id);
        Optional<CoreSource> coreSource = coreSourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coreSource);
    }

    /**
     * DELETE  /core-sources/:id : delete the "id" coreSource.
     *
     * @param id the id of the coreSource to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/core-sources/{id}")
    @Timed
    public ResponseEntity<Void> deleteCoreSource(@PathVariable Long id) {
        log.debug("REST request to delete CoreSource : {}", id);
        coreSourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
