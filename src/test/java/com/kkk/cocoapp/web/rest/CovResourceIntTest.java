package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.Cov;
import com.kkk.cocoapp.repository.CovRepository;
import com.kkk.cocoapp.service.CovService;
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
 * Test class for the CovResource REST controller.
 *
 * @see CovResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class CovResourceIntTest {

    private static final Integer DEFAULT_CORE_POINT_ID = 1;
    private static final Integer UPDATED_CORE_POINT_ID = 2;

    private static final Integer DEFAULT_CORE_SOURCE_ID = 1;
    private static final Integer UPDATED_CORE_SOURCE_ID = 2;

    private static final Integer DEFAULT_ENGINE_ID = 1;
    private static final Integer UPDATED_ENGINE_ID = 2;

    private static final Integer DEFAULT_QOS = 1;
    private static final Integer UPDATED_QOS = 2;

    private static final Instant DEFAULT_BIRTH_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BIRTH_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATE = 1;
    private static final Integer UPDATED_STATE = 2;

    @Autowired
    private CovRepository covRepository;

    @Autowired
    private CovService covService;

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

    private MockMvc restCovMockMvc;

    private Cov cov;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CovResource covResource = new CovResource(covService);
        this.restCovMockMvc = MockMvcBuilders.standaloneSetup(covResource)
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
    public static Cov createEntity(EntityManager em) {
        Cov cov = new Cov()
            .corePointId(DEFAULT_CORE_POINT_ID)
            .coreSourceId(DEFAULT_CORE_SOURCE_ID)
            .engineId(DEFAULT_ENGINE_ID)
            .qos(DEFAULT_QOS)
            .birthTime(DEFAULT_BIRTH_TIME)
            .value(DEFAULT_VALUE)
            .state(DEFAULT_STATE);
        return cov;
    }

    @Before
    public void initTest() {
        cov = createEntity(em);
    }

    @Test
    @Transactional
    public void createCov() throws Exception {
        int databaseSizeBeforeCreate = covRepository.findAll().size();

        // Create the Cov
        restCovMockMvc.perform(post("/api/covs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cov)))
            .andExpect(status().isCreated());

        // Validate the Cov in the database
        List<Cov> covList = covRepository.findAll();
        assertThat(covList).hasSize(databaseSizeBeforeCreate + 1);
        Cov testCov = covList.get(covList.size() - 1);
        assertThat(testCov.getCorePointId()).isEqualTo(DEFAULT_CORE_POINT_ID);
        assertThat(testCov.getCoreSourceId()).isEqualTo(DEFAULT_CORE_SOURCE_ID);
        assertThat(testCov.getEngineId()).isEqualTo(DEFAULT_ENGINE_ID);
        assertThat(testCov.getQos()).isEqualTo(DEFAULT_QOS);
        assertThat(testCov.getBirthTime()).isEqualTo(DEFAULT_BIRTH_TIME);
        assertThat(testCov.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testCov.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    public void createCovWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = covRepository.findAll().size();

        // Create the Cov with an existing ID
        cov.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCovMockMvc.perform(post("/api/covs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cov)))
            .andExpect(status().isBadRequest());

        // Validate the Cov in the database
        List<Cov> covList = covRepository.findAll();
        assertThat(covList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCovs() throws Exception {
        // Initialize the database
        covRepository.saveAndFlush(cov);

        // Get all the covList
        restCovMockMvc.perform(get("/api/covs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cov.getId().intValue())))
            .andExpect(jsonPath("$.[*].corePointId").value(hasItem(DEFAULT_CORE_POINT_ID)))
            .andExpect(jsonPath("$.[*].coreSourceId").value(hasItem(DEFAULT_CORE_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].engineId").value(hasItem(DEFAULT_ENGINE_ID)))
            .andExpect(jsonPath("$.[*].qos").value(hasItem(DEFAULT_QOS)))
            .andExpect(jsonPath("$.[*].birthTime").value(hasItem(DEFAULT_BIRTH_TIME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)));
    }
    
    @Test
    @Transactional
    public void getCov() throws Exception {
        // Initialize the database
        covRepository.saveAndFlush(cov);

        // Get the cov
        restCovMockMvc.perform(get("/api/covs/{id}", cov.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cov.getId().intValue()))
            .andExpect(jsonPath("$.corePointId").value(DEFAULT_CORE_POINT_ID))
            .andExpect(jsonPath("$.coreSourceId").value(DEFAULT_CORE_SOURCE_ID))
            .andExpect(jsonPath("$.engineId").value(DEFAULT_ENGINE_ID))
            .andExpect(jsonPath("$.qos").value(DEFAULT_QOS))
            .andExpect(jsonPath("$.birthTime").value(DEFAULT_BIRTH_TIME.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE));
    }

    @Test
    @Transactional
    public void getNonExistingCov() throws Exception {
        // Get the cov
        restCovMockMvc.perform(get("/api/covs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCov() throws Exception {
        // Initialize the database
        covService.save(cov);

        int databaseSizeBeforeUpdate = covRepository.findAll().size();

        // Update the cov
        Cov updatedCov = covRepository.findById(cov.getId()).get();
        // Disconnect from session so that the updates on updatedCov are not directly saved in db
        em.detach(updatedCov);
        updatedCov
            .corePointId(UPDATED_CORE_POINT_ID)
            .coreSourceId(UPDATED_CORE_SOURCE_ID)
            .engineId(UPDATED_ENGINE_ID)
            .qos(UPDATED_QOS)
            .birthTime(UPDATED_BIRTH_TIME)
            .value(UPDATED_VALUE)
            .state(UPDATED_STATE);

        restCovMockMvc.perform(put("/api/covs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCov)))
            .andExpect(status().isOk());

        // Validate the Cov in the database
        List<Cov> covList = covRepository.findAll();
        assertThat(covList).hasSize(databaseSizeBeforeUpdate);
        Cov testCov = covList.get(covList.size() - 1);
        assertThat(testCov.getCorePointId()).isEqualTo(UPDATED_CORE_POINT_ID);
        assertThat(testCov.getCoreSourceId()).isEqualTo(UPDATED_CORE_SOURCE_ID);
        assertThat(testCov.getEngineId()).isEqualTo(UPDATED_ENGINE_ID);
        assertThat(testCov.getQos()).isEqualTo(UPDATED_QOS);
        assertThat(testCov.getBirthTime()).isEqualTo(UPDATED_BIRTH_TIME);
        assertThat(testCov.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testCov.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCov() throws Exception {
        int databaseSizeBeforeUpdate = covRepository.findAll().size();

        // Create the Cov

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCovMockMvc.perform(put("/api/covs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cov)))
            .andExpect(status().isBadRequest());

        // Validate the Cov in the database
        List<Cov> covList = covRepository.findAll();
        assertThat(covList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCov() throws Exception {
        // Initialize the database
        covService.save(cov);

        int databaseSizeBeforeDelete = covRepository.findAll().size();

        // Get the cov
        restCovMockMvc.perform(delete("/api/covs/{id}", cov.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cov> covList = covRepository.findAll();
        assertThat(covList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cov.class);
        Cov cov1 = new Cov();
        cov1.setId(1L);
        Cov cov2 = new Cov();
        cov2.setId(cov1.getId());
        assertThat(cov1).isEqualTo(cov2);
        cov2.setId(2L);
        assertThat(cov1).isNotEqualTo(cov2);
        cov1.setId(null);
        assertThat(cov1).isNotEqualTo(cov2);
    }
}
