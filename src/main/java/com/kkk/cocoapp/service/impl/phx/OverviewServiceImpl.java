package com.kkk.cocoapp.service.impl.phx;

import com.kkk.cocoapp.domain.*;
import com.kkk.cocoapp.repository.*;
import com.kkk.cocoapp.service.OverviewService;
import com.kkk.cocoapp.service.dto.overview.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OverviewServiceImpl implements OverviewService {

    @Autowired
    private ParkRepository parkRepo;

    @Autowired
    private BuildingRepository buildingRepo;

    @Autowired
    private FloorRepository floorRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ParkAlarmOverView> getAll(boolean existAlarms){
        //todo
        //获取所有的元数据
        List<Park> parks= parkRepo.findAll();
        List<Building> buildings = buildingRepo.findAll();
        List<Floor> floors = floorRepo.findAll();
        List<Room> rooms =roomRepo.findAll();
        List<Device> devices = deviceRepo.findAll();
        List<DeviceAlarmStatistics>  deviceAlarmStatistics =  getAllDeviceStatistics();
        //按指定的格式组装数据
        List<DeviceAlarmOverView> deviceAlarmOverViews = constructDeviceAlarmOverView(devices,deviceAlarmStatistics,existAlarms);
        List<RoomAlarmOverView> roomAlarmOverViews = constructRoomAlarmOverView(rooms,deviceAlarmOverViews);
        List<FloorAlarmOverView> floorAlarmOverViews = constructFloorAlarmOverView(floors,roomAlarmOverViews);
        List<BuildingAlarmOverView> buildingAlarmOverViews =constructBuildingAlarmOverView(buildings,floorAlarmOverViews);

        return constructParkAlarmOverView(parks,buildingAlarmOverViews);
    }

    private  List<DeviceAlarmOverView> constructDeviceAlarmOverView( List<Device> devices, List<DeviceAlarmStatistics>  deviceAlarmStatistics,boolean existAlarms){
        List<DeviceAlarmOverView> deviceAlarmOverViews = new ArrayList<>();
        for(Device device: devices){
            DeviceAlarmOverView deviceAlarmOverView = new DeviceAlarmOverView(device);
            if(existAlarms) {
                final List<DeviceAlarmStatistics> deviceAlarmStatistics1 = deviceAlarmStatistics.stream()
                        .filter(cov1 -> cov1.getDeviceId() == device.getDeviceId())
                        .collect(Collectors.toList());

                if(deviceAlarmStatistics1.size()>0){
                    deviceAlarmOverView.setAlarmStatistics(deviceAlarmStatistics1);

                    deviceAlarmOverViews.add(deviceAlarmOverView);
                }
            }
            else
            {
                deviceAlarmOverViews.add(deviceAlarmOverView);
            }
        }
       return deviceAlarmOverViews;
    }

    private  List<RoomAlarmOverView> constructRoomAlarmOverView(List<Room> rooms, List<DeviceAlarmOverView> deviceAlarmOverViews){
        List<RoomAlarmOverView> roomAlarmOverViews = new ArrayList<>();
        for(Room room: rooms){
            RoomAlarmOverView roomAlarmOverView = new RoomAlarmOverView(room);
            final List<DeviceAlarmOverView> deviceAlarmOverViews1 = deviceAlarmOverViews.stream()
                    .filter(cov1 ->cov1.getRoomId()==room.getRoomId())
                    .collect(Collectors.toList());

            if(deviceAlarmOverViews1.size() > 0){
                roomAlarmOverView.setChildren(deviceAlarmOverViews1);
                roomAlarmOverViews.add(roomAlarmOverView);
            }
        }
        return roomAlarmOverViews;
    }

    private  List<FloorAlarmOverView> constructFloorAlarmOverView(List<Floor> floors, List<RoomAlarmOverView> roomAlarmOverViews){
        List<FloorAlarmOverView> floorAlarmOverViews = new ArrayList<>();
        for(Floor floor: floors){
            FloorAlarmOverView floorAlarmOverView = new FloorAlarmOverView(floor);
            final List<RoomAlarmOverView> roomAlarmOverViews1 = roomAlarmOverViews.stream()
                    .filter(cov1 ->cov1.getFloorId()==floor.getFloorId())
                    .collect(Collectors.toList());

            if(roomAlarmOverViews1.size() > 0) {
                floorAlarmOverView.setChildren(roomAlarmOverViews1);
                floorAlarmOverViews.add(floorAlarmOverView);
            }
        }
        return floorAlarmOverViews;
    }

    private  List<BuildingAlarmOverView> constructBuildingAlarmOverView(List<Building> buildings, List<FloorAlarmOverView> floorAlarmOverViews){
        List<BuildingAlarmOverView> buildingAlarmOverViews = new ArrayList<>();
        for(Building building: buildings){
            BuildingAlarmOverView buildingAlarmOverView = new BuildingAlarmOverView(building);
            final List<FloorAlarmOverView> floorAlarmOverViews1 = floorAlarmOverViews.stream()
                    .filter(cov1 ->cov1.getBuildingId()==building.getBuildingId())
                    .collect(Collectors.toList());

            if(floorAlarmOverViews1.size() > 0){
                buildingAlarmOverView.setChildren(floorAlarmOverViews1);
                buildingAlarmOverViews.add(buildingAlarmOverView);
            }
        }
        return buildingAlarmOverViews;
    }

    private  List<ParkAlarmOverView> constructParkAlarmOverView(List<Park> parks, List<BuildingAlarmOverView> buildingAlarmOverViews) {
        List<ParkAlarmOverView> parkAlarmOverViews = new ArrayList<>();
        for (Park park : parks) {
            ParkAlarmOverView parkAlarmOverView = new ParkAlarmOverView(park);
            final List<BuildingAlarmOverView> buildingAlarmOverViews1 = buildingAlarmOverViews.stream()
                    .filter(cov1 -> cov1.getParkId() == park.getParkId())
                    .collect(Collectors.toList());

            if (buildingAlarmOverViews1.size() > 0){
                parkAlarmOverView.setChildren(buildingAlarmOverViews1);
                parkAlarmOverViews.add(parkAlarmOverView);
            }
        }
        return parkAlarmOverViews;
    }
    public List<DeviceAlarmStatistics> getAllDeviceStatistics(){
        class DeviceAlarmStatisticsMapper implements RowMapper<DeviceAlarmStatistics> {
//            @Override
//            public DeviceAlarmStatistics mapRow(ResultSet row, int rowNum) throws SQLException {
//                DeviceAlarmStatistics deviceInfo = new DeviceAlarmStatistics();
//                deviceInfo.setDeviceId(row.getInt("DeviceId"));
//                deviceInfo.setSeverityId(row.getInt("SeverityId"));
//                deviceInfo.setAlarmCount(row.getInt("cnt"));
//                return  deviceInfo;
            @Override
            public DeviceAlarmStatistics mapRow(ResultSet row, int rowNum) throws SQLException {
                DeviceAlarmStatistics deviceInfo = new DeviceAlarmStatistics();
                deviceInfo.setDeviceId(row.getInt("Device_Id"));
                deviceInfo.setSeverityId(row.getInt("Severity_Id"));
                deviceInfo.setAlarmCount(row.getInt("cnt"));
                return  deviceInfo;
            }
        }
//        return new ArrayList<DeviceAlarmStatistics>();
        //todo
//        String sql = "select de.DeviceId, de.DeviceName,le.SeverityId, count(*) cnt from device de inner join coresource cs on de.DeviceId = cs.CoreSourceId  \n" +
//                "inner join LiveEvent le on cs.CoreSourceId = le.CoreSourceId  \n" +
//                "group by de.DeviceId, de.DeviceName,le.SeverityId";
        String sql = "select de.Device_Id, de.Device_Name,le.Severity_Id, count(*) cnt \n" +
            "from device de \n" +
            "inner join core_source cs \n" +
            "\ton de.Device_Id = cs.core_source_id  \n" +
            "inner join Live_Event le \n" +
            "\ton cs.core_source_id = le.core_source_id  \n" +
            "group by de.Device_Id, de.Device_Name,le.Severity_Id";
        List<DeviceAlarmStatistics> result = jdbcTemplate.query(sql, new DeviceAlarmStatisticsMapper());
        return  result;
    }
}
