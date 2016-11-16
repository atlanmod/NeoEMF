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

import java.util.function.Function;

/**
 * A cache for feature values.
 *
 * @param <V> the type of cached values
 */
// TODO Create an NeoCache to wrap the embed cache, and an IdCache
public class FeatureCache<V> {

    private static final int DEFAULT_CACHE_SIZE = 10000;

    private final Cache<FeatureKey, V> cache;

    public FeatureCache() {
        this(DEFAULT_CACHE_SIZE);
    }

    public FeatureCache(int cacheSize) {
        this.cache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    public V get(FeatureKey featureKey, Function<? super FeatureKey, ? extends V> mappingFunction) {
        return cache.get(featureKey, mappingFunction);
    }

    public V getIfPresent(FeatureKey featureKey) {
        return cache.getIfPresent(featureKey);
    }

    public void put(FeatureKey featureKey, V value) {
        cache.put(featureKey, value);
    }

    public void invalidate(FeatureKey featureKey) {
        cache.invalidate(featureKey);
    }
}
