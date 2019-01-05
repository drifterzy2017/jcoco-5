package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.DesiredCov;
import com.kkk.cocoapp.repository.DesiredCovRepository;
import com.kkk.cocoapp.service.DesiredCovService;
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
 * Test class for the DesiredCovResource REST controller.
 *
 * @see DesiredCovResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class DesiredCovResourceIntTest {

    private static final Integer DEFAULT_CORE_POINT_ID = 1;
    private static final Integer UPDATED_CORE_POINT_ID = 2;

    private static final Integer DEFAULT_CORE_SOURCE_ID = 1;
    private static final Integer UPDATED_CORE_SOURCE_ID = 2;

    private static final Integer DEFAULT_ENGINE_ID = 1;
    private static final Integer UPDATED_ENGINE_ID = 2;

    private static final String DEFAULT_DESIRED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_DESIRED_VALUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final Instant DEFAULT_BIRTH_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BIRTH_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_STATE = 1;
    private static final Integer UPDATED_STATE = 2;

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private DesiredCovRepository desiredCovRepository;

    @Autowired
    private DesiredCovService desiredCovService;

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

    private MockMvc restDesiredCovMockMvc;

    private DesiredCov desiredCov;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DesiredCovResource desiredCovResource = new DesiredCovResource(desiredCovService);
        this.restDesiredCovMockMvc = MockMvcBuilders.standaloneSetup(desiredCovResource)
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
    public static DesiredCov createEntity(EntityManager em) {
        DesiredCov desiredCov = new DesiredCov()
            .corePointId(DEFAULT_CORE_POINT_ID)
            .coreSourceId(DEFAULT_CORE_SOURCE_ID)
            .engineId(DEFAULT_ENGINE_ID)
            .desiredValue(DEFAULT_DESIRED_VALUE)
            .userId(DEFAULT_USER_ID)
            .birthTime(DEFAULT_BIRTH_TIME)
            .state(DEFAULT_STATE)
            .message(DEFAULT_MESSAGE);
        return desiredCov;
    }

    @Before
    public void initTest() {
        desiredCov = createEntity(em);
    }

    @Test
    @Transactional
    public void createDesiredCov() throws Exception {
        int databaseSizeBeforeCreate = desiredCovRepository.findAll().size();

        // Create the DesiredCov
        restDesiredCovMockMvc.perform(post("/api/desired-covs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desiredCov)))
            .andExpect(status().isCreated());

        // Validate the DesiredCov in the database
        List<DesiredCov> desiredCovList = desiredCovRepository.findAll();
        assertThat(desiredCovList).hasSize(databaseSizeBeforeCreate + 1);
        DesiredCov testDesiredCov = desiredCovList.get(desiredCovList.size() - 1);
        assertThat(testDesiredCov.getCorePointId()).isEqualTo(DEFAULT_CORE_POINT_ID);
        assertThat(testDesiredCov.getCoreSourceId()).isEqualTo(DEFAULT_CORE_SOURCE_ID);
        assertThat(testDesiredCov.getEngineId()).isEqualTo(DEFAULT_ENGINE_ID);
        assertThat(testDesiredCov.getDesiredValue()).isEqualTo(DEFAULT_DESIRED_VALUE);
        assertThat(testDesiredCov.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testDesiredCov.getBirthTime()).isEqualTo(DEFAULT_BIRTH_TIME);
        assertThat(testDesiredCov.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testDesiredCov.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createDesiredCovWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = desiredCovRepository.findAll().size();

        // Create the DesiredCov with an existing ID
        desiredCov.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDesiredCovMockMvc.perform(post("/api/desired-covs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desiredCov)))
            .andExpect(status().isBadRequest());

        // Validate the DesiredCov in the database
        List<DesiredCov> desiredCovList = desiredCovRepository.findAll();
        assertThat(desiredCovList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDesiredCovs() throws Exception {
        // Initialize the database
        desiredCovRepository.saveAndFlush(desiredCov);

        // Get all the desiredCovList
        restDesiredCovMockMvc.perform(get("/api/desired-covs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(desiredCov.getId().intValue())))
            .andExpect(jsonPath("$.[*].corePointId").value(hasItem(DEFAULT_CORE_POINT_ID)))
            .andExpect(jsonPath("$.[*].coreSourceId").value(hasItem(DEFAULT_CORE_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].engineId").value(hasItem(DEFAULT_ENGINE_ID)))
            .andExpect(jsonPath("$.[*].desiredValue").value(hasItem(DEFAULT_DESIRED_VALUE.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].birthTime").value(hasItem(DEFAULT_BIRTH_TIME.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getDesiredCov() throws Exception {
        // Initialize the database
        desiredCovRepository.saveAndFlush(desiredCov);

        // Get the desiredCov
        restDesiredCovMockMvc.perform(get("/api/desired-covs/{id}", desiredCov.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(desiredCov.getId().intValue()))
            .andExpect(jsonPath("$.corePointId").value(DEFAULT_CORE_POINT_ID))
            .andExpect(jsonPath("$.coreSourceId").value(DEFAULT_CORE_SOURCE_ID))
            .andExpect(jsonPath("$.engineId").value(DEFAULT_ENGINE_ID))
            .andExpect(jsonPath("$.desiredValue").value(DEFAULT_DESIRED_VALUE.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.birthTime").value(DEFAULT_BIRTH_TIME.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDesiredCov() throws Exception {
        // Get the desiredCov
        restDesiredCovMockMvc.perform(get("/api/desired-covs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDesiredCov() throws Exception {
        // Initialize the database
        desiredCovService.save(desiredCov);

        int databaseSizeBeforeUpdate = desiredCovRepository.findAll().size();

        // Update the desiredCov
        DesiredCov updatedDesiredCov = desiredCovRepository.findById(desiredCov.getId()).get();
        // Disconnect from session so that the updates on updatedDesiredCov are not directly saved in db
        em.detach(updatedDesiredCov);
        updatedDesiredCov
            .corePointId(UPDATED_CORE_POINT_ID)
            .coreSourceId(UPDATED_CORE_SOURCE_ID)
            .engineId(UPDATED_ENGINE_ID)
            .desiredValue(UPDATED_DESIRED_VALUE)
            .userId(UPDATED_USER_ID)
            .birthTime(UPDATED_BIRTH_TIME)
            .state(UPDATED_STATE)
            .message(UPDATED_MESSAGE);

        restDesiredCovMockMvc.perform(put("/api/desired-covs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDesiredCov)))
            .andExpect(status().isOk());

        // Validate the DesiredCov in the database
        List<DesiredCov> desiredCovList = desiredCovRepository.findAll();
        assertThat(desiredCovList).hasSize(databaseSizeBeforeUpdate);
        DesiredCov testDesiredCov = desiredCovList.get(desiredCovList.size() - 1);
        assertThat(testDesiredCov.getCorePointId()).isEqualTo(UPDATED_CORE_POINT_ID);
        assertThat(testDesiredCov.getCoreSourceId()).isEqualTo(UPDATED_CORE_SOURCE_ID);
        assertThat(testDesiredCov.getEngineId()).isEqualTo(UPDATED_ENGINE_ID);
        assertThat(testDesiredCov.getDesiredValue()).isEqualTo(UPDATED_DESIRED_VALUE);
        assertThat(testDesiredCov.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testDesiredCov.getBirthTime()).isEqualTo(UPDATED_BIRTH_TIME);
        assertThat(testDesiredCov.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testDesiredCov.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingDesiredCov() throws Exception {
        int databaseSizeBeforeUpdate = desiredCovRepository.findAll().size();

        // Create the DesiredCov

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDesiredCovMockMvc.perform(put("/api/desired-covs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desiredCov)))
            .andExpect(status().isBadRequest());

        // Validate the DesiredCov in the database
        List<DesiredCov> desiredCovList = desiredCovRepository.findAll();
        assertThat(desiredCovList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDesiredCov() throws Exception {
        // Initialize the database
        desiredCovService.save(desiredCov);

        int databaseSizeBeforeDelete = desiredCovRepository.findAll().size();

        // Get the desiredCov
        restDesiredCovMockMvc.perform(delete("/api/desired-covs/{id}", desiredCov.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DesiredCov> desiredCovList = desiredCovRepository.findAll();
        assertThat(desiredCovList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DesiredCov.class);
        DesiredCov desiredCov1 = new DesiredCov();
        desiredCov1.setId(1L);
        DesiredCov desiredCov2 = new DesiredCov();
        desiredCov2.setId(desiredCov1.getId());
        assertThat(desiredCov1).isEqualTo(desiredCov2);
        desiredCov2.setId(2L);
        assertThat(desiredCov1).isNotEqualTo(desiredCov2);
        desiredCov1.setId(null);
        assertThat(desiredCov1).isNotEqualTo(desiredCov2);
    }
}
