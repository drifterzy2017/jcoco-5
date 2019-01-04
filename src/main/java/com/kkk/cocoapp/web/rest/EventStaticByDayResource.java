package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.EventStaticByDay;
import com.kkk.cocoapp.service.EventStaticByDayService;
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
 * REST controller for managing EventStaticByDay.
 */
@RestController
@RequestMapping("/api")
public class EventStaticByDayResource {

    private final Logger log = LoggerFactory.getLogger(EventStaticByDayResource.class);

    private static final String ENTITY_NAME = "eventStaticByDay";

    private final EventStaticByDayService eventStaticByDayService;

    public EventStaticByDayResource(EventStaticByDayService eventStaticByDayService) {
        this.eventStaticByDayService = eventStaticByDayService;
    }

    /**
     * POST  /event-static-by-days : Create a new eventStaticByDay.
     *
     * @param eventStaticByDay the eventStaticByDay to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventStaticByDay, or with status 400 (Bad Request) if the eventStaticByDay has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/event-static-by-days")
    @Timed
    public ResponseEntity<EventStaticByDay> createEventStaticByDay(@RequestBody EventStaticByDay eventStaticByDay) throws URISyntaxException {
        log.debug("REST request to save EventStaticByDay : {}", eventStaticByDay);
        if (eventStaticByDay.getId() != null) {
            throw new BadRequestAlertException("A new eventStaticByDay cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventStaticByDay result = eventStaticByDayService.save(eventStaticByDay);
        return ResponseEntity.created(new URI("/api/event-static-by-days/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /event-static-by-days : Updates an existing eventStaticByDay.
     *
     * @param eventStaticByDay the eventStaticByDay to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eventStaticByDay,
     * or with status 400 (Bad Request) if the eventStaticByDay is not valid,
     * or with status 500 (Internal Server Error) if the eventStaticByDay couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/event-static-by-days")
    @Timed
    public ResponseEntity<EventStaticByDay> updateEventStaticByDay(@RequestBody EventStaticByDay eventStaticByDay) throws URISyntaxException {
        log.debug("REST request to update EventStaticByDay : {}", eventStaticByDay);
        if (eventStaticByDay.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventStaticByDay result = eventStaticByDayService.save(eventStaticByDay);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eventStaticByDay.getId().toString()))
            .body(result);
    }

    /**
     * GET  /event-static-by-days : get all the eventStaticByDays.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of eventStaticByDays in body
     */
    @GetMapping("/event-static-by-days")
    @Timed
    public List<EventStaticByDay> getAllEventStaticByDays() {
        log.debug("REST request to get all EventStaticByDays");
        return eventStaticByDayService.findAll();
    }

    /**
     * GET  /event-static-by-days/:id : get the "id" eventStaticByDay.
     *
     * @param id the id of the eventStaticByDay to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventStaticByDay, or with status 404 (Not Found)
     */
    @GetMapping("/event-static-by-days/{id}")
    @Timed
    public ResponseEntity<EventStaticByDay> getEventStaticByDay(@PathVariable Long id) {
        log.debug("REST request to get EventStaticByDay : {}", id);
        Optional<EventStaticByDay> eventStaticByDay = eventStaticByDayService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventStaticByDay);
    }

    /**
     * DELETE  /event-static-by-days/:id : delete the "id" eventStaticByDay.
     *
     * @param id the id of the eventStaticByDay to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/event-static-by-days/{id}")
    @Timed
    public ResponseEntity<Void> deleteEventStaticByDay(@PathVariable Long id) {
        log.debug("REST request to delete EventStaticByDay : {}", id);
        eventStaticByDayService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
