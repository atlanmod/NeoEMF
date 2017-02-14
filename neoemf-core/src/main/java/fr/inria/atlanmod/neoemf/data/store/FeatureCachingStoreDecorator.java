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
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * A {@link PersistentStore} wrapper that caches {@link EStructuralFeature}.
 */
public class FeatureCachingStoreDecorator extends AbstractPersistentStoreDecorator {

    /**
     * The default cache size (10 000).
     */
    // TODO Find the more predictable maximum cache size
    private static final int DEFAULT_CACHE_SIZE = 10000;

    /**
     * In-memory cache that holds loaded features, identified by their {@link FeatureKey}.
     */
    private final Cache<FeatureKey, Object> objectsCache;

    /**
     * Constructs a new {@code FeatureCachingStoreDecorator} with the default cache size.
     *
     * @param store the underlying store
     */
    public FeatureCachingStoreDecorator(PersistentStore store) {
        this(store, DEFAULT_CACHE_SIZE);
    }

    /**
     * Constructs a new {@code FeatureCachingStoreDecorator} with the given {@code cacheSize}.
     *
     * @param store     the underlying store
     * @param cacheSize the size of the cache
     */
    public FeatureCachingStoreDecorator(PersistentStore store, int cacheSize) {
        super(store);
        this.objectsCache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        FeatureKey featureKey = MultiFeatureKey.from(internalObject, feature, index);
        return objectsCache.get(featureKey, key -> super.get(internalObject, feature, index));
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = MultiFeatureKey.from(internalObject, feature, index);
        Object old = super.set(internalObject, feature, index, value);
        objectsCache.put(featureKey, value);
        return old;
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        if (!feature.isMany()) {
            FeatureKey featureKey = FeatureKey.from(internalObject, feature);
            objectsCache.invalidate(featureKey);
        }
        else {
            invalidateValues(internalObject, feature, 0);
        }
        super.unset(internalObject, feature);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = MultiFeatureKey.from(internalObject, feature, index);
        super.add(internalObject, feature, index, value);
        objectsCache.put(featureKey, value);
        invalidateValues(internalObject, feature, index + 1);
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        Object old = super.remove(internalObject, feature, index);
        invalidateValues(internalObject, feature, index);
        return old;
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        FeatureKey featureKey = MultiFeatureKey.from(internalObject, feature, targetIndex);
        Object old = super.move(internalObject, feature, targetIndex, sourceIndex);
        invalidateValues(internalObject, feature, Math.min(sourceIndex, targetIndex));
        objectsCache.put(featureKey, old);
        return old;
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        super.clear(internalObject, feature);
        invalidateValues(internalObject, feature, 0);
    }

    /**
     * Remove cached elements, from an initial {@code index} to the size of an element.
     *
     * @param internalObject the concerned object
     * @param feature        the feature of the {@code internalObject}
     * @param index          the index from which to start the removing
     *
     * @see FeatureKey#from(InternalEObject, EStructuralFeature)
     */
    private void invalidateValues(InternalEObject internalObject, EStructuralFeature feature, int index) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        for (int i = index; i < size(internalObject, feature); i++) {
            objectsCache.invalidate(featureKey.withPosition(i));
        }
    }
}
