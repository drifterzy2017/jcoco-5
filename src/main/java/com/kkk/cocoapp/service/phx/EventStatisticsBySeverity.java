package com.kkk.cocoapp.service.phx;

import lombok.Data;

/**
 * Created by 13714 on 2019/1/3.
 */
@Data
public class EventStatisticsBySeverity {
    private  int Id;

    private String name;

    private long value;

    public void setValue(long value){
        this.value = value;
    }

    public EventStatisticsBySeverity(int Id, String name, long value){
        this.Id = Id;
        this.name  = name;
        this.value = value;
    }
}
