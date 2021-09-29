package com.janani.twtdw.configurations.caching;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.janani.twtdw.resources.TwitterResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@ComponentScan("com.janani.twtdw.resources")
public class CacheConfig {

    private static Logger logger =  LoggerFactory.getLogger(TwitterResource.class);
    public String[] cacheNames = {
            "filter"
    };

    @Bean
    @Primary
    public CacheManager cacheManagerTimeline() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(Arrays.asList(
                "timeline",
                "filter"
        ));
        logger.info("CacheManager");
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    Caffeine< Object, Object > caffeineCacheBuilder() {
        logger.info("CacheBuilder");
        return Caffeine.newBuilder()
                .initialCapacity(10)
                .maximumSize(500)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .weakKeys()
                .recordStats();
    }

    @Bean
    CacheManager alternateCacheManager() {
        return new ConcurrentMapCacheManager(cacheNames);
    }
}
