package com.kulsin.caffeine.synchronous;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.kulsin.model.DataObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CaffeineSynchronousLoading {

    /*
    * This method of loading the cache takes a Function, which is used for initializing values,
    *  similar to the get method of the manual strategy
    * */
    LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .recordStats() //
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build(k -> DataObject.get("Data for " + k));

    public DataObject get(String key) {
        return cache.get(key);
    }

    public Map<String, DataObject> getAll(List<String> keys) {
        return cache.getAll(keys);
    }
}
