package com.kkk.cocoapp.service.shadow;

import com.kkk.cocoapp.domain.Floor;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by HP on 2017/7/12.
 */
@Data
public class FloorShadow {
    private Floor floor;
    private ConcurrentHashMap<Integer,RoomShadow> roomShadows;

}
