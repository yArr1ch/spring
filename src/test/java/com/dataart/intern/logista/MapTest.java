package com.dataart.intern.logista;

import org.junit.Assert;
import org.junit.Test;

public class MapTest {

    @Test
    public void checkDelay() throws InterruptedException {
        CacheMap<Integer, String> map = new CacheMap<>(7);
        map.put(1, "first");
        Thread.sleep(4000);
        map.put(2, "second");
        Thread.sleep(2000);
        Assert.assertTrue(map.containsKey(1));
        Thread.sleep(2000);
        Assert.assertNull(map.get(1));
        Assert.assertTrue(map.containsKey(2));
    }
}
