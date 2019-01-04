package com.kkk.cocoapp.service.dto.overview;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.kkk.cocoapp.domain.Floor;
import lombok.Data;

import java.util.List;

@Data
public class FloorAlarmOverView {
    @JsonIgnore
    private  int id;

    private  String name;

    private List<RoomAlarmOverView> children;

    @JsonIgnore
    private  int buildingId;

    public FloorAlarmOverView(Floor floor){
        this.id = floor.getFloorId();
        this.name = floor.getFloorName();
        this.buildingId=floor.getBuildingId();
    }
}
