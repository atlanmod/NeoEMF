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
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackend;

import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkNotNull;

public class DirectWriteMapWithListsResourceEStoreImpl extends DirectWriteMapResourceEStoreImpl {

	private static final int DEFAULT_CACHE_SIZE = 100;

	private final LoadingCache<Tuple2<Id, String>, Object> mapCache;

	public DirectWriteMapWithListsResourceEStoreImpl(Resource.Internal resource, MapPersistenceBackend persistenceBackend) {
		super(resource, persistenceBackend);
		this.mapCache = CacheBuilder.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).softValues().build(new Tuple2CacheLoader());
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object getWithAttribute(InternalPersistentEObject object, EAttribute eAttribute, int index) {
		Object returnValue;
		Object value = getFromMap(object, eAttribute);
		if (!eAttribute.isMany()) {
			returnValue = parseProperty(eAttribute, value);
		} else {
			List<Object> list = (List<Object>) value;
			returnValue = parseProperty(eAttribute, list.get(index));
		}
		return returnValue;
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object getWithReference(InternalPersistentEObject object, EReference eReference, int index) {
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
	protected Object setWithAttribute(InternalPersistentEObject object, EAttribute eAttribute, int index, Object value) {
		Object returnValue;
		if (!eAttribute.isMany()) {
			Object oldValue = tuple2Map.put(Fun.t2(object.id(), eAttribute.getName()), serializeToProperty(eAttribute, value));
			returnValue = parseProperty(eAttribute, oldValue);
		} else {

			List<Object> list = (List<Object>) getFromMap(object, eAttribute);
			Object oldValue = list.get(index);
			list.set(index, serializeToProperty(eAttribute, value));
			tuple2Map.put(Fun.t2(object.id(), eAttribute.getName()), list.toArray());
			returnValue = parseProperty(eAttribute, oldValue);
		}
		return returnValue;
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object setWithReference(InternalPersistentEObject object, EReference eReference, int index, PersistentEObject value) {
		Object returnValue;
		updateContainment(object, eReference, value);
		updateInstanceOf(value);
		if (!eReference.isMany()) {
			Object oldId = tuple2Map.put(Fun.t2(object.id(), eReference.getName()), value.id());
			returnValue = oldId != null ? eObject((Id) oldId) : null;
		} else {
			List<Object> list = (List<Object>) getFromMap(object, eReference);
			Object oldId = list.get(index);
			list.set(index, value.id());
			tuple2Map.put(Fun.t2(object.id(), eReference.getName()), list.toArray());
			returnValue = oldId != null ? eObject((Id) oldId) : null;
		}
		return returnValue;
	}


	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected void addWithAttribute(InternalPersistentEObject object, EAttribute eAttribute, int index, Object value) {
		List<Object> list = (List<Object>) getFromMap(object, eAttribute);
		list.add(index, serializeToProperty(eAttribute, value));
		tuple2Map.put(Fun.t2(object.id(), eAttribute.getName()), list.toArray());
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected void addWithReference(InternalPersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		List<Object> list = (List<Object>) getFromMap(object, eReference);
		list.add(index, referencedObject.id());
		tuple2Map.put(Fun.t2(object.id(), eReference.getName()), list.toArray());
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object removeWithAttribute(InternalPersistentEObject object, EAttribute eAttribute, int index) {
		List<Object> list = (List<Object>) getFromMap(object, eAttribute);
		Object oldValue = list.get(index);
		list.remove(index);
		tuple2Map.put(Fun.t2(object.id(), eAttribute.getName()), list.toArray());
		return parseProperty(eAttribute, oldValue);
	}

	@Override
	@SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'java.util.List<java.lang.Object>'
	protected Object removeWithReference(InternalPersistentEObject object, EReference eReference, int index) {
		List<Object> list = (List<Object>) getFromMap(object, eReference);
		Object oldId = list.get(index);
		list.remove(index);
		tuple2Map.put(Fun.t2(object.id(), eReference.getName()), list.toArray());
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
			returnValue = list.indexOf(serializeToProperty((EAttribute) feature, value));
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
			returnValue = list.lastIndexOf(serializeToProperty((EAttribute) feature, value));
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
		tuple2Map.put(Fun.t2(persistentEObject.id(), feature.getName()), new ArrayList<>());
	}

	@Override
	protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
		Object returnValue = null;
		if (!feature.isMany()) {
			returnValue = tuple2Map.get(Fun.t2(object.id(), feature.getName()));
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
            Object value = tuple2Map.get(key);
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
