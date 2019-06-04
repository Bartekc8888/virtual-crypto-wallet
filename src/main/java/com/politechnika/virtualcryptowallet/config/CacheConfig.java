package com.politechnika.virtualcryptowallet.config;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
@Slf4j
public class CacheConfig {
    public static final String CACHE_NAME = "CACHE_NAME";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(new ConcurrentMapCache(CACHE_NAME));
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {CACHE_NAME})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportCacheEvict() {
        log.info("Cache evicted: " + CACHE_NAME);
    }
}