package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.EventStaticByDayService;
import com.kkk.cocoapp.domain.EventStaticByDay;
import com.kkk.cocoapp.repository.EventStaticByDayRepository;
import org.mapstruct.ap.shaded.freemarker.template.utility.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing EventStaticByDay.
 */
@Service
@Transactional
public class EventStaticByDayServiceImpl implements EventStaticByDayService {

    private final Logger log = LoggerFactory.getLogger(EventStaticByDayServiceImpl.class);

    private final EventStaticByDayRepository eventStaticByDayRepository;

    public EventStaticByDayServiceImpl(EventStaticByDayRepository eventStaticByDayRepository) {
        this.eventStaticByDayRepository = eventStaticByDayRepository;
    }

    /**
     * Save a eventStaticByDay.
     *
     * @param eventStaticByDay the entity to save
     * @return the persisted entity
     */
    @Override
    public EventStaticByDay save(EventStaticByDay eventStaticByDay) {
        log.debug("Request to save EventStaticByDay : {}", eventStaticByDay);
        return eventStaticByDayRepository.save(eventStaticByDay);
    }

    /**
     * Get all the eventStaticByDays.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EventStaticByDay> findAll() {
        log.debug("Request to get all EventStaticByDays");
        return eventStaticByDayRepository.findAll();
    }


    /**
     * Get one eventStaticByDay by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EventStaticByDay> findOne(Long id) {
        log.debug("Request to get EventStaticByDay : {}", id);
        return eventStaticByDayRepository.findById(id);
    }

    /**
     * Delete the eventStaticByDay by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EventStaticByDay : {}", id);
        eventStaticByDayRepository.deleteById(id);
    }

    @Override
    public List<EventStaticByDay> FindEventStaticLast(int n){

        ConcurrentHashMap<Integer, EventStaticByDay> eventStaticByDayConcurrentHashMap =  constructLastWeekStatistic(n);
        Page<EventStaticByDay> eventStaticByDays = eventStaticByDayRepository.findEventStaticByDayTop(new PageRequest(0, 8));

        for (EventStaticByDay eventStaticByDay:eventStaticByDays){
            if(eventStaticByDayConcurrentHashMap.containsKey(eventStaticByDay.getStaticDay())){
                eventStaticByDayConcurrentHashMap.put(eventStaticByDay.getStaticDay(),eventStaticByDay);
            }

        }
        return  new ArrayList<>(eventStaticByDayConcurrentHashMap.values());
    }
    private ConcurrentHashMap<Integer, EventStaticByDay> constructLastWeekStatistic(int n){
        Calendar c = Calendar.getInstance();
        Date endTime = c.getTime();
        c.add(Calendar.DAY_OF_WEEK, 0-n); // 目前的時間加7天
        Date startTime = c.getTime();
        ConcurrentHashMap<Integer, EventStaticByDay> staticByDayList = new ConcurrentHashMap<Integer, EventStaticByDay>();
        while(startTime.before(endTime)){
            EventStaticByDay eventStaticByDay = new EventStaticByDay(dateToInt(startTime),0,0,0,0);
            staticByDayList.put(eventStaticByDay.getStaticDay(),eventStaticByDay);
            c.add(Calendar.DAY_OF_WEEK,1);
            startTime = c.getTime();

        }
        return  staticByDayList;
    }
    public static int dateToInt(Date time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String yyyyMMdd = formatter.format(time);
        return  Integer.parseInt(yyyyMMdd);
    }
}
