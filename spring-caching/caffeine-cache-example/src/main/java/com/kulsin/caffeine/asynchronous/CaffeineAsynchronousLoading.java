package com.kulsin.caffeine.asynchronous;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.kulsin.model.DataObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CaffeineAsynchronousLoading {

    /*
    * This strategy works the same as the previous but performs operations asynchronously and
    * returns a CompletableFuture holding the actual value
    * */
    AsyncLoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .refreshAfterWrite(1, TimeUnit.MINUTES) // if the entry is eligible for the refreshing, then the cache would return an old value and asynchronously reload the value
            .buildAsync(k -> DataObject.get("Data for " + k));


    public CompletableFuture<DataObject> get(String key) {
        return cache.get(key);
    }

    public CompletableFuture<Map<String, DataObject>> getAll(List<String> keys) {
        return cache.getAll(keys);
    }


}
