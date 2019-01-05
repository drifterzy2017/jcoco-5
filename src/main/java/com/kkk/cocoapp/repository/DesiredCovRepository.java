package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.DesiredCov;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the DesiredCov entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DesiredCovRepository extends JpaRepository<DesiredCov, Long> {
    List<DesiredCov> findAllByCorePointId(Long corePointId);
}
