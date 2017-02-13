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

package fr.inria.atlanmod.neoemf.data.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link PersistentStore} wrapper that caches the size data.
 */
public class SizeCachingStoreDecorator extends AbstractPersistentStoreDecorator {

    /**
     * The default cache size (10 000).
     */
    // TODO Find the more predictable maximum cache size
    private static final int DEFAULT_CACHE_SIZE = 10000;

    /**
     * In-memory cache that holds recently processed sizes, identified by the associated {@link SingleFeatureKey}.
     */
    private final Cache<SingleFeatureKey, Integer> sizesCache;

    /**
     * Constructs a new {@code SizeCachingStoreDecorator} with the default cache size.
     *
     * @param store the underlying store
     */
    public SizeCachingStoreDecorator(PersistentStore store) {
        this(store, DEFAULT_CACHE_SIZE);
    }

    /**
     * Constructs a new {@code SizeCachingStoreDecorator} with the given {@code cacheSize}.
     *
     * @param store     the underlying store
     * @param cacheSize the size of the cache
     */
    public SizeCachingStoreDecorator(PersistentStore store, int cacheSize) {
        super(store);
        this.sizesCache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        SingleFeatureKey featureKey = SingleFeatureKey.from(internalObject, feature);
        sizesCache.put(featureKey, 0);
        super.unset(internalObject, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        SingleFeatureKey featureKey = SingleFeatureKey.from(internalObject, feature);
        Integer size = sizesCache.getIfPresent(featureKey);
        return isNull(size) ? super.isEmpty(internalObject, feature) : (size == 0);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        SingleFeatureKey featureKey = SingleFeatureKey.from(internalObject, feature);
        return sizesCache.get(featureKey, key -> super.size(internalObject, feature));
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        SingleFeatureKey featureKey = SingleFeatureKey.from(internalObject, feature);
        Integer size = sizesCache.getIfPresent(featureKey);
        if (nonNull(size)) {
            sizesCache.put(featureKey, size + 1);
        }
        super.add(internalObject, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        SingleFeatureKey featureKey = SingleFeatureKey.from(internalObject, feature);
        Integer size = sizesCache.getIfPresent(featureKey);
        if (nonNull(size)) {
            sizesCache.put(featureKey, size - 1);
        }
        return super.remove(internalObject, feature, index);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        SingleFeatureKey featureKey = SingleFeatureKey.from(internalObject, feature);
        sizesCache.put(featureKey, 0);
        super.clear(internalObject, feature);
    }
}
