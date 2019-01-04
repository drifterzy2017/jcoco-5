package com.kkk.cocoapp.service.dto.overview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkk.cocoapp.domain.Building;
import com.kkk.cocoapp.domain.Park;
import lombok.Data;

import java.util.List;

/**
 * Created by HP on 2017/7/17.
 */
@Data
public class BuildingAlarmOverView {
    @JsonIgnore
    private  int id;

    private  String name;

    private List<FloorAlarmOverView> children;

    @JsonIgnore
    private  int parkId;

    public BuildingAlarmOverView(Building building){
        this.id = building.getBuildingId();
        this.name = building.getBuildingName();
        this.parkId = building.getParkId();
    }
}
