/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;

import org.apache.commons.collections4.map.LRUMap;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;

import java.util.Map;

/**
 * A {@link SearcheableResourceEStore} wrapper that caches {@link EStructuralFeature}s
 * 
 */
public class EStructuralFeatureCachingDelegatedEStoreImpl extends DelegatedResourceEStoreImpl implements SearcheableResourceEStore {
	
	protected static final int DEFAULT_CACHE_SIZE = 10000;
	
	protected Map<MapKey, Object> cache;
	
	public EStructuralFeatureCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore) {
		this(eStore, DEFAULT_CACHE_SIZE);
	}

	public EStructuralFeatureCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore, int cacheSize) {
		super(eStore);
		this.cache = new LRUMap<>(cacheSize);
	}
	
	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue = cache.get(new MapKey(object, feature, index));
		if (returnValue == null) { 
			returnValue = super.get(object, feature, index);
			cache.put(new MapKey(object, feature, index), returnValue);
		}
		return returnValue;
	}
	
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Object returnValue = super.set(object, feature, index, value);
		cache.put(new MapKey(object, feature, index), value);
		return returnValue;
	}
	
	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		super.add(object, feature, index, value);
		cache.put(new MapKey(object, feature, index), value);
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
		cache.put(new MapKey(object, feature, targetIndex), returnValue);
		return returnValue;
	}
	
	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		if (feature.isMany()) {
			removeFrom(0, object, feature);
		} else {
			cache.remove(new MapKey(object, feature, EStore.NO_INDEX));
		}
		super.unset(object, feature);
	}

	/**
	 * Remove cached elements, from a initial position to the size of an element.
	 * @param start
	 * @param object
	 * @param feature
     */
	// FIXME object allocation inside loop is a great place to look for memory leaks and performance issues
	private void removeFrom(int start, InternalEObject object, EStructuralFeature feature) {
		for (int i = start; i < size(object, feature); i++) {
			cache.remove(new MapKey(object, feature, i));
		}
	}

	protected class MapKey {
		protected InternalEObject object;
		protected EStructuralFeature feature;
		protected int index;

		public MapKey(InternalEObject object, EStructuralFeature feature, int index) {
			this.object = object;
			this.feature = feature;
			this.index = index;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((feature == null) ? 0 : feature.hashCode());
			result = prime * result + index;
			result = prime * result + ((object == null) ? 0 : object.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			MapKey other = (MapKey) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (feature == null) {
				if (other.feature != null) {
					return false;
				}
			} else if (!feature.equals(other.feature)) {
				return false;
			}
			if (index != other.index) {
				return false;
			}
			if (object == null) {
				if (other.object != null) {
					return false;
				}
			} else if (!object.equals(other.object)) {
				return false;
			}
			return true;
		}

		private EStructuralFeatureCachingDelegatedEStoreImpl getOuterType() {
			return EStructuralFeatureCachingDelegatedEStoreImpl.this;
		}

	}
}
