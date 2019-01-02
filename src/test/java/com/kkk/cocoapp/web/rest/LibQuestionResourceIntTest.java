package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.LibQuestion;
import com.kkk.cocoapp.repository.LibQuestionRepository;
import com.kkk.cocoapp.service.LibQuestionService;
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
 * Test class for the LibQuestionResource REST controller.
 *
 * @see LibQuestionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class LibQuestionResourceIntTest {

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
    private LibQuestionRepository libQuestionRepository;

    @Autowired
    private LibQuestionService libQuestionService;

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

    private MockMvc restLibQuestionMockMvc;

    private LibQuestion libQuestion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LibQuestionResource libQuestionResource = new LibQuestionResource(libQuestionService);
        this.restLibQuestionMockMvc = MockMvcBuilders.standaloneSetup(libQuestionResource)
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
    public static LibQuestion createEntity(EntityManager em) {
        LibQuestion libQuestion = new LibQuestion()
            .libName(DEFAULT_LIB_NAME)
            .ask(DEFAULT_ASK)
            .answer(DEFAULT_ANSWER)
            .countPass(DEFAULT_COUNT_PASS)
            .countFail(DEFAULT_COUNT_FAIL)
            .countRate(DEFAULT_COUNT_RATE)
            .countPassAgain(DEFAULT_COUNT_PASS_AGAIN)
            .isPass(DEFAULT_IS_PASS);
        return libQuestion;
    }

    @Before
    public void initTest() {
        libQuestion = createEntity(em);
    }

    @Test
    @Transactional
    public void createLibQuestion() throws Exception {
        int databaseSizeBeforeCreate = libQuestionRepository.findAll().size();

        // Create the LibQuestion
        restLibQuestionMockMvc.perform(post("/api/lib-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(libQuestion)))
            .andExpect(status().isCreated());

        // Validate the LibQuestion in the database
        List<LibQuestion> libQuestionList = libQuestionRepository.findAll();
        assertThat(libQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        LibQuestion testLibQuestion = libQuestionList.get(libQuestionList.size() - 1);
        assertThat(testLibQuestion.getLibName()).isEqualTo(DEFAULT_LIB_NAME);
        assertThat(testLibQuestion.getAsk()).isEqualTo(DEFAULT_ASK);
        assertThat(testLibQuestion.getAnswer()).isEqualTo(DEFAULT_ANSWER);
        assertThat(testLibQuestion.getCountPass()).isEqualTo(DEFAULT_COUNT_PASS);
        assertThat(testLibQuestion.getCountFail()).isEqualTo(DEFAULT_COUNT_FAIL);
        assertThat(testLibQuestion.getCountRate()).isEqualTo(DEFAULT_COUNT_RATE);
        assertThat(testLibQuestion.getCountPassAgain()).isEqualTo(DEFAULT_COUNT_PASS_AGAIN);
        assertThat(testLibQuestion.isIsPass()).isEqualTo(DEFAULT_IS_PASS);
    }

    @Test
    @Transactional
    public void createLibQuestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = libQuestionRepository.findAll().size();

        // Create the LibQuestion with an existing ID
        libQuestion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLibQuestionMockMvc.perform(post("/api/lib-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(libQuestion)))
            .andExpect(status().isBadRequest());

        // Validate the LibQuestion in the database
        List<LibQuestion> libQuestionList = libQuestionRepository.findAll();
        assertThat(libQuestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLibQuestions() throws Exception {
        // Initialize the database
        libQuestionRepository.saveAndFlush(libQuestion);

        // Get all the libQuestionList
        restLibQuestionMockMvc.perform(get("/api/lib-questions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(libQuestion.getId().intValue())))
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
    public void getLibQuestion() throws Exception {
        // Initialize the database
        libQuestionRepository.saveAndFlush(libQuestion);

        // Get the libQuestion
        restLibQuestionMockMvc.perform(get("/api/lib-questions/{id}", libQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(libQuestion.getId().intValue()))
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
    public void getNonExistingLibQuestion() throws Exception {
        // Get the libQuestion
        restLibQuestionMockMvc.perform(get("/api/lib-questions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLibQuestion() throws Exception {
        // Initialize the database
        libQuestionService.save(libQuestion);

        int databaseSizeBeforeUpdate = libQuestionRepository.findAll().size();

        // Update the libQuestion
        LibQuestion updatedLibQuestion = libQuestionRepository.findById(libQuestion.getId()).get();
        // Disconnect from session so that the updates on updatedLibQuestion are not directly saved in db
        em.detach(updatedLibQuestion);
        updatedLibQuestion
            .libName(UPDATED_LIB_NAME)
            .ask(UPDATED_ASK)
            .answer(UPDATED_ANSWER)
            .countPass(UPDATED_COUNT_PASS)
            .countFail(UPDATED_COUNT_FAIL)
            .countRate(UPDATED_COUNT_RATE)
            .countPassAgain(UPDATED_COUNT_PASS_AGAIN)
            .isPass(UPDATED_IS_PASS);

        restLibQuestionMockMvc.perform(put("/api/lib-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLibQuestion)))
            .andExpect(status().isOk());

        // Validate the LibQuestion in the database
        List<LibQuestion> libQuestionList = libQuestionRepository.findAll();
        assertThat(libQuestionList).hasSize(databaseSizeBeforeUpdate);
        LibQuestion testLibQuestion = libQuestionList.get(libQuestionList.size() - 1);
        assertThat(testLibQuestion.getLibName()).isEqualTo(UPDATED_LIB_NAME);
        assertThat(testLibQuestion.getAsk()).isEqualTo(UPDATED_ASK);
        assertThat(testLibQuestion.getAnswer()).isEqualTo(UPDATED_ANSWER);
        assertThat(testLibQuestion.getCountPass()).isEqualTo(UPDATED_COUNT_PASS);
        assertThat(testLibQuestion.getCountFail()).isEqualTo(UPDATED_COUNT_FAIL);
        assertThat(testLibQuestion.getCountRate()).isEqualTo(UPDATED_COUNT_RATE);
        assertThat(testLibQuestion.getCountPassAgain()).isEqualTo(UPDATED_COUNT_PASS_AGAIN);
        assertThat(testLibQuestion.isIsPass()).isEqualTo(UPDATED_IS_PASS);
    }

    @Test
    @Transactional
    public void updateNonExistingLibQuestion() throws Exception {
        int databaseSizeBeforeUpdate = libQuestionRepository.findAll().size();

        // Create the LibQuestion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLibQuestionMockMvc.perform(put("/api/lib-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(libQuestion)))
            .andExpect(status().isBadRequest());

        // Validate the LibQuestion in the database
        List<LibQuestion> libQuestionList = libQuestionRepository.findAll();
        assertThat(libQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLibQuestion() throws Exception {
        // Initialize the database
        libQuestionService.save(libQuestion);

        int databaseSizeBeforeDelete = libQuestionRepository.findAll().size();

        // Get the libQuestion
        restLibQuestionMockMvc.perform(delete("/api/lib-questions/{id}", libQuestion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LibQuestion> libQuestionList = libQuestionRepository.findAll();
        assertThat(libQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LibQuestion.class);
        LibQuestion libQuestion1 = new LibQuestion();
        libQuestion1.setId(1L);
        LibQuestion libQuestion2 = new LibQuestion();
        libQuestion2.setId(libQuestion1.getId());
        assertThat(libQuestion1).isEqualTo(libQuestion2);
        libQuestion2.setId(2L);
        assertThat(libQuestion1).isNotEqualTo(libQuestion2);
        libQuestion1.setId(null);
        assertThat(libQuestion1).isNotEqualTo(libQuestion2);
    }
}
