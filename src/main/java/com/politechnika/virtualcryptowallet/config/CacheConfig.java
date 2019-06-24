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
    public static final String BITCOIN_CACHE = "BITCOIN_CACHE";
    public static final String ETHERUM_CACHE = "ETHERUM_CACHE";
    public static final String XRP_CACHE = "XRP_CACHE";
    public static final String LITECOIN_CACHE = "LITECOIN_CACHE";
    public static final String BITCOINCASH_CACHE = "BITCOINCASH_CACHE";
    public static final String EOS_CACHE = "EOS_CACHE";
    public static final String BINANCECOIN_CACHE = "BINANCECOIN_CACHE";
    public static final String BITCOINSV_CACHE = "BITCOINSV_CACHE";
    public static final String TETHER_CACHE = "TETHER_CACHE";
    public static final String TRON_CACHE = "TRON_CACHE";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(new ConcurrentMapCache(BITCOIN_CACHE));
        caches.add(new ConcurrentMapCache(ETHERUM_CACHE));
        caches.add(new ConcurrentMapCache(XRP_CACHE));
        caches.add(new ConcurrentMapCache(LITECOIN_CACHE));
        caches.add(new ConcurrentMapCache(BITCOINCASH_CACHE));
        caches.add(new ConcurrentMapCache(EOS_CACHE));
        caches.add(new ConcurrentMapCache(BINANCECOIN_CACHE));
        caches.add(new ConcurrentMapCache(BITCOINSV_CACHE));
        caches.add(new ConcurrentMapCache(TETHER_CACHE));
        caches.add(new ConcurrentMapCache(TRON_CACHE));
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {BITCOIN_CACHE})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportBitcoinEvict() {
        log.info("Cache evicted: " + BITCOIN_CACHE);
    }

    @CacheEvict(allEntries = true, value = {ETHERUM_CACHE})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportEtherumEvict() {
        log.info("Cache evicted: " + ETHERUM_CACHE);
    }

    @CacheEvict(allEntries = true, value = {XRP_CACHE})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportXRPEvict() {
        log.info("Cache evicted: " + XRP_CACHE);
    }

    @CacheEvict(allEntries = true, value = {LITECOIN_CACHE})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportLitecoinEvict() {
        log.info("Cache evicted: " + LITECOIN_CACHE);
    }

    @CacheEvict(allEntries = true, value = {BITCOINCASH_CACHE})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportBitcoinCashEvict() {
        log.info("Cache evicted: " + BITCOINCASH_CACHE);
    }

    @CacheEvict(allEntries = true, value = {EOS_CACHE})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportEOSEvict() {
        log.info("Cache evicted: " + EOS_CACHE);
    }

    @CacheEvict(allEntries = true, value = {BINANCECOIN_CACHE})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportBinanceCoinEvict() {
        log.info("Cache evicted: " + BINANCECOIN_CACHE);
    }

    @CacheEvict(allEntries = true, value = {BITCOINSV_CACHE})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportBitcoinSVEvict() {
        log.info("Cache evicted: " + BITCOINSV_CACHE);
    }

    @CacheEvict(allEntries = true, value = {TETHER_CACHE})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportTetherEvict() {
        log.info("Cache evicted: " + TETHER_CACHE);
    }

    @CacheEvict(allEntries = true, value = {TRON_CACHE})
    @Scheduled(fixedDelay = 5 * 60 * 1000 , initialDelay = 500)
    public void reportTRONEvict() {
        log.info("Cache evicted: " + TRON_CACHE);
    }
}