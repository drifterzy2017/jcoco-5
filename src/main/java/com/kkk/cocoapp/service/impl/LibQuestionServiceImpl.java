package com.kkk.cocoapp.service.impl;

import com.kkk.cocoapp.service.LibQuestionService;
import com.kkk.cocoapp.domain.LibQuestion;
import com.kkk.cocoapp.repository.LibQuestionRepository;

import lombok.val;


import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.cache.CacheManager;
import org.springframework.cache.jcache.JCacheCache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing LibQuestion.
 */
@Service
@Transactional
public class LibQuestionServiceImpl implements LibQuestionService {

    private final EntityManager entityManager;

    private final CacheManager cacheManager;
    private final Logger log = LoggerFactory.getLogger(LibQuestionServiceImpl.class);

    private final LibQuestionRepository libQuestionRepository;

    public LibQuestionServiceImpl(EntityManager entityManager, CacheManager cacheManager, LibQuestionRepository libQuestionRepository) {
        this.entityManager = entityManager;
        this.cacheManager = cacheManager;
        this.libQuestionRepository = libQuestionRepository;
    }

    /**
     * Save a libQuestion.
     *
     * @param libQuestion the entity to save
     * @return the persisted entity
     */
    @Override
    public LibQuestion save(LibQuestion libQuestion) {
        log.debug("Request to save LibQuestion : {}", libQuestion);
        //kkk
        return libQuestionRepository.save(libQuestion);
//        return libQuestionRepository.saveAndFlush(libQuestion);
    }



    /**
     * Get all the libQuestions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LibQuestion> findAll() {
        log.debug("Request to get all LibQuestions");
        return libQuestionRepository.findAll();

        //可以运行，但没有缓存
//        javax.persistence.Query query = entityManager.createQuery("from LibQuestion c") ;
//        query.setHint("org.hibernate.cacheable", true);
//        List<LibQuestion> list = query.getResultList() ;

        // 获取所有的缓存对象
//        val cacheUser = cacheManager.getCache(com.kkk.cocoapp.domain.User.class.getName());
//
//        val cacheLibName= cacheManager.getCache(com.kkk.cocoapp.repository.LibQuestionRepository.LIBQ_BY_NAME_CACHE);
//
//        val cache = cacheManager.getCache(com.kkk.cocoapp.domain.LibQuestion.class.getName());
//        val ehCache = ((Eh107Cache) ((JCacheCache) cache).cache).ehCache;
//       JCacheCache eh = (JCacheCache)cache;

//        Set<String> keys = new HashSet<>();
//        cache.forEach(entry -> keys.add(entry.getKey()));

//        LibQuestion q=new LibQuestion();
//        q.setId(Long.valueOf(79302));
//        val data1 = cache.get(q);




        //"com.kkk.cocoapp.domain.LibQuestion#79302"
//        val data = cache.get(79302);
//
//        val data3 =  cache.get("com.kkk.cocoapp.domain.LibQuestion#79302");


        //.getNativeCache();
//        org.ehcache.jsr107.Eh107Cache ehCache =(org.ehcache.jsr107.Eh107Cache)nativeCache;
//        val alls =ehCache.getAll(null);
//        EhCache cache =  nativeCache;
//        for (Object key: cache.getKeys()) {
//            Element element = cache.get(key);
//            if (element != null && element.getObjectValue().equals(o)) {
//                return true;..




//        for (Object key : ) {
//            System.out.println(key);
//        }



//        EntityManager entityManager = emf.createEntityManager();
//        Query query = entityManager.createQuery("from Customer c") ;
//        query.setHint("org.hibernate.cacheable", true);
//        List<LibQuestion> list = query.getResultList() ;
    }



    /**
     * Get one libQuestion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LibQuestion> findOne(Long id) {
        log.debug("Request to get LibQuestion : {}", id);
        return libQuestionRepository.findById(id);
    }

    /**
     * Delete the libQuestion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LibQuestion : {}", id);
        libQuestionRepository.deleteById(id);
    }

    @Override
    public List<LibQuestion>  findAllByLibName(String libName){
        return libQuestionRepository.findAllByLibName(libName);
    }

    @Override
    public void flushAll(){
        libQuestionRepository.flush();
    }
}
