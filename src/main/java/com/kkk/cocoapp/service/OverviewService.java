package com.kkk.cocoapp.service;

import com.kkk.cocoapp.domain.Park;
import com.kkk.cocoapp.service.dto.overview.DeviceAlarmStatistics;
import com.kkk.cocoapp.service.dto.overview.ParkAlarmOverView;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Park.
 */
public interface OverviewService {
    List<ParkAlarmOverView> getAll(boolean existAlarms);
    List<DeviceAlarmStatistics> getAllDeviceStatistics();
}
