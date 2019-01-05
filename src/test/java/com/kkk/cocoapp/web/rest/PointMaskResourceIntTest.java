package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.PointMask;
import com.kkk.cocoapp.repository.PointMaskRepository;
import com.kkk.cocoapp.service.PointMaskService;
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
 * Test class for the PointMaskResource REST controller.
 *
 * @see PointMaskResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class PointMaskResourceIntTest {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CORE_POINT_ID = 1;
    private static final Integer UPDATED_CORE_POINT_ID = 2;

    private static final Integer DEFAULT_DEVICE_ID = 1;
    private static final Integer UPDATED_DEVICE_ID = 2;

    private static final Instant DEFAULT_OPERATION_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OPERATION_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PointMaskRepository pointMaskRepository;

    @Autowired
    private PointMaskService pointMaskService;

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

    private MockMvc restPointMaskMockMvc;

    private PointMask pointMask;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PointMaskResource pointMaskResource = new PointMaskResource(pointMaskService);
        this.restPointMaskMockMvc = MockMvcBuilders.standaloneSetup(pointMaskResource)
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
    public static PointMask createEntity(EntityManager em) {
        PointMask pointMask = new PointMask()
            .userId(DEFAULT_USER_ID)
            .userName(DEFAULT_USER_NAME)
            .comment(DEFAULT_COMMENT)
            .corePointId(DEFAULT_CORE_POINT_ID)
            .deviceId(DEFAULT_DEVICE_ID)
            .operationTime(DEFAULT_OPERATION_TIME);
        return pointMask;
    }

    @Before
    public void initTest() {
        pointMask = createEntity(em);
    }

    @Test
    @Transactional
    public void createPointMask() throws Exception {
        int databaseSizeBeforeCreate = pointMaskRepository.findAll().size();

        // Create the PointMask
        restPointMaskMockMvc.perform(post("/api/point-masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pointMask)))
            .andExpect(status().isCreated());

        // Validate the PointMask in the database
        List<PointMask> pointMaskList = pointMaskRepository.findAll();
        assertThat(pointMaskList).hasSize(databaseSizeBeforeCreate + 1);
        PointMask testPointMask = pointMaskList.get(pointMaskList.size() - 1);
        assertThat(testPointMask.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testPointMask.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testPointMask.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testPointMask.getCorePointId()).isEqualTo(DEFAULT_CORE_POINT_ID);
        assertThat(testPointMask.getDeviceId()).isEqualTo(DEFAULT_DEVICE_ID);
        assertThat(testPointMask.getOperationTime()).isEqualTo(DEFAULT_OPERATION_TIME);
    }

    @Test
    @Transactional
    public void createPointMaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pointMaskRepository.findAll().size();

        // Create the PointMask with an existing ID
        pointMask.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPointMaskMockMvc.perform(post("/api/point-masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pointMask)))
            .andExpect(status().isBadRequest());

        // Validate the PointMask in the database
        List<PointMask> pointMaskList = pointMaskRepository.findAll();
        assertThat(pointMaskList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPointMasks() throws Exception {
        // Initialize the database
        pointMaskRepository.saveAndFlush(pointMask);

        // Get all the pointMaskList
        restPointMaskMockMvc.perform(get("/api/point-masks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pointMask.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].corePointId").value(hasItem(DEFAULT_CORE_POINT_ID)))
            .andExpect(jsonPath("$.[*].deviceId").value(hasItem(DEFAULT_DEVICE_ID)))
            .andExpect(jsonPath("$.[*].operationTime").value(hasItem(DEFAULT_OPERATION_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getPointMask() throws Exception {
        // Initialize the database
        pointMaskRepository.saveAndFlush(pointMask);

        // Get the pointMask
        restPointMaskMockMvc.perform(get("/api/point-masks/{id}", pointMask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pointMask.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.corePointId").value(DEFAULT_CORE_POINT_ID))
            .andExpect(jsonPath("$.deviceId").value(DEFAULT_DEVICE_ID))
            .andExpect(jsonPath("$.operationTime").value(DEFAULT_OPERATION_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPointMask() throws Exception {
        // Get the pointMask
        restPointMaskMockMvc.perform(get("/api/point-masks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePointMask() throws Exception {
        // Initialize the database
        pointMaskService.save(pointMask);

        int databaseSizeBeforeUpdate = pointMaskRepository.findAll().size();

        // Update the pointMask
        PointMask updatedPointMask = pointMaskRepository.findById(pointMask.getId()).get();
        // Disconnect from session so that the updates on updatedPointMask are not directly saved in db
        em.detach(updatedPointMask);
        updatedPointMask
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .comment(UPDATED_COMMENT)
            .corePointId(UPDATED_CORE_POINT_ID)
            .deviceId(UPDATED_DEVICE_ID)
            .operationTime(UPDATED_OPERATION_TIME);

        restPointMaskMockMvc.perform(put("/api/point-masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPointMask)))
            .andExpect(status().isOk());

        // Validate the PointMask in the database
        List<PointMask> pointMaskList = pointMaskRepository.findAll();
        assertThat(pointMaskList).hasSize(databaseSizeBeforeUpdate);
        PointMask testPointMask = pointMaskList.get(pointMaskList.size() - 1);
        assertThat(testPointMask.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPointMask.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testPointMask.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testPointMask.getCorePointId()).isEqualTo(UPDATED_CORE_POINT_ID);
        assertThat(testPointMask.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
        assertThat(testPointMask.getOperationTime()).isEqualTo(UPDATED_OPERATION_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingPointMask() throws Exception {
        int databaseSizeBeforeUpdate = pointMaskRepository.findAll().size();

        // Create the PointMask

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPointMaskMockMvc.perform(put("/api/point-masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pointMask)))
            .andExpect(status().isBadRequest());

        // Validate the PointMask in the database
        List<PointMask> pointMaskList = pointMaskRepository.findAll();
        assertThat(pointMaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePointMask() throws Exception {
        // Initialize the database
        pointMaskService.save(pointMask);

        int databaseSizeBeforeDelete = pointMaskRepository.findAll().size();

        // Get the pointMask
        restPointMaskMockMvc.perform(delete("/api/point-masks/{id}", pointMask.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PointMask> pointMaskList = pointMaskRepository.findAll();
        assertThat(pointMaskList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PointMask.class);
        PointMask pointMask1 = new PointMask();
        pointMask1.setId(1L);
        PointMask pointMask2 = new PointMask();
        pointMask2.setId(pointMask1.getId());
        assertThat(pointMask1).isEqualTo(pointMask2);
        pointMask2.setId(2L);
        assertThat(pointMask1).isNotEqualTo(pointMask2);
        pointMask1.setId(null);
        assertThat(pointMask1).isNotEqualTo(pointMask2);
    }
}
