package com.kulsin.caffeine.manual;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.kulsin.model.DataObject;

import java.util.concurrent.TimeUnit;

public class CaffeineManualLoading {

    Cache<String, DataObject> cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES) // time-based eviction
            .maximumSize(100) // size-based eviction
            .build();


    public void populateCache(String key, DataObject dataObject) {
        cache.put(key, dataObject);
    }

    public DataObject getIfPresent(String key) {
        return cache.getIfPresent(key);
    }

    public DataObject get(String key) {
        /*
        * The get method performs the computation atomically.
        * This means that the computation will be made only once — even if several threads ask for the value simultaneously.
        * That's why using get is preferable to getIfPresent.
        * */
        return cache.get(key, k -> DataObject.get("Data for A"));
    }

    public void invalidate(String key) {
        cache.invalidate(key);
    }

}
