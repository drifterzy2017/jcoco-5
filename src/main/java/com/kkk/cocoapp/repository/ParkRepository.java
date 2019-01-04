package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.Park;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Park entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {

}
