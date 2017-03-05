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

import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A {@link Cache} implementation which either returns an already-loaded value for a given key or atomically
 * computes or retrieves it.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
@ParametersAreNonnullByDefault
class LoadingCache<K, V> extends ManualCache<K, V> {

    /**
     * Constructs a new {@code LoadingCache}.
     *
     * @param cache the internal cache implementation
     */
    protected LoadingCache(com.github.benmanes.caffeine.cache.LoadingCache<K, V> cache) {
        super(cache);
    }

    @Override
    public V get(K key) {
        checkNotNull(key);

        return ((com.github.benmanes.caffeine.cache.LoadingCache<K, V>) cache).get(key);
    }
}
