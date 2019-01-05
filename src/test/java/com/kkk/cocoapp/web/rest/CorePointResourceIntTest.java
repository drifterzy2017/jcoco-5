package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.CorePoint;
import com.kkk.cocoapp.repository.CorePointRepository;
import com.kkk.cocoapp.service.CorePointService;
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
 * Test class for the CorePointResource REST controller.
 *
 * @see CorePointResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class CorePointResourceIntTest {

    private static final Integer DEFAULT_CORE_POINT_ID = 1;
    private static final Integer UPDATED_CORE_POINT_ID = 2;

    private static final String DEFAULT_POINT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_POINT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCURACY = "AAAAAAAAAA";
    private static final String UPDATED_ACCURACY = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_MAX = "AAAAAAAAAA";
    private static final String UPDATED_MAX = "BBBBBBBBBB";

    private static final String DEFAULT_MIN = "AAAAAAAAAA";
    private static final String UPDATED_MIN = "BBBBBBBBBB";

    private static final Integer DEFAULT_CORE_SOURCE_ID = 1;
    private static final Integer UPDATED_CORE_SOURCE_ID = 2;

    private static final Integer DEFAULT_CORE_DATA_TYPE_ID = 1;
    private static final Integer UPDATED_CORE_DATA_TYPE_ID = 2;

    private static final Integer DEFAULT_EVENT_SEVERITY = 1;
    private static final Integer UPDATED_EVENT_SEVERITY = 2;

    private static final Integer DEFAULT_STATE_RULE_ID = 1;
    private static final Integer UPDATED_STATE_RULE_ID = 2;

    private static final Boolean DEFAULT_READABLE = false;
    private static final Boolean UPDATED_READABLE = true;

    private static final Boolean DEFAULT_WRITABLE = false;
    private static final Boolean UPDATED_WRITABLE = true;

    private static final String DEFAULT_DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_VALUE = "BBBBBBBBBB";

    private static final Float DEFAULT_STEP = 1F;
    private static final Float UPDATED_STEP = 2F;

    private static final Boolean DEFAULT_MASKED = false;
    private static final Boolean UPDATED_MASKED = true;

    @Autowired
    private CorePointRepository corePointRepository;

    @Autowired
    private CorePointService corePointService;

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

    private MockMvc restCorePointMockMvc;

    private CorePoint corePoint;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CorePointResource corePointResource = new CorePointResource(corePointService);
        this.restCorePointMockMvc = MockMvcBuilders.standaloneSetup(corePointResource)
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
    public static CorePoint createEntity(EntityManager em) {
        CorePoint corePoint = new CorePoint()
            .corePointId(DEFAULT_CORE_POINT_ID)
            .pointName(DEFAULT_POINT_NAME)
            .accuracy(DEFAULT_ACCURACY)
            .unit(DEFAULT_UNIT)
            .max(DEFAULT_MAX)
            .min(DEFAULT_MIN)
            .coreSourceId(DEFAULT_CORE_SOURCE_ID)
            .coreDataTypeId(DEFAULT_CORE_DATA_TYPE_ID)
            .eventSeverity(DEFAULT_EVENT_SEVERITY)
            .stateRuleId(DEFAULT_STATE_RULE_ID)
            .readable(DEFAULT_READABLE)
            .writable(DEFAULT_WRITABLE)
            .defaultValue(DEFAULT_DEFAULT_VALUE)
            .step(DEFAULT_STEP)
            .masked(DEFAULT_MASKED);
        return corePoint;
    }

    @Before
    public void initTest() {
        corePoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createCorePoint() throws Exception {
        int databaseSizeBeforeCreate = corePointRepository.findAll().size();

        // Create the CorePoint
        restCorePointMockMvc.perform(post("/api/core-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(corePoint)))
            .andExpect(status().isCreated());

        // Validate the CorePoint in the database
        List<CorePoint> corePointList = corePointRepository.findAll();
        assertThat(corePointList).hasSize(databaseSizeBeforeCreate + 1);
        CorePoint testCorePoint = corePointList.get(corePointList.size() - 1);
        assertThat(testCorePoint.getCorePointId()).isEqualTo(DEFAULT_CORE_POINT_ID);
        assertThat(testCorePoint.getPointName()).isEqualTo(DEFAULT_POINT_NAME);
        assertThat(testCorePoint.getAccuracy()).isEqualTo(DEFAULT_ACCURACY);
        assertThat(testCorePoint.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testCorePoint.getMax()).isEqualTo(DEFAULT_MAX);
        assertThat(testCorePoint.getMin()).isEqualTo(DEFAULT_MIN);
        assertThat(testCorePoint.getCoreSourceId()).isEqualTo(DEFAULT_CORE_SOURCE_ID);
        assertThat(testCorePoint.getCoreDataTypeId()).isEqualTo(DEFAULT_CORE_DATA_TYPE_ID);
        assertThat(testCorePoint.getEventSeverity()).isEqualTo(DEFAULT_EVENT_SEVERITY);
        assertThat(testCorePoint.getStateRuleId()).isEqualTo(DEFAULT_STATE_RULE_ID);
        assertThat(testCorePoint.isReadable()).isEqualTo(DEFAULT_READABLE);
        assertThat(testCorePoint.isWritable()).isEqualTo(DEFAULT_WRITABLE);
        assertThat(testCorePoint.getDefaultValue()).isEqualTo(DEFAULT_DEFAULT_VALUE);
        assertThat(testCorePoint.getStep()).isEqualTo(DEFAULT_STEP);
        assertThat(testCorePoint.isMasked()).isEqualTo(DEFAULT_MASKED);
    }

    @Test
    @Transactional
    public void createCorePointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = corePointRepository.findAll().size();

        // Create the CorePoint with an existing ID
        corePoint.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCorePointMockMvc.perform(post("/api/core-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(corePoint)))
            .andExpect(status().isBadRequest());

        // Validate the CorePoint in the database
        List<CorePoint> corePointList = corePointRepository.findAll();
        assertThat(corePointList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCorePoints() throws Exception {
        // Initialize the database
        corePointRepository.saveAndFlush(corePoint);

        // Get all the corePointList
        restCorePointMockMvc.perform(get("/api/core-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(corePoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].corePointId").value(hasItem(DEFAULT_CORE_POINT_ID)))
            .andExpect(jsonPath("$.[*].pointName").value(hasItem(DEFAULT_POINT_NAME.toString())))
            .andExpect(jsonPath("$.[*].accuracy").value(hasItem(DEFAULT_ACCURACY.toString())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.toString())))
            .andExpect(jsonPath("$.[*].max").value(hasItem(DEFAULT_MAX.toString())))
            .andExpect(jsonPath("$.[*].min").value(hasItem(DEFAULT_MIN.toString())))
            .andExpect(jsonPath("$.[*].coreSourceId").value(hasItem(DEFAULT_CORE_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].coreDataTypeId").value(hasItem(DEFAULT_CORE_DATA_TYPE_ID)))
            .andExpect(jsonPath("$.[*].eventSeverity").value(hasItem(DEFAULT_EVENT_SEVERITY)))
            .andExpect(jsonPath("$.[*].stateRuleId").value(hasItem(DEFAULT_STATE_RULE_ID)))
            .andExpect(jsonPath("$.[*].readable").value(hasItem(DEFAULT_READABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].writable").value(hasItem(DEFAULT_WRITABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].defaultValue").value(hasItem(DEFAULT_DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].step").value(hasItem(DEFAULT_STEP.doubleValue())))
            .andExpect(jsonPath("$.[*].masked").value(hasItem(DEFAULT_MASKED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCorePoint() throws Exception {
        // Initialize the database
        corePointRepository.saveAndFlush(corePoint);

        // Get the corePoint
        restCorePointMockMvc.perform(get("/api/core-points/{id}", corePoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(corePoint.getId().intValue()))
            .andExpect(jsonPath("$.corePointId").value(DEFAULT_CORE_POINT_ID))
            .andExpect(jsonPath("$.pointName").value(DEFAULT_POINT_NAME.toString()))
            .andExpect(jsonPath("$.accuracy").value(DEFAULT_ACCURACY.toString()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT.toString()))
            .andExpect(jsonPath("$.max").value(DEFAULT_MAX.toString()))
            .andExpect(jsonPath("$.min").value(DEFAULT_MIN.toString()))
            .andExpect(jsonPath("$.coreSourceId").value(DEFAULT_CORE_SOURCE_ID))
            .andExpect(jsonPath("$.coreDataTypeId").value(DEFAULT_CORE_DATA_TYPE_ID))
            .andExpect(jsonPath("$.eventSeverity").value(DEFAULT_EVENT_SEVERITY))
            .andExpect(jsonPath("$.stateRuleId").value(DEFAULT_STATE_RULE_ID))
            .andExpect(jsonPath("$.readable").value(DEFAULT_READABLE.booleanValue()))
            .andExpect(jsonPath("$.writable").value(DEFAULT_WRITABLE.booleanValue()))
            .andExpect(jsonPath("$.defaultValue").value(DEFAULT_DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.step").value(DEFAULT_STEP.doubleValue()))
            .andExpect(jsonPath("$.masked").value(DEFAULT_MASKED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCorePoint() throws Exception {
        // Get the corePoint
        restCorePointMockMvc.perform(get("/api/core-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCorePoint() throws Exception {
        // Initialize the database
        corePointService.save(corePoint);

        int databaseSizeBeforeUpdate = corePointRepository.findAll().size();

        // Update the corePoint
        CorePoint updatedCorePoint = corePointRepository.findById(corePoint.getId()).get();
        // Disconnect from session so that the updates on updatedCorePoint are not directly saved in db
        em.detach(updatedCorePoint);
        updatedCorePoint
            .corePointId(UPDATED_CORE_POINT_ID)
            .pointName(UPDATED_POINT_NAME)
            .accuracy(UPDATED_ACCURACY)
            .unit(UPDATED_UNIT)
            .max(UPDATED_MAX)
            .min(UPDATED_MIN)
            .coreSourceId(UPDATED_CORE_SOURCE_ID)
            .coreDataTypeId(UPDATED_CORE_DATA_TYPE_ID)
            .eventSeverity(UPDATED_EVENT_SEVERITY)
            .stateRuleId(UPDATED_STATE_RULE_ID)
            .readable(UPDATED_READABLE)
            .writable(UPDATED_WRITABLE)
            .defaultValue(UPDATED_DEFAULT_VALUE)
            .step(UPDATED_STEP)
            .masked(UPDATED_MASKED);

        restCorePointMockMvc.perform(put("/api/core-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCorePoint)))
            .andExpect(status().isOk());

        // Validate the CorePoint in the database
        List<CorePoint> corePointList = corePointRepository.findAll();
        assertThat(corePointList).hasSize(databaseSizeBeforeUpdate);
        CorePoint testCorePoint = corePointList.get(corePointList.size() - 1);
        assertThat(testCorePoint.getCorePointId()).isEqualTo(UPDATED_CORE_POINT_ID);
        assertThat(testCorePoint.getPointName()).isEqualTo(UPDATED_POINT_NAME);
        assertThat(testCorePoint.getAccuracy()).isEqualTo(UPDATED_ACCURACY);
        assertThat(testCorePoint.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testCorePoint.getMax()).isEqualTo(UPDATED_MAX);
        assertThat(testCorePoint.getMin()).isEqualTo(UPDATED_MIN);
        assertThat(testCorePoint.getCoreSourceId()).isEqualTo(UPDATED_CORE_SOURCE_ID);
        assertThat(testCorePoint.getCoreDataTypeId()).isEqualTo(UPDATED_CORE_DATA_TYPE_ID);
        assertThat(testCorePoint.getEventSeverity()).isEqualTo(UPDATED_EVENT_SEVERITY);
        assertThat(testCorePoint.getStateRuleId()).isEqualTo(UPDATED_STATE_RULE_ID);
        assertThat(testCorePoint.isReadable()).isEqualTo(UPDATED_READABLE);
        assertThat(testCorePoint.isWritable()).isEqualTo(UPDATED_WRITABLE);
        assertThat(testCorePoint.getDefaultValue()).isEqualTo(UPDATED_DEFAULT_VALUE);
        assertThat(testCorePoint.getStep()).isEqualTo(UPDATED_STEP);
        assertThat(testCorePoint.isMasked()).isEqualTo(UPDATED_MASKED);
    }

    @Test
    @Transactional
    public void updateNonExistingCorePoint() throws Exception {
        int databaseSizeBeforeUpdate = corePointRepository.findAll().size();

        // Create the CorePoint

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCorePointMockMvc.perform(put("/api/core-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(corePoint)))
            .andExpect(status().isBadRequest());

        // Validate the CorePoint in the database
        List<CorePoint> corePointList = corePointRepository.findAll();
        assertThat(corePointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCorePoint() throws Exception {
        // Initialize the database
        corePointService.save(corePoint);

        int databaseSizeBeforeDelete = corePointRepository.findAll().size();

        // Get the corePoint
        restCorePointMockMvc.perform(delete("/api/core-points/{id}", corePoint.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CorePoint> corePointList = corePointRepository.findAll();
        assertThat(corePointList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CorePoint.class);
        CorePoint corePoint1 = new CorePoint();
        corePoint1.setId(1L);
        CorePoint corePoint2 = new CorePoint();
        corePoint2.setId(corePoint1.getId());
        assertThat(corePoint1).isEqualTo(corePoint2);
        corePoint2.setId(2L);
        assertThat(corePoint1).isNotEqualTo(corePoint2);
        corePoint1.setId(null);
        assertThat(corePoint1).isNotEqualTo(corePoint2);
    }
}
