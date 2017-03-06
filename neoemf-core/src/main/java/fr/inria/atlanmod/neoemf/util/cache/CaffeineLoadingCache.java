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

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A Caffeine {@link Cache} implementation which either returns an already-loaded value for a given key or atomically
 * computes or retrieves it.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
@ParametersAreNonnullByDefault
class CaffeineLoadingCache<K, V> extends CaffeineManualCache<K, V> {

    /**
     * Constructs a new {@code CaffeineLoadingCache}.
     *
     * @param cache the internal cache implementation
     */
    protected CaffeineLoadingCache(com.github.benmanes.caffeine.cache.LoadingCache<K, V> cache) {
        super(cache);
    }

    @Override
    public V get(K key) {
        checkNotNull(key);

        return ((com.github.benmanes.caffeine.cache.LoadingCache<K, V>) cache).get(key);
    }

    @Nonnull
    @Override
    public Map<K, V> getAll(Iterable<? extends K> keys) {
        checkNotNull(keys);

        return ((com.github.benmanes.caffeine.cache.LoadingCache<K, V>) cache).getAll(keys);
    }

    @Override
    public void refresh(K key) {
        checkNotNull(key);

        ((com.github.benmanes.caffeine.cache.LoadingCache<K, V>) cache).refresh(key);
    }
}
