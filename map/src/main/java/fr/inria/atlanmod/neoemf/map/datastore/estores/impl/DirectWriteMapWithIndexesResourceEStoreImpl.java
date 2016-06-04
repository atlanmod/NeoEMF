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
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackend;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple3;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class DirectWriteMapWithIndexesResourceEStoreImpl extends DirectWriteMapResourceEStoreImpl {

	private Map<Tuple3<Id, String, Integer>, Object> tuple3Map;

	public DirectWriteMapWithIndexesResourceEStoreImpl(Resource.Internal resource, MapPersistenceBackend persistenceBackend) {
		super(resource, persistenceBackend, persistenceBackend.getHashMap("SIZES"));
		this.tuple3Map = persistenceBackend.getHashMap("NeoEMF");
	}

	@Override
	protected Object getWithAttribute(InternalPersistentEObject object, EAttribute eAttribute, int index) {
		Object value = tuple3Map.get(Fun.t3(object.id(), eAttribute.getName(), index));
		return parseProperty(eAttribute, value);
	}

	@Override
	protected Object getWithReference(InternalPersistentEObject object, EReference eReference, int index) {
		Object value = tuple3Map.get(Fun.t3(object.id(), eReference.getName(), index));
		return eObject((Id) value);
	}
	
	@Override
	protected Object setWithAttribute(InternalPersistentEObject object, EAttribute eAttribute, int index, Object value) {
		if (!eAttribute.isMany()) {
			tuple2Map.put(Fun.t2(object.id(), eAttribute.getName()), EStore.NO_INDEX);
		}
		Object oldValue = tuple3Map.put(Fun.t3(object.id(), eAttribute.getName(), index), serializeToProperty(eAttribute, value));
		return parseProperty(eAttribute, oldValue);
	}

	@Override
	protected Object setWithReference(InternalPersistentEObject object, EReference eReference, int index, PersistentEObject value) {
		if (!eReference.isMany()) {
			tuple2Map.put(Fun.t2(object.id(), eReference.getName()), EStore.NO_INDEX);
		}
		Object returnValue = null;
		updateContainment(object, eReference, value);
		updateInstanceOf(value);
		Object oldId = tuple3Map.put(Fun.t3(object.id(), eReference.getName(), index), value.id());
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
		Integer size = (Integer) tuple2Map.get(Fun.t2(persistentEObject.id(), feature.getName()));
		if (size == null) {
			size = 0;
		}
		for (int i = size - 1; i >= index; i--) {
			Object movingValue = tuple3Map.get(Fun.t3(persistentEObject.id(),  feature.getName(), i));
			tuple3Map.put(Fun.t3(persistentEObject.id(), feature.getName(), i + 1), movingValue);
		}
		tuple2Map.put(Fun.t2(persistentEObject.id(), feature.getName()), size + 1);
		
		// add element
		if (feature instanceof EAttribute) {
			tuple3Map.put(Fun.t3(persistentEObject.id(), feature.getName(), index), value);
		} else if (feature instanceof EReference) {
			PersistentEObject referencedEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class));
			updateContainment(persistentEObject, (EReference) feature, referencedEObject);
			updateInstanceOf(referencedEObject);
			tuple3Map.put(Fun.t3(persistentEObject.id(), feature.getName(), index), referencedEObject.id());
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		Integer size = (Integer) tuple2Map.get(Fun.t2(persistentEObject.id(), feature.getName()));
		// Gete element to remove
		Object returnValue = tuple3Map.get(Fun.t3(persistentEObject.id(),feature.getName(), index));
		// Update indexes (element to remove is overwriten)
		for (int i = index + 1; i < size; i++) {
			Object movingValue = tuple3Map.get(Fun.t3(persistentEObject.id(), feature.getName(), i));
			tuple3Map.put(Fun.t3(persistentEObject.id(), feature.getName(), i - 1), movingValue);
		}
		tuple2Map.put(Fun.t2(persistentEObject.id(), feature.getName()), size - 1);
		return returnValue;
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		Integer size = (Integer) tuple2Map.get(Fun.t2(persistentEObject.id(), feature.getName()));
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
		tuple2Map.remove(Fun.t2(persistentEObject.id(), feature.getName()));
	}
}
