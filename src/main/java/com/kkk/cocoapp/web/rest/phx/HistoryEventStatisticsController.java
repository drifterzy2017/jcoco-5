package com.kkk.cocoapp.web.rest.phx;

import com.kkk.cocoapp.domain.EventStaticByDay;
import com.kkk.cocoapp.service.EventStaticByDayService;
import com.kkk.cocoapp.service.dto.OperationResponse;
import com.kkk.cocoapp.service.dto.overview.EventStatisticsByDurationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by 13714 on 2019/1/3.
 */
@RestController
@RequestMapping("/api")
public class HistoryEventStatisticsController {
    private final Logger log = LoggerFactory.getLogger(HistoryEventStatisticsController.class);

    @Autowired
    private EventStaticByDayService staticByDayService;

    public HistoryEventStatisticsController() {
    }

    @GetMapping("/historyeventstatistics/getlast")
    public ResponseEntity<EventStatisticsByDurationResponse> getAllEventStatisticsByDuration(@RequestParam("size") int size ) {
        EventStatisticsByDurationResponse resp = new EventStatisticsByDurationResponse();
        List<EventStaticByDay> dataItemList = staticByDayService.FindEventStaticLast(size);
        resp.setItems(dataItemList);
        resp.setOperationStatus(OperationResponse.ResponseStatusEnum.SUCCESS);
        resp.setOperationMessage("Group by Quantity Duration");
        return Optional.ofNullable(resp)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
