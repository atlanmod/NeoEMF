/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.util.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A Caffeine {@link Cache} implementation which does not automatically load values when keys are requested.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
@ParametersAreNonnullByDefault
class CaffeineManualCache<K, V> implements Cache<K, V> {

    /**
     * The internal cache implementation.
     */
    protected final com.github.benmanes.caffeine.cache.Cache<K, V> cache;

    /**
     * Constructs a new {@code CaffeineManualCache}.
     *
     * @param cache the internal cache implementation
     */
    protected CaffeineManualCache(com.github.benmanes.caffeine.cache.Cache<K, V> cache) {
        this.cache = cache;
    }

    @Nullable
    @Override
    public V get(K key) {
        checkNotNull(key);

        return cache.getIfPresent(key);
    }

    @Override
    public V get(K key, Function<? super K, ? extends V> mappingFunction) {
        checkNotNull(key);
        checkNotNull(mappingFunction);

        return cache.get(key, mappingFunction);
    }

    @Nonnull
    @Override
    public Map<K, V> getAll(Iterable<? extends K> keys) {
        checkNotNull(keys);

        return cache.getAllPresent(keys);
    }

    @Override
    public void put(K key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        cache.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        checkNotNull(map);

        cache.putAll(map);
    }

    @Override
    public void invalidate(K key) {
        checkNotNull(key);

        cache.invalidate(key);
    }

    @Override
    public void invalidateAll(Iterable<? extends K> keys) {
        checkNotNull(keys);

        cache.invalidateAll(keys);
    }

    @Override
    public void invalidateAll() {
        cache.invalidateAll();
    }

    @Override
    public long size() {
        return cache.estimatedSize();
    }

    @Override
    public void refresh(K key) {
        // Do nothing
    }

    @Override
    public void cleanUp() {
        cache.cleanUp();
    }

    @Nonnull
    @Override
    public ConcurrentMap<K, V> asMap() {
        return cache.asMap();
    }

    @Nonnull
    @Override
    public CacheStats stats() {
        com.github.benmanes.caffeine.cache.stats.CacheStats stats = cache.stats();

        return new CacheStats(
                stats.hitCount(),
                stats.missCount(),
                stats.loadSuccessCount(),
                stats.loadFailureCount(),
                stats.totalLoadTime(),
                stats.evictionCount());
    }
}
