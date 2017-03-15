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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;

/**
 * A semi-persistent mapping from keys to values. Cache entries are manually added using {@link #get(Object, Function)}
 * or {@link #put(Object, Object)}, and are stored in the cache until either evicted or manually invalidated.
 * <p>
 * Implementations of this interface are expected to be thread-safe, and can be safely accessed by multiple concurrent
 * threads.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
@ThreadSafe
@ParametersAreNonnullByDefault
public interface Cache<K, V> {

    /**
     * Returns the value associated with the {@code key} in this cache, or {@code null} if there is no cached value for
     * the {@code key}.
     *
     * @param key the key whose associated value is to be returned
     *
     * @return the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the
     * key
     */
    @Nullable
    V get(K key);

    /**
     * Returns the value associated with the {@code key} in this cache, obtaining that value from the {@code
     * mappingFunction} if necessary.
     * <p>
     * If the specified {@code key} is not already associated with a value, attempts to compute its value using the
     * given {@code mappingFunction} and enters it into this cache unless {@code null}. The entire method invocation is
     * performed atomically, so the function is applied at most once per key.
     *
     * @param key             the key with which the specified value is to be associated
     * @param mappingFunction the function to compute a value
     *
     * @return the current (existing or computed) value associated with the specified key, or {@code null} if the
     * computed value is {@code null}
     *
     * @throws IllegalStateException if the computation detectably attempts a recursive update to this cache that would
     *                               otherwise never complete
     * @throws RuntimeException      if the mappingFunction does so, in which case the mapping is left unestablished
     */
    V get(K key, Function<? super K, ? extends V> mappingFunction);

    /**
     * Returns a map of the values associated with the {@code keys} in this cache. The returned map will only contain
     * entries which are already present in the cache.
     *
     * @param keys the keys whose associated values are to be returned
     *
     * @return the unmodifiable mapping of keys to values for the specified keys found in this cache
     */
    @Nonnull
    Map<K, V> getAll(Iterable<? extends K> keys);

    /**
     * Associates the {@code value} with the {@code key} in this cache. If the cache previously contained a value
     * associated with the {@code key}, the old value is replaced by the new {@code value}.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    void put(K key, V value);

    /**
     * Copies all of the mappings from the specified map to the cache. The effect of this call is equivalent to that of
     * calling {@link #put(Object, Object)} on this map once for each mapping from key {@code k} to value {@code v} in
     * the specified map. The behavior of this operation is undefined if the specified map is modified while the
     * operation is in progress.
     *
     * @param map the mappings to be stored in this cache
     */
    void putAll(Map<? extends K, ? extends V> map);

    /**
     * Discards any cached value for the {@code key}.
     *
     * @param key the key whose mapping is to be removed from the cache
     */
    void invalidate(K key);

    /**
     * Discards any cached values for the {@code keys}.
     *
     * @param keys the keys whose associated values are to be removed
     */
    void invalidateAll(Iterable<? extends K> keys);

    /**
     * Discards all entries in the cache.
     */
    void invalidateAll();

    /**
     * Returns the approximate number of entries in this cache.
     *
     * @return the estimated number of mappings
     */
    @Nonnegative
    long size();

    /**
     * Loads a new value for the {@code key}, asynchronously. While the new value is loading the previous value (if any)
     * will continue to be returned by {@code get(key)} unless it is evicted. If the new value is loaded successfully it
     * will replace the previous value in the cache; if an exception is thrown while refreshing the previous value will
     * remain.
     *
     * @param key key with which a value may be associated
     */
    void refresh(K key);

    /**
     * Performs any pending maintenance operations needed by the cache.
     */
    void cleanUp();

    /**
     * Returns a view of the entries stored in this cache as a thread-safe map. Modifications made to the map directly
     * affect the cache.
     * <p>
     * Iterators from the returned map are at least <i>weakly consistent</i>: they are safe for concurrent use, but if
     * the cache is modified (including by eviction) after the iterator is created, it is undefined which of the changes
     * (if any) will be reflected in that iterator.
     *
     * @return a thread-safe view of this cache supporting all of the optional {@link Map} operations
     */
    @Nonnull
    ConcurrentMap<K, V> asMap();

    /**
     * Returns a current snapshot of this cache's cumulative statistics. All statistics are initialized to zero, and are
     * monotonically increasing over the lifetime of the cache.
     * <p>
     * Due to the performance penalty of maintaining statistics, some implementations may not record the usage history
     * immediately or at all.
     * <p>
     * <b>Warning:</b> this cache may not be recording statistical data. For example, a cache created using CacheBuilder
     * only does so if the {@link CaffeineCacheBuilder#recordStats()} method was called. If statistics are not being
     * recorded, a {@link CacheStats} instance with zero for all values is returned.
     *
     * @return the current snapshot of the statistics of this cache
     */
    @Nonnull
    CacheStats stats();
}
