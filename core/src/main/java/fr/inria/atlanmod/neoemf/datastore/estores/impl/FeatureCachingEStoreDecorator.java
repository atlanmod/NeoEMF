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

package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.datastore.estores.PersistentEStore;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import static java.util.Objects.isNull;

/**
 * A {@link PersistentEStore} decorator that caches {@link EStructuralFeature structural features}.
 * 
 */
public class FeatureCachingEStoreDecorator extends  AbstractEStoreDecorator {

	private final ValueCache<Object> cache;
	
	public FeatureCachingEStoreDecorator(PersistentEStore eStore) {
		super(eStore);
		cache = new ValueCache<>();
	}

	public FeatureCachingEStoreDecorator(PersistentEStore eStore, int cacheSize) {
		super(eStore);
		cache = new ValueCache<>(cacheSize);
	}
	
	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue = cache.get(object, feature, index);
		if (isNull(returnValue)) {
			returnValue = super.get(object, feature, index);
			cache.put(object, feature, index, returnValue);
		}
		return returnValue;
	}
	
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Object returnValue = super.set(object, feature, index, value);
		cache.put(object, feature, index, value);
		return returnValue;
	}
	
	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		super.add(object, feature, index, value);
		cache.put(object, feature, index, value);
		invalidateValues(object, feature, index + 1);
	}
	
	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue = super.remove(object, feature, index);
		invalidateValues(object, feature, index);
		return returnValue;
	}
	
	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		super.clear(object, feature);
		invalidateValues(object, feature, 0);
	}
	
	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		Object returnValue = super.move(object, feature, targetIndex, sourceIndex);
		invalidateValues(object, feature, Math.min(sourceIndex, targetIndex));
		cache.put(object, feature, targetIndex, returnValue);
		return returnValue;
	}
	
	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		if (!feature.isMany()) {
			cache.invalidate(object, feature);
		} else {
			invalidateValues(object, feature, 0);
		}
		super.unset(object, feature);
	}

	/**
	 * Remove cached elements, from a initial position to the size of an element.
     */
	private void invalidateValues(InternalEObject object, EStructuralFeature feature, int startIndex) {
		for (int i = startIndex; i < size(object, feature); i++) {
			cache.invalidate(object, feature, i);
		}
	}
}
