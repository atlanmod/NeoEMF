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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.Objects;

/**
 * A {@link SearcheableResourceEStore} wrapper that caches the size data.
 * 
 */
public class IsSetCachingDelegatedEStoreImpl extends DelegatedResourceEStoreImpl implements SearcheableResourceEStore {
	
	private static final int DEFAULT_CACHE_SIZE = 10000;
	
	private final Cache<CacheKey, Boolean> isSetCache;
	
	public IsSetCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore) {
		this(eStore, DEFAULT_CACHE_SIZE);
	}

	public IsSetCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore, int sizeCacheSize) {
		super(eStore);
		this.isSetCache = CacheBuilder.newBuilder().maximumSize(sizeCacheSize).build();
	}
	
	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		super.unset(object, feature);
		isSetCache.put(new CacheKey(object, feature), false);
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		Boolean isSet = isSetCache.getIfPresent(new CacheKey(object, feature));
		return isSet != null ? isSet : eStore.isSet(object, feature);
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		eStore.add(object, feature, index, value);
		isSetCache.put(new CacheKey(object, feature), true);
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		isSetCache.invalidate(new CacheKey(object, feature)); // Remove, next queries will update the right cached value
		return super.remove(object, feature, index);
	}
	
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Object returnValue = eStore.set(object, feature, index, value);
		isSetCache.put(new CacheKey(object, feature), true);
		return returnValue;
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		isSetCache.put(new CacheKey(object, feature), false);
		eStore.clear(object, feature);
	}
	
	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		Object returnValue = super.move(object, feature, targetIndex, sourceIndex);
		isSetCache.put(new CacheKey(object, feature), true);
		return returnValue;
	}
	
	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		boolean returnValue = super.contains(object, feature, value);
		if (returnValue) {
			isSetCache.put(new CacheKey(object, feature), true);
		}
		return returnValue;
	}
	
	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue = super.get(object, feature, index);
		if (returnValue != null) {
			isSetCache.put(new CacheKey(object, feature), true);
		}
		return returnValue;
	}
	
	// TODO Other methods may be added...

	private class CacheKey {
		private InternalEObject object;
		private EStructuralFeature feature;

		public CacheKey(InternalEObject object, EStructuralFeature feature) {
			this.object = object;
			this.feature = feature;
		}

		@Override
		public int hashCode() {
			return Objects.hash(getOuterType(), object, feature);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			CacheKey other = (CacheKey) obj;
			return Objects.equals(getOuterType(), other.getOuterType())
					&& Objects.equals(object, other.object)
					&& Objects.equals(feature, other.feature);
		}

		private IsSetCachingDelegatedEStoreImpl getOuterType() {
			return IsSetCachingDelegatedEStoreImpl.this;
		}
	}
}
