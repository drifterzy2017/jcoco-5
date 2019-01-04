package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.CoreSource;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CoreSource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoreSourceRepository extends JpaRepository<CoreSource, Long> {

}
