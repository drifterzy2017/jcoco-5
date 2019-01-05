package com.kkk.cocoapp.service.shadow;



import com.kkk.cocoapp.domain.*;
import lombok.Data;

import java.util.List;

@Data
public class PointShadow {
    private CorePoint corePoint;
    private  String CurrentValue;
    private  List<Cov> pointStates;
    private  List<DesiredCov> desiredCovs;
    private  List<CorePointMeaning> corePointMeanings;

}

