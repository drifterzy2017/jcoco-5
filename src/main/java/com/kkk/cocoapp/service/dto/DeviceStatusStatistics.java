package com.kkk.cocoapp.service.dto;

import lombok.Data;

/**
 * Created by HP on 2017/7/17.
 */
@Data
public class DeviceStatusStatistics {
    private  int stateId;
    //private  String stateName;
    private  long devicecount;

    public DeviceStatusStatistics(int stateId, long count){
        this.stateId = stateId;
        //this.stateName =stateName;
        this.devicecount = count;
    }
}
