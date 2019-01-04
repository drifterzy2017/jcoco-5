package com.kkk.cocoapp.service.dto.overview;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.kkk.cocoapp.domain.Device;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DeviceAlarmOverView {
    @Getter
    @Setter
    private String name;

    private  int size;
    @Getter
    @Setter
    private  int alarm;
    @Getter
    @Setter
    private  int id;

    @JsonIgnore
    @Getter
    @Setter
    private  int roomId;

    @JsonIgnore
    @Getter
    @Setter
    private List<DeviceAlarmStatistics>  alarmStatistics;


    public int getSize(){
        int csize = 0;
        if(alarmStatistics != null) {
            for (DeviceAlarmStatistics deviceAlarmStatistics : alarmStatistics) {
                switch (deviceAlarmStatistics.getSeverityId()) {
                    case 1:
                        csize = csize + deviceAlarmStatistics.getAlarmCount() * 4;
                        if(alarm > 1 || alarm == 0){
                            alarm = 1;
                        }
                        break;
                    case 2:
                        csize = csize + deviceAlarmStatistics.getAlarmCount() * 3;
                        if(alarm >  2 || alarm == 0) {
                            alarm = 2;
                        }
                        break;
                    case 3:
                        csize = csize + deviceAlarmStatistics.getAlarmCount() * 2;
                        if(alarm > 3 || alarm == 0) {
                            alarm = 3;
                        }
                        break;
                    default:
                        csize = csize + deviceAlarmStatistics.getAlarmCount() * 1;
                        if(alarm==0) {
                            alarm = 4;
                        }
                        break;
                }
            }
        }
        return  csize;
    }

    public DeviceAlarmOverView(Device device){
        this.id = device.getDeviceId();
        this.name = device.getDeviceName();
        this.roomId = device.getRoomId();
    }
}
