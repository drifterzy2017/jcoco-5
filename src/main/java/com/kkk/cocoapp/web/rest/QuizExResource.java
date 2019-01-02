package com.kkk.cocoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kkk.cocoapp.domain.Quiz;
import com.kkk.cocoapp.service.QuizService;
import com.kkk.cocoapp.service.dto.CoinStat;
import com.kkk.cocoapp.service.quiz.QuizFacadeService;
import com.kkk.cocoapp.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.ResponseUtil;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing Quiz.
 */
@RestController
@RequestMapping("/api")
public class QuizExResource {

    private final Logger log = LoggerFactory.getLogger(QuizExResource.class);
    private static final String ENTITY_NAME = "quiz";
    private final QuizFacadeService quizFacade;
    private final QuizService quizService;

    public QuizExResource(QuizFacadeService quizExService, QuizService quizService) {
        this.quizFacade = quizExService;
        this.quizService = quizService;
    }

    /**
     * GET  /quizzes/:id : get the "id" quiz.
     *
     * @param id the id of the quiz to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quiz, or with status 404 (Not Found)
     */
    @GetMapping("/quizzesex/{id}")
    @Timed
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        log.debug("REST request to get Quiz : {}", id);
        val quiz = quizFacade.autoCreateOne();
        return ResponseUtil.wrapOrNotFound(Optional.of(quiz));
    }

    @PostMapping("/quizzesex")
    @Timed
    public Boolean submitQuiz(@RequestBody Quiz quiz) throws URISyntaxException {
        log.debug("REST request to save Quiz : {}", quiz);
        if (quiz.getId() != null) {
            throw new BadRequestAlertException("A new quiz cannot already have an ID", ENTITY_NAME, "idexists");
        }
        val r = quizFacade.save(quiz);
        return r;
    }


    @GetMapping("/bank/stat")
    @Timed
    public ResponseEntity<CoinStat> getCoinStat() {
        log.debug("REST request to getCoinStat");
        val stat  = quizFacade.getCoinStat();
        return ResponseUtil.wrapOrNotFound(Optional.of(stat));
    }

    @GetMapping("/bank/buy")
    @Timed
    public String buyUserDefProduct(int price, String productName) {
        log.debug("REST request to getCoinStat");
        val rtn  = quizFacade.buyProduct(price, productName);
        return rtn;
    }
}
