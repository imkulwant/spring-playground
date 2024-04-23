package com.kulsin.rest;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("monitor/caffeine")
public class MonitorCaffeineResource {

    @Resource
    private CacheManager caffeine;


    @GetMapping("cacheNames")
    public Collection<String> cacheNames() {
        return caffeine.getCacheNames();
    }

    @GetMapping("stats")
    public Map<String, Object> stats(@RequestParam String cacheName) {
        CaffeineCache caffeineCache = (CaffeineCache) caffeine.getCache(cacheName);
        CacheStats stats = CacheStats.empty();
        if (caffeineCache != null) {
            stats = caffeineCache.getNativeCache().stats();
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("Number of requests ", stats.requestCount());
        map.put("Number of hits ", stats.hitCount());
        map.put("Number of misses ", stats.missCount());
        map.put("Load success times ", stats.loadSuccessCount());
        map.put("Number of load failures ", stats.loadFailureCount());
        map.put("Percentage of loading failures ", stats.loadFailureRate());
        map.put("Total load time ", stats.totalLoadTime());
        map.put("Total recycling times ", stats.evictionCount());
        map.put("Reclaim the total weight ", stats.evictionWeight());

        return map;
    }

}
