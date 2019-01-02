package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.Quiz;
import com.kkk.cocoapp.repository.QuizRepository;
import com.kkk.cocoapp.service.QuizService;
import com.kkk.cocoapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.kkk.cocoapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuizResource REST controller.
 *
 * @see QuizResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class QuizResourceIntTest {

    private static final String DEFAULT_TEST_KEY = "AAAAAAAAAA";
    private static final String UPDATED_TEST_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LIB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LIB_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_AUTO_SUBMIT = false;
    private static final Boolean UPDATED_IS_AUTO_SUBMIT = true;

    private static final Integer DEFAULT_MY_ROUND = 1;
    private static final Integer UPDATED_MY_ROUND = 2;

    private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SUCCESS_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUCCESS_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_TOPMOST_SECONDS = 1;
    private static final Integer UPDATED_TOPMOST_SECONDS = 2;

    private static final Integer DEFAULT_COST_SECONDS = 1;
    private static final Integer UPDATED_COST_SECONDS = 2;

    private static final Float DEFAULT_MARK = 1F;
    private static final Float UPDATED_MARK = 2F;

    private static final Integer DEFAULT_PASS_COUNT = 1;
    private static final Integer UPDATED_PASS_COUNT = 2;

    private static final Integer DEFAULT_FAIL_COUNT = 1;
    private static final Integer UPDATED_FAIL_COUNT = 2;

    private static final Integer DEFAULT_CENT_COUNT = 1;
    private static final Integer UPDATED_CENT_COUNT = 2;

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    private static final Boolean DEFAULT_USED = false;
    private static final Boolean UPDATED_USED = true;

    private static final Instant DEFAULT_USE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_USE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_USE_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_USE_NOTE = "BBBBBBBBBB";

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizService quizService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restQuizMockMvc;

    private Quiz quiz;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuizResource quizResource = new QuizResource(quizService);
        this.restQuizMockMvc = MockMvcBuilders.standaloneSetup(quizResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quiz createEntity(EntityManager em) {
        Quiz quiz = new Quiz()
            .testKey(DEFAULT_TEST_KEY)
            .testName(DEFAULT_TEST_NAME)
            .libName(DEFAULT_LIB_NAME)
            .isAutoSubmit(DEFAULT_IS_AUTO_SUBMIT)
            .myRound(DEFAULT_MY_ROUND)
            .startTime(DEFAULT_START_TIME)
            .successTime(DEFAULT_SUCCESS_TIME)
            .topmostSeconds(DEFAULT_TOPMOST_SECONDS)
            .costSeconds(DEFAULT_COST_SECONDS)
            .mark(DEFAULT_MARK)
            .passCount(DEFAULT_PASS_COUNT)
            .failCount(DEFAULT_FAIL_COUNT)
            .centCount(DEFAULT_CENT_COUNT)
            .point(DEFAULT_POINT)
            .used(DEFAULT_USED)
            .useTime(DEFAULT_USE_TIME)
            .useNote(DEFAULT_USE_NOTE);
        return quiz;
    }

    @Before
    public void initTest() {
        quiz = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuiz() throws Exception {
        int databaseSizeBeforeCreate = quizRepository.findAll().size();

        // Create the Quiz
        restQuizMockMvc.perform(post("/api/quizzes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quiz)))
            .andExpect(status().isCreated());

        // Validate the Quiz in the database
        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeCreate + 1);
        Quiz testQuiz = quizList.get(quizList.size() - 1);
        assertThat(testQuiz.getTestKey()).isEqualTo(DEFAULT_TEST_KEY);
        assertThat(testQuiz.getTestName()).isEqualTo(DEFAULT_TEST_NAME);
        assertThat(testQuiz.getLibName()).isEqualTo(DEFAULT_LIB_NAME);
        assertThat(testQuiz.isIsAutoSubmit()).isEqualTo(DEFAULT_IS_AUTO_SUBMIT);
        assertThat(testQuiz.getMyRound()).isEqualTo(DEFAULT_MY_ROUND);
        assertThat(testQuiz.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testQuiz.getSuccessTime()).isEqualTo(DEFAULT_SUCCESS_TIME);
        assertThat(testQuiz.getTopmostSeconds()).isEqualTo(DEFAULT_TOPMOST_SECONDS);
        assertThat(testQuiz.getCostSeconds()).isEqualTo(DEFAULT_COST_SECONDS);
        assertThat(testQuiz.getMark()).isEqualTo(DEFAULT_MARK);
        assertThat(testQuiz.getPassCount()).isEqualTo(DEFAULT_PASS_COUNT);
        assertThat(testQuiz.getFailCount()).isEqualTo(DEFAULT_FAIL_COUNT);
        assertThat(testQuiz.getCentCount()).isEqualTo(DEFAULT_CENT_COUNT);
        assertThat(testQuiz.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testQuiz.isUsed()).isEqualTo(DEFAULT_USED);
        assertThat(testQuiz.getUseTime()).isEqualTo(DEFAULT_USE_TIME);
        assertThat(testQuiz.getUseNote()).isEqualTo(DEFAULT_USE_NOTE);
    }

    @Test
    @Transactional
    public void createQuizWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quizRepository.findAll().size();

        // Create the Quiz with an existing ID
        quiz.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuizMockMvc.perform(post("/api/quizzes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quiz)))
            .andExpect(status().isBadRequest());

        // Validate the Quiz in the database
        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllQuizzes() throws Exception {
        // Initialize the database
        quizRepository.saveAndFlush(quiz);

        // Get all the quizList
        restQuizMockMvc.perform(get("/api/quizzes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quiz.getId().intValue())))
            .andExpect(jsonPath("$.[*].testKey").value(hasItem(DEFAULT_TEST_KEY.toString())))
            .andExpect(jsonPath("$.[*].testName").value(hasItem(DEFAULT_TEST_NAME.toString())))
            .andExpect(jsonPath("$.[*].libName").value(hasItem(DEFAULT_LIB_NAME.toString())))
            .andExpect(jsonPath("$.[*].isAutoSubmit").value(hasItem(DEFAULT_IS_AUTO_SUBMIT.booleanValue())))
            .andExpect(jsonPath("$.[*].myRound").value(hasItem(DEFAULT_MY_ROUND)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].successTime").value(hasItem(DEFAULT_SUCCESS_TIME.toString())))
            .andExpect(jsonPath("$.[*].topmostSeconds").value(hasItem(DEFAULT_TOPMOST_SECONDS)))
            .andExpect(jsonPath("$.[*].costSeconds").value(hasItem(DEFAULT_COST_SECONDS)))
            .andExpect(jsonPath("$.[*].mark").value(hasItem(DEFAULT_MARK.doubleValue())))
            .andExpect(jsonPath("$.[*].passCount").value(hasItem(DEFAULT_PASS_COUNT)))
            .andExpect(jsonPath("$.[*].failCount").value(hasItem(DEFAULT_FAIL_COUNT)))
            .andExpect(jsonPath("$.[*].centCount").value(hasItem(DEFAULT_CENT_COUNT)))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].used").value(hasItem(DEFAULT_USED.booleanValue())))
            .andExpect(jsonPath("$.[*].useTime").value(hasItem(DEFAULT_USE_TIME.toString())))
            .andExpect(jsonPath("$.[*].useNote").value(hasItem(DEFAULT_USE_NOTE.toString())));
    }
    
    @Test
    @Transactional
    public void getQuiz() throws Exception {
        // Initialize the database
        quizRepository.saveAndFlush(quiz);

        // Get the quiz
        restQuizMockMvc.perform(get("/api/quizzes/{id}", quiz.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quiz.getId().intValue()))
            .andExpect(jsonPath("$.testKey").value(DEFAULT_TEST_KEY.toString()))
            .andExpect(jsonPath("$.testName").value(DEFAULT_TEST_NAME.toString()))
            .andExpect(jsonPath("$.libName").value(DEFAULT_LIB_NAME.toString()))
            .andExpect(jsonPath("$.isAutoSubmit").value(DEFAULT_IS_AUTO_SUBMIT.booleanValue()))
            .andExpect(jsonPath("$.myRound").value(DEFAULT_MY_ROUND))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.successTime").value(DEFAULT_SUCCESS_TIME.toString()))
            .andExpect(jsonPath("$.topmostSeconds").value(DEFAULT_TOPMOST_SECONDS))
            .andExpect(jsonPath("$.costSeconds").value(DEFAULT_COST_SECONDS))
            .andExpect(jsonPath("$.mark").value(DEFAULT_MARK.doubleValue()))
            .andExpect(jsonPath("$.passCount").value(DEFAULT_PASS_COUNT))
            .andExpect(jsonPath("$.failCount").value(DEFAULT_FAIL_COUNT))
            .andExpect(jsonPath("$.centCount").value(DEFAULT_CENT_COUNT))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT))
            .andExpect(jsonPath("$.used").value(DEFAULT_USED.booleanValue()))
            .andExpect(jsonPath("$.useTime").value(DEFAULT_USE_TIME.toString()))
            .andExpect(jsonPath("$.useNote").value(DEFAULT_USE_NOTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQuiz() throws Exception {
        // Get the quiz
        restQuizMockMvc.perform(get("/api/quizzes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuiz() throws Exception {
        // Initialize the database
        quizService.save(quiz);

        int databaseSizeBeforeUpdate = quizRepository.findAll().size();

        // Update the quiz
        Quiz updatedQuiz = quizRepository.findById(quiz.getId()).get();
        // Disconnect from session so that the updates on updatedQuiz are not directly saved in db
        em.detach(updatedQuiz);
        updatedQuiz
            .testKey(UPDATED_TEST_KEY)
            .testName(UPDATED_TEST_NAME)
            .libName(UPDATED_LIB_NAME)
            .isAutoSubmit(UPDATED_IS_AUTO_SUBMIT)
            .myRound(UPDATED_MY_ROUND)
            .startTime(UPDATED_START_TIME)
            .successTime(UPDATED_SUCCESS_TIME)
            .topmostSeconds(UPDATED_TOPMOST_SECONDS)
            .costSeconds(UPDATED_COST_SECONDS)
            .mark(UPDATED_MARK)
            .passCount(UPDATED_PASS_COUNT)
            .failCount(UPDATED_FAIL_COUNT)
            .centCount(UPDATED_CENT_COUNT)
            .point(UPDATED_POINT)
            .used(UPDATED_USED)
            .useTime(UPDATED_USE_TIME)
            .useNote(UPDATED_USE_NOTE);

        restQuizMockMvc.perform(put("/api/quizzes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedQuiz)))
            .andExpect(status().isOk());

        // Validate the Quiz in the database
        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeUpdate);
        Quiz testQuiz = quizList.get(quizList.size() - 1);
        assertThat(testQuiz.getTestKey()).isEqualTo(UPDATED_TEST_KEY);
        assertThat(testQuiz.getTestName()).isEqualTo(UPDATED_TEST_NAME);
        assertThat(testQuiz.getLibName()).isEqualTo(UPDATED_LIB_NAME);
        assertThat(testQuiz.isIsAutoSubmit()).isEqualTo(UPDATED_IS_AUTO_SUBMIT);
        assertThat(testQuiz.getMyRound()).isEqualTo(UPDATED_MY_ROUND);
        assertThat(testQuiz.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testQuiz.getSuccessTime()).isEqualTo(UPDATED_SUCCESS_TIME);
        assertThat(testQuiz.getTopmostSeconds()).isEqualTo(UPDATED_TOPMOST_SECONDS);
        assertThat(testQuiz.getCostSeconds()).isEqualTo(UPDATED_COST_SECONDS);
        assertThat(testQuiz.getMark()).isEqualTo(UPDATED_MARK);
        assertThat(testQuiz.getPassCount()).isEqualTo(UPDATED_PASS_COUNT);
        assertThat(testQuiz.getFailCount()).isEqualTo(UPDATED_FAIL_COUNT);
        assertThat(testQuiz.getCentCount()).isEqualTo(UPDATED_CENT_COUNT);
        assertThat(testQuiz.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testQuiz.isUsed()).isEqualTo(UPDATED_USED);
        assertThat(testQuiz.getUseTime()).isEqualTo(UPDATED_USE_TIME);
        assertThat(testQuiz.getUseNote()).isEqualTo(UPDATED_USE_NOTE);
    }

    @Test
    @Transactional
    public void updateNonExistingQuiz() throws Exception {
        int databaseSizeBeforeUpdate = quizRepository.findAll().size();

        // Create the Quiz

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuizMockMvc.perform(put("/api/quizzes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quiz)))
            .andExpect(status().isBadRequest());

        // Validate the Quiz in the database
        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuiz() throws Exception {
        // Initialize the database
        quizService.save(quiz);

        int databaseSizeBeforeDelete = quizRepository.findAll().size();

        // Get the quiz
        restQuizMockMvc.perform(delete("/api/quizzes/{id}", quiz.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Quiz.class);
        Quiz quiz1 = new Quiz();
        quiz1.setId(1L);
        Quiz quiz2 = new Quiz();
        quiz2.setId(quiz1.getId());
        assertThat(quiz1).isEqualTo(quiz2);
        quiz2.setId(2L);
        assertThat(quiz1).isNotEqualTo(quiz2);
        quiz1.setId(null);
        assertThat(quiz1).isNotEqualTo(quiz2);
    }
}
