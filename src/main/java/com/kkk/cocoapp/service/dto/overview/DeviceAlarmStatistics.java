package com.kkk.cocoapp.service.dto.overview;

import lombok.Data;

@Data
public class DeviceAlarmStatistics {

    private  int  deviceId;

    private  int  severityId;

    private  int  alarmCount;
}
