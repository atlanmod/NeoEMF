/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class NeoCache<K, V> {

    private static final int DEFAULT_CACHE_SIZE = 10000;

    private final Cache<K, V> cache;

    public NeoCache() {
        this(DEFAULT_CACHE_SIZE);
    }

    public NeoCache(long cacheSize) {
        this.cache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    public V getIfPresent(K key) {
        return cache.getIfPresent(key);
    }

    public V get(K key, Function<? super K, ? extends V> mappingFunction) {
        return cache.get(key, mappingFunction);
    }

    public Map<K, V> getAllPresent(Iterable<K> keys) {
        return cache.getAllPresent(keys);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        cache.putAll(map);
    }

    public void invalidate(K key) {
        cache.invalidate(key);
    }

    public void invalidateAll(Iterable<K> keys) {
        cache.invalidateAll(keys);
    }

    public void invalidateAll() {
        cache.invalidateAll();
    }

    public long estimatedSize() {
        return cache.estimatedSize();
    }

    public ConcurrentMap<K, V> asMap() {
        return cache.asMap();
    }

    public void cleanUp() {
        cache.cleanUp();
    }
}
