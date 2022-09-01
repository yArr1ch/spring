package com.dataart.intern.logista;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class CacheMap<K, V> extends HashMap<K, V> {

    private final long timeout;

    public CacheMap(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public V put(K key, V value) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> remove(key);
        executorService.schedule(task, timeout, TimeUnit.SECONDS);
        return super.put(key, value);
    }
}
