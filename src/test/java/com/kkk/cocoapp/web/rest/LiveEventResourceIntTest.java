package com.kkk.cocoapp.web.rest;

import com.kkk.cocoapp.Jcoco5App;

import com.kkk.cocoapp.domain.LiveEvent;
import com.kkk.cocoapp.repository.LiveEventRepository;
import com.kkk.cocoapp.service.LiveEventService;
import com.kkk.cocoapp.web.rest.errors.ExceptionTranslator;

//import com.kkk.cocoapp.web.rest.homepage.LiveEventResource;
import com.kkk.cocoapp.web.rest.homepage.LiveEventResource;
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
 * Test class for the LiveEventResource REST controller.
 *
 * @see LiveEventResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jcoco5App.class)
public class LiveEventResourceIntTest {

    private static final Long DEFAULT_LIVE_EVENT_ID = 1L;
    private static final Long UPDATED_LIVE_EVENT_ID = 2L;

    private static final Instant DEFAULT_BIRTH_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BIRTH_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_CLEARED_BY_ID = 1;
    private static final Integer UPDATED_CLEARED_BY_ID = 2;

    private static final String DEFAULT_CLEARED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLEARED_BY_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_CLEAR_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLEAR_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CONFIRMER_ID = 1;
    private static final Integer UPDATED_CONFIRMER_ID = 2;

    private static final String DEFAULT_CONFIRMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONFIRMER_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_CONFIRM_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CONFIRM_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_CORE_POINT_ID = 1;
    private static final Integer UPDATED_CORE_POINT_ID = 2;

    private static final String DEFAULT_CORE_POINT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CORE_POINT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CORE_SOURCE_ID = 1;
    private static final Integer UPDATED_CORE_SOURCE_ID = 2;

    private static final String DEFAULT_CORE_SOURCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CORE_SOURCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OCCUR_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_OCCUR_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_OCCUR_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_OCCUR_VALUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_SEVERITY_ID = 1;
    private static final Integer UPDATED_SEVERITY_ID = 2;

    private static final String DEFAULT_SEVERITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SEVERITY_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATE_ID = 1;
    private static final Integer UPDATED_STATE_ID = 2;

    private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

    @Autowired
    private LiveEventRepository liveEventRepository;

    @Autowired
    private LiveEventService liveEventService;

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

    private MockMvc restLiveEventMockMvc;

    private LiveEvent liveEvent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LiveEventResource liveEventResource = new LiveEventResource(liveEventService);
        this.restLiveEventMockMvc = MockMvcBuilders.standaloneSetup(liveEventResource)
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
    public static LiveEvent createEntity(EntityManager em) {
        LiveEvent liveEvent = new LiveEvent()
            .liveEventId(DEFAULT_LIVE_EVENT_ID)
            .birthTime(DEFAULT_BIRTH_TIME)
            .clearedById(DEFAULT_CLEARED_BY_ID)
            .clearedByName(DEFAULT_CLEARED_BY_NAME)
            .clearTime(DEFAULT_CLEAR_TIME)
            .comment(DEFAULT_COMMENT)
            .confirmerId(DEFAULT_CONFIRMER_ID)
            .confirmerName(DEFAULT_CONFIRMER_NAME)
            .confirmTime(DEFAULT_CONFIRM_TIME)
            .corePointId(DEFAULT_CORE_POINT_ID)
            .corePointName(DEFAULT_CORE_POINT_NAME)
            .coreSourceId(DEFAULT_CORE_SOURCE_ID)
            .coreSourceName(DEFAULT_CORE_SOURCE_NAME)
            .occurRemark(DEFAULT_OCCUR_REMARK)
            .occurValue(DEFAULT_OCCUR_VALUE)
            .severityId(DEFAULT_SEVERITY_ID)
            .severityName(DEFAULT_SEVERITY_NAME)
            .stateId(DEFAULT_STATE_ID)
            .stateName(DEFAULT_STATE_NAME);
        return liveEvent;
    }

    @Before
    public void initTest() {
        liveEvent = createEntity(em);
    }

