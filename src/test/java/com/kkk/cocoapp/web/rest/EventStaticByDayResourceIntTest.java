package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.EventStaticByDay;
import com.kkk.cocoapp.repository.EventStaticByDayRepository;
import com.kkk.cocoapp.service.EventStaticByDayService;
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
 * Test class for the EventStaticByDayResource REST controller.
 *
 * @see EventStaticByDayResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class EventStaticByDayResourceIntTest {

    private static final Integer DEFAULT_STATIC_DAY = 1;
    private static final Integer UPDATED_STATIC_DAY = 2;

    private static final Integer DEFAULT_SEVERITY_1 = 1;
    private static final Integer UPDATED_SEVERITY_1 = 2;

    private static final Integer DEFAULT_SEVERITY_2 = 1;
    private static final Integer UPDATED_SEVERITY_2 = 2;

    private static final Integer DEFAULT_SEVERITY_3 = 1;
    private static final Integer UPDATED_SEVERITY_3 = 2;

    private static final Integer DEFAULT_SEVERITY_4 = 1;
    private static final Integer UPDATED_SEVERITY_4 = 2;

    @Autowired
    private EventStaticByDayRepository eventStaticByDayRepository;

    @Autowired
    private EventStaticByDayService eventStaticByDayService;

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

    private MockMvc restEventStaticByDayMockMvc;

    private EventStaticByDay eventStaticByDay;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventStaticByDayResource eventStaticByDayResource = new EventStaticByDayResource(eventStaticByDayService);
        this.restEventStaticByDayMockMvc = MockMvcBuilders.standaloneSetup(eventStaticByDayResource)
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
    public static EventStaticByDay createEntity(EntityManager em) {
        EventStaticByDay eventStaticByDay = new EventStaticByDay()
            .staticDay(DEFAULT_STATIC_DAY)
            .severity1(DEFAULT_SEVERITY_1)
            .severity2(DEFAULT_SEVERITY_2)
            .severity3(DEFAULT_SEVERITY_3)
            .severity4(DEFAULT_SEVERITY_4);
        return eventStaticByDay;
    }

    @Before
    public void initTest() {
        eventStaticByDay = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventStaticByDay() throws Exception {
        int databaseSizeBeforeCreate = eventStaticByDayRepository.findAll().size();

        // Create the EventStaticByDay
        restEventStaticByDayMockMvc.perform(post("/api/event-static-by-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventStaticByDay)))
            .andExpect(status().isCreated());

        // Validate the EventStaticByDay in the database
        List<EventStaticByDay> eventStaticByDayList = eventStaticByDayRepository.findAll();
        assertThat(eventStaticByDayList).hasSize(databaseSizeBeforeCreate + 1);
        EventStaticByDay testEventStaticByDay = eventStaticByDayList.get(eventStaticByDayList.size() - 1);
        assertThat(testEventStaticByDay.getStaticDay()).isEqualTo(DEFAULT_STATIC_DAY);
        assertThat(testEventStaticByDay.getSeverity1()).isEqualTo(DEFAULT_SEVERITY_1);
        assertThat(testEventStaticByDay.getSeverity2()).isEqualTo(DEFAULT_SEVERITY_2);
        assertThat(testEventStaticByDay.getSeverity3()).isEqualTo(DEFAULT_SEVERITY_3);
        assertThat(testEventStaticByDay.getSeverity4()).isEqualTo(DEFAULT_SEVERITY_4);
    }

    @Test
    @Transactional
    public void createEventStaticByDayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventStaticByDayRepository.findAll().size();

        // Create the EventStaticByDay with an existing ID
        eventStaticByDay.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventStaticByDayMockMvc.perform(post("/api/event-static-by-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventStaticByDay)))
            .andExpect(status().isBadRequest());

        // Validate the EventStaticByDay in the database
        List<EventStaticByDay> eventStaticByDayList = eventStaticByDayRepository.findAll();
        assertThat(eventStaticByDayList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEventStaticByDays() throws Exception {
        // Initialize the database
        eventStaticByDayRepository.saveAndFlush(eventStaticByDay);

        // Get all the eventStaticByDayList
        restEventStaticByDayMockMvc.perform(get("/api/event-static-by-days?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventStaticByDay.getId().intValue())))
            .andExpect(jsonPath("$.[*].staticDay").value(hasItem(DEFAULT_STATIC_DAY)))
            .andExpect(jsonPath("$.[*].severity1").value(hasItem(DEFAULT_SEVERITY_1)))
            .andExpect(jsonPath("$.[*].severity2").value(hasItem(DEFAULT_SEVERITY_2)))
            .andExpect(jsonPath("$.[*].severity3").value(hasItem(DEFAULT_SEVERITY_3)))
            .andExpect(jsonPath("$.[*].severity4").value(hasItem(DEFAULT_SEVERITY_4)));
    }
    
    @Test
    @Transactional
    public void getEventStaticByDay() throws Exception {
        // Initialize the database
        eventStaticByDayRepository.saveAndFlush(eventStaticByDay);

        // Get the eventStaticByDay
        restEventStaticByDayMockMvc.perform(get("/api/event-static-by-days/{id}", eventStaticByDay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eventStaticByDay.getId().intValue()))
            .andExpect(jsonPath("$.staticDay").value(DEFAULT_STATIC_DAY))
            .andExpect(jsonPath("$.severity1").value(DEFAULT_SEVERITY_1))
            .andExpect(jsonPath("$.severity2").value(DEFAULT_SEVERITY_2))
            .andExpect(jsonPath("$.severity3").value(DEFAULT_SEVERITY_3))
            .andExpect(jsonPath("$.severity4").value(DEFAULT_SEVERITY_4));
    }

    @Test
    @Transactional
    public void getNonExistingEventStaticByDay() throws Exception {
        // Get the eventStaticByDay
        restEventStaticByDayMockMvc.perform(get("/api/event-static-by-days/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventStaticByDay() throws Exception {
        // Initialize the database
        eventStaticByDayService.save(eventStaticByDay);

        int databaseSizeBeforeUpdate = eventStaticByDayRepository.findAll().size();

        // Update the eventStaticByDay
        EventStaticByDay updatedEventStaticByDay = eventStaticByDayRepository.findById(eventStaticByDay.getId()).get();
        // Disconnect from session so that the updates on updatedEventStaticByDay are not directly saved in db
        em.detach(updatedEventStaticByDay);
        updatedEventStaticByDay
            .staticDay(UPDATED_STATIC_DAY)
            .severity1(UPDATED_SEVERITY_1)
            .severity2(UPDATED_SEVERITY_2)
            .severity3(UPDATED_SEVERITY_3)
            .severity4(UPDATED_SEVERITY_4);

        restEventStaticByDayMockMvc.perform(put("/api/event-static-by-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEventStaticByDay)))
            .andExpect(status().isOk());

        // Validate the EventStaticByDay in the database
        List<EventStaticByDay> eventStaticByDayList = eventStaticByDayRepository.findAll();
        assertThat(eventStaticByDayList).hasSize(databaseSizeBeforeUpdate);
        EventStaticByDay testEventStaticByDay = eventStaticByDayList.get(eventStaticByDayList.size() - 1);
        assertThat(testEventStaticByDay.getStaticDay()).isEqualTo(UPDATED_STATIC_DAY);
        assertThat(testEventStaticByDay.getSeverity1()).isEqualTo(UPDATED_SEVERITY_1);
        assertThat(testEventStaticByDay.getSeverity2()).isEqualTo(UPDATED_SEVERITY_2);
        assertThat(testEventStaticByDay.getSeverity3()).isEqualTo(UPDATED_SEVERITY_3);
        assertThat(testEventStaticByDay.getSeverity4()).isEqualTo(UPDATED_SEVERITY_4);
    }

    @Test
    @Transactional
    public void updateNonExistingEventStaticByDay() throws Exception {
        int databaseSizeBeforeUpdate = eventStaticByDayRepository.findAll().size();

        // Create the EventStaticByDay

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventStaticByDayMockMvc.perform(put("/api/event-static-by-days")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventStaticByDay)))
            .andExpect(status().isBadRequest());

        // Validate the EventStaticByDay in the database
        List<EventStaticByDay> eventStaticByDayList = eventStaticByDayRepository.findAll();
        assertThat(eventStaticByDayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventStaticByDay() throws Exception {
        // Initialize the database
        eventStaticByDayService.save(eventStaticByDay);

        int databaseSizeBeforeDelete = eventStaticByDayRepository.findAll().size();

        // Get the eventStaticByDay
        restEventStaticByDayMockMvc.perform(delete("/api/event-static-by-days/{id}", eventStaticByDay.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EventStaticByDay> eventStaticByDayList = eventStaticByDayRepository.findAll();
        assertThat(eventStaticByDayList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventStaticByDay.class);
        EventStaticByDay eventStaticByDay1 = new EventStaticByDay();
        eventStaticByDay1.setId(1L);
        EventStaticByDay eventStaticByDay2 = new EventStaticByDay();
        eventStaticByDay2.setId(eventStaticByDay1.getId());
        assertThat(eventStaticByDay1).isEqualTo(eventStaticByDay2);
        eventStaticByDay2.setId(2L);
        assertThat(eventStaticByDay1).isNotEqualTo(eventStaticByDay2);
        eventStaticByDay1.setId(null);
        assertThat(eventStaticByDay1).isNotEqualTo(eventStaticByDay2);
    }
}
