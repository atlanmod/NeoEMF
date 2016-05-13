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

import java.util.Map;

/**
 * A {@link SearcheableResourceEStore} wrapper that caches the size data
 * 
 */
public class IsSetCachingDelegatedEStoreImpl extends DelegatedResourceEStoreImpl implements SearcheableResourceEStore {
	
	protected static final int DEFAULT_IS_SET_CACHE_SIZE = 10000;
	
	protected Map<MapKey, Boolean> isSetCache;
	
	public IsSetCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore) {
		this(eStore, DEFAULT_IS_SET_CACHE_SIZE);
	}

	public IsSetCachingDelegatedEStoreImpl(SearcheableResourceEStore eStore, int sizeCacheSize) {
		super(eStore);
		this.isSetCache = new LRUMap<>(sizeCacheSize);
	}
	
	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		super.unset(object, feature);
		isSetCache.put(new MapKey(object, feature), false);
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		Boolean isSet = isSetCache.get(new MapKey(object, feature));
		return isSet != null ? isSet : eStore.isSet(object, feature);
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		eStore.add(object, feature, index, value);
		isSetCache.put(new MapKey(object, feature), true); 
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		isSetCache.remove(new MapKey(object, feature)); // Remove, next queries will update the right cached value
		return super.remove(object, feature, index);
	}
	
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Object returnValue = eStore.set(object, feature, index, value);
		isSetCache.put(new MapKey(object, feature), true); 
		return returnValue;
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		isSetCache.put(new MapKey(object, feature), false); 
		eStore.clear(object, feature);
	}
	
	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		Object returnValue = super.move(object, feature, targetIndex, sourceIndex);
		isSetCache.put(new MapKey(object, feature), true);
		return returnValue;
	}
	
	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		boolean returnValue = super.contains(object, feature, value);
		if (returnValue) {
			isSetCache.put(new MapKey(object, feature), true);
		}
		return returnValue;
	}
	
	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue = super.get(object, feature, index);
		if (returnValue != null) {
			isSetCache.put(new MapKey(object, feature), true);
		}
		return returnValue;
	}
	
	// TODO: Other methods may be added...

	protected class MapKey {
		protected InternalEObject object;
		protected EStructuralFeature feature;

		public MapKey(InternalEObject object, EStructuralFeature feature) {
			this.object = object;
			this.feature = feature;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((object == null) ? 0 : object.hashCode());
			result = prime * result + ((feature == null) ? 0 : feature.hashCode());
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
			if (object == null) {
				if (other.object != null) {
					return false;
				}
			} else if (!object.equals(other.object)) {
				return false;
			}
			if (feature == null) {
				if (other.feature != null) {
					return false;
				}
			} else if (!feature.equals(other.feature)) {
				return false;
			}
			return true;
		}

		private IsSetCachingDelegatedEStoreImpl getOuterType() {
			return IsSetCachingDelegatedEStoreImpl.this;
		}
	}
}
