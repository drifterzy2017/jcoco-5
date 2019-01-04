package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.LiveEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LiveEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LiveEventRepository extends JpaRepository<LiveEvent, Long> {
    @Query("SELECT mm FROM LiveEvent mm ORDER BY birthTime desc")
    Page<LiveEvent> findLiveEventTopn(Pageable pageable);
}
