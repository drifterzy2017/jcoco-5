package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.Park;
import com.kkk.cocoapp.repository.ParkRepository;
import com.kkk.cocoapp.service.ParkService;
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
 * Test class for the ParkResource REST controller.
 *
 * @see ParkResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class ParkResourceIntTest {

    private static final Integer DEFAULT_PARK_ID = 1;
    private static final Integer UPDATED_PARK_ID = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final String DEFAULT_PARK_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PARK_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PARK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PARK_NAME = "BBBBBBBBBB";

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private ParkService parkService;

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

    private MockMvc restParkMockMvc;

    private Park park;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParkResource parkResource = new ParkResource(parkService);
        this.restParkMockMvc = MockMvcBuilders.standaloneSetup(parkResource)
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
    public static Park createEntity(EntityManager em) {
        Park park = new Park()
            .parkId(DEFAULT_PARK_ID)
            .description(DEFAULT_DESCRIPTION)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .parkAddress(DEFAULT_PARK_ADDRESS)
            .parkName(DEFAULT_PARK_NAME);
        return park;
    }

    @Before
    public void initTest() {
        park = createEntity(em);
    }

    @Test
    @Transactional
    public void createPark() throws Exception {
        int databaseSizeBeforeCreate = parkRepository.findAll().size();

        // Create the Park
        restParkMockMvc.perform(post("/api/parks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(park)))
            .andExpect(status().isCreated());

        // Validate the Park in the database
        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeCreate + 1);
        Park testPark = parkList.get(parkList.size() - 1);
        assertThat(testPark.getParkId()).isEqualTo(DEFAULT_PARK_ID);
        assertThat(testPark.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPark.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testPark.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testPark.getParkAddress()).isEqualTo(DEFAULT_PARK_ADDRESS);
        assertThat(testPark.getParkName()).isEqualTo(DEFAULT_PARK_NAME);
    }

    @Test
    @Transactional
    public void createParkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parkRepository.findAll().size();

        // Create the Park with an existing ID
        park.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParkMockMvc.perform(post("/api/parks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(park)))
            .andExpect(status().isBadRequest());

        // Validate the Park in the database
        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllParks() throws Exception {
        // Initialize the database
        parkRepository.saveAndFlush(park);

        // Get all the parkList
        restParkMockMvc.perform(get("/api/parks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(park.getId().intValue())))
            .andExpect(jsonPath("$.[*].parkId").value(hasItem(DEFAULT_PARK_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].parkAddress").value(hasItem(DEFAULT_PARK_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].parkName").value(hasItem(DEFAULT_PARK_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getPark() throws Exception {
        // Initialize the database
        parkRepository.saveAndFlush(park);

        // Get the park
        restParkMockMvc.perform(get("/api/parks/{id}", park.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(park.getId().intValue()))
            .andExpect(jsonPath("$.parkId").value(DEFAULT_PARK_ID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.parkAddress").value(DEFAULT_PARK_ADDRESS.toString()))
            .andExpect(jsonPath("$.parkName").value(DEFAULT_PARK_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPark() throws Exception {
        // Get the park
        restParkMockMvc.perform(get("/api/parks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePark() throws Exception {
        // Initialize the database
        parkService.save(park);

        int databaseSizeBeforeUpdate = parkRepository.findAll().size();

        // Update the park
        Park updatedPark = parkRepository.findById(park.getId()).get();
        // Disconnect from session so that the updates on updatedPark are not directly saved in db
        em.detach(updatedPark);
        updatedPark
            .parkId(UPDATED_PARK_ID)
            .description(UPDATED_DESCRIPTION)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .parkAddress(UPDATED_PARK_ADDRESS)
            .parkName(UPDATED_PARK_NAME);

        restParkMockMvc.perform(put("/api/parks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPark)))
            .andExpect(status().isOk());

        // Validate the Park in the database
        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeUpdate);
        Park testPark = parkList.get(parkList.size() - 1);
        assertThat(testPark.getParkId()).isEqualTo(UPDATED_PARK_ID);
        assertThat(testPark.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPark.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testPark.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testPark.getParkAddress()).isEqualTo(UPDATED_PARK_ADDRESS);
        assertThat(testPark.getParkName()).isEqualTo(UPDATED_PARK_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPark() throws Exception {
        int databaseSizeBeforeUpdate = parkRepository.findAll().size();

        // Create the Park

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParkMockMvc.perform(put("/api/parks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(park)))
            .andExpect(status().isBadRequest());

        // Validate the Park in the database
        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePark() throws Exception {
        // Initialize the database
        parkService.save(park);

        int databaseSizeBeforeDelete = parkRepository.findAll().size();

        // Get the park
        restParkMockMvc.perform(delete("/api/parks/{id}", park.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Park.class);
        Park park1 = new Park();
        park1.setId(1L);
        Park park2 = new Park();
        park2.setId(park1.getId());
        assertThat(park1).isEqualTo(park2);
        park2.setId(2L);
        assertThat(park1).isNotEqualTo(park2);
        park1.setId(null);
        assertThat(park1).isNotEqualTo(park2);
    }
}
