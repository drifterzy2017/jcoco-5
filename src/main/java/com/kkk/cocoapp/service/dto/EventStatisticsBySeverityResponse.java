package com.kkk.cocoapp.service.dto;

import com.kkk.cocoapp.service.phx.EventStatisticsBySeverity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by 13714 on 2019/1/3.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EventStatisticsBySeverityResponse extends OperationResponse {
    private List<EventStatisticsBySeverity> items;

    public List<EventStatisticsBySeverity> getItems() {
        return items;
    }

    public void setItems(List<EventStatisticsBySeverity> items) {
        this.items = items;
    }


}
