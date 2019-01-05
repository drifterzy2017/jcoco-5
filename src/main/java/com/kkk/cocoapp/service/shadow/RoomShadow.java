package com.kkk.cocoapp.service.shadow;

import com.kkk.cocoapp.domain.Room;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 2017/7/12.
 */
@Data
public class RoomShadow {
    private Room room;
    private ConcurrentHashMap<Integer,DeviceShadow> deviceShadows;
}
