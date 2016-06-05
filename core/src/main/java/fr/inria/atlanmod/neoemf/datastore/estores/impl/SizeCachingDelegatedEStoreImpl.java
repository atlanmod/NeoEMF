/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * A {@link SearcheableResourceEStore} wrapper that caches the size data.
 * 
 */
public class SizeCachingDelegatedEStoreImpl extends AbstractCachingDelegatedResourceEStoreImpl<Integer> {
	
	public SizeCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore) {
		super(eStore);
	}

	public SizeCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore, int cacheSize) {
		super(eStore, cacheSize);
	}
	
	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		cacheValue(object, feature, 0);
		super.unset(object, feature);
	}

	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		Integer size = getValueFromCache(object, feature);
		return size != null ? size == 0 : super.isEmpty(object, feature);
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		Integer size = getValueFromCache(object, feature);
		if (size == null) {
			size = super.size(object, feature);
			cacheValue(object, feature, size);
		}
		return size;
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Integer size = getValueFromCache(object, feature);
		if (size != null) {
			cacheValue(object, feature, size + 1);
		} 
		super.add(object, feature, index, value);
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		Integer size = getValueFromCache(object, feature);
		if (size != null) {
			cacheValue(object, feature, size - 1);
		} 
		return super.remove(object, feature, index);
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		cacheValue(object, feature, 0);
		super.clear(object, feature);
	}
}
