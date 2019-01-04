package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.EventStaticByDay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EventStaticByDay entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventStaticByDayRepository extends JpaRepository<EventStaticByDay, Long> {

    @Query("SELECT mm FROM EventStaticByDay mm ORDER BY staticDay desc")
    Page<EventStaticByDay> findEventStaticByDayTop(Pageable pageable);
}
