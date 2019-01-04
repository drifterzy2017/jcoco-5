package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.CoreEventSeverity;
import com.kkk.cocoapp.repository.CoreEventSeverityRepository;
import com.kkk.cocoapp.service.CoreEventSeverityService;
import com.kkk.cocoapp.web.rest.errors.ExceptionTranslator;

//import com.kkk.cocoapp.web.rest.homepage.CoreEventSeverityResource;
import com.kkk.cocoapp.web.rest.homepage.CoreEventSeverityResource;
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
 * Test class for the CoreEventSeverityResource REST controller.
 *
 * @see CoreEventSeverityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class CoreEventSeverityResourceIntTest {

    private static final Integer DEFAULT_EVENT_SEVERITY_ID = 1;
    private static final Integer UPDATED_EVENT_SEVERITY_ID = 2;

    private static final String DEFAULT_SEVERITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SEVERITY_NAME = "BBBBBBBBBB";

    @Autowired
    private CoreEventSeverityRepository coreEventSeverityRepository;

    @Autowired
    private CoreEventSeverityService coreEventSeverityService;

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

    private MockMvc restCoreEventSeverityMockMvc;

    private CoreEventSeverity coreEventSeverity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CoreEventSeverityResource coreEventSeverityResource = new CoreEventSeverityResource(coreEventSeverityService);
        this.restCoreEventSeverityMockMvc = MockMvcBuilders.standaloneSetup(coreEventSeverityResource)
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
    public static CoreEventSeverity createEntity(EntityManager em) {
        CoreEventSeverity coreEventSeverity = new CoreEventSeverity()
            .eventSeverityId(DEFAULT_EVENT_SEVERITY_ID)
            .severityName(DEFAULT_SEVERITY_NAME);
        return coreEventSeverity;
    }

    @Before
    public void initTest() {
        coreEventSeverity = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoreEventSeverity() throws Exception {
        int databaseSizeBeforeCreate = coreEventSeverityRepository.findAll().size();

        // Create the CoreEventSeverity
        restCoreEventSeverityMockMvc.perform(post("/api/core-event-severities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coreEventSeverity)))
            .andExpect(status().isCreated());

        // Validate the CoreEventSeverity in the database
        List<CoreEventSeverity> coreEventSeverityList = coreEventSeverityRepository.findAll();
        assertThat(coreEventSeverityList).hasSize(databaseSizeBeforeCreate + 1);
        CoreEventSeverity testCoreEventSeverity = coreEventSeverityList.get(coreEventSeverityList.size() - 1);
        assertThat(testCoreEventSeverity.getEventSeverityId()).isEqualTo(DEFAULT_EVENT_SEVERITY_ID);
        assertThat(testCoreEventSeverity.getSeverityName()).isEqualTo(DEFAULT_SEVERITY_NAME);
    }

    @Test
    @Transactional
    public void createCoreEventSeverityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coreEventSeverityRepository.findAll().size();

        // Create the CoreEventSeverity with an existing ID
        coreEventSeverity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoreEventSeverityMockMvc.perform(post("/api/core-event-severities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coreEventSeverity)))
            .andExpect(status().isBadRequest());

        // Validate the CoreEventSeverity in the database
        List<CoreEventSeverity> coreEventSeverityList = coreEventSeverityRepository.findAll();
        assertThat(coreEventSeverityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCoreEventSeverities() throws Exception {
        // Initialize the database
        coreEventSeverityRepository.saveAndFlush(coreEventSeverity);

        // Get all the coreEventSeverityList
        restCoreEventSeverityMockMvc.perform(get("/api/core-event-severities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coreEventSeverity.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventSeverityId").value(hasItem(DEFAULT_EVENT_SEVERITY_ID)))
            .andExpect(jsonPath("$.[*].severityName").value(hasItem(DEFAULT_SEVERITY_NAME.toString())));
    }

    @Test
    @Transactional
    public void getCoreEventSeverity() throws Exception {
        // Initialize the database
        coreEventSeverityRepository.saveAndFlush(coreEventSeverity);

        // Get the coreEventSeverity
        restCoreEventSeverityMockMvc.perform(get("/api/core-event-severities/{id}", coreEventSeverity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coreEventSeverity.getId().intValue()))
            .andExpect(jsonPath("$.eventSeverityId").value(DEFAULT_EVENT_SEVERITY_ID))
            .andExpect(jsonPath("$.severityName").value(DEFAULT_SEVERITY_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCoreEventSeverity() throws Exception {
        // Get the coreEventSeverity
        restCoreEventSeverityMockMvc.perform(get("/api/core-event-severities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoreEventSeverity() throws Exception {
        // Initialize the database
        coreEventSeverityService.save(coreEventSeverity);

        int databaseSizeBeforeUpdate = coreEventSeverityRepository.findAll().size();

        // Update the coreEventSeverity
        CoreEventSeverity updatedCoreEventSeverity = coreEventSeverityRepository.findById(coreEventSeverity.getId()).get();
        // Disconnect from session so that the updates on updatedCoreEventSeverity are not directly saved in db
        em.detach(updatedCoreEventSeverity);
        updatedCoreEventSeverity
            .eventSeverityId(UPDATED_EVENT_SEVERITY_ID)
            .severityName(UPDATED_SEVERITY_NAME);

        restCoreEventSeverityMockMvc.perform(put("/api/core-event-severities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCoreEventSeverity)))
            .andExpect(status().isOk());

        // Validate the CoreEventSeverity in the database
        List<CoreEventSeverity> coreEventSeverityList = coreEventSeverityRepository.findAll();
        assertThat(coreEventSeverityList).hasSize(databaseSizeBeforeUpdate);
        CoreEventSeverity testCoreEventSeverity = coreEventSeverityList.get(coreEventSeverityList.size() - 1);
        assertThat(testCoreEventSeverity.getEventSeverityId()).isEqualTo(UPDATED_EVENT_SEVERITY_ID);
        assertThat(testCoreEventSeverity.getSeverityName()).isEqualTo(UPDATED_SEVERITY_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCoreEventSeverity() throws Exception {
        int databaseSizeBeforeUpdate = coreEventSeverityRepository.findAll().size();

        // Create the CoreEventSeverity

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoreEventSeverityMockMvc.perform(put("/api/core-event-severities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coreEventSeverity)))
            .andExpect(status().isBadRequest());

        // Validate the CoreEventSeverity in the database
        List<CoreEventSeverity> coreEventSeverityList = coreEventSeverityRepository.findAll();
        assertThat(coreEventSeverityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoreEventSeverity() throws Exception {
        // Initialize the database
        coreEventSeverityService.save(coreEventSeverity);

        int databaseSizeBeforeDelete = coreEventSeverityRepository.findAll().size();

        // Get the coreEventSeverity
        restCoreEventSeverityMockMvc.perform(delete("/api/core-event-severities/{id}", coreEventSeverity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CoreEventSeverity> coreEventSeverityList = coreEventSeverityRepository.findAll();
        assertThat(coreEventSeverityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoreEventSeverity.class);
        CoreEventSeverity coreEventSeverity1 = new CoreEventSeverity();
        coreEventSeverity1.setId(1L);
        CoreEventSeverity coreEventSeverity2 = new CoreEventSeverity();
        coreEventSeverity2.setId(coreEventSeverity1.getId());
        assertThat(coreEventSeverity1).isEqualTo(coreEventSeverity2);
        coreEventSeverity2.setId(2L);
        assertThat(coreEventSeverity1).isNotEqualTo(coreEventSeverity2);
        coreEventSeverity1.setId(null);
        assertThat(coreEventSeverity1).isNotEqualTo(coreEventSeverity2);
    }
}
