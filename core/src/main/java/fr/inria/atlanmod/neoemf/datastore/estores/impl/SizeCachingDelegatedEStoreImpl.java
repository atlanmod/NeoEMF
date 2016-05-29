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
 * A {@link SearcheableResourceEStore} wrapper that caches the size data
 * 
 */
public class SizeCachingDelegatedEStoreImpl extends DelegatedResourceEStoreImpl implements SearcheableResourceEStore {
	
	private static final int DEFAULT_SIZE_CACHE_SIZE = 10000;
	
	private final Cache<CacheKey, Integer> sizeCache;
	
	public SizeCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore) {
		this(eStore, DEFAULT_SIZE_CACHE_SIZE);
	}

	public SizeCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore, int sizeCacheSize) {
		super(eStore);
		this.sizeCache = CacheBuilder.newBuilder().maximumSize(sizeCacheSize).build();
	}
	
	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		super.unset(object, feature);
		sizeCache.put(new CacheKey(object, feature), 0);
	}

	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		Integer size = sizeCache.getIfPresent(new CacheKey(object, feature));
		return size != null ? size == 0 : super.isEmpty(object, feature);
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		Integer size = sizeCache.getIfPresent(new CacheKey(object, feature));
		if (size == null) {
			size = super.size(object, feature); 
			sizeCache.put(new CacheKey(object, feature), size);
		}
		return size;
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Integer size = sizeCache.getIfPresent(new CacheKey(object, feature));
		if (size != null) {
			sizeCache.put(new CacheKey(object, feature), size + 1);
		} 
		super.add(object, feature, index, value);
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		Integer size = sizeCache.getIfPresent(new CacheKey(object, feature));
		if (size != null) {
			sizeCache.put(new CacheKey(object, feature), size - 1);
		} 
		return super.remove(object, feature, index);
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		sizeCache.put(new CacheKey(object, feature), 0);
		super.clear(object, feature);
	}

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

		private SizeCachingDelegatedEStoreImpl getOuterType() {
			return SizeCachingDelegatedEStoreImpl.this;
		}
	}
}
