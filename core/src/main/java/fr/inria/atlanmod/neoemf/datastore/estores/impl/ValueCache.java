/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.Objects;

/**
 * A cache for feature values.
 *
 * @param <V> the type of cached values
 */
public class ValueCache<V> {

    private static final int DEFAULT_CACHE_SIZE = 10000;

    private final Cache<CacheKey, V> cache;

    public ValueCache() {
        this(DEFAULT_CACHE_SIZE);
    }

    public ValueCache(int cacheSize) {
        this.cache = CacheBuilder.newBuilder().maximumSize(cacheSize).build();
    }

    protected V getValueFromCache(InternalEObject object, EStructuralFeature feature) {
        return cache.getIfPresent(new CacheKey(object, feature));
    }

    protected V getValueFromCache(InternalEObject object, EStructuralFeature feature, int index) {
        return cache.getIfPresent(new CacheKey(object, feature, index));
    }

    protected void cacheValue(InternalEObject object, EStructuralFeature feature, V value) {
        cache.put(new CacheKey(object, feature), value);
    }

    protected void cacheValue(InternalEObject object, EStructuralFeature feature, int index, V value) {
        cache.put(new CacheKey(object, feature, index), value);
    }

    protected void invalidateValue(InternalEObject object, EStructuralFeature feature) {
        cache.invalidate(new CacheKey(object, feature));
    }

    protected void invalidateValue(InternalEObject object, EStructuralFeature feature, int index) {
        cache.invalidate(new CacheKey(object, feature, index));
    }

    private static class CacheKey {
        private final InternalEObject object;
        private final EStructuralFeature feature;
        private final int index;

        private CacheKey(InternalEObject object, EStructuralFeature feature) {
            this(object, feature, InternalEObject.EStore.NO_INDEX);
        }

        private CacheKey(InternalEObject object, EStructuralFeature feature, int index) {
            this.object = object;
            this.feature = feature;
            this.index = index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(feature, index, object);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            @SuppressWarnings("unchecked")
            CacheKey other = (CacheKey) obj;
            return Objects.equals(object, other.object)
                    && Objects.equals(feature, other.feature)
                    && (index == other.index
                        || index == InternalEObject.EStore.NO_INDEX
                        || other.index == InternalEObject.EStore.NO_INDEX);
        }
    }
}
