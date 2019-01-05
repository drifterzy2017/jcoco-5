package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.LivePoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LivePoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LivePointRepository extends JpaRepository<LivePoint, Long> {

}
