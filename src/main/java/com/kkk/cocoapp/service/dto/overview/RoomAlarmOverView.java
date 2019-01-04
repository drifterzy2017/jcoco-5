package com.kkk.cocoapp.service.dto.overview;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.kkk.cocoapp.domain.Room;
import lombok.Data;

import java.util.List;

@Data
public class RoomAlarmOverView {
    @JsonIgnore
    private  int id;

    private  String name;

    private  List<DeviceAlarmOverView> children;

    @JsonIgnore
    private  int floorId;

    public RoomAlarmOverView(Room room){
        this.id = room.getRoomId();
        this.name = room.getRoomName();
        this.floorId = room.getFloorId();
    }
}
