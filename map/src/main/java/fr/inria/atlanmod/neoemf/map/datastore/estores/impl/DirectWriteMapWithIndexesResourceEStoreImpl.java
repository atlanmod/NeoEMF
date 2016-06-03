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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;
import org.mapdb.DB;
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple3;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class DirectWriteMapWithIndexesResourceEStoreImpl extends DirectWriteMapResourceEStoreImpl {

	private Map<Tuple3<Id, String, Integer>, Object> objectMap;

	public DirectWriteMapWithIndexesResourceEStoreImpl(Resource.Internal resource, DB db) {
		super(resource, db, db.getHashMap("SIZES"));
		this.objectMap = db.getHashMap("NeoEMF");
	}

	@Override
	protected Object get(PersistentEObject object, EAttribute eAttribute, int index) {
		Object value = objectMap.get(Fun.t3(object.id(), eAttribute.getName(), index));
		return parseMapValue(eAttribute, value);
	}

	@Override
	protected Object get(PersistentEObject object, EReference eReference, int index) {
		Object value = objectMap.get(Fun.t3(object.id(), eReference.getName(), index));
		return eObject((Id) value);
	}
	
	@Override
	protected Object set(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
		if (!eAttribute.isMany()) {
			map.put(Fun.t2(object.id(), eAttribute.getName()), EStore.NO_INDEX);
		}
		Object oldValue = objectMap.put(Fun.t3(object.id(), eAttribute.getName(), index), serializeToMapValue(eAttribute, value));
		return parseMapValue(eAttribute, oldValue);
	}

	@Override
	protected Object set(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		if (!eReference.isMany()) {
			map.put(Fun.t2(object.id(), eReference.getName()), EStore.NO_INDEX);
		}
		Object returnValue = null;
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		Object oldId = objectMap.put(Fun.t3(object.id(), eReference.getName(), index), referencedObject.id());
		if (oldId != null) {
			returnValue = eObject((Id) oldId);
		}
		return returnValue;
	}

	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		// Make space for the new element
		Integer size = (Integer) map.get(Fun.t2(persistentEObject.id(), feature.getName()));
		if (size == null) {
			size = 0;
		}
		for (int i = size - 1; i >= index; i--) {
			Object movingValue = objectMap.get(Fun.t3(persistentEObject.id(),  feature.getName(), i));
			objectMap.put(Fun.t3(persistentEObject.id(), feature.getName(), i + 1), movingValue);
		}
		map.put(Fun.t2(persistentEObject.id(), feature.getName()), size + 1);
		
		// add element
		if (feature instanceof EAttribute) {
			objectMap.put(Fun.t3(persistentEObject.id(), feature.getName(), index), value);
		} else if (feature instanceof EReference) {
			PersistentEObject referencedEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class));
			updateContainment(persistentEObject, (EReference) feature, referencedEObject);
			updateInstanceOf(referencedEObject);
			objectMap.put(Fun.t3(persistentEObject.id(), feature.getName(), index), referencedEObject.id());
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		Integer size = (Integer) map.get(Fun.t2(persistentEObject.id(), feature.getName()));
		// Gete element to remove
		Object returnValue = objectMap.get(Fun.t3(persistentEObject.id(),feature.getName(), index));
		// Update indexes (element to remove is overwriten)
		for (int i = index + 1; i < size; i++) {
			Object movingValue = objectMap.get(Fun.t3(persistentEObject.id(), feature.getName(), i));
			objectMap.put(Fun.t3(persistentEObject.id(), feature.getName(), i - 1), movingValue);
		}
		map.put(Fun.t2(persistentEObject.id(), feature.getName()), size - 1);
		return returnValue;
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		Integer size = (Integer) map.get(Fun.t2(persistentEObject.id(), feature.getName()));
		return size != null ? size : 0; 
	}

	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		return ArrayUtils.indexOf(toArray(object, feature), value);
	}

	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		return ArrayUtils.indexOf(toArray(object, feature), value);
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		map.remove(Fun.t2(persistentEObject.id(), feature.getName()));
	}
}
