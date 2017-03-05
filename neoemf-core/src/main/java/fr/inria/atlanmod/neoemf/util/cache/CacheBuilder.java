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

import com.github.benmanes.caffeine.cache.Caffeine;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.function.Function;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A builder of {@link Cache} instances.
 *
 * @param <K> the base key type for {@link Cache}s created by this builder
 * @param <V> the base value type for {@link Cache} created by this builder
 */
@ParametersAreNonnullByDefault
public final class CacheBuilder<K, V> {

    /**
     * The internal cache builder implementation.
     */
    private final Caffeine<Object, Object> builder;

    /**
     * Constructs a new {@code CacheBuilder}.
     */
    private CacheBuilder() {
        builder = Caffeine.newBuilder();
    }

    /**
     * Creates a new {@code CacheBuilder} with default settings, including strong keys, strong values, and no automatic
     * eviction of any kind.
     *
     * @return a new builder
     */
    @Nonnull
    public static CacheBuilder<Object, Object> newBuilder() {
        return new CacheBuilder<>();
    }

    /**
     * Sets the minimum total size for the internal data structures. Providing a large enough estimate at construction
     * time avoids the need for expensive resizing operations later, but setting this value unnecessarily high wastes
     * memory.
     *
     * @param initialCapacity minimum total size for the internal data structures
     *
     * @return this builder (for chaining)
     *
     * @throws IllegalArgumentException if {@code initialCapacity} is negative
     * @throws IllegalStateException    if an initial capacity was already set
     */
    @Nonnull
    public CacheBuilder<K, V> initialCapacity(@Nonnegative int initialCapacity) {
        checkArgument(initialCapacity >= 0);

        builder.initialCapacity(initialCapacity);
        return this;
    }

    /**
     * Specifies the maximum number of entries the cache may contain. Note that the cache <b>may evict an entry before
     * this limit is exceeded or temporarily exceed the threshold while evicting</b>. As the cache size grows close to
     * the maximum, the cache evicts entries that are less likely to be used again. For example, the cache may evict an
     * entry because it hasn't been used recently or very often.
     * <p>
     * When {@code size} is zero, elements will be evicted immediately after being loaded into the cache. This can be
     * useful in testing, or to disable caching temporarily without a code change.
     *
     * @param maximumSize the maximum size of the cache
     *
     * @return this builder (for chaining)
     *
     * @throws IllegalArgumentException if {@code size} is negative
     * @throws IllegalStateException    if a maximum size or weight was already set
     */
    @Nonnull
    public CacheBuilder<K, V> maximumSize(@Nonnegative long maximumSize) {
        checkArgument(maximumSize >= 0);

        builder.maximumSize(maximumSize);
        return this;
    }

    /**
     * Specifies that each key (not value) stored in the cache should be wrapped in a {@link WeakReference} (by default,
     * strong references are used).
     * <p>
     * <b>Warning:</b> when this method is used, the resulting cache will use identity ({@code ==}) comparison to
     * determine equality of keys.
     *
     * @return this builder (for chaining)
     *
     * @throws IllegalStateException if the key strength was already set or the writer was set
     */
    @Nonnull
    public CacheBuilder<K, V> weakKeys() {
        builder.weakKeys();
        return this;
    }

    /**
     * Specifies that each value (not key) stored in the cache should be wrapped in a {@link SoftReference} (by default,
     * strong references are used). Softly-referenced objects will be garbage-collected in a <i>globally</i>
     * least-recently-used manner, in response to memory demand.
     * <p>
     * <b>Warning:</b> when this method is used, the resulting cache will use identity ({@code ==}) comparison to
     * determine equality of values.
     *
     * @return this builder (for chaining)
     *
     * @throws IllegalStateException if the value strength was already set
     */
    @Nonnull
    public CacheBuilder<K, V> softValues() {
        builder.softValues();
        return this;
    }

    /**
     * Builds a {@link Cache} which does not automatically load values when keys are requested.
     * <p>
     * Consider {@link #build(Function)} instead, if it is feasible to implement a {@code CacheLoader}.
     *
     * @param <K1> the key type of the cache
     * @param <V1> the value type of the cache
     *
     * @return a new cache
     */
    @Nonnull
    public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
        return new ManualCache<>(builder.build());
    }

    /**
     * Builds a {@link Cache}, which either returns an already-loaded value for a given key or atomically computes or
     * retrieves it using the supplied {@link Function}. If another thread is currently loading the value for this key,
     * simply waits for that thread to finish and returns its loaded value. Note that multiple threads can concurrently
     * load values for distinct keys.
     *
     * @param mappingFunction the function used to obtain new values
     * @param <K1>            the key type of the loader
     * @param <V1>            the value type of the loader
     *
     * @return a new cache
     *
     * @throws NullPointerException if the specified cache loader is null
     */
    @Nonnull
    public <K1 extends K, V1 extends V> Cache<K1, V1> build(Function<? super K1, ? extends V1> mappingFunction) {
        checkNotNull(mappingFunction);

        return new LoadingCache<>(builder.build(mappingFunction::apply));
    }
}
