package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.CorePointMeaning;
import com.kkk.cocoapp.repository.CorePointMeaningRepository;
import com.kkk.cocoapp.service.CorePointMeaningService;
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
 * Test class for the CorePointMeaningResource REST controller.
 *
 * @see CorePointMeaningResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class CorePointMeaningResourceIntTest {

    private static final Integer DEFAULT_CORE_SOURCE_ID = 1;
    private static final Integer UPDATED_CORE_SOURCE_ID = 2;

    private static final Integer DEFAULT_CORE_POINT_ID = 1;
    private static final Integer UPDATED_CORE_POINT_ID = 2;

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_MEANING = "AAAAAAAAAA";
    private static final String UPDATED_MEANING = "BBBBBBBBBB";

    @Autowired
    private CorePointMeaningRepository corePointMeaningRepository;

    @Autowired
    private CorePointMeaningService corePointMeaningService;

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

    private MockMvc restCorePointMeaningMockMvc;

    private CorePointMeaning corePointMeaning;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CorePointMeaningResource corePointMeaningResource = new CorePointMeaningResource(corePointMeaningService);
        this.restCorePointMeaningMockMvc = MockMvcBuilders.standaloneSetup(corePointMeaningResource)
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
    public static CorePointMeaning createEntity(EntityManager em) {
        CorePointMeaning corePointMeaning = new CorePointMeaning()
            .coreSourceId(DEFAULT_CORE_SOURCE_ID)
            .corePointId(DEFAULT_CORE_POINT_ID)
            .value(DEFAULT_VALUE)
            .meaning(DEFAULT_MEANING);
        return corePointMeaning;
    }

    @Before
    public void initTest() {
        corePointMeaning = createEntity(em);
    }

    @Test
    @Transactional
    public void createCorePointMeaning() throws Exception {
        int databaseSizeBeforeCreate = corePointMeaningRepository.findAll().size();

        // Create the CorePointMeaning
        restCorePointMeaningMockMvc.perform(post("/api/core-point-meanings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(corePointMeaning)))
            .andExpect(status().isCreated());

        // Validate the CorePointMeaning in the database
        List<CorePointMeaning> corePointMeaningList = corePointMeaningRepository.findAll();
        assertThat(corePointMeaningList).hasSize(databaseSizeBeforeCreate + 1);
        CorePointMeaning testCorePointMeaning = corePointMeaningList.get(corePointMeaningList.size() - 1);
        assertThat(testCorePointMeaning.getCoreSourceId()).isEqualTo(DEFAULT_CORE_SOURCE_ID);
        assertThat(testCorePointMeaning.getCorePointId()).isEqualTo(DEFAULT_CORE_POINT_ID);
        assertThat(testCorePointMeaning.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testCorePointMeaning.getMeaning()).isEqualTo(DEFAULT_MEANING);
    }

    @Test
    @Transactional
    public void createCorePointMeaningWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = corePointMeaningRepository.findAll().size();

        // Create the CorePointMeaning with an existing ID
        corePointMeaning.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCorePointMeaningMockMvc.perform(post("/api/core-point-meanings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(corePointMeaning)))
            .andExpect(status().isBadRequest());

        // Validate the CorePointMeaning in the database
        List<CorePointMeaning> corePointMeaningList = corePointMeaningRepository.findAll();
        assertThat(corePointMeaningList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCorePointMeanings() throws Exception {
        // Initialize the database
        corePointMeaningRepository.saveAndFlush(corePointMeaning);

        // Get all the corePointMeaningList
        restCorePointMeaningMockMvc.perform(get("/api/core-point-meanings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(corePointMeaning.getId().intValue())))
            .andExpect(jsonPath("$.[*].coreSourceId").value(hasItem(DEFAULT_CORE_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].corePointId").value(hasItem(DEFAULT_CORE_POINT_ID)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].meaning").value(hasItem(DEFAULT_MEANING.toString())));
    }
    
    @Test
    @Transactional
    public void getCorePointMeaning() throws Exception {
        // Initialize the database
        corePointMeaningRepository.saveAndFlush(corePointMeaning);

        // Get the corePointMeaning
        restCorePointMeaningMockMvc.perform(get("/api/core-point-meanings/{id}", corePointMeaning.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(corePointMeaning.getId().intValue()))
            .andExpect(jsonPath("$.coreSourceId").value(DEFAULT_CORE_SOURCE_ID))
            .andExpect(jsonPath("$.corePointId").value(DEFAULT_CORE_POINT_ID))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.meaning").value(DEFAULT_MEANING.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCorePointMeaning() throws Exception {
        // Get the corePointMeaning
        restCorePointMeaningMockMvc.perform(get("/api/core-point-meanings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCorePointMeaning() throws Exception {
        // Initialize the database
        corePointMeaningService.save(corePointMeaning);

        int databaseSizeBeforeUpdate = corePointMeaningRepository.findAll().size();

        // Update the corePointMeaning
        CorePointMeaning updatedCorePointMeaning = corePointMeaningRepository.findById(corePointMeaning.getId()).get();
        // Disconnect from session so that the updates on updatedCorePointMeaning are not directly saved in db
        em.detach(updatedCorePointMeaning);
        updatedCorePointMeaning
            .coreSourceId(UPDATED_CORE_SOURCE_ID)
            .corePointId(UPDATED_CORE_POINT_ID)
            .value(UPDATED_VALUE)
            .meaning(UPDATED_MEANING);

        restCorePointMeaningMockMvc.perform(put("/api/core-point-meanings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCorePointMeaning)))
            .andExpect(status().isOk());

        // Validate the CorePointMeaning in the database
        List<CorePointMeaning> corePointMeaningList = corePointMeaningRepository.findAll();
        assertThat(corePointMeaningList).hasSize(databaseSizeBeforeUpdate);
        CorePointMeaning testCorePointMeaning = corePointMeaningList.get(corePointMeaningList.size() - 1);
        assertThat(testCorePointMeaning.getCoreSourceId()).isEqualTo(UPDATED_CORE_SOURCE_ID);
        assertThat(testCorePointMeaning.getCorePointId()).isEqualTo(UPDATED_CORE_POINT_ID);
        assertThat(testCorePointMeaning.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testCorePointMeaning.getMeaning()).isEqualTo(UPDATED_MEANING);
    }

    @Test
    @Transactional
    public void updateNonExistingCorePointMeaning() throws Exception {
        int databaseSizeBeforeUpdate = corePointMeaningRepository.findAll().size();

        // Create the CorePointMeaning

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCorePointMeaningMockMvc.perform(put("/api/core-point-meanings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(corePointMeaning)))
            .andExpect(status().isBadRequest());

        // Validate the CorePointMeaning in the database
        List<CorePointMeaning> corePointMeaningList = corePointMeaningRepository.findAll();
        assertThat(corePointMeaningList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCorePointMeaning() throws Exception {
        // Initialize the database
        corePointMeaningService.save(corePointMeaning);

        int databaseSizeBeforeDelete = corePointMeaningRepository.findAll().size();

        // Get the corePointMeaning
        restCorePointMeaningMockMvc.perform(delete("/api/core-point-meanings/{id}", corePointMeaning.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CorePointMeaning> corePointMeaningList = corePointMeaningRepository.findAll();
        assertThat(corePointMeaningList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CorePointMeaning.class);
        CorePointMeaning corePointMeaning1 = new CorePointMeaning();
        corePointMeaning1.setId(1L);
        CorePointMeaning corePointMeaning2 = new CorePointMeaning();
        corePointMeaning2.setId(corePointMeaning1.getId());
        assertThat(corePointMeaning1).isEqualTo(corePointMeaning2);
        corePointMeaning2.setId(2L);
        assertThat(corePointMeaning1).isNotEqualTo(corePointMeaning2);
        corePointMeaning1.setId(null);
        assertThat(corePointMeaning1).isNotEqualTo(corePointMeaning2);
    }
}
