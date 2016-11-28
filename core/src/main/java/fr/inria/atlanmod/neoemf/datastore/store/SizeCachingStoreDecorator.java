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

package fr.inria.atlanmod.neoemf.datastore.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.cache.FeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link PersistentStore} decorator that caches the size data.
 */
public class SizeCachingStoreDecorator extends AbstractPersistentStoreDecorator {

    private final Cache<FeatureKey, Integer> sizesCache;

    public SizeCachingStoreDecorator(PersistentStore eStore) {
        this(eStore, 10000);
    }

    public SizeCachingStoreDecorator(PersistentStore eStore, int cacheSize) {
        super(eStore);
        this.sizesCache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        sizesCache.put(featureKey, 0);
        super.unset(object, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        Integer size = sizesCache.getIfPresent(featureKey);
        return isNull(size) ? super.isEmpty(object, feature) : (size == 0);
    }

    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        Integer size = sizesCache.getIfPresent(featureKey);
        if (isNull(size)) {
            size = super.size(object, feature);
            sizesCache.put(featureKey, size);
        }
        return size;
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        Integer size = sizesCache.getIfPresent(featureKey);
        if (nonNull(size)) {
            sizesCache.put(featureKey, size + 1);
        }
        super.add(object, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        Integer size = sizesCache.getIfPresent(featureKey);
        if (nonNull(size)) {
            sizesCache.put(featureKey, size - 1);
        }
        return super.remove(object, feature, index);
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        sizesCache.put(featureKey, 0);
        super.clear(object, feature);
    }
}
