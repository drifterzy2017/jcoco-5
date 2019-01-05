package com.kkk.cocoapp.service.shadow;

import com.kkk.cocoapp.domain.CoreSource;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 2017/7/3.
 */
@Data
public class CoreShadow {

    private CoreSource coreSource;

    private  int  connectState;

    ConcurrentHashMap<Integer, PointShadow> pointShadows;
}
