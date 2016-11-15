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

package fr.inria.atlanmod.neoemf.datastore.store.impl.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.Id;

import java.util.function.Function;

/**
 * A cache for feature values.
 *
 * @param <V> the type of cached values
 */
// TODO: Create an NeoCache to wrap the embed cache, and an IdCache
public class FeatureCache<V> {

    private static final int DEFAULT_CACHE_SIZE = 10000;

    private final Cache<FeatureKey, V> cache;

    public FeatureCache() {
        this(DEFAULT_CACHE_SIZE);
    }

    public FeatureCache(int cacheSize) {
        this.cache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    public V get(Id id, String name, Function<FeatureKey, V> function) {
        return cache.get(new FeatureKey(id, name), function);
    }

    public V get(Id id, String name, int index, Function<FeatureKey, V> function) {
        return cache.get(new MultivaluedFeatureKey(id, name, index), function);
    }

    public V getIfPresent(Id id, String name) {
        return cache.getIfPresent(new FeatureKey(id, name));
    }

    public V getIfPresent(Id id, String name, int index) {
        return cache.getIfPresent(new MultivaluedFeatureKey(id, name, index));
    }

    public void put(Id object, String name, V value) {
        cache.put(new FeatureKey(object, name), value);
    }

    public void put(Id object, String name, int index, V value) {
        cache.put(new MultivaluedFeatureKey(object, name, index), value);
    }

    public void invalidate(Id object, String name) {
        cache.invalidate(new FeatureKey(object, name));
    }

    public void invalidate(Id object, String name, int index) {
        cache.invalidate(new MultivaluedFeatureKey(object, name, index));
    }
}
