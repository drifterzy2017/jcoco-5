package com.kkk.cocoapp.service.impl.phx;


import com.kkk.cocoapp.service.DevicePositionService;
import com.kkk.cocoapp.service.OverviewService;
import com.kkk.cocoapp.service.dto.overview.DeviceAlarmStatistics;
import com.kkk.cocoapp.service.dto.overview.DevicePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevicePositionServiceImpl implements DevicePositionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OverviewService overviewService;

    public List<DevicePosition> getAll(){
        List<DeviceAlarmStatistics>  deviceAlarmStatistics =  overviewService.getAllDeviceStatistics();
        List<DevicePosition> devicePositions = getDevicePosition();

        return  constructDevicePositionInfo(devicePositions,deviceAlarmStatistics);
    }
    private  List<DevicePosition> constructDevicePositionInfo(List<DevicePosition> devices, List<DeviceAlarmStatistics>  deviceAlarmStatistics){
        for(DevicePosition device: devices){
                final List<DeviceAlarmStatistics> deviceAlarmStatistics1 = deviceAlarmStatistics.stream()
                        .filter(cov1 -> cov1.getDeviceId() == device.getDeviceId())
                        .collect(Collectors.toList());

                if(deviceAlarmStatistics1.size()>0){
                    device.setAlarmStatistics(deviceAlarmStatistics1);
                }
        }
        return devices;
    }
    private List<DevicePosition> getDevicePosition(){
        class DevicePositionMapper  implements RowMapper<DevicePosition> {
            @Override
            public DevicePosition mapRow(ResultSet row, int rowNum) throws SQLException {
                DevicePosition deviceInfo = new DevicePosition();
                deviceInfo.setDeviceId(row.getInt("Device_Id"));
                deviceInfo.setDeviceName(row.getString("Device_Name"));
                deviceInfo.setBuildingName(row.getString("Building_Name"));
                deviceInfo.setFloorName(row.getString("Floor_Name"));
                deviceInfo.setRoomName(row.getString("Room_Name"));
                deviceInfo.setBuildingId(row.getInt("Building_Id"));
                deviceInfo.setFloorId(row.getInt("Floor_Id"));
                deviceInfo.setRoomId(row.getInt("Room_Id"));
                deviceInfo.setStatus(row.getInt("Status"));
                return  deviceInfo;

            }
        }
        String sql = "select Device_Id, Device_Name,Status, r.Room_Id, r.Room_Name, f.Floor_Id,f.Floor_Name,b.Building_Id, b.Building_Name \n" +
            "from Device a \n" +
            "inner join Room r on a.Room_Id= r.Room_Id\n" +
            "inner join Floor f on r.Floor_Id = f.Floor_Id\n" +
            "inner join Building b on f.Building_Id = b.Building_Id";
        List<DevicePosition> result = jdbcTemplate.query(sql, new DevicePositionMapper());
        return  result;
    }
}
