package com.yazid.demo.java.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yazid
 * @date 2020/03/18 0018 12:55
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int CACHE_SIZE;

    public LRUCache(int cacheSize) {
        //accessOrder参数为true表示让LinkedHashMap按照访问顺序来进行排序，最近访问放在尾部，最老最少访问放在头部。
        super((int) (Math.ceil(cacheSize / 0.75) + 1), 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        //当map中数据量大于指定容量时，自动删除最老最少访问数据。
        return size() > CACHE_SIZE;
    }
}
