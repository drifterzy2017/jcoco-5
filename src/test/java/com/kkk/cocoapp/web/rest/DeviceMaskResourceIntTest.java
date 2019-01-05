package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.DeviceMask;
import com.kkk.cocoapp.repository.DeviceMaskRepository;
import com.kkk.cocoapp.service.DeviceMaskService;
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
 * Test class for the DeviceMaskResource REST controller.
 *
 * @see DeviceMaskResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class DeviceMaskResourceIntTest {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_DEVICE_ID = 1;
    private static final Integer UPDATED_DEVICE_ID = 2;

    private static final Instant DEFAULT_OPERATION_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OPERATION_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DeviceMaskRepository deviceMaskRepository;

    @Autowired
    private DeviceMaskService deviceMaskService;

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

    private MockMvc restDeviceMaskMockMvc;

    private DeviceMask deviceMask;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeviceMaskResource deviceMaskResource = new DeviceMaskResource(deviceMaskService);
        this.restDeviceMaskMockMvc = MockMvcBuilders.standaloneSetup(deviceMaskResource)
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
    public static DeviceMask createEntity(EntityManager em) {
        DeviceMask deviceMask = new DeviceMask()
            .userId(DEFAULT_USER_ID)
            .userName(DEFAULT_USER_NAME)
            .comment(DEFAULT_COMMENT)
            .deviceId(DEFAULT_DEVICE_ID)
            .operationTime(DEFAULT_OPERATION_TIME);
        return deviceMask;
    }

    @Before
    public void initTest() {
        deviceMask = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeviceMask() throws Exception {
        int databaseSizeBeforeCreate = deviceMaskRepository.findAll().size();

        // Create the DeviceMask
        restDeviceMaskMockMvc.perform(post("/api/device-masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceMask)))
            .andExpect(status().isCreated());

        // Validate the DeviceMask in the database
        List<DeviceMask> deviceMaskList = deviceMaskRepository.findAll();
        assertThat(deviceMaskList).hasSize(databaseSizeBeforeCreate + 1);
        DeviceMask testDeviceMask = deviceMaskList.get(deviceMaskList.size() - 1);
        assertThat(testDeviceMask.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testDeviceMask.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testDeviceMask.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testDeviceMask.getDeviceId()).isEqualTo(DEFAULT_DEVICE_ID);
        assertThat(testDeviceMask.getOperationTime()).isEqualTo(DEFAULT_OPERATION_TIME);
    }

    @Test
    @Transactional
    public void createDeviceMaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deviceMaskRepository.findAll().size();

        // Create the DeviceMask with an existing ID
        deviceMask.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeviceMaskMockMvc.perform(post("/api/device-masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceMask)))
            .andExpect(status().isBadRequest());

        // Validate the DeviceMask in the database
        List<DeviceMask> deviceMaskList = deviceMaskRepository.findAll();
        assertThat(deviceMaskList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDeviceMasks() throws Exception {
        // Initialize the database
        deviceMaskRepository.saveAndFlush(deviceMask);

        // Get all the deviceMaskList
        restDeviceMaskMockMvc.perform(get("/api/device-masks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deviceMask.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].deviceId").value(hasItem(DEFAULT_DEVICE_ID)))
            .andExpect(jsonPath("$.[*].operationTime").value(hasItem(DEFAULT_OPERATION_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getDeviceMask() throws Exception {
        // Initialize the database
        deviceMaskRepository.saveAndFlush(deviceMask);

        // Get the deviceMask
        restDeviceMaskMockMvc.perform(get("/api/device-masks/{id}", deviceMask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(deviceMask.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.deviceId").value(DEFAULT_DEVICE_ID))
            .andExpect(jsonPath("$.operationTime").value(DEFAULT_OPERATION_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDeviceMask() throws Exception {
        // Get the deviceMask
        restDeviceMaskMockMvc.perform(get("/api/device-masks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeviceMask() throws Exception {
        // Initialize the database
        deviceMaskService.save(deviceMask);

        int databaseSizeBeforeUpdate = deviceMaskRepository.findAll().size();

        // Update the deviceMask
        DeviceMask updatedDeviceMask = deviceMaskRepository.findById(deviceMask.getId()).get();
        // Disconnect from session so that the updates on updatedDeviceMask are not directly saved in db
        em.detach(updatedDeviceMask);
        updatedDeviceMask
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .comment(UPDATED_COMMENT)
            .deviceId(UPDATED_DEVICE_ID)
            .operationTime(UPDATED_OPERATION_TIME);

        restDeviceMaskMockMvc.perform(put("/api/device-masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDeviceMask)))
            .andExpect(status().isOk());

        // Validate the DeviceMask in the database
        List<DeviceMask> deviceMaskList = deviceMaskRepository.findAll();
        assertThat(deviceMaskList).hasSize(databaseSizeBeforeUpdate);
        DeviceMask testDeviceMask = deviceMaskList.get(deviceMaskList.size() - 1);
        assertThat(testDeviceMask.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testDeviceMask.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testDeviceMask.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testDeviceMask.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
        assertThat(testDeviceMask.getOperationTime()).isEqualTo(UPDATED_OPERATION_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingDeviceMask() throws Exception {
        int databaseSizeBeforeUpdate = deviceMaskRepository.findAll().size();

        // Create the DeviceMask

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeviceMaskMockMvc.perform(put("/api/device-masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceMask)))
            .andExpect(status().isBadRequest());

        // Validate the DeviceMask in the database
        List<DeviceMask> deviceMaskList = deviceMaskRepository.findAll();
        assertThat(deviceMaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeviceMask() throws Exception {
        // Initialize the database
        deviceMaskService.save(deviceMask);

        int databaseSizeBeforeDelete = deviceMaskRepository.findAll().size();

        // Get the deviceMask
        restDeviceMaskMockMvc.perform(delete("/api/device-masks/{id}", deviceMask.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DeviceMask> deviceMaskList = deviceMaskRepository.findAll();
        assertThat(deviceMaskList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeviceMask.class);
        DeviceMask deviceMask1 = new DeviceMask();
        deviceMask1.setId(1L);
        DeviceMask deviceMask2 = new DeviceMask();
        deviceMask2.setId(deviceMask1.getId());
        assertThat(deviceMask1).isEqualTo(deviceMask2);
        deviceMask2.setId(2L);
        assertThat(deviceMask1).isNotEqualTo(deviceMask2);
        deviceMask1.setId(null);
        assertThat(deviceMask1).isNotEqualTo(deviceMask2);
    }
}
