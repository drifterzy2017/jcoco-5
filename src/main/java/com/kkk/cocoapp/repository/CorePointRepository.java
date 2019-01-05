package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.CorePoint;
import com.kkk.cocoapp.domain.LibQuestion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CorePoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CorePointRepository extends JpaRepository<CorePoint, Long> {
    List<CorePoint> findAllByCoreSourceId(Integer coreSourceId);
}
