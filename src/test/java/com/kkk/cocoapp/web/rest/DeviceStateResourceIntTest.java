package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.DeviceState;
import com.kkk.cocoapp.repository.DeviceStateRepository;
import com.kkk.cocoapp.service.DeviceStateService;
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
 * Test class for the DeviceStateResource REST controller.
 *
 * @see DeviceStateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class DeviceStateResourceIntTest {

    private static final Integer DEFAULT_STATE_ID = 1;
    private static final Integer UPDATED_STATE_ID = 2;

    private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private DeviceStateRepository deviceStateRepository;

    @Autowired
    private DeviceStateService deviceStateService;

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

    private MockMvc restDeviceStateMockMvc;

    private DeviceState deviceState;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeviceStateResource deviceStateResource = new DeviceStateResource(deviceStateService);
        this.restDeviceStateMockMvc = MockMvcBuilders.standaloneSetup(deviceStateResource)
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
    public static DeviceState createEntity(EntityManager em) {
        DeviceState deviceState = new DeviceState()
            .stateId(DEFAULT_STATE_ID)
            .stateName(DEFAULT_STATE_NAME)
            .remark(DEFAULT_REMARK);
        return deviceState;
    }

    @Before
    public void initTest() {
        deviceState = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeviceState() throws Exception {
        int databaseSizeBeforeCreate = deviceStateRepository.findAll().size();

        // Create the DeviceState
        restDeviceStateMockMvc.perform(post("/api/device-states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceState)))
            .andExpect(status().isCreated());

        // Validate the DeviceState in the database
        List<DeviceState> deviceStateList = deviceStateRepository.findAll();
        assertThat(deviceStateList).hasSize(databaseSizeBeforeCreate + 1);
        DeviceState testDeviceState = deviceStateList.get(deviceStateList.size() - 1);
        assertThat(testDeviceState.getStateId()).isEqualTo(DEFAULT_STATE_ID);
        assertThat(testDeviceState.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
        assertThat(testDeviceState.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createDeviceStateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deviceStateRepository.findAll().size();

        // Create the DeviceState with an existing ID
        deviceState.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeviceStateMockMvc.perform(post("/api/device-states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceState)))
            .andExpect(status().isBadRequest());

        // Validate the DeviceState in the database
        List<DeviceState> deviceStateList = deviceStateRepository.findAll();
        assertThat(deviceStateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDeviceStates() throws Exception {
        // Initialize the database
        deviceStateRepository.saveAndFlush(deviceState);

        // Get all the deviceStateList
        restDeviceStateMockMvc.perform(get("/api/device-states?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deviceState.getId().intValue())))
            .andExpect(jsonPath("$.[*].stateId").value(hasItem(DEFAULT_STATE_ID)))
            .andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }
    
    @Test
    @Transactional
    public void getDeviceState() throws Exception {
        // Initialize the database
        deviceStateRepository.saveAndFlush(deviceState);

        // Get the deviceState
        restDeviceStateMockMvc.perform(get("/api/device-states/{id}", deviceState.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(deviceState.getId().intValue()))
            .andExpect(jsonPath("$.stateId").value(DEFAULT_STATE_ID))
            .andExpect(jsonPath("$.stateName").value(DEFAULT_STATE_NAME.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDeviceState() throws Exception {
        // Get the deviceState
        restDeviceStateMockMvc.perform(get("/api/device-states/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeviceState() throws Exception {
        // Initialize the database
        deviceStateService.save(deviceState);

        int databaseSizeBeforeUpdate = deviceStateRepository.findAll().size();

        // Update the deviceState
        DeviceState updatedDeviceState = deviceStateRepository.findById(deviceState.getId()).get();
        // Disconnect from session so that the updates on updatedDeviceState are not directly saved in db
        em.detach(updatedDeviceState);
        updatedDeviceState
            .stateId(UPDATED_STATE_ID)
            .stateName(UPDATED_STATE_NAME)
            .remark(UPDATED_REMARK);

        restDeviceStateMockMvc.perform(put("/api/device-states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDeviceState)))
            .andExpect(status().isOk());

        // Validate the DeviceState in the database
        List<DeviceState> deviceStateList = deviceStateRepository.findAll();
        assertThat(deviceStateList).hasSize(databaseSizeBeforeUpdate);
        DeviceState testDeviceState = deviceStateList.get(deviceStateList.size() - 1);
        assertThat(testDeviceState.getStateId()).isEqualTo(UPDATED_STATE_ID);
        assertThat(testDeviceState.getStateName()).isEqualTo(UPDATED_STATE_NAME);
        assertThat(testDeviceState.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingDeviceState() throws Exception {
        int databaseSizeBeforeUpdate = deviceStateRepository.findAll().size();

        // Create the DeviceState

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeviceStateMockMvc.perform(put("/api/device-states")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceState)))
            .andExpect(status().isBadRequest());

        // Validate the DeviceState in the database
        List<DeviceState> deviceStateList = deviceStateRepository.findAll();
        assertThat(deviceStateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeviceState() throws Exception {
        // Initialize the database
        deviceStateService.save(deviceState);

        int databaseSizeBeforeDelete = deviceStateRepository.findAll().size();

        // Get the deviceState
        restDeviceStateMockMvc.perform(delete("/api/device-states/{id}", deviceState.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DeviceState> deviceStateList = deviceStateRepository.findAll();
        assertThat(deviceStateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeviceState.class);
        DeviceState deviceState1 = new DeviceState();
        deviceState1.setId(1L);
        DeviceState deviceState2 = new DeviceState();
        deviceState2.setId(deviceState1.getId());
        assertThat(deviceState1).isEqualTo(deviceState2);
        deviceState2.setId(2L);
        assertThat(deviceState1).isNotEqualTo(deviceState2);
        deviceState1.setId(null);
        assertThat(deviceState1).isNotEqualTo(deviceState2);
    }
}
