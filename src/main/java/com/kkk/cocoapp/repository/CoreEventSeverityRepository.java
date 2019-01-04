package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.CoreEventSeverity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CoreEventSeverity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoreEventSeverityRepository extends JpaRepository<CoreEventSeverity, Long> {

}
