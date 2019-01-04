package com.kkk.cocoapp.web.rest.homepage;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.LiveEvent;
import com.kkk.cocoapp.service.LiveEventService;
import com.kkk.cocoapp.web.rest.errors.BadRequestAlertException;
import com.kkk.cocoapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LiveEvent.
 */
@RestController
    @RequestMapping("/api")
public class LiveEventResource {

    private final Logger log = LoggerFactory.getLogger(LiveEventResource.class);

    private static final String ENTITY_NAME = "liveEvent";

    private final LiveEventService liveEventService;

    public LiveEventResource(LiveEventService liveEventService) {
        this.liveEventService = liveEventService;
    }

    /**
     * POST  /live-events : Create a new liveEvent.
     *
     * @param liveEvent the liveEvent to create
     * @return the ResponseEntity with status 201 (Created) and with body the new liveEvent, or with status 400 (Bad Request) if the liveEvent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/live-events")
    @Timed
    public ResponseEntity<LiveEvent> createLiveEvent(@RequestBody LiveEvent liveEvent) throws URISyntaxException {
        log.debug("REST request to save LiveEvent : {}", liveEvent);
        if (liveEvent.getId() != null) {
            throw new BadRequestAlertException("A new liveEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LiveEvent result = liveEventService.save(liveEvent);
        return ResponseEntity.created(new URI("/api/live-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /live-events : Updates an existing liveEvent.
     *
     * @param liveEvent the liveEvent to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated liveEvent,
     * or with status 400 (Bad Request) if the liveEvent is not valid,
     * or with status 500 (Internal Server Error) if the liveEvent couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/live-events")
    @Timed
    public ResponseEntity<LiveEvent> updateLiveEvent(@RequestBody LiveEvent liveEvent) throws URISyntaxException {
        log.debug("REST request to update LiveEvent : {}", liveEvent);
        if (liveEvent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LiveEvent result = liveEventService.save(liveEvent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, liveEvent.getId().toString()))
            .body(result);
    }

    /**
     * GET  /live-events : get all the liveEvents.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of liveEvents in body
     */
    @GetMapping("/live-events")
    @Timed
    public List<LiveEvent> getAllLiveEvents() {
        log.debug("REST request to get all LiveEvents");
        return liveEventService.findAll();
    }

    /**
     * GET  /live-events/:id : get the "id" liveEvent.
     *
     * @param id the id of the liveEvent to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the liveEvent, or with status 404 (Not Found)
     */
    @GetMapping("/live-events/{id}")
    @Timed
    public ResponseEntity<LiveEvent> getLiveEvent(@PathVariable Long id) {
        log.debug("REST request to get LiveEvent : {}", id);
        Optional<LiveEvent> liveEvent = liveEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(liveEvent);
    }

    /**
     * DELETE  /live-events/:id : delete the "id" liveEvent.
     *
     * @param id the id of the liveEvent to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/live-events/{id}")
    @Timed
    public ResponseEntity<Void> deleteLiveEvent(@PathVariable Long id) {
        log.debug("REST request to delete LiveEvent : {}", id);
        liveEventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

//    @RequestMapping(value = "/live-events/top10",
//        method = RequestMethod.GET,
//        produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/live-events/top10")
    @Timed
    public List<LiveEvent> getTop10() {
        //获取最新的10条告警
        log.debug("REST request to get top10 LiveEvents");
        List<LiveEvent> liveEvents = liveEventService.getEventByLastest(10);
        return liveEvents;
    }

}
