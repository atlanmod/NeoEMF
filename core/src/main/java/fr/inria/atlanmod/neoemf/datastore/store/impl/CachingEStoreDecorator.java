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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import static java.util.Objects.isNull;

/**
 * A {@link PersistentEStore} decorator that caches the size data.
 */
public class CachingEStoreDecorator extends AbstractPersistentEStoreDecorator {

    private final ValueCache<Integer> cache;

    public CachingEStoreDecorator(PersistentEStore eStore) {
        super(eStore);
        cache = new ValueCache<>();
    }

    public CachingEStoreDecorator(PersistentEStore eStore, int cacheSize) {
        super(eStore);
        cache = new ValueCache<>(cacheSize);
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        cache.put(object, feature, 0);
        super.unset(object, feature);
    }

    @Override
    public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
        Integer size = cache.get(object, feature);
        return isNull(size) ? super.isEmpty(object, feature) : (size == 0);
    }

    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
        Integer size = cache.get(object, feature);
        if (isNull(size)) {
            size = super.size(object, feature);
            cache.put(object, feature, size);
        }
        return size;
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        Integer size = cache.get(object, feature);
        if (!isNull(size)) {
            cache.put(object, feature, size + 1);
        }
        super.add(object, feature, index, value);
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
        Integer size = cache.get(object, feature);
        if (!isNull(size)) {
            cache.put(object, feature, size - 1);
        }
        return super.remove(object, feature, index);
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        cache.put(object, feature, 0);
        super.clear(object, feature);
    }
}
