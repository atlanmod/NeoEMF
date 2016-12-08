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

import fr.inria.atlanmod.neoemf.datastore.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.datastore.structure.MultivaluedFeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import static java.util.Objects.isNull;

/**
 * A {@link PersistentStore} decorator that caches {@link EStructuralFeature}.
 */
public class FeatureCachingStoreDecorator extends AbstractPersistentStoreDecorator {

    private final Cache<FeatureKey, Object> objectsCache;

    public FeatureCachingStoreDecorator(PersistentStore eStore) {
        this(eStore, 10000);
    }

    public FeatureCachingStoreDecorator(PersistentStore eStore, int cacheSize) {
        super(eStore);
        this.objectsCache = Caffeine.newBuilder().maximumSize(cacheSize).build();
    }

    @Override
    public Object get(InternalEObject object, EStructuralFeature feature, int index) {
        FeatureKey featureKey = MultivaluedFeatureKey.from(object, feature, index);
        Object returnValue = objectsCache.getIfPresent(featureKey);
        if (isNull(returnValue)) {
            returnValue = super.get(object, feature, index);
            objectsCache.put(featureKey, returnValue);
        }
        return returnValue;
    }

    @Override
    public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = MultivaluedFeatureKey.from(object, feature, index);
        Object returnValue = super.set(object, feature, index, value);
        objectsCache.put(featureKey, value);
        return returnValue;
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        if (!feature.isMany()) {
            FeatureKey featureKey = FeatureKey.from(object, feature);
            objectsCache.invalidate(featureKey);
        }
        else {
            invalidateValues(object, feature, 0);
        }
        super.unset(object, feature);
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = MultivaluedFeatureKey.from(object, feature, index);
        super.add(object, feature, index, value);
        objectsCache.put(featureKey, value);
        invalidateValues(object, feature, index + 1);
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
        Object returnValue = super.remove(object, feature, index);
        invalidateValues(object, feature, index);
        return returnValue;
    }

    @Override
    public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        FeatureKey featureKey = MultivaluedFeatureKey.from(object, feature, targetIndex);
        Object returnValue = super.move(object, feature, targetIndex, sourceIndex);
        invalidateValues(object, feature, Math.min(sourceIndex, targetIndex));
        objectsCache.put(featureKey, returnValue);
        return returnValue;
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        super.clear(object, feature);
        invalidateValues(object, feature, 0);
    }

    /**
     * Remove cached elements, from a initial position to the size of an element.
     */
    private void invalidateValues(InternalEObject object, EStructuralFeature feature, int startIndex) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        for (int i = startIndex; i < size(object, feature); i++) {
            objectsCache.invalidate(featureKey.withPosition(i));
        }
    }
}
