package com.kkk.cocoapp.service.dto.overview;


import com.kkk.cocoapp.domain.EventStaticByDay;
import com.kkk.cocoapp.service.dto.OperationResponse;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by HP on 2017/7/14.
 */
@EqualsAndHashCode(callSuper=false)
public class EventStatisticsByDurationResponse extends OperationResponse {
    private List<EventStaticByDay> items;

    public List<EventStaticByDay> getItems() {
        return items;
    }

    public void setItems(List<EventStaticByDay> items) {
        this.items = items;
    }
}
