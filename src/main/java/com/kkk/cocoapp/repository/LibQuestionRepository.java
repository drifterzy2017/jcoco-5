package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.LibQuestion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the LibQuestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LibQuestionRepository extends JpaRepository<LibQuestion, Long> {
     String LIBQ_BY_NAME_CACHE="libqByName";

    @Cacheable(cacheNames =LIBQ_BY_NAME_CACHE)
    List<LibQuestion> findAllByLibName(String libName);
}
