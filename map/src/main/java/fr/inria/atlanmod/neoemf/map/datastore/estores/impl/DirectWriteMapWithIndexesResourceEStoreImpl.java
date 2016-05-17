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

package fr.inria.atlanmod.neoemf.map.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.ContainerInfo;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.EClassInfo;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jboss.util.collection.SoftValueHashMap;
import org.mapdb.DB;
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple2;
import org.mapdb.Fun.Tuple3;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class DirectWriteMapWithIndexesResourceEStoreImpl implements SearcheableResourceEStore {

	protected static final String INSTANCE_OF = "neoInstanceOf";
	protected static final String CONTAINER = "eContainer";

	protected Map<Object, InternalPersistentEObject> loadedEObjects;
	
	protected DB db;
	
	protected Map<Tuple3<Id, String, Integer>, Object> map;
	protected Map<Tuple2<Id, String>, Integer> sizesMap;
	
	protected Map<Id, EClassInfo> instanceOfMap;

	protected Map<Id, ContainerInfo> containersMap;
	
	protected Resource.Internal resource;

	@SuppressWarnings("unchecked")
	public DirectWriteMapWithIndexesResourceEStoreImpl(Resource.Internal resource, DB db) {
		this.loadedEObjects = new SoftValueHashMap();
		this.db = db;
		this.resource = resource;
		this.map = db.getHashMap("NeoEMF");
		this.sizesMap = db.getHashMap("SIZES");
		this.instanceOfMap = db.getHashMap(INSTANCE_OF);
		this.containersMap = db.getHashMap(CONTAINER);
	}


	@Override
	public Resource.Internal resource() {
		return resource;
	}


	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue;
		PersistentEObject persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		if (feature instanceof EAttribute) {
			Object value = map.get(Fun.t3(persistentEObject.id(), feature.getName(), index));
			returnValue = parseMapValue((EAttribute) feature, value);
		} else if (feature instanceof EReference) {
			Object value = map.get(Fun.t3(persistentEObject.id(), feature.getName(), index));
			returnValue = eObject((Id) value);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
		return returnValue;
	}
	
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Object returnValue;
		PersistentEObject persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		if (!feature.isMany()) {
			sizesMap.put(Fun.t2(persistentEObject.id(), feature.getName()), EStore.NO_INDEX);
		}
		if (feature instanceof EAttribute) {
			returnValue = set(persistentEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			PersistentEObject referencedEObject = NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class);
			returnValue = set(persistentEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
		return returnValue;
	}

	protected Object set(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
		Object oldValue = map.put(Fun.t3(object.id(), eAttribute.getName(), index), serializeToMapValue(eAttribute, value));
		return parseMapValue(eAttribute, oldValue);
	}

	protected Object set(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		Object oldId = map.put(Fun.t3(object.id(), eReference.getName(), index), referencedObject.id());
		return oldId != null ? eObject((Id) oldId) : null;
	}


	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		return sizesMap.containsKey(Fun.t2(persistentEObject.id(), feature.getName()));
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		PersistentEObject persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		// Make space for the new element
		Integer size = sizesMap.get(Fun.t2(persistentEObject.id(), feature.getName()));
		if (size == null) {
			size = 0;
		}
		for (int i = size - 1; i >= index; i--) {
			Object movingValue = map.get(Fun.t3(persistentEObject.id(),  feature.getName(), i));
			map.put(Fun.t3(persistentEObject.id(), feature.getName(), i + 1), movingValue);
		}
		sizesMap.put(Fun.t2(persistentEObject.id(), feature.getName()), size + 1);
		
		// add element
		if (feature instanceof EAttribute) {
			map.put(Fun.t3(persistentEObject.id(), feature.getName(), index), value);
		} else if (feature instanceof EReference) {
			PersistentEObject referencedEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class));
			updateContainment(persistentEObject, (EReference) feature, referencedEObject);
			updateInstanceOf(referencedEObject);
			map.put(Fun.t3(persistentEObject.id(), feature.getName(), index), referencedEObject.id());
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		PersistentEObject persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));

		Integer size = sizesMap.get(Fun.t2(persistentEObject.id(), feature.getName()));
		// Gete element to remove
		Object returnValue = map.get(Fun.t3(persistentEObject.id(),feature.getName(), index));
		// Update indexes (element to remove is overwriten)
		for (int i = index + 1; i < size; i++) {
			Object movingValue = map.get(Fun.t3(persistentEObject.id(), feature.getName(), i));
			map.put(Fun.t3(persistentEObject.id(), feature.getName(), i - 1), movingValue);
		}
		sizesMap.put(Fun.t2(persistentEObject.id(), feature.getName()), size - 1);
		return returnValue;
	}



	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		Object movedElement = remove(object, feature, sourceIndex);
		add(object, feature, targetIndex, movedElement);
		return movedElement;
	}


	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		sizesMap.remove(Fun.t2(persistentEObject.id(), feature.getName()));
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0; 
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		Integer size = sizesMap.get(Fun.t2(persistentEObject.id(), feature.getName()));
		return size != null ? size : 0; 
	}


	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return indexOf(object, feature, value) != -1;
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
		PersistentEObject persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		sizesMap.remove(Fun.t2(persistentEObject.id(), feature.getName()));