    @Test
    @Transactional
    public void createLiveEvent() throws Exception {
        int databaseSizeBeforeCreate = liveEventRepository.findAll().size();

        // Create the LiveEvent
        restLiveEventMockMvc.perform(post("/api/live-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(liveEvent)))
            .andExpect(status().isCreated());

        // Validate the LiveEvent in the database
        List<LiveEvent> liveEventList = liveEventRepository.findAll();
        assertThat(liveEventList).hasSize(databaseSizeBeforeCreate + 1);
        LiveEvent testLiveEvent = liveEventList.get(liveEventList.size() - 1);
        assertThat(testLiveEvent.getLiveEventId()).isEqualTo(DEFAULT_LIVE_EVENT_ID);
        assertThat(testLiveEvent.getBirthTime()).isEqualTo(DEFAULT_BIRTH_TIME);
        assertThat(testLiveEvent.getClearedById()).isEqualTo(DEFAULT_CLEARED_BY_ID);
        assertThat(testLiveEvent.getClearedByName()).isEqualTo(DEFAULT_CLEARED_BY_NAME);
        assertThat(testLiveEvent.getClearTime()).isEqualTo(DEFAULT_CLEAR_TIME);
        assertThat(testLiveEvent.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testLiveEvent.getConfirmerId()).isEqualTo(DEFAULT_CONFIRMER_ID);
        assertThat(testLiveEvent.getConfirmerName()).isEqualTo(DEFAULT_CONFIRMER_NAME);
        assertThat(testLiveEvent.getConfirmTime()).isEqualTo(DEFAULT_CONFIRM_TIME);
        assertThat(testLiveEvent.getCorePointId()).isEqualTo(DEFAULT_CORE_POINT_ID);
        assertThat(testLiveEvent.getCorePointName()).isEqualTo(DEFAULT_CORE_POINT_NAME);
        assertThat(testLiveEvent.getCoreSourceId()).isEqualTo(DEFAULT_CORE_SOURCE_ID);
        assertThat(testLiveEvent.getCoreSourceName()).isEqualTo(DEFAULT_CORE_SOURCE_NAME);
        assertThat(testLiveEvent.getOccurRemark()).isEqualTo(DEFAULT_OCCUR_REMARK);
        assertThat(testLiveEvent.getOccurValue()).isEqualTo(DEFAULT_OCCUR_VALUE);
        assertThat(testLiveEvent.getSeverityId()).isEqualTo(DEFAULT_SEVERITY_ID);
        assertThat(testLiveEvent.getSeverityName()).isEqualTo(DEFAULT_SEVERITY_NAME);
        assertThat(testLiveEvent.getStateId()).isEqualTo(DEFAULT_STATE_ID);
        assertThat(testLiveEvent.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
    }

    @Test
    @Transactional
    public void createLiveEventWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = liveEventRepository.findAll().size();

        // Create the LiveEvent with an existing ID
        liveEvent.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLiveEventMockMvc.perform(post("/api/live-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(liveEvent)))
            .andExpect(status().isBadRequest());

        // Validate the LiveEvent in the database
        List<LiveEvent> liveEventList = liveEventRepository.findAll();
        assertThat(liveEventList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLiveEvents() throws Exception {
        // Initialize the database
        liveEventRepository.saveAndFlush(liveEvent);

        // Get all the liveEventList
        restLiveEventMockMvc.perform(get("/api/live-events?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(liveEvent.getId().intValue())))
            .andExpect(jsonPath("$.[*].liveEventId").value(hasItem(DEFAULT_LIVE_EVENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].birthTime").value(hasItem(DEFAULT_BIRTH_TIME.toString())))
            .andExpect(jsonPath("$.[*].clearedById").value(hasItem(DEFAULT_CLEARED_BY_ID)))
            .andExpect(jsonPath("$.[*].clearedByName").value(hasItem(DEFAULT_CLEARED_BY_NAME.toString())))
            .andExpect(jsonPath("$.[*].clearTime").value(hasItem(DEFAULT_CLEAR_TIME.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].confirmerId").value(hasItem(DEFAULT_CONFIRMER_ID)))
            .andExpect(jsonPath("$.[*].confirmerName").value(hasItem(DEFAULT_CONFIRMER_NAME.toString())))
            .andExpect(jsonPath("$.[*].confirmTime").value(hasItem(DEFAULT_CONFIRM_TIME.toString())))
            .andExpect(jsonPath("$.[*].corePointId").value(hasItem(DEFAULT_CORE_POINT_ID)))
            .andExpect(jsonPath("$.[*].corePointName").value(hasItem(DEFAULT_CORE_POINT_NAME.toString())))
            .andExpect(jsonPath("$.[*].coreSourceId").value(hasItem(DEFAULT_CORE_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].coreSourceName").value(hasItem(DEFAULT_CORE_SOURCE_NAME.toString())))
            .andExpect(jsonPath("$.[*].occurRemark").value(hasItem(DEFAULT_OCCUR_REMARK.toString())))
            .andExpect(jsonPath("$.[*].occurValue").value(hasItem(DEFAULT_OCCUR_VALUE.toString())))
            .andExpect(jsonPath("$.[*].severityId").value(hasItem(DEFAULT_SEVERITY_ID)))
            .andExpect(jsonPath("$.[*].severityName").value(hasItem(DEFAULT_SEVERITY_NAME.toString())))
            .andExpect(jsonPath("$.[*].stateId").value(hasItem(DEFAULT_STATE_ID)))
            .andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getLiveEvent() throws Exception {
        // Initialize the database
        liveEventRepository.saveAndFlush(liveEvent);

        // Get the liveEvent
        restLiveEventMockMvc.perform(get("/api/live-events/{id}", liveEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(liveEvent.getId().intValue()))
            .andExpect(jsonPath("$.liveEventId").value(DEFAULT_LIVE_EVENT_ID.intValue()))
            .andExpect(jsonPath("$.birthTime").value(DEFAULT_BIRTH_TIME.toString()))
            .andExpect(jsonPath("$.clearedById").value(DEFAULT_CLEARED_BY_ID))
            .andExpect(jsonPath("$.clearedByName").value(DEFAULT_CLEARED_BY_NAME.toString()))
            .andExpect(jsonPath("$.clearTime").value(DEFAULT_CLEAR_TIME.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.confirmerId").value(DEFAULT_CONFIRMER_ID))
            .andExpect(jsonPath("$.confirmerName").value(DEFAULT_CONFIRMER_NAME.toString()))
            .andExpect(jsonPath("$.confirmTime").value(DEFAULT_CONFIRM_TIME.toString()))
            .andExpect(jsonPath("$.corePointId").value(DEFAULT_CORE_POINT_ID))
            .andExpect(jsonPath("$.corePointName").value(DEFAULT_CORE_POINT_NAME.toString()))
            .andExpect(jsonPath("$.coreSourceId").value(DEFAULT_CORE_SOURCE_ID))
            .andExpect(jsonPath("$.coreSourceName").value(DEFAULT_CORE_SOURCE_NAME.toString()))
            .andExpect(jsonPath("$.occurRemark").value(DEFAULT_OCCUR_REMARK.toString()))
            .andExpect(jsonPath("$.occurValue").value(DEFAULT_OCCUR_VALUE.toString()))
            .andExpect(jsonPath("$.severityId").value(DEFAULT_SEVERITY_ID))
            .andExpect(jsonPath("$.severityName").value(DEFAULT_SEVERITY_NAME.toString()))
            .andExpect(jsonPath("$.stateId").value(DEFAULT_STATE_ID))
            .andExpect(jsonPath("$.stateName").value(DEFAULT_STATE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLiveEvent() throws Exception {
        // Get the liveEvent
        restLiveEventMockMvc.perform(get("/api/live-events/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLiveEvent() throws Exception {
        // Initialize the database
        liveEventService.save(liveEvent);

        int databaseSizeBeforeUpdate = liveEventRepository.findAll().size();

        // Update the liveEvent
        LiveEvent updatedLiveEvent = liveEventRepository.findById(liveEvent.getId()).get();
        // Disconnect from session so that the updates on updatedLiveEvent are not directly saved in db
        em.detach(updatedLiveEvent);
        updatedLiveEvent
            .liveEventId(UPDATED_LIVE_EVENT_ID)
            .birthTime(UPDATED_BIRTH_TIME)
            .clearedById(UPDATED_CLEARED_BY_ID)
            .clearedByName(UPDATED_CLEARED_BY_NAME)
            .clearTime(UPDATED_CLEAR_TIME)
            .comment(UPDATED_COMMENT)
            .confirmerId(UPDATED_CONFIRMER_ID)
            .confirmerName(UPDATED_CONFIRMER_NAME)
            .confirmTime(UPDATED_CONFIRM_TIME)
            .corePointId(UPDATED_CORE_POINT_ID)
            .corePointName(UPDATED_CORE_POINT_NAME)
            .coreSourceId(UPDATED_CORE_SOURCE_ID)
            .coreSourceName(UPDATED_CORE_SOURCE_NAME)
            .occurRemark(UPDATED_OCCUR_REMARK)
            .occurValue(UPDATED_OCCUR_VALUE)
            .severityId(UPDATED_SEVERITY_ID)
            .severityName(UPDATED_SEVERITY_NAME)
            .stateId(UPDATED_STATE_ID)
            .stateName(UPDATED_STATE_NAME);

        restLiveEventMockMvc.perform(put("/api/live-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLiveEvent)))
            .andExpect(status().isOk());

        // Validate the LiveEvent in the database
        List<LiveEvent> liveEventList = liveEventRepository.findAll();
        assertThat(liveEventList).hasSize(databaseSizeBeforeUpdate);
        LiveEvent testLiveEvent = liveEventList.get(liveEventList.size() - 1);
        assertThat(testLiveEvent.getLiveEventId()).isEqualTo(UPDATED_LIVE_EVENT_ID);
        assertThat(testLiveEvent.getBirthTime()).isEqualTo(UPDATED_BIRTH_TIME);
        assertThat(testLiveEvent.getClearedById()).isEqualTo(UPDATED_CLEARED_BY_ID);
        assertThat(testLiveEvent.getClearedByName()).isEqualTo(UPDATED_CLEARED_BY_NAME);
        assertThat(testLiveEvent.getClearTime()).isEqualTo(UPDATED_CLEAR_TIME);
        assertThat(testLiveEvent.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testLiveEvent.getConfirmerId()).isEqualTo(UPDATED_CONFIRMER_ID);
        assertThat(testLiveEvent.getConfirmerName()).isEqualTo(UPDATED_CONFIRMER_NAME);
        assertThat(testLiveEvent.getConfirmTime()).isEqualTo(UPDATED_CONFIRM_TIME);
        assertThat(testLiveEvent.getCorePointId()).isEqualTo(UPDATED_CORE_POINT_ID);
        assertThat(testLiveEvent.getCorePointName()).isEqualTo(UPDATED_CORE_POINT_NAME);
        assertThat(testLiveEvent.getCoreSourceId()).isEqualTo(UPDATED_CORE_SOURCE_ID);
        assertThat(testLiveEvent.getCoreSourceName()).isEqualTo(UPDATED_CORE_SOURCE_NAME);
        assertThat(testLiveEvent.getOccurRemark()).isEqualTo(UPDATED_OCCUR_REMARK);
        assertThat(testLiveEvent.getOccurValue()).isEqualTo(UPDATED_OCCUR_VALUE);
        assertThat(testLiveEvent.getSeverityId()).isEqualTo(UPDATED_SEVERITY_ID);
        assertThat(testLiveEvent.getSeverityName()).isEqualTo(UPDATED_SEVERITY_NAME);
        assertThat(testLiveEvent.getStateId()).isEqualTo(UPDATED_STATE_ID);
        assertThat(testLiveEvent.getStateName()).isEqualTo(UPDATED_STATE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingLiveEvent() throws Exception {
        int databaseSizeBeforeUpdate = liveEventRepository.findAll().size();

        // Create the LiveEvent

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLiveEventMockMvc.perform(put("/api/live-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(liveEvent)))
            .andExpect(status().isBadRequest());

        // Validate the LiveEvent in the database
        List<LiveEvent> liveEventList = liveEventRepository.findAll();
        assertThat(liveEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLiveEvent() throws Exception {
        // Initialize the database
        liveEventService.save(liveEvent);

        int databaseSizeBeforeDelete = liveEventRepository.findAll().size();

        // Get the liveEvent
        restLiveEventMockMvc.perform(delete("/api/live-events/{id}", liveEvent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LiveEvent> liveEventList = liveEventRepository.findAll();
        assertThat(liveEventList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LiveEvent.class);
        LiveEvent liveEvent1 = new LiveEvent();
        liveEvent1.setId(1L);
        LiveEvent liveEvent2 = new LiveEvent();
        liveEvent2.setId(liveEvent1.getId());
        assertThat(liveEvent1).isEqualTo(liveEvent2);
        liveEvent2.setId(2L);
        assertThat(liveEvent1).isNotEqualTo(liveEvent2);
        liveEvent1.setId(null);
        assertThat(liveEvent1).isNotEqualTo(liveEvent2);
    }
}
