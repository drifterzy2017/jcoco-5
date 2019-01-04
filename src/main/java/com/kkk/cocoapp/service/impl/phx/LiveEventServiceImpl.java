package com.kkk.cocoapp.service.impl.phx;

import com.kkk.cocoapp.service.LiveEventService;
import com.kkk.cocoapp.domain.LiveEvent;
import com.kkk.cocoapp.repository.LiveEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing LiveEvent.
 */
@Service
@Transactional
public class LiveEventServiceImpl implements LiveEventService {

    private final Logger log = LoggerFactory.getLogger(LiveEventServiceImpl.class);

    private final LiveEventRepository liveEventRepository;

    public LiveEventServiceImpl(LiveEventRepository liveEventRepository) {
        this.liveEventRepository = liveEventRepository;
    }

    /**
     * Save a liveEvent.
     *
     * @param liveEvent the entity to save
     * @return the persisted entity
     */
    @Override
    public LiveEvent save(LiveEvent liveEvent) {
        log.debug("Request to save LiveEvent : {}", liveEvent);
        return liveEventRepository.save(liveEvent);
    }

    /**
     * Get all the liveEvents.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LiveEvent> findAll() {
        log.debug("Request to get all LiveEvents");
        return liveEventRepository.findAll();
    }


    /**
     * Get one liveEvent by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LiveEvent> findOne(Long id) {
        log.debug("Request to get LiveEvent : {}", id);
        return liveEventRepository.findById(id);
    }

    /**
     * Delete the liveEvent by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LiveEvent : {}", id);
        liveEventRepository.deleteById(id);
    }

    @Override
    public List<LiveEvent> getEventByLastest( int recordNumber){
//        Page<LiveEvent> liveEvents = liveEventRepository.findLiveEventTopn(new PageRequest(0, recordNumber));
        Page<LiveEvent> liveEvents = liveEventRepository.findLiveEventTopn(PageRequest.of(0, recordNumber));
        return  liveEvents.getContent();
    }
}