//		Integer size =
//		for (int i = 0; i < size; i++) {
//			map.remove(Fun.t3(persistentEObject.id(), feature.getName(), i));
//		}
	}


	@Override
	public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
		int size = size(object, feature);
		Object[] result = new Object[size];
		for (int index = 0; index < size; index++) {
			result[index] = get(object, feature, index);
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
		int size = size(object, feature);
		T[] result = array.length < size ? Arrays.copyOf(array, size) : array;
		for (int index = 0; index < size; index++) {
			result[index] = (T) get(object, feature, index);
		}
		return result;
	}


	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		return Arrays.hashCode(toArray(object, feature));
	}


	@Override
	public InternalEObject getContainer(InternalEObject object) {
		InternalEObject returnValue = null;
		PersistentEObject persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		ContainerInfo info = containersMap.get(persistentEObject.id());
		if (info != null) {
			returnValue = (InternalEObject) eObject(info.containerId);
		}
		return returnValue;
	}


	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		PersistentEObject persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		ContainerInfo info = containersMap.get(persistentEObject.id());
		if (info != null) {
			EObject container = eObject(info.containerId);
			container.eClass().getEStructuralFeature(info.containingFeatureName);
		}
		return null;
	}


	@Override
	public EObject create(EClass eClass) {
		// This should not be called
		throw new UnsupportedOperationException();
	}


	@Override
	public EObject eObject(Id id) {
		InternalPersistentEObject persistentEObject = null;
		if (id != null) {
			persistentEObject = loadedEObjects.get(id);
			if (persistentEObject == null) {
				EClass eClass = resolveInstanceOf(id);
				if (eClass != null) {
					EObject eObject;
					if (eClass.getEPackage().getClass().equals(EPackageImpl.class)) {
						// Dynamic EMF
						eObject = PersistenceFactory.eINSTANCE.create(eClass);
					} else {
						eObject = EcoreUtil.create(eClass);
					}
					if (eObject instanceof InternalPersistentEObject) {
						persistentEObject = (InternalPersistentEObject) eObject;
					} else {
						persistentEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(eObject, InternalPersistentEObject.class));
					}
					persistentEObject.id(id);
				} else {
					NeoLogger.log(NeoLogger.SEVERITY_ERROR,
							MessageFormat.format("Element {0} does not have an associated EClass", id));
				}
				loadedEObjects.put(id, persistentEObject);
			}
			Objects.requireNonNull(persistentEObject);
			if (persistentEObject.resource() != resource()) {
				persistentEObject.resource(resource());
			}
		}
		return persistentEObject;
	}
	

	protected EClass resolveInstanceOf(Id id) {
		EClassInfo eClassInfo = instanceOfMap.get(id);
		return eClassInfo != null ? (EClass) Registry.INSTANCE.getEPackage(eClassInfo.nsURI).getEClassifier(eClassInfo.className) : null;
	}
	
	protected void updateContainment(PersistentEObject object, EReference eReference, PersistentEObject referencedObject) {
		if (eReference.isContainment()) {
			ContainerInfo info = containersMap.get(referencedObject.id());
			if (info == null || !info.containerId.equals(object.id())) {
				containersMap.put(referencedObject.id(), new ContainerInfo(object.id(), eReference.getName()));
			}
		}
	}
	
	protected void updateInstanceOf(PersistentEObject object) {
		EClassInfo info = instanceOfMap.get(object.id());
		if (info == null) {
			instanceOfMap.put(object.id(), new EClassInfo(object.eClass().getEPackage().getNsURI(), object.eClass().getName()));
		}
	}


	protected static Object parseMapValue(EAttribute eAttribute, Object property) {
		return property != null ? EcoreUtil.createFromString(eAttribute.getEAttributeType(), property.toString()) : null;
	}

	protected static Object serializeToMapValue(EAttribute eAttribute, Object value) {
		return value != null ? EcoreUtil.convertToString(eAttribute.getEAttributeType(), value) : null;
	}
	
	@Override
	public EList<EObject> getAllInstances(EClass eClass, boolean strict)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
}
