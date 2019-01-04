package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.Park;
import com.kkk.cocoapp.service.ParkService;
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
 * REST controller for managing Park.
 */
@RestController
@RequestMapping("/api")
public class ParkResource {

    private final Logger log = LoggerFactory.getLogger(ParkResource.class);

    private static final String ENTITY_NAME = "park";

    private final ParkService parkService;

    public ParkResource(ParkService parkService) {
        this.parkService = parkService;
    }

    /**
     * POST  /parks : Create a new park.
     *
     * @param park the park to create
     * @return the ResponseEntity with status 201 (Created) and with body the new park, or with status 400 (Bad Request) if the park has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/parks")
    @Timed
    public ResponseEntity<Park> createPark(@RequestBody Park park) throws URISyntaxException {
        log.debug("REST request to save Park : {}", park);
        if (park.getId() != null) {
            throw new BadRequestAlertException("A new park cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Park result = parkService.save(park);
        return ResponseEntity.created(new URI("/api/parks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /parks : Updates an existing park.
     *
     * @param park the park to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated park,
     * or with status 400 (Bad Request) if the park is not valid,
     * or with status 500 (Internal Server Error) if the park couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/parks")
    @Timed
    public ResponseEntity<Park> updatePark(@RequestBody Park park) throws URISyntaxException {
        log.debug("REST request to update Park : {}", park);
        if (park.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Park result = parkService.save(park);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, park.getId().toString()))
            .body(result);
    }

    /**
     * GET  /parks : get all the parks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of parks in body
     */
    @GetMapping("/parks")
    @Timed
    public List<Park> getAllParks() {
        log.debug("REST request to get all Parks");
        return parkService.findAll();
    }

    /**
     * GET  /parks/:id : get the "id" park.
     *
     * @param id the id of the park to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the park, or with status 404 (Not Found)
     */
    @GetMapping("/parks/{id}")
    @Timed
    public ResponseEntity<Park> getPark(@PathVariable Long id) {
        log.debug("REST request to get Park : {}", id);
        Optional<Park> park = parkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(park);
    }

    /**
     * DELETE  /parks/:id : delete the "id" park.
     *
     * @param id the id of the park to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/parks/{id}")
    @Timed
    public ResponseEntity<Void> deletePark(@PathVariable Long id) {
        log.debug("REST request to delete Park : {}", id);
        parkService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
