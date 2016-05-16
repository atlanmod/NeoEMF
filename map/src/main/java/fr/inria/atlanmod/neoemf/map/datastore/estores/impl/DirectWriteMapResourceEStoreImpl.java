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
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jboss.util.collection.SoftValueHashMap;
import org.mapdb.DB;
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple2;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class DirectWriteMapResourceEStoreImpl implements SearcheableResourceEStore {

	protected static final String INSTANCE_OF = "neoInstanceOf";
	protected static final String CONTAINER = "eContainer";

	@SuppressWarnings("unchecked")
	protected Map<Object, InternalPersistentEObject> loadedEObjects = new SoftValueHashMap();
	
	protected DB db;
	
	protected Map<Tuple2<Id, String>, Object> map;
	
	protected Map<Id, EClassInfo> instanceOfMap;

	protected Map<Id, ContainerInfo> containersMap;
	
	protected Resource.Internal resource;

	public DirectWriteMapResourceEStoreImpl(Resource.Internal resource, DB db) {
		this.db = db;
		this.resource = resource;
		this.map = db.getHashMap("NeoEMF");
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
		PersistentEObject neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			returnValue = get(neoEObject, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			returnValue = get(neoEObject, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
		return returnValue;
	}
	
	protected Object get(PersistentEObject object, EAttribute eAttribute, int index) {
		Object returnValue;
		Object value = getFromMap(object, eAttribute);
		if (!eAttribute.isMany()) {
			returnValue = parseMapValue(eAttribute, value);
		} else {
			Object[] array = (Object[]) value;
			returnValue = parseMapValue(eAttribute, array[index]);
		}
		return returnValue;
	}

	protected Object get(PersistentEObject object, EReference eReference, int index) {
		Object returnValue;
		Object value = getFromMap(object, eReference);
		if (!eReference.isMany()) {
			returnValue = eObject((Id) value);
		} else {
			Object[] array = (Object[]) value;
			returnValue = eObject((Id) array[index]);
		}
		return returnValue;
	}


	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Object returnValue;
		PersistentEObject neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			returnValue = set(neoEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			PersistentEObject referencedEObject = NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class);
			returnValue = set(neoEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
		return returnValue;
	}

	protected Object set(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
		Object returnValue;
		if (!eAttribute.isMany()) {
			Object oldValue = map.put(Fun.t2(object.id(), eAttribute.getName()), serializeToMapValue(eAttribute, value));
			returnValue = parseMapValue(eAttribute, oldValue);
		} else {
			Object[] array = (Object[]) getFromMap(object, eAttribute);
			Object oldValue = array[index]; 
			array[index] = serializeToMapValue(eAttribute, value);
			map.put(Fun.t2(object.id(), eAttribute.getName()), array);
			returnValue = parseMapValue(eAttribute, oldValue);
		}
		return returnValue;
	}

	protected Object set(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		Object returnValue;
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		if (!eReference.isMany()) {
			Object oldId = map.put(Fun.t2(object.id(), eReference.getName()), referencedObject.id());
			returnValue = oldId != null ? eObject((Id) oldId) : null;
		} else {
			Object[] array = (Object[]) getFromMap(object, eReference);
			Object oldId = array[index];
			array[index] = referencedObject.id();
			map.put(Fun.t2(object.id(), eReference.getName()), array);
			returnValue = oldId != null ? eObject((Id) oldId) : null;
		}
		return returnValue;
	}


	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject neoEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		return map.containsKey(Fun.t2(neoEObject.id(), feature.getName()));
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		PersistentEObject neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			add(neoEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			PersistentEObject referencedEObject = NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class);
			add(neoEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected void add(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
		Object[] array = (Object[]) getFromMap(object, eAttribute);
		if (array == null) {
			array = new Object[] {};
		}
		array = ArrayUtils.add(array, index, serializeToMapValue(eAttribute, value));
		map.put(Fun.t2(object.id(), eAttribute.getName()), array);
	}

	protected void add(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		Object[] array = (Object[]) getFromMap(object, eReference);
		if (array == null) {
			array = new Object[] {};
		}
		array = ArrayUtils.add(array, index, referencedObject.id());
		map.put(Fun.t2(object.id(), eReference.getName()), array);
		loadedEObjects.put(referencedObject.id(),(InternalPersistentEObject)referencedObject);
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue;
		PersistentEObject neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			returnValue = remove(neoEObject, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			returnValue = remove(neoEObject, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
		return returnValue;
	}

	protected Object remove(PersistentEObject object, EAttribute eAttribute, int index) {
		Object[] array = (Object[]) getFromMap(object, eAttribute);
		Object oldValue = array[index];
		array = ArrayUtils.remove(array, index);
		map.put(Fun.t2(object.id(), eAttribute.getName()), array);
		return parseMapValue(eAttribute, oldValue);
	}

	protected Object remove(PersistentEObject object, EReference eReference, int index) {
		Object[] array = (Object[]) getFromMap(object, eReference);
		Object oldId = array[index];
		array = ArrayUtils.remove(array, index);
		map.put(Fun.t2(object.id(), eReference.getName()), array);
		return eObject((Id)oldId);

	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		Object movedElement = remove(object, feature, sourceIndex);
		add(object, feature, targetIndex, movedElement);
		return movedElement;
	}


	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject neoEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		map.remove(Fun.t2(neoEObject.id(), feature.getName()));
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0; 
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		Object[] array = (Object[]) getFromMap(neoEObject, feature);
		return array != null ? array.length : 0; 
	}


	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return indexOf(object, feature, value) != -1;
	}


	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		int resultValue;
		PersistentEObject neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		Object[] array = (Object[]) getFromMap(neoEObject, feature);
		if (array == null) {
			resultValue = ArrayUtils.INDEX_NOT_FOUND;
		} else if (feature instanceof EAttribute) {
			resultValue = ArrayUtils.indexOf(array, serializeToMapValue((EAttribute) feature, value));
		} else {
			PersistentEObject childEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class));
			resultValue = ArrayUtils.indexOf(array, childEObject.id());
		}
		return resultValue;
	}


	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		int resultValue;
		PersistentEObject neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		Object[] array = (Object[]) getFromMap(neoEObject, feature);
		if (array == null) {
			resultValue = ArrayUtils.INDEX_NOT_FOUND;
		} else if (feature instanceof EAttribute) {
			resultValue = ArrayUtils.lastIndexOf(array, serializeToMapValue((EAttribute) feature, value));
		} else {
			PersistentEObject childEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class));
			resultValue = ArrayUtils.lastIndexOf(array, childEObject.id());
		}
		return resultValue;
	}


	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject neoEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		map.put(Fun.t2(neoEObject.id(), feature.getName()), new Object[] {});
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
		PersistentEObject neoEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		ContainerInfo info = containersMap.get(neoEObject.id());
		if (info != null) {
			returnValue = (InternalEObject) eObject(info.containerId);
		}
		return returnValue;
	}


	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		PersistentEObject neoEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		ContainerInfo info = containersMap.get(neoEObject.id());
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
		InternalPersistentEObject neoEObject = null;
		if (id != null) {
			neoEObject = loadedEObjects.get(id);
			if (neoEObject == null) {
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
						neoEObject = (InternalPersistentEObject) eObject;
					} else {
						neoEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(eObject, InternalPersistentEObject.class));
					}
					neoEObject.id(id);
				} else {
					NeoLogger.log(NeoLogger.SEVERITY_ERROR,
							MessageFormat.format("Element {0} does not have an associated EClass", id));
				}
				loadedEObjects.put(id, neoEObject);
			}
			Objects.requireNonNull(neoEObject);
			if (neoEObject.resource() != resource()) {
				neoEObject.resource(resource());
			}
		}
		return neoEObject;
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
	
	protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
		return map.get(Fun.t2(object.id(), feature.getName()));
	}
	
	@Override
	public EList<EObject> getAllInstances(EClass eClass, boolean strict)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
}
