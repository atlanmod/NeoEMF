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

import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.Optional;

/**
 * A {@link PersistentStore} wrapper that caches the size data.
 */
public class SizeCachingStoreDecorator extends AbstractPersistentStoreDecorator {

    /**
     * In-memory cache that holds recently processed sizes, identified by the associated {@link FeatureKey}.
     */
    private final Cache<FeatureKey, Integer> sizesCache;

    /**
     * Constructs a new {@code SizeCachingStoreDecorator} with the default cache size.
     *
     * @param store the underlying store
     */
    public SizeCachingStoreDecorator(PersistentStore store) {
        this(store, 10_000);
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
        FeatureKey key = FeatureKey.from(internalObject, feature);
        sizesCache.put(key, 0);
        super.unset(internalObject, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return Optional.ofNullable(sizesCache.getIfPresent(key))
                .map(v -> v == 0)
                .orElseGet(() -> super.isEmpty(internalObject, feature));
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        return sizesCache.get(key, k -> super.size(internalObject, feature));
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        Optional.ofNullable(sizesCache.getIfPresent(key))
                .ifPresent(s -> sizesCache.put(key, s + 1));

        super.add(internalObject, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        Optional.ofNullable(sizesCache.getIfPresent(key))
                .ifPresent(s -> sizesCache.put(key, s - 1));

        return super.remove(internalObject, feature, index);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey key = FeatureKey.from(internalObject, feature);
        sizesCache.put(key, 0);
        super.clear(internalObject, feature);
    }
}
