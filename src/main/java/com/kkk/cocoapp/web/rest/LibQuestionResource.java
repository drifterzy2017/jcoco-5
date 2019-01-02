package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.LibQuestion;
import com.kkk.cocoapp.service.LibQuestionService;
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
 * REST controller for managing LibQuestion.
 */
@RestController
@RequestMapping("/api")
public class LibQuestionResource {

    private final Logger log = LoggerFactory.getLogger(LibQuestionResource.class);

    private static final String ENTITY_NAME = "libQuestion";

    private final LibQuestionService libQuestionService;

    public LibQuestionResource(LibQuestionService libQuestionService) {
        this.libQuestionService = libQuestionService;
    }

    /**
     * POST  /lib-questions : Create a new libQuestion.
     *
     * @param libQuestion the libQuestion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new libQuestion, or with status 400 (Bad Request) if the libQuestion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/lib-questions")
    @Timed
    public ResponseEntity<LibQuestion> createLibQuestion(@RequestBody LibQuestion libQuestion) throws URISyntaxException {
        log.debug("REST request to save LibQuestion : {}", libQuestion);
        if (libQuestion.getId() != null) {
            throw new BadRequestAlertException("A new libQuestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LibQuestion result = libQuestionService.save(libQuestion);
        return ResponseEntity.created(new URI("/api/lib-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lib-questions : Updates an existing libQuestion.
     *
     * @param libQuestion the libQuestion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated libQuestion,
     * or with status 400 (Bad Request) if the libQuestion is not valid,
     * or with status 500 (Internal Server Error) if the libQuestion couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/lib-questions")
    @Timed
    public ResponseEntity<LibQuestion> updateLibQuestion(@RequestBody LibQuestion libQuestion) throws URISyntaxException {
        log.debug("REST request to update LibQuestion : {}", libQuestion);
        if (libQuestion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LibQuestion result = libQuestionService.save(libQuestion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, libQuestion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lib-questions : get all the libQuestions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of libQuestions in body
     */
    @GetMapping("/lib-questions")
    @Timed
    public List<LibQuestion> getAllLibQuestions() {
        log.debug("REST request to get all LibQuestions");
        return libQuestionService.findAll();
    }

    /**
     * GET  /lib-questions/:id : get the "id" libQuestion.
     *
     * @param id the id of the libQuestion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the libQuestion, or with status 404 (Not Found)
     */
    @GetMapping("/lib-questions/{id}")
    @Timed
    public ResponseEntity<LibQuestion> getLibQuestion(@PathVariable Long id) {
        log.debug("REST request to get LibQuestion : {}", id);
        Optional<LibQuestion> libQuestion = libQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(libQuestion);
    }

    /**
     * DELETE  /lib-questions/:id : delete the "id" libQuestion.
     *
     * @param id the id of the libQuestion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/lib-questions/{id}")
    @Timed
    public ResponseEntity<Void> deleteLibQuestion(@PathVariable Long id) {
        log.debug("REST request to delete LibQuestion : {}", id);
        libQuestionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
