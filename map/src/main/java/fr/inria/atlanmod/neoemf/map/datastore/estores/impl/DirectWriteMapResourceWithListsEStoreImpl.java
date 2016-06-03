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

package fr.inria.atlanmod.neoemf.map.datastore.estores.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.mapdb.DB;
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkNotNull;

public class DirectWriteMapResourceWithListsEStoreImpl extends DirectWriteMapResourceEStoreImpl {

	private static final int DEFAULT_CACHE_SIZE = 100;

	private final LoadingCache<Tuple2<Id, String>, Object> mapCache;

	public DirectWriteMapResourceWithListsEStoreImpl(Resource.Internal resource, DB db) {
		super(resource, db);
		this.mapCache = CacheBuilder.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).softValues().build(new Tuple2CacheLoader());
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object get(PersistentEObject object, EAttribute eAttribute, int index) {
		Object returnValue;
		Object value = getFromMap(object, eAttribute);
		if (!eAttribute.isMany()) {
			returnValue = parseMapValue(eAttribute, value);
		} else {
			List<Object> list = (List<Object>) value;
			returnValue = parseMapValue(eAttribute, list.get(index));
		}
		return returnValue;
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object get(PersistentEObject object, EReference eReference, int index) {
		Object returnValue;
		Object value = getFromMap(object, eReference);
		if (!eReference.isMany()) {
			returnValue = eObject((Id) value);
		} else {
			List<Object> list = (List<Object>) value;
			returnValue = eObject((Id) list.get(index));
		}
		return returnValue;
	}


	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object set(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
		Object returnValue;
		if (!eAttribute.isMany()) {
			Object oldValue = map.put(Fun.t2(object.id(), eAttribute.getName()), serializeToMapValue(eAttribute, value));
			returnValue = parseMapValue(eAttribute, oldValue);
		} else {

			List<Object> list = (List<Object>) getFromMap(object, eAttribute);
			Object oldValue = list.get(index);
			list.set(index, serializeToMapValue(eAttribute, value));
			map.put(Fun.t2(object.id(), eAttribute.getName()), list.toArray());
			returnValue = parseMapValue(eAttribute, oldValue);
		}
		return returnValue;
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object set(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		Object returnValue;
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		if (!eReference.isMany()) {
			Object oldId = map.put(Fun.t2(object.id(), eReference.getName()), referencedObject.id());
			returnValue = oldId != null ? eObject((Id) oldId) : null;
		} else {
			List<Object> list = (List<Object>) getFromMap(object, eReference);
			Object oldId = list.get(index);
			list.set(index, referencedObject.id());
			map.put(Fun.t2(object.id(), eReference.getName()), list.toArray());
			returnValue = oldId != null ? eObject((Id) oldId) : null;
		}
		return returnValue;
	}


	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected void add(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
		List<Object> list = (List<Object>) getFromMap(object, eAttribute);
		list.add(index, serializeToMapValue(eAttribute, value));
		map.put(Fun.t2(object.id(), eAttribute.getName()), list.toArray());
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected void add(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		List<Object> list = (List<Object>) getFromMap(object, eReference);
		list.add(index, referencedObject.id());
		map.put(Fun.t2(object.id(), eReference.getName()), list.toArray());
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object remove(PersistentEObject object, EAttribute eAttribute, int index) {
		List<Object> list = (List<Object>) getFromMap(object, eAttribute);
		Object oldValue = list.get(index);
		list.remove(index);
		map.put(Fun.t2(object.id(), eAttribute.getName()), list.toArray());
		return parseMapValue(eAttribute, oldValue);
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object remove(PersistentEObject object, EReference eReference, int index) {
		List<Object> list = (List<Object>) getFromMap(object, eReference);
		Object oldId = list.get(index);
		list.remove(index);
		map.put(Fun.t2(object.id(), eReference.getName()), list.toArray());
		return eObject((Id) oldId);
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	public int size(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		List<Object> list = (List<Object>) getFromMap(persistentEObject, feature);
		return list != null ? list.size() : 0; 
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		int returnValue;
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		List<Object> list = (List<Object>) getFromMap(persistentEObject, feature);
		if (list== null) {
			returnValue = -1;
		} else if (feature instanceof EAttribute) {
			returnValue = list.indexOf(serializeToMapValue((EAttribute) feature, value));
		} else {
			PersistentEObject childEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class));
			returnValue = list.indexOf(childEObject.id());
		}
		return returnValue;
	}


	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		int returnValue;
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		List<Object> list = (List<Object>) getFromMap(persistentEObject, feature);
		if (list == null) {
			returnValue = -1;
		} else if (feature instanceof EAttribute) {
			returnValue = list.lastIndexOf(serializeToMapValue((EAttribute) feature, value));
		} else {
			PersistentEObject childEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class));
			returnValue = list.lastIndexOf(childEObject.id());
		}
		return returnValue;
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		map.put(Fun.t2(persistentEObject.id(), feature.getName()), new ArrayList<>());
	}

	@Override
	protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
		Object returnValue = null;
		if (!feature.isMany()) {
			returnValue = map.get(Fun.t2(object.id(), feature.getName()));
		} else {
			try {
				returnValue = mapCache.get(Fun.t2(object.id(), feature.getName()));
			} catch (ExecutionException e) {
				NeoLogger.warn(e.getCause());
			}
		}
		return returnValue;
	}
	
	private class Tuple2CacheLoader extends CacheLoader<Tuple2<Id, String>, Object> {

		private static final int ARRAY_SIZE_OFFSET = 10;

		@Override
        public Object load(Tuple2<Id, String> key) throws Exception {
            Object returnValue;
            Object value = map.get(key);
            if (value == null) {
                returnValue = new ArrayList<>();
            } else if (value instanceof Object[]) {
                Object[] array = (Object[]) value;
                List<Object> list = new ArrayList<>(array.length + ARRAY_SIZE_OFFSET);
                CollectionUtils.addAll(list, array);
                returnValue = list;
            } else {
                returnValue = value;
            }
            return returnValue;
        }
	}
}
