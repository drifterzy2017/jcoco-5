package com.kkk.cocoapp.service.shadow;


import com.kkk.cocoapp.domain.*;
import com.kkk.cocoapp.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by HP on 2017/7/12.
 */
@Service
public class ShadowServiceImpl implements ShadowService {

    @Autowired
    public CoreSourceRepository coreSourceRepo;

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private FloorRepository floorRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private CorePointRepository corePointRepo;

    @Autowired
    private CoreShadowManager coreShadowManager;

    @Autowired
    private BuildingRepository buildingRepo;

    @Autowired
    private ParkRepository parkRepo;

    public FloorShadow findFloorShadowByFloorId(Long floorId){
        FloorShadow floorShadow = new FloorShadow();


        Floor floor= floorRepo.findById(floorId).orElse(null);
        if(floor== null){
            return null;
        }
        floorShadow.setFloor(floor);

        List<Room> rooms = roomRepo.findAllByFloorId(floorId);
        ConcurrentHashMap<Integer,RoomShadow> roomShadows  = new ConcurrentHashMap<Integer,RoomShadow>();

        for(Room src : rooms){
            RoomShadow roomShadow = findRoomShadowByRoomId((long) src.getRoomId());
            roomShadows.put(src.getRoomId(),roomShadow);
        }

        floorShadow.setRoomShadows(roomShadows);

        return  floorShadow;
    }

    public RoomShadow findRoomShadowByRoomId(Long roomId){
        RoomShadow roomShadow = new RoomShadow();

        Room room = roomRepo.findById(roomId).orElse(null);
        if(room == null){
            return  null;
        }
        roomShadow.setRoom(room);

        List<Device> devices = deviceRepo.findAllByRoomId(roomId);
        ConcurrentHashMap<Integer,DeviceShadow> deviceShadows =  new ConcurrentHashMap<Integer,DeviceShadow>();

        for(Device src : devices) {
            DeviceShadow deviceShadow = findDeviceShadowByDeviceId((long) src.getDeviceId());
            deviceShadows.put(src.getDeviceId(),deviceShadow);
        }
        roomShadow.setDeviceShadows(deviceShadows);

        return  roomShadow;
    }


    public DeviceShadow findDeviceShadowByDeviceId(Long deviceId)
    {
        DeviceShadow deviceShadow = new DeviceShadow();

        Device device = deviceRepo.findById(deviceId).orElse(null);
        if(device == null){
            return null;
        }
        String devicePosition = getDevicePosition(device);
        deviceShadow.setDevicePosition(devicePosition);
        deviceShadow.setDevice(device);

        ConcurrentHashMap<Integer,CoreShadow> coreShadows =  new ConcurrentHashMap<Integer,CoreShadow>();

        for(CoreSource src : device.getCoreSources()){
            CoreShadow coreShadow = coreShadowManager.getShadow(src.getCoreSourceId());
            coreShadows.put(src.getCoreSourceId(),coreShadow);
        }
        deviceShadow.setCoreShadows(coreShadows);

        return  deviceShadow;
    }

    public CoreShadow findCoreShadowByCoreSourceId(Long coreSourceId)
    {
        return coreShadowManager.getShadow(coreSourceId.intValue());
    }

    public PointShadow findPointShadowByCorePointId(Long corePointId)
    {
        CorePoint corePoint = corePointRepo.findById(new Long(corePointId)).orElse(null);
        if(corePoint == null){
            return  null;
        }
        CoreShadow coreShadow =  coreShadowManager.getShadow(corePoint.getCoreSourceId());
        if(coreShadow == null){
            return  null;
        }
        return  coreShadow.getPointShadows().get(corePointId);
    }

    private String getDevicePosition(Device device ){
        if(device == null){
            return null;
        }
        Room room = roomRepo.findById((long) device.getRoomId()).orElse(null);
        if(room == null){
            return  null;
        }
        Floor floor = floorRepo.findById((long) room.getRoomId()).orElse(null);
        if(floor == null){
            return  null;
        }
        Building building = buildingRepo.findById((long) floor.getBuildingId()).orElse(null);
        if(building == null){
            return  null;
        }
        Park park = parkRepo.findById(((long) building.getParkId())).orElse(null);
        if(park == null){
            return null;
        }
        return park.getParkName()+">"+building.getBuildingName()+">"+floor.getFloorName()+">"+room.getRoomName();
    }
}
