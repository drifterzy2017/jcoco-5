package com.kkk.cocoapp.web.rest.phx;

import com.kkk.cocoapp.service.phx.ActiveEventStatisticsManager;
import com.kkk.cocoapp.service.dto.EventStatisticsBySeverityResponse;
import com.kkk.cocoapp.service.dto.OperationResponse;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by 13714 on 2019/1/3.
 */
@RestController
@RequestMapping("/api")
public class ActiveEventStatisticsController {

    private final ActiveEventStatisticsManager activeEventStatisticsManager;

    public ActiveEventStatisticsController(ActiveEventStatisticsManager activeEventStatisticsManager) {
        this.activeEventStatisticsManager = activeEventStatisticsManager;
    }

    @GetMapping("/activeeventstatistics")
    public ResponseEntity<EventStatisticsBySeverityResponse> getLiveEventStatistics() {
        val resp = new EventStatisticsBySeverityResponse();
        val dataItemList = activeEventStatisticsManager.getCurrentStatistic();
        resp.setItems(dataItemList);
        resp.setOperationStatus(OperationResponse.ResponseStatusEnum.SUCCESS);
        resp.setOperationMessage("Group by Quantity Severity");
        return Optional.ofNullable(resp)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
