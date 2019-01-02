package com.kkk.cocoapp.repository;

import com.kkk.cocoapp.domain.Quiz;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the Quiz entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query(value ="select min(COST_SECONDS) from QUIZ where LIB_NAME  = ?1", nativeQuery=true)
    public Optional<Integer> getTopOne(String libName);

    @Query(value ="select count(1) from Quiz q")
    public Integer getAllCoinsCount();


    @Query(value ="select count(1) from Quiz q where successTime>?1")
    public Integer getCoinCntToday(Instant today);

    @Query(value ="select sum(q.point) from Quiz q where q.used = false")
    public Optional<Long> getCoinCntLeft();

//    @Query(value ="select q) from Quiz q where successTime>?1 and  used  = true")
//    List<Quiz> findAllByUsed(Instant today);

    @Query(value ="select q from Quiz q where  used = false order by q.point")
    List<Quiz> findAllByUsed(Boolean used);

    @Query(value ="select q from Quiz q where useTime>?1 and  used = true")
    List<Quiz> findAllUsedToday(Instant today);
}
