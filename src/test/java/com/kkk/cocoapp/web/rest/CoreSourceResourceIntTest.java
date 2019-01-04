package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.CoreSource;
import com.kkk.cocoapp.repository.CoreSourceRepository;
import com.kkk.cocoapp.service.CoreSourceService;
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
 * Test class for the CoreSourceResource REST controller.
 *
 * @see CoreSourceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class CoreSourceResourceIntTest {

    private static final Integer DEFAULT_CORE_SOURCE_ID = 1;
    private static final Integer UPDATED_CORE_SOURCE_ID = 2;

    private static final String DEFAULT_SOURCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENGINE_ID = 1;
    private static final Integer UPDATED_ENGINE_ID = 2;

    private static final Integer DEFAULT_MAPPER_ID = 1;
    private static final Integer UPDATED_MAPPER_ID = 2;

    private static final Integer DEFAULT_LINK_STATE = 1;
    private static final Integer UPDATED_LINK_STATE = 2;

    @Autowired
    private CoreSourceRepository coreSourceRepository;

    @Autowired
    private CoreSourceService coreSourceService;

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

    private MockMvc restCoreSourceMockMvc;

    private CoreSource coreSource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CoreSourceResource coreSourceResource = new CoreSourceResource(coreSourceService);
        this.restCoreSourceMockMvc = MockMvcBuilders.standaloneSetup(coreSourceResource)
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
    public static CoreSource createEntity(EntityManager em) {
        CoreSource coreSource = new CoreSource()
            .coreSourceId(DEFAULT_CORE_SOURCE_ID)
            .sourceName(DEFAULT_SOURCE_NAME)
            .engineId(DEFAULT_ENGINE_ID)
            .mapperId(DEFAULT_MAPPER_ID)
            .linkState(DEFAULT_LINK_STATE);
        return coreSource;
    }

    @Before
    public void initTest() {
        coreSource = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoreSource() throws Exception {
        int databaseSizeBeforeCreate = coreSourceRepository.findAll().size();

        // Create the CoreSource
        restCoreSourceMockMvc.perform(post("/api/core-sources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coreSource)))
            .andExpect(status().isCreated());

        // Validate the CoreSource in the database
        List<CoreSource> coreSourceList = coreSourceRepository.findAll();
        assertThat(coreSourceList).hasSize(databaseSizeBeforeCreate + 1);
        CoreSource testCoreSource = coreSourceList.get(coreSourceList.size() - 1);
        assertThat(testCoreSource.getCoreSourceId()).isEqualTo(DEFAULT_CORE_SOURCE_ID);
        assertThat(testCoreSource.getSourceName()).isEqualTo(DEFAULT_SOURCE_NAME);
        assertThat(testCoreSource.getEngineId()).isEqualTo(DEFAULT_ENGINE_ID);
        assertThat(testCoreSource.getMapperId()).isEqualTo(DEFAULT_MAPPER_ID);
        assertThat(testCoreSource.getLinkState()).isEqualTo(DEFAULT_LINK_STATE);
    }

    @Test
    @Transactional
    public void createCoreSourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coreSourceRepository.findAll().size();

        // Create the CoreSource with an existing ID
        coreSource.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoreSourceMockMvc.perform(post("/api/core-sources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coreSource)))
            .andExpect(status().isBadRequest());

        // Validate the CoreSource in the database
        List<CoreSource> coreSourceList = coreSourceRepository.findAll();
        assertThat(coreSourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCoreSources() throws Exception {
        // Initialize the database
        coreSourceRepository.saveAndFlush(coreSource);

        // Get all the coreSourceList
        restCoreSourceMockMvc.perform(get("/api/core-sources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coreSource.getId().intValue())))
            .andExpect(jsonPath("$.[*].coreSourceId").value(hasItem(DEFAULT_CORE_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].sourceName").value(hasItem(DEFAULT_SOURCE_NAME.toString())))
            .andExpect(jsonPath("$.[*].engineId").value(hasItem(DEFAULT_ENGINE_ID)))
            .andExpect(jsonPath("$.[*].mapperId").value(hasItem(DEFAULT_MAPPER_ID)))
            .andExpect(jsonPath("$.[*].linkState").value(hasItem(DEFAULT_LINK_STATE)));
    }
    
    @Test
    @Transactional
    public void getCoreSource() throws Exception {
        // Initialize the database
        coreSourceRepository.saveAndFlush(coreSource);

        // Get the coreSource
        restCoreSourceMockMvc.perform(get("/api/core-sources/{id}", coreSource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coreSource.getId().intValue()))
            .andExpect(jsonPath("$.coreSourceId").value(DEFAULT_CORE_SOURCE_ID))
            .andExpect(jsonPath("$.sourceName").value(DEFAULT_SOURCE_NAME.toString()))
            .andExpect(jsonPath("$.engineId").value(DEFAULT_ENGINE_ID))
            .andExpect(jsonPath("$.mapperId").value(DEFAULT_MAPPER_ID))
            .andExpect(jsonPath("$.linkState").value(DEFAULT_LINK_STATE));
    }

    @Test
    @Transactional
    public void getNonExistingCoreSource() throws Exception {
        // Get the coreSource
        restCoreSourceMockMvc.perform(get("/api/core-sources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoreSource() throws Exception {
        // Initialize the database
        coreSourceService.save(coreSource);

        int databaseSizeBeforeUpdate = coreSourceRepository.findAll().size();

        // Update the coreSource
        CoreSource updatedCoreSource = coreSourceRepository.findById(coreSource.getId()).get();
        // Disconnect from session so that the updates on updatedCoreSource are not directly saved in db
        em.detach(updatedCoreSource);
        updatedCoreSource
            .coreSourceId(UPDATED_CORE_SOURCE_ID)
            .sourceName(UPDATED_SOURCE_NAME)
            .engineId(UPDATED_ENGINE_ID)
            .mapperId(UPDATED_MAPPER_ID)
            .linkState(UPDATED_LINK_STATE);

        restCoreSourceMockMvc.perform(put("/api/core-sources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCoreSource)))
            .andExpect(status().isOk());

        // Validate the CoreSource in the database
        List<CoreSource> coreSourceList = coreSourceRepository.findAll();
        assertThat(coreSourceList).hasSize(databaseSizeBeforeUpdate);
        CoreSource testCoreSource = coreSourceList.get(coreSourceList.size() - 1);
        assertThat(testCoreSource.getCoreSourceId()).isEqualTo(UPDATED_CORE_SOURCE_ID);
        assertThat(testCoreSource.getSourceName()).isEqualTo(UPDATED_SOURCE_NAME);
        assertThat(testCoreSource.getEngineId()).isEqualTo(UPDATED_ENGINE_ID);
        assertThat(testCoreSource.getMapperId()).isEqualTo(UPDATED_MAPPER_ID);
        assertThat(testCoreSource.getLinkState()).isEqualTo(UPDATED_LINK_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCoreSource() throws Exception {
        int databaseSizeBeforeUpdate = coreSourceRepository.findAll().size();

        // Create the CoreSource

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoreSourceMockMvc.perform(put("/api/core-sources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coreSource)))
            .andExpect(status().isBadRequest());

        // Validate the CoreSource in the database
        List<CoreSource> coreSourceList = coreSourceRepository.findAll();
        assertThat(coreSourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoreSource() throws Exception {
        // Initialize the database
        coreSourceService.save(coreSource);

        int databaseSizeBeforeDelete = coreSourceRepository.findAll().size();

        // Get the coreSource
        restCoreSourceMockMvc.perform(delete("/api/core-sources/{id}", coreSource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CoreSource> coreSourceList = coreSourceRepository.findAll();
        assertThat(coreSourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoreSource.class);
        CoreSource coreSource1 = new CoreSource();
        coreSource1.setId(1L);
        CoreSource coreSource2 = new CoreSource();
        coreSource2.setId(coreSource1.getId());
        assertThat(coreSource1).isEqualTo(coreSource2);
        coreSource2.setId(2L);
        assertThat(coreSource1).isNotEqualTo(coreSource2);
        coreSource1.setId(null);
        assertThat(coreSource1).isNotEqualTo(coreSource2);
    }
}
