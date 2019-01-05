package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.CorePointMeaning;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CorePointMeaning entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CorePointMeaningRepository extends JpaRepository<CorePointMeaning, Long> {
    List<CorePointMeaning> findByCorePointId(Long corePointId);
}
