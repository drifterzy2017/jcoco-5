package com.kkk.cocoapp.service.dto.overview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DevicePosition {
    @Getter
    @Setter
    private  Integer  deviceId;
    @Getter
    @Setter
    private  String   deviceName;
    @Getter
    @Setter
    private  Integer floorId;
    @Getter
    @Setter
    private  String   floorName;
    @Getter
    @Setter
    private  int   buildingId;
    @Getter
    @Setter
    private  String   buildingName;
    @Getter
    @Setter
    private  Integer   roomId;
    @Getter
    @Setter
    private  String roomName;
    @Getter
    @Setter
    private  int      status;

    private  String   alarmStatus;

    @JsonIgnore
    @Getter
    @Setter
    private List<DeviceAlarmStatistics> alarmStatistics;

    public String getalarmStatus(){
        String  csize ="0";
        if(alarmStatistics != null) {
            for (DeviceAlarmStatistics deviceAlarmStatistics : alarmStatistics) {
                switch (deviceAlarmStatistics.getSeverityId()) {
                    case 1:
                        csize = csize + "1";
                        break;
                    case 2:
                        csize = csize + "2";
                        break;
                    case 3:
                        csize = csize +  "3";
                       break;
                    default:
                        csize = csize +  "4";
                          break;
                }
            }
        }
        return csize;
    }
}
