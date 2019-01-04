package com.kkk.cocoapp.service.dto.overview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkk.cocoapp.domain.Park;
import lombok.Data;

import java.util.List;

/**
 * Created by HP on 2017/7/17.
 */
@Data
public class ParkAlarmOverView {
    @JsonIgnore
    private  int id;

    private  String name;

    private List<BuildingAlarmOverView> children;

    public ParkAlarmOverView(Park p){
        this.id = p.getParkId();
        this.name = p.getParkName();
    }
}
