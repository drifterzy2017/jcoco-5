package com.kkk.cocoapp.web.rest.homepage;

import com.kkk.cocoapp.service.OverviewService;
import com.kkk.cocoapp.service.dto.EventStatisticsBySeverityResponse;
import com.kkk.cocoapp.service.dto.OperationResponse;
import com.kkk.cocoapp.service.dto.overview.ParkAlarmOverView;
import com.kkk.cocoapp.service.phx.ActiveEventStatisticsManager;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Created by 13714 on 2019/1/3.
 */
@RestController
@RequestMapping("/api")
public class ParkAlarmOverviewController {
    private final Logger log = LoggerFactory.getLogger(ParkAlarmOverviewController.class);
    private final ActiveEventStatisticsManager activeEventStatisticsManager;
    @Autowired
    OverviewService overviewService;

    public ParkAlarmOverviewController(ActiveEventStatisticsManager activeEventStatisticsManager) {
        this.activeEventStatisticsManager = activeEventStatisticsManager;
    }

    @GetMapping("/parkalarnoverview")
    public List<ParkAlarmOverView> getParkAlarmPositionOverviewInfo() throws URISyntaxException {
        log.debug("REST request get all alarm position overview");
        //获取当前所有告警设备的详细信息
        List<ParkAlarmOverView>  result = overviewService.getAll(true);
        return  result;
    }
}
