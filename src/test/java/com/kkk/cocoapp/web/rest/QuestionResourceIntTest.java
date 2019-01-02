package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.Question;
import com.kkk.cocoapp.repository.QuestionRepository;
import com.kkk.cocoapp.service.QuestionService;
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
import java.util.List;


import static com.kkk.cocoapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuestionResource REST controller.
 *
 * @see QuestionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class QuestionResourceIntTest {

    private static final String DEFAULT_LIB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LIB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ASK = "AAAAAAAAAA";
    private static final String UPDATED_ASK = "BBBBBBBBBB";

    private static final String DEFAULT_ANSWER = "AAAAAAAAAA";
    private static final String UPDATED_ANSWER = "BBBBBBBBBB";

    private static final Integer DEFAULT_COUNT_PASS = 1;
    private static final Integer UPDATED_COUNT_PASS = 2;

    private static final Integer DEFAULT_COUNT_FAIL = 1;
    private static final Integer UPDATED_COUNT_FAIL = 2;

    private static final Integer DEFAULT_COUNT_RATE = 1;
    private static final Integer UPDATED_COUNT_RATE = 2;

    private static final Integer DEFAULT_COUNT_PASS_AGAIN = 1;
    private static final Integer UPDATED_COUNT_PASS_AGAIN = 2;

    private static final Boolean DEFAULT_IS_PASS = false;
    private static final Boolean UPDATED_IS_PASS = true;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

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

    private MockMvc restQuestionMockMvc;

    private Question question;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuestionResource questionResource = new QuestionResource(questionService);
        this.restQuestionMockMvc = MockMvcBuilders.standaloneSetup(questionResource)
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
    public static Question createEntity(EntityManager em) {
        Question question = new Question()
            .libName(DEFAULT_LIB_NAME)
            .ask(DEFAULT_ASK)
            .answer(DEFAULT_ANSWER)
            .countPass(DEFAULT_COUNT_PASS)
            .countFail(DEFAULT_COUNT_FAIL)
            .countRate(DEFAULT_COUNT_RATE)
            .countPassAgain(DEFAULT_COUNT_PASS_AGAIN)
            .isPass(DEFAULT_IS_PASS);
        return question;
    }

    @Before
    public void initTest() {
        question = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuestion() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();

        // Create the Question
        restQuestionMockMvc.perform(post("/api/questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(question)))
            .andExpect(status().isCreated());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeCreate + 1);
        Question testQuestion = questionList.get(questionList.size() - 1);
        assertThat(testQuestion.getLibName()).isEqualTo(DEFAULT_LIB_NAME);
        assertThat(testQuestion.getAsk()).isEqualTo(DEFAULT_ASK);
        assertThat(testQuestion.getAnswer()).isEqualTo(DEFAULT_ANSWER);
        assertThat(testQuestion.getCountPass()).isEqualTo(DEFAULT_COUNT_PASS);
        assertThat(testQuestion.getCountFail()).isEqualTo(DEFAULT_COUNT_FAIL);
        assertThat(testQuestion.getCountRate()).isEqualTo(DEFAULT_COUNT_RATE);
        assertThat(testQuestion.getCountPassAgain()).isEqualTo(DEFAULT_COUNT_PASS_AGAIN);
        assertThat(testQuestion.isIsPass()).isEqualTo(DEFAULT_IS_PASS);
    }

    @Test
    @Transactional
    public void createQuestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();

        // Create the Question with an existing ID
        question.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionMockMvc.perform(post("/api/questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(question)))
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllQuestions() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        // Get all the questionList
        restQuestionMockMvc.perform(get("/api/questions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(question.getId().intValue())))
            .andExpect(jsonPath("$.[*].libName").value(hasItem(DEFAULT_LIB_NAME.toString())))
            .andExpect(jsonPath("$.[*].ask").value(hasItem(DEFAULT_ASK.toString())))
            .andExpect(jsonPath("$.[*].answer").value(hasItem(DEFAULT_ANSWER.toString())))
            .andExpect(jsonPath("$.[*].countPass").value(hasItem(DEFAULT_COUNT_PASS)))
            .andExpect(jsonPath("$.[*].countFail").value(hasItem(DEFAULT_COUNT_FAIL)))
            .andExpect(jsonPath("$.[*].countRate").value(hasItem(DEFAULT_COUNT_RATE)))
            .andExpect(jsonPath("$.[*].countPassAgain").value(hasItem(DEFAULT_COUNT_PASS_AGAIN)))
            .andExpect(jsonPath("$.[*].isPass").value(hasItem(DEFAULT_IS_PASS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        // Get the question
        restQuestionMockMvc.perform(get("/api/questions/{id}", question.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(question.getId().intValue()))
            .andExpect(jsonPath("$.libName").value(DEFAULT_LIB_NAME.toString()))
            .andExpect(jsonPath("$.ask").value(DEFAULT_ASK.toString()))
            .andExpect(jsonPath("$.answer").value(DEFAULT_ANSWER.toString()))
            .andExpect(jsonPath("$.countPass").value(DEFAULT_COUNT_PASS))
            .andExpect(jsonPath("$.countFail").value(DEFAULT_COUNT_FAIL))
            .andExpect(jsonPath("$.countRate").value(DEFAULT_COUNT_RATE))
            .andExpect(jsonPath("$.countPassAgain").value(DEFAULT_COUNT_PASS_AGAIN))
            .andExpect(jsonPath("$.isPass").value(DEFAULT_IS_PASS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQuestion() throws Exception {
        // Get the question
        restQuestionMockMvc.perform(get("/api/questions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuestion() throws Exception {
        // Initialize the database
        questionService.save(question);

        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Update the question
        Question updatedQuestion = questionRepository.findById(question.getId()).get();
        // Disconnect from session so that the updates on updatedQuestion are not directly saved in db
        em.detach(updatedQuestion);
        updatedQuestion
            .libName(UPDATED_LIB_NAME)
            .ask(UPDATED_ASK)
            .answer(UPDATED_ANSWER)
            .countPass(UPDATED_COUNT_PASS)
            .countFail(UPDATED_COUNT_FAIL)
            .countRate(UPDATED_COUNT_RATE)
            .countPassAgain(UPDATED_COUNT_PASS_AGAIN)
            .isPass(UPDATED_IS_PASS);

        restQuestionMockMvc.perform(put("/api/questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedQuestion)))
            .andExpect(status().isOk());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
        Question testQuestion = questionList.get(questionList.size() - 1);
        assertThat(testQuestion.getLibName()).isEqualTo(UPDATED_LIB_NAME);
        assertThat(testQuestion.getAsk()).isEqualTo(UPDATED_ASK);
        assertThat(testQuestion.getAnswer()).isEqualTo(UPDATED_ANSWER);
        assertThat(testQuestion.getCountPass()).isEqualTo(UPDATED_COUNT_PASS);
        assertThat(testQuestion.getCountFail()).isEqualTo(UPDATED_COUNT_FAIL);
        assertThat(testQuestion.getCountRate()).isEqualTo(UPDATED_COUNT_RATE);
        assertThat(testQuestion.getCountPassAgain()).isEqualTo(UPDATED_COUNT_PASS_AGAIN);
        assertThat(testQuestion.isIsPass()).isEqualTo(UPDATED_IS_PASS);
    }

    @Test
    @Transactional
    public void updateNonExistingQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Create the Question

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionMockMvc.perform(put("/api/questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(question)))
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuestion() throws Exception {
        // Initialize the database
        questionService.save(question);

        int databaseSizeBeforeDelete = questionRepository.findAll().size();

        // Get the question
        restQuestionMockMvc.perform(delete("/api/questions/{id}", question.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Question.class);
        Question question1 = new Question();
        question1.setId(1L);
        Question question2 = new Question();
        question2.setId(question1.getId());
        assertThat(question1).isEqualTo(question2);
        question2.setId(2L);
        assertThat(question1).isNotEqualTo(question2);
        question1.setId(null);
        assertThat(question1).isNotEqualTo(question2);
    }
}
