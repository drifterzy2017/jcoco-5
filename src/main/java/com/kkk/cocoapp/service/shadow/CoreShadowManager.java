package com.kkk.cocoapp.service.shadow;


import com.kkk.cocoapp.domain.*;
import com.kkk.cocoapp.repository.*;

import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoreShadowManager {
    private ConcurrentHashMap<Integer,CoreShadow> coreShadowConcurrentHashMap = new ConcurrentHashMap<>();

    @Autowired
    private CoreSourceRepository coreSourceRepo;

    @Autowired
    private DesiredCovRepository desiredCovRepo;

    @Autowired
    private CorePointRepository corePointRepo;

    @Autowired
    private CovRepository covRepo;

    @Autowired
    private LivePointRepository livePointRepo;

    @Autowired
    private CorePointMeaningRepository corePointMeaningRepo;

    //初始化所有coreshadow
    public void init(){
        List<CoreSource> coreSources = coreSourceRepo.findAll();
        for(CoreSource coreSource:coreSources) {
            CoreShadow coreShadow = findCoreShadowByCoreSourceId(new Long((long)coreSource.getCoreSourceId()));
            coreShadowConcurrentHashMap.put(coreSource.getCoreSourceId(),coreShadow);
        }
    }

    public void addCov(Cov cov){
        if(cov == null){
            return;
        }
        //get pointShadow
        CoreShadow coreShadow = coreShadowConcurrentHashMap.get(cov.getCoreSourceId());
        if(coreShadow == null){
            return;
        }

        PointShadow pointShadow = coreShadow.getPointShadows().get(cov.getCorePointId());

        //如果是信号，直接更新value
        if(pointShadow == null){
            return;
        }
        if(cov.getState()<=1) {
            setPointValue(pointShadow, cov.getValue());
        }

        if(pointShadow.getCorePoint().getStateRuleId() ==2){
             if(cov.getState() > 0){
                 pointShadow.getPointStates().add(cov);
             }
             final List<Cov> startCov = pointShadow.getPointStates().stream()
                     .filter(cov1 ->cov1.getState()==1)
                     .collect(Collectors.toList());

             //筛选状态为结束的
             final List<Cov> endCov = pointShadow.getPointStates().stream()
                     .filter(cov1 ->cov1.getState()==2)
                     .collect(Collectors.toList());
             if (startCov.size() == 1 && endCov.size()==1){
                 pointShadow.getPointStates().clear();
             }
         }

        if(pointShadow.getCorePoint().getStateRuleId() ==3){
            final List<Cov> startCov = pointShadow.getPointStates().stream()
                    .filter(cov1 ->cov1.getState()==1)
                    .collect(Collectors.toList());
            //筛选状态为结束的
            final List<Cov> endCov = pointShadow.getPointStates().stream()
                    .filter(cov1 ->cov1.getState()==2)
                    .collect(Collectors.toList());

            //筛选状态为结束的
            final List<Cov> conCov = pointShadow.getPointStates().stream()
                    .filter(cov1 ->cov1.getState()==3)
                    .collect(Collectors.toList());

            if(startCov.size() == 1  && endCov.size() == 1 && cov.getState() == 1){
                pointShadow.getPointStates().clear();
            }

            if(cov.getState() > 0){
                pointShadow.getPointStates().add(cov);
            }

            if (startCov.size() == 1 && endCov.size()==1 && cov.getState()==3){
                pointShadow.getPointStates().clear();
            }

            if (startCov.size() == 1 && conCov.size()==1 && cov.getState()==2){
                pointShadow.getPointStates().clear();
                return;
            }
        }
    }

    public void addDesiredCov(DesiredCov desiredCov){
        if(desiredCov == null){
            return;
        }
        //get pointShadow
        CoreShadow coreShadow = coreShadowConcurrentHashMap.get(desiredCov.getCoreSourceId());
        if(coreShadow == null){
            return;
        }
        PointShadow pointShadow = coreShadow.getPointShadows().get(desiredCov.getCorePointId());
        if(pointShadow == null){
            return;
        }
        pointShadow.getDesiredCovs().add(desiredCov);
        return;
    }

    public void setLinkState(LinkState linkState){
        if(linkState == null){
            return;
        }
        CoreShadow coreShadow = coreShadowConcurrentHashMap.get(linkState.getCoreSourceId());
        coreShadow.setConnectState(linkState.getConnectState());
        return;
    }

    public CoreShadow getShadow(int coreSourceId){
        CoreShadow coreShadow = coreShadowConcurrentHashMap.get(coreSourceId);
        //如果不存在,则尝试重新构建
        if(coreShadow == null){
             coreShadow = findCoreShadowByCoreSourceId(new Long ((long)coreSourceId));
            coreShadowConcurrentHashMap.put(coreSourceId,coreShadow);
        }
        return coreShadow;
    }

    public void setPointMask(CorePoint corePoint){
        if(corePoint == null){
            return;
        }
        CoreShadow coreShadow = coreShadowConcurrentHashMap.get(corePoint.getCoreSourceId());
        PointShadow pointShadow = coreShadow.getPointShadows().get(corePoint.getCorePointId());
        pointShadow.getCorePoint().setMasked(corePoint.isMasked());
        return;
    }

    public CoreShadow findCoreShadowByCoreSourceId(Long coreSourceId)
    {
        CoreShadow coreShadow = new CoreShadow();
        //获取CoreSoure信息
        CoreSource coreSource = coreSourceRepo.findById(coreSourceId).orElse(null);
        if(coreShadow == null){
            return  null;
        }
        coreShadow.setCoreSource(coreSource);

        java.util.concurrent.ConcurrentHashMap<Integer,PointShadow> pointShadows =  new java.util.concurrent.ConcurrentHashMap<Integer,PointShadow>();
        List<CorePoint> corePoints = corePointRepo.findAllByCoreSourceId(coreSourceId.intValue());
        for(CorePoint corePoint : corePoints){
            PointShadow pointShadow = findPointShadowByCorePointId(new Long((long)corePoint.getCorePointId()));
            pointShadows.put(corePoint.getCorePointId(),pointShadow);
        }

        coreShadow.setPointShadows(pointShadows);
        return  coreShadow;
    }

    public PointShadow findPointShadowByCorePointId(Long corePointId)
    {
        PointShadow pointShadow = new PointShadow();
        CorePoint corePoint = corePointRepo.findById(corePointId).orElse(null);
        if(corePoint == null){
            return null;
        }
        pointShadow.setCorePoint(corePoint);

        List<Cov> covs = covRepo.findAllByCorePointId(corePointId);
        pointShadow.setPointStates(covs);

        List<DesiredCov> desiredCovs = desiredCovRepo.findAllByCorePointId(corePointId);
        pointShadow.setDesiredCovs(desiredCovs);

        List<CorePointMeaning> corePointMeanings = corePointMeaningRepo.findByCorePointId(corePointId);
        pointShadow.setCorePointMeanings(corePointMeanings);

        LivePoint livePoint = livePointRepo.findById(corePointId).orElse(null);
        if(livePoint != null) {
            setPointValue(pointShadow,livePoint.getCollectValue());
        }
        return  pointShadow;
    }

    private void setPointValue(PointShadow pointShadow, String originValue){
        if(pointShadow == null){
            return;
        }
        String value = originValue;

        if(pointShadow.getCorePoint().getCoreDataTypeId() ==1){
            Optional<CorePointMeaning> corePointMeaning= pointShadow.getCorePointMeanings().stream()
                    .filter(meaning ->meaning.getValue().equals(originValue)).findFirst();

            if (corePointMeaning.isPresent()) {
                //调用get()返回Optional值。
                value = corePointMeaning.get().getMeaning();
            }
        }
        else{
            value = value + pointShadow.getCorePoint().getUnit();
        }
        pointShadow.setCurrentValue(value);
        return;
    }
}
