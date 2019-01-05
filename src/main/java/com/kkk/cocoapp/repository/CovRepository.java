package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.Cov;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Cov entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CovRepository extends JpaRepository<Cov, Long> {
    List<Cov> findAllByCorePointId(Long corePointId);
}
