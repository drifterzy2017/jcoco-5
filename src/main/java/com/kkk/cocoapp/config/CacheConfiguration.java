package com.kkk.cocoapp.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.kkk.cocoapp.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);

        cm.createCache(com.kkk.cocoapp.repository.LibQuestionRepository.LIBQ_BY_NAME_CACHE, jcacheConfiguration);

            cm.createCache(com.kkk.cocoapp.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.Bill.class.getName(), jcacheConfiguration);

            cm.createCache(com.kkk.cocoapp.domain.LibQuestion.class.getName(), jcacheConfiguration);

            cm.createCache(com.kkk.cocoapp.domain.Question.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.Quiz.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.Quiz.class.getName() + ".questions", jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.LiveEvent.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.CoreEventSeverity.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.Device.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.DeviceState.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.Park.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.Room.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.Floor.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.Building.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.CoreSource.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.EventStaticByDay.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.DeviceMask.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.CorePoint.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.Cov.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.CorePointMeaning.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.DesiredCov.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.LivePoint.class.getName(), jcacheConfiguration);
            cm.createCache(com.kkk.cocoapp.domain.PointMask.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
