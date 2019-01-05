package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.LivePoint;
import com.kkk.cocoapp.repository.LivePointRepository;
import com.kkk.cocoapp.service.LivePointService;
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
 * Test class for the LivePointResource REST controller.
 *
 * @see LivePointResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class LivePointResourceIntTest {

    private static final Integer DEFAULT_CORE_POINT_ID = 1;
    private static final Integer UPDATED_CORE_POINT_ID = 2;

    private static final String DEFAULT_CORE_POINT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CORE_POINT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CORE_SOURCE_ID = 1;
    private static final Integer UPDATED_CORE_SOURCE_ID = 2;

    private static final String DEFAULT_CORE_SOURCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CORE_SOURCE_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_BIRTH_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BIRTH_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_COLLECT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_COLLECT_VALUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATE = 1;
    private static final Integer UPDATED_STATE = 2;

    private static final Integer DEFAULT_SEVERITY = 1;
    private static final Integer UPDATED_SEVERITY = 2;

    @Autowired
    private LivePointRepository livePointRepository;

    @Autowired
    private LivePointService livePointService;

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

    private MockMvc restLivePointMockMvc;

    private LivePoint livePoint;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LivePointResource livePointResource = new LivePointResource(livePointService);
        this.restLivePointMockMvc = MockMvcBuilders.standaloneSetup(livePointResource)
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
    public static LivePoint createEntity(EntityManager em) {
        LivePoint livePoint = new LivePoint()
            .corePointId(DEFAULT_CORE_POINT_ID)
            .corePointName(DEFAULT_CORE_POINT_NAME)
            .coreSourceId(DEFAULT_CORE_SOURCE_ID)
            .coreSourceName(DEFAULT_CORE_SOURCE_NAME)
            .birthTime(DEFAULT_BIRTH_TIME)
            .collectValue(DEFAULT_COLLECT_VALUE)
            .state(DEFAULT_STATE)
            .severity(DEFAULT_SEVERITY);
        return livePoint;
    }

    @Before
    public void initTest() {
        livePoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createLivePoint() throws Exception {
        int databaseSizeBeforeCreate = livePointRepository.findAll().size();

        // Create the LivePoint
        restLivePointMockMvc.perform(post("/api/live-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livePoint)))
            .andExpect(status().isCreated());

        // Validate the LivePoint in the database
        List<LivePoint> livePointList = livePointRepository.findAll();
        assertThat(livePointList).hasSize(databaseSizeBeforeCreate + 1);
        LivePoint testLivePoint = livePointList.get(livePointList.size() - 1);
        assertThat(testLivePoint.getCorePointId()).isEqualTo(DEFAULT_CORE_POINT_ID);
        assertThat(testLivePoint.getCorePointName()).isEqualTo(DEFAULT_CORE_POINT_NAME);
        assertThat(testLivePoint.getCoreSourceId()).isEqualTo(DEFAULT_CORE_SOURCE_ID);
        assertThat(testLivePoint.getCoreSourceName()).isEqualTo(DEFAULT_CORE_SOURCE_NAME);
        assertThat(testLivePoint.getBirthTime()).isEqualTo(DEFAULT_BIRTH_TIME);
        assertThat(testLivePoint.getCollectValue()).isEqualTo(DEFAULT_COLLECT_VALUE);
        assertThat(testLivePoint.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testLivePoint.getSeverity()).isEqualTo(DEFAULT_SEVERITY);
    }

    @Test
    @Transactional
    public void createLivePointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = livePointRepository.findAll().size();

        // Create the LivePoint with an existing ID
        livePoint.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLivePointMockMvc.perform(post("/api/live-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livePoint)))
            .andExpect(status().isBadRequest());

        // Validate the LivePoint in the database
        List<LivePoint> livePointList = livePointRepository.findAll();
        assertThat(livePointList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLivePoints() throws Exception {
        // Initialize the database
        livePointRepository.saveAndFlush(livePoint);

        // Get all the livePointList
        restLivePointMockMvc.perform(get("/api/live-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(livePoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].corePointId").value(hasItem(DEFAULT_CORE_POINT_ID)))
            .andExpect(jsonPath("$.[*].corePointName").value(hasItem(DEFAULT_CORE_POINT_NAME.toString())))
            .andExpect(jsonPath("$.[*].coreSourceId").value(hasItem(DEFAULT_CORE_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].coreSourceName").value(hasItem(DEFAULT_CORE_SOURCE_NAME.toString())))
            .andExpect(jsonPath("$.[*].birthTime").value(hasItem(DEFAULT_BIRTH_TIME.toString())))
            .andExpect(jsonPath("$.[*].collectValue").value(hasItem(DEFAULT_COLLECT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].severity").value(hasItem(DEFAULT_SEVERITY)));
    }
    
    @Test
    @Transactional
    public void getLivePoint() throws Exception {
        // Initialize the database
        livePointRepository.saveAndFlush(livePoint);

        // Get the livePoint
        restLivePointMockMvc.perform(get("/api/live-points/{id}", livePoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(livePoint.getId().intValue()))
            .andExpect(jsonPath("$.corePointId").value(DEFAULT_CORE_POINT_ID))
            .andExpect(jsonPath("$.corePointName").value(DEFAULT_CORE_POINT_NAME.toString()))
            .andExpect(jsonPath("$.coreSourceId").value(DEFAULT_CORE_SOURCE_ID))
            .andExpect(jsonPath("$.coreSourceName").value(DEFAULT_CORE_SOURCE_NAME.toString()))
            .andExpect(jsonPath("$.birthTime").value(DEFAULT_BIRTH_TIME.toString()))
            .andExpect(jsonPath("$.collectValue").value(DEFAULT_COLLECT_VALUE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.severity").value(DEFAULT_SEVERITY));
    }

    @Test
    @Transactional
    public void getNonExistingLivePoint() throws Exception {
        // Get the livePoint
        restLivePointMockMvc.perform(get("/api/live-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLivePoint() throws Exception {
        // Initialize the database
        livePointService.save(livePoint);

        int databaseSizeBeforeUpdate = livePointRepository.findAll().size();

        // Update the livePoint
        LivePoint updatedLivePoint = livePointRepository.findById(livePoint.getId()).get();
        // Disconnect from session so that the updates on updatedLivePoint are not directly saved in db
        em.detach(updatedLivePoint);
        updatedLivePoint
            .corePointId(UPDATED_CORE_POINT_ID)
            .corePointName(UPDATED_CORE_POINT_NAME)
            .coreSourceId(UPDATED_CORE_SOURCE_ID)
            .coreSourceName(UPDATED_CORE_SOURCE_NAME)
            .birthTime(UPDATED_BIRTH_TIME)
            .collectValue(UPDATED_COLLECT_VALUE)
            .state(UPDATED_STATE)
            .severity(UPDATED_SEVERITY);

        restLivePointMockMvc.perform(put("/api/live-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLivePoint)))
            .andExpect(status().isOk());

        // Validate the LivePoint in the database
        List<LivePoint> livePointList = livePointRepository.findAll();
        assertThat(livePointList).hasSize(databaseSizeBeforeUpdate);
        LivePoint testLivePoint = livePointList.get(livePointList.size() - 1);
        assertThat(testLivePoint.getCorePointId()).isEqualTo(UPDATED_CORE_POINT_ID);
        assertThat(testLivePoint.getCorePointName()).isEqualTo(UPDATED_CORE_POINT_NAME);
        assertThat(testLivePoint.getCoreSourceId()).isEqualTo(UPDATED_CORE_SOURCE_ID);
        assertThat(testLivePoint.getCoreSourceName()).isEqualTo(UPDATED_CORE_SOURCE_NAME);
        assertThat(testLivePoint.getBirthTime()).isEqualTo(UPDATED_BIRTH_TIME);
        assertThat(testLivePoint.getCollectValue()).isEqualTo(UPDATED_COLLECT_VALUE);
        assertThat(testLivePoint.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testLivePoint.getSeverity()).isEqualTo(UPDATED_SEVERITY);
    }

    @Test
    @Transactional
    public void updateNonExistingLivePoint() throws Exception {
        int databaseSizeBeforeUpdate = livePointRepository.findAll().size();

        // Create the LivePoint

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLivePointMockMvc.perform(put("/api/live-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livePoint)))
            .andExpect(status().isBadRequest());

        // Validate the LivePoint in the database
        List<LivePoint> livePointList = livePointRepository.findAll();
        assertThat(livePointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLivePoint() throws Exception {
        // Initialize the database
        livePointService.save(livePoint);

        int databaseSizeBeforeDelete = livePointRepository.findAll().size();

        // Get the livePoint
        restLivePointMockMvc.perform(delete("/api/live-points/{id}", livePoint.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LivePoint> livePointList = livePointRepository.findAll();
        assertThat(livePointList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LivePoint.class);
        LivePoint livePoint1 = new LivePoint();
        livePoint1.setId(1L);
        LivePoint livePoint2 = new LivePoint();
        livePoint2.setId(livePoint1.getId());
        assertThat(livePoint1).isEqualTo(livePoint2);
        livePoint2.setId(2L);
        assertThat(livePoint1).isNotEqualTo(livePoint2);
        livePoint1.setId(null);
        assertThat(livePoint1).isNotEqualTo(livePoint2);
    }
}
