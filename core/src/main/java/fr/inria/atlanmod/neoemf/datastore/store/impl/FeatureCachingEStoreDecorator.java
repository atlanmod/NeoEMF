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

package fr.inria.atlanmod.neoemf.datastore.store.impl;

import fr.inria.atlanmod.neoemf.datastore.store.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.cache.FeatureCache;
import fr.inria.atlanmod.neoemf.datastore.store.impl.cache.FeatureKey;
import fr.inria.atlanmod.neoemf.datastore.store.impl.cache.MultivaluedFeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import static java.util.Objects.isNull;

/**
 * A {@link PersistentEStore} decorator that caches {@link EStructuralFeature structural features}.
 */
public class FeatureCachingEStoreDecorator extends AbstractPersistentEStoreDecorator {

    private final FeatureCache<Object> cache;

    public FeatureCachingEStoreDecorator(PersistentEStore eStore) {
        super(eStore);
        cache = new FeatureCache<>();
    }

    public FeatureCachingEStoreDecorator(PersistentEStore eStore, int cacheSize) {
        super(eStore);
        cache = new FeatureCache<>(cacheSize);
    }

    @Override
    public Object get(InternalEObject object, EStructuralFeature feature, int index) {
        FeatureKey featureKey = MultivaluedFeatureKey.from(object, feature, index);
        Object returnValue = cache.getIfPresent(featureKey);
        if (isNull(returnValue)) {
            returnValue = super.get(object, feature, index);
            cache.put(featureKey, returnValue);
        }
        return returnValue;
    }

    @Override
    public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        FeatureKey featureKey = MultivaluedFeatureKey.from(object, feature, index);
        Object returnValue = super.set(object, feature, index, value);
        cache.put(featureKey, value);
        return returnValue;
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        if (!feature.isMany()) {
            FeatureKey featureKey = FeatureKey.from(object, feature);
            cache.invalidate(featureKey);
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
        cache.put(featureKey, value);
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
        cache.put(featureKey, returnValue);
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
            cache.invalidate(featureKey.withPosition(i));
        }
    }
}
