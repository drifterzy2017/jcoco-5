package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.Bill;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;


/**
 * Spring Data  repository for the Bill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query(value ="select q from Bill q where buyTime>?1")
    List<Bill> findAllToday(Instant today);
}
