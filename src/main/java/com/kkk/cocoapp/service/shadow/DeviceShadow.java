package com.kkk.cocoapp.service.shadow;


import com.kkk.cocoapp.domain.Device;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 2017/7/3.
 */
@Data
public class DeviceShadow {
    private String devicePosition;
    private Device device;
    private ConcurrentHashMap<Integer,CoreShadow> coreShadows;
}
