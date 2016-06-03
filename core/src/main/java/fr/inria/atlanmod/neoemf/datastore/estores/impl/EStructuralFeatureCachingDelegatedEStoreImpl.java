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
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * A {@link SearcheableResourceEStore} wrapper that caches {@link EStructuralFeature}s.
 * 
 */
public class EStructuralFeatureCachingDelegatedEStoreImpl extends DelegatedResourceEStoreImpl implements SearcheableResourceEStore {
	
	private static final int DEFAULT_CACHE_SIZE = 10000;
	
	private final Cache<CacheKey, Object> cache;
	
	public EStructuralFeatureCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore) {
		this(eStore, DEFAULT_CACHE_SIZE);
	}

	public EStructuralFeatureCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore, int cacheSize) {
		super(eStore);
		this.cache = CacheBuilder.newBuilder().maximumSize(cacheSize).build();
	}
	
	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue = null;
		try {
			returnValue = cache.get(new CacheKey(object, feature, index), new CacheLoader(object, feature, index));
		} catch (ExecutionException e) {
			NeoLogger.error(e.getCause());
		}
		return returnValue;
	}
	
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Object returnValue = super.set(object, feature, index, value);
		cache.put(new CacheKey(object, feature, index), value);
		return returnValue;
	}
	
	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		super.add(object, feature, index, value);
		cache.put(new CacheKey(object, feature, index), value);
		removeFrom(index + 1, object, feature);
	}
	
	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue = super.remove(object, feature, index);
		removeFrom(index, object, feature);
		return returnValue;
	}
	
	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		super.clear(object, feature);
		removeFrom(0, object, feature);
	}
	
	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		Object returnValue = super.move(object, feature, targetIndex, sourceIndex);
		removeFrom(Math.min(sourceIndex, targetIndex), object, feature);
		cache.put(new CacheKey(object, feature, targetIndex), returnValue);
		return returnValue;
	}
	
	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		if (!feature.isMany()) {
			cache.invalidate(new CacheKey(object, feature, EStore.NO_INDEX));
		} else {
			removeFrom(0, object, feature);
		}
		super.unset(object, feature);
	}

	/**
	 * Remove cached elements, from a initial position to the size of an element.
     */
	// FIXME Object allocation inside loop is a great place to look for memory leaks and performance issues
	private void removeFrom(int start, InternalEObject object, EStructuralFeature feature) {
		for (int i = start; i < size(object, feature); i++) {
			cache.invalidate(new CacheKey(object, feature, i));
		}
	}

	private class CacheKey {
		private InternalEObject object;
		private EStructuralFeature feature;
		private int index;

		public CacheKey(InternalEObject object, EStructuralFeature feature, int index) {
			this.object = object;
			this.feature = feature;
			this.index = index;
		}

		@Override
		public int hashCode() {
			return Objects.hash(getOuterType(), feature, index, object);
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
					&& Objects.equals(feature, other.feature)
					&& index == other.index;
		}

		private EStructuralFeatureCachingDelegatedEStoreImpl getOuterType() {
			return EStructuralFeatureCachingDelegatedEStoreImpl.this;
		}

	}

	private class CacheLoader implements Callable<Object> {

		private final InternalEObject object;
		private final EStructuralFeature feature;
		private final int index;

		public CacheLoader(InternalEObject object, EStructuralFeature feature, int index) {
			this.object = object;
			this.feature = feature;
			this.index = index;
		}

		@Override
        public Object call() throws Exception {
            return EStructuralFeatureCachingDelegatedEStoreImpl.super.get(object, feature, index);
        }
	}
}
