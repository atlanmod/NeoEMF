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

import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.Objects;

/**
 * A {@link SearcheableResourceEStore} wrapper able to cache values.
 *
 * @param <T> the type of cached values
 */
abstract class AbstractCachingDelegatedResourceEStoreImpl<T> extends DelegatedResourceEStoreImpl {

    private static final int DEFAULT_SIZE_CACHE_SIZE = 10000;

    protected final Cache<CacheKey, T> cache;

    public AbstractCachingDelegatedResourceEStoreImpl(SearcheableResourceEStore eStore) {
        this(eStore, DEFAULT_SIZE_CACHE_SIZE);
    }

    public AbstractCachingDelegatedResourceEStoreImpl(SearcheableResourceEStore eStore, int cacheSize) {
        super(eStore);
        this.cache = CacheBuilder.newBuilder().maximumSize(cacheSize).build();
    }

    protected T getValueFromCache(InternalEObject object, EStructuralFeature feature) {
        return cache.getIfPresent(new CacheKey(object, feature));
    }

    protected T getValueFromCache(InternalEObject object, EStructuralFeature feature, int index) {
        return cache.getIfPresent(new CacheKey(object, feature, index));
    }

    protected void cacheValue(InternalEObject object, EStructuralFeature feature, T value) {
        cache.put(new CacheKey(object, feature), value);
    }

    protected void cacheValue(InternalEObject object, EStructuralFeature feature, int index, T value) {
        cache.put(new CacheKey(object, feature, index), value);
    }

    protected void invalidateValue(InternalEObject object, EStructuralFeature feature) {
        cache.invalidate(new CacheKey(object, feature));
    }

    protected void invalidateValue(InternalEObject object, EStructuralFeature feature, int index) {
        cache.invalidate(new CacheKey(object, feature, index));
    }

    private class CacheKey {
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
            return Objects.hash(getOuterType(), feature, index, object);
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
            return Objects.equals(getOuterType(), other.getOuterType())
                    && Objects.equals(object, other.object)
                    && Objects.equals(feature, other.feature)
                    && (index == other.index || index == InternalEObject.EStore.NO_INDEX);
        }

        private AbstractCachingDelegatedResourceEStoreImpl<?> getOuterType() {
            return AbstractCachingDelegatedResourceEStoreImpl.this;
        }
    }
}
