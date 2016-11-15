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

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentEStore;
import fr.inria.atlanmod.neoemf.datastore.store.impl.cache.FeatureCache;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import static java.util.Objects.isNull;

/**
 * A {@link PersistentEStore} wrapper that caches the presence of a value.
 */
public class IsSetCachingEStoreDecorator extends AbstractPersistentEStoreDecorator {

    private final FeatureCache<Boolean> cache;

    public IsSetCachingEStoreDecorator(PersistentEStore eStore) {
        super(eStore);
        cache = new FeatureCache<>();
    }

    public IsSetCachingEStoreDecorator(PersistentEStore eStore, int cacheSize) {
        super(eStore);
        cache = new FeatureCache<>(cacheSize);
    }

    @Override
    public Object get(InternalEObject object, EStructuralFeature feature, int index) {
        Object returnValue = super.get(object, feature, index);
        if (!isNull(returnValue)) {
            cache.put(PersistentEObject.from(object).id(), feature.getName(), true);
        }
        return returnValue;
    }

    @Override
    public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        cache.put(PersistentEObject.from(object).id(), feature.getName(), true);
        return super.set(object, feature, index, value);
    }

    @Override
    public boolean isSet(InternalEObject object, EStructuralFeature feature) {
        Boolean isSet = cache.getIfPresent(PersistentEObject.from(object).id(), feature.getName());
        return isNull(isSet) ? super.isSet(object, feature) : isSet;
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        cache.put(PersistentEObject.from(object).id(), feature.getName(), false);
        super.unset(object, feature);
    }

    @Override
    public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
        boolean returnValue = super.contains(object, feature, value);
        if (returnValue) {
            cache.put(PersistentEObject.from(object).id(), feature.getName(), true);
        }
        return returnValue;
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        cache.put(PersistentEObject.from(object).id(), feature.getName(), true);
        super.add(object, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
        cache.invalidate(PersistentEObject.from(object).id(), feature.getName());
        return super.remove(object, feature, index);
    }

    @Override
    public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        cache.put(PersistentEObject.from(object).id(), feature.getName(), true);
        return super.move(object, feature, targetIndex, sourceIndex);
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        cache.put(PersistentEObject.from(object).id(), feature.getName(), false);
        super.clear(object, feature);
    }
}
