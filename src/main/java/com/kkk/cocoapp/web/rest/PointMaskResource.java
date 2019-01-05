package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.PointMask;
import com.kkk.cocoapp.service.PointMaskService;
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
 * REST controller for managing PointMask.
 */
@RestController
@RequestMapping("/api")
public class PointMaskResource {

    private final Logger log = LoggerFactory.getLogger(PointMaskResource.class);

    private static final String ENTITY_NAME = "pointMask";

    private final PointMaskService pointMaskService;

    public PointMaskResource(PointMaskService pointMaskService) {
        this.pointMaskService = pointMaskService;
    }

    /**
     * POST  /point-masks : Create a new pointMask.
     *
     * @param pointMask the pointMask to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pointMask, or with status 400 (Bad Request) if the pointMask has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/point-masks")
    @Timed
    public ResponseEntity<PointMask> createPointMask(@RequestBody PointMask pointMask) throws URISyntaxException {
        log.debug("REST request to save PointMask : {}", pointMask);
        if (pointMask.getId() != null) {
            throw new BadRequestAlertException("A new pointMask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PointMask result = pointMaskService.save(pointMask);
        return ResponseEntity.created(new URI("/api/point-masks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /point-masks : Updates an existing pointMask.
     *
     * @param pointMask the pointMask to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pointMask,
     * or with status 400 (Bad Request) if the pointMask is not valid,
     * or with status 500 (Internal Server Error) if the pointMask couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/point-masks")
    @Timed
    public ResponseEntity<PointMask> updatePointMask(@RequestBody PointMask pointMask) throws URISyntaxException {
        log.debug("REST request to update PointMask : {}", pointMask);
        if (pointMask.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PointMask result = pointMaskService.save(pointMask);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pointMask.getId().toString()))
            .body(result);
    }

    /**
     * GET  /point-masks : get all the pointMasks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pointMasks in body
     */
    @GetMapping("/point-masks")
    @Timed
    public List<PointMask> getAllPointMasks() {
        log.debug("REST request to get all PointMasks");
        return pointMaskService.findAll();
    }


    /**
     * GET  /point-masks/:id : get the "id" pointMask.
     *
     * @param id the id of the pointMask to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pointMask, or with status 404 (Not Found)
     */
    @GetMapping("/point-masks/{id}")
    @Timed
    public ResponseEntity<PointMask> getPointMask(@PathVariable Long id) {
        log.debug("REST request to get PointMask : {}", id);
        Optional<PointMask> pointMask = pointMaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pointMask);
    }

    /**
     * DELETE  /point-masks/:id : delete the "id" pointMask.
     *
     * @param id the id of the pointMask to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/point-masks/{id}")
    @Timed
    public ResponseEntity<Void> deletePointMask(@PathVariable Long id) {
        log.debug("REST request to delete PointMask : {}", id);
        pointMaskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pointmasks/query")
    @Timed
    public List<PointMask> getAllPointMasksByUserIdAndDeviceId( int userId,
                                                                int deviceId) {
        log.debug("REST request to get all PointMask by userid and deviceid");
        List<PointMask> pointMasks = pointMaskService.findAllByDeviceIdAndUserId(deviceId,userId);
        return pointMasks;
    }
}
