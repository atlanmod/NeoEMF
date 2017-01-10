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

package fr.inria.atlanmod.neoemf.data.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.function.Function;

import static java.util.Objects.isNull;

/**
 * A {@link PersistentStore} decorator that caches {@link EStructuralFeature}.
 */
public class FeatureCachingStoreDecorator extends AbstractPersistentStoreDecorator {

    private final Cache<FeatureKey, Object> objectsCache;

    public FeatureCachingStoreDecorator(PersistentStore store) {
        this(store, 10000);
    }

    public FeatureCachingStoreDecorator(PersistentStore store, int cacheSize) {
        super(store);
        this.objectsCache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    @Override
    public Object get(InternalEObject internalObject, EStructuralFeature feature, int index) {
        FeatureKey featureKey = MultivaluedFeatureKey.from(internalObject, feature, index);
        return objectsCache.get(featureKey, key -> super.get(internalObject, feature, index));
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = MultivaluedFeatureKey.from(internalObject, feature, index);
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
        FeatureKey featureKey = MultivaluedFeatureKey.from(internalObject, feature, index);
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
        FeatureKey featureKey = MultivaluedFeatureKey.from(internalObject, feature, targetIndex);
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
     * Remove cached elements, from a initial position to the size of an element.
     */
    private void invalidateValues(InternalEObject internalObject, EStructuralFeature feature, int startIndex) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        for (int i = startIndex; i < size(internalObject, feature); i++) {
            objectsCache.invalidate(featureKey.withPosition(i));
        }
    }
}
