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

import static java.util.Objects.nonNull;

/**
 * A {@link PersistentStore} wrapper that caches the presence of a value.
 */
public class IsSetCachingStoreDecorator extends AbstractPersistentStoreDecorator {

    /**
     * The default cache size (10 000).
     */
    // TODO Find the more predictable maximum cache size
    private static final int DEFAULT_CACHE_SIZE = 10000;

    /**
     * In-memory cache that holds presence of a value, identified by the associated {@link FeatureKey}.
     */
    private final Cache<FeatureKey, Boolean> isSetCache;

    /**
     * Constructs a new {@code IsSetCachingStoreDecorator} with the default cache size.
     *
     * @param store the underlying store
     */
    public IsSetCachingStoreDecorator(PersistentStore store) {
        this(store, DEFAULT_CACHE_SIZE);
    }

    /**
     * Constructs a new {@code IsSetCachingStoreDecorator} with the given {@code cacheSize}.
     *
     * @param store     the underlying store
     * @param cacheSize the size of the cache
     */
    public IsSetCachingStoreDecorator(PersistentStore store, int cacheSize) {
        super(store);
        this.isSetCache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        Object soughtValue = super.get(internalObject, feature, index);
        if (nonNull(soughtValue)) {
            FeatureKey featureKey = FeatureKey.from(internalObject, feature);
            isSetCache.put(featureKey, true);
        }
        return soughtValue;
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        isSetCache.put(featureKey, true);
        return super.set(internalObject, feature, index, value);
    }

    @Override
    public boolean isSet(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        return isSetCache.get(featureKey, key -> super.isSet(internalObject, feature));
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        isSetCache.put(featureKey, false);
        super.unset(internalObject, feature);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        boolean contains = super.contains(internalObject, feature, value);
        if (contains) {
            FeatureKey featureKey = FeatureKey.from(internalObject, feature);
            isSetCache.put(featureKey, true);
        }
        return contains;
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        isSetCache.put(featureKey, true);
        super.add(internalObject, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        isSetCache.invalidate(featureKey);
        return super.remove(internalObject, feature, index);
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        isSetCache.put(featureKey, true);
        return super.move(internalObject, feature, targetIndex, sourceIndex);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        isSetCache.put(featureKey, false);
        super.clear(internalObject, feature);
    }
}
