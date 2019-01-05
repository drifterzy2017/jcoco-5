package com.kkk.cocoapp.service.shadow;


/**
 * Created by HP on 2017/7/12.
 */
public interface ShadowService {
    CoreShadow findCoreShadowByCoreSourceId(Long coreSourceId);
    DeviceShadow findDeviceShadowByDeviceId(Long deviceId);
    FloorShadow findFloorShadowByFloorId(Long floorId);
    RoomShadow findRoomShadowByRoomId(Long roomId);
    PointShadow findPointShadowByCorePointId(Long corePointId);
}
