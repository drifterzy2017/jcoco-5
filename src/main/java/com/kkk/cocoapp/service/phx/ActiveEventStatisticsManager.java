package com.kkk.cocoapp.service.phx;


import com.kkk.cocoapp.domain.CoreEventSeverity;
import com.kkk.cocoapp.domain.LiveEvent;
import com.kkk.cocoapp.repository.CoreEventSeverityRepository;
import com.kkk.cocoapp.repository.LiveEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by 13714 on 2019/1/3.
 */
@Service
@Transactional
public class ActiveEventStatisticsManager {
    private static final Logger log = LoggerFactory.getLogger(ActiveEventStatisticsManager.class);

    private ConcurrentHashMap<Integer,EventStatisticsBySeverity> currentStatistic = new ConcurrentHashMap<>();

    @Autowired
    private LiveEventRepository liveEventRepo;

    @Autowired
    private CoreEventSeverityRepository coreEventSeverityRepo;

    public void init(){
        constructCurrentStatistics();

        Map<Integer, Long> staticdb = getStatisticFromDB();
        log.info(staticdb.toString());

        for (Integer key : staticdb.keySet()) {
            //kkk alt
//            if(currentStatistic.containsKey(key)){
//                EventStatisticsBySeverity eventStatisticsBySeverity = currentStatistic.get(key);
//                eventStatisticsBySeverity.setValue(staticdb.get(key));
//            }
            EventStatisticsBySeverity eventStatisticsBySeverity = new EventStatisticsBySeverity(0,"",0);
            eventStatisticsBySeverity.setValue(staticdb.get(key));
            currentStatistic.put(key, eventStatisticsBySeverity);


        }
    }
    private void constructCurrentStatistics(){
        List<CoreEventSeverity> coreEventSeverities = coreEventSeverityRepo.findAll();
        for(CoreEventSeverity coreEventSeverity:coreEventSeverities){
            EventStatisticsBySeverity eventStatisticsBySeverity= new  EventStatisticsBySeverity(coreEventSeverity.getEventSeverityId(),coreEventSeverity.getSeverityName(),0);
            if(coreEventSeverity.getEventSeverityId() > 0)
                currentStatistic.put(coreEventSeverity.getEventSeverityId(),eventStatisticsBySeverity);
        }

    }
    private Map<Integer, Long> getStatisticFromDB(){
        List<LiveEvent> liveEvents = liveEventRepo.findAll();
        Map<Integer, Long> staticResult = liveEvents.stream().collect(
            Collectors.groupingBy(
                LiveEvent::getSeverityId, Collectors.counting()
            )
        );

        return  staticResult;
    }

    public List<EventStatisticsBySeverity> getCurrentStatistic(){
        return  new ArrayList<>(currentStatistic.values());
    }

}
