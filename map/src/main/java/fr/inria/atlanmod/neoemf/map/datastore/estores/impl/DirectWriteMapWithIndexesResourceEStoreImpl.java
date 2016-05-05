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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.ContainerInfo;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.EClassInfo;

public class DirectWriteMapWithIndexesResourceEStoreImpl implements SearcheableResourceEStore {

	protected static final String INSTANCE_OF = "neoInstanceOf";
	protected static final String CONTAINER = "eContainer";

	@SuppressWarnings("unchecked")
	protected Map<Object, InternalPersistentEObject> loadedEObjects = new SoftValueHashMap();
	
	protected DB db;
	
	protected Map<Tuple3<Id, String, Integer>, Object> map;
	protected Map<Tuple2<Id, String>, Integer> sizesMap;
	
	protected Map<Id, EClassInfo> instanceOfMap;

	protected Map<Id, ContainerInfo> containersMap;
	
	protected Resource.Internal resource;

	public DirectWriteMapWithIndexesResourceEStoreImpl(Resource.Internal resource, DB db) {
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
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			Object value = map.get(Fun.t3(PersistentEObject.id(), feature.getName(), index));
			return parseMapValue((EAttribute) feature, value);
		} else if (feature instanceof EReference) {
			Object value = map.get(Fun.t3(PersistentEObject.id(), feature.getName(), index));
			return eObject((Id) value);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}
	
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (!feature.isMany()) {
			sizesMap.put(Fun.t2(PersistentEObject.id(), feature.getName()), EStore.NO_INDEX);
		}
		if (feature instanceof EAttribute) {
			return set(PersistentEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			PersistentEObject referencedEObject = NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class);
			return set(PersistentEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
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
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		return sizesMap.containsKey(Fun.t2(PersistentEObject.id(), feature.getName()));
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		// Make space for the new element
		Integer size = sizesMap.get(Fun.t2(PersistentEObject.id(), feature.getName()));
		if (size == null) {
			size = 0;
		}
		for (int i = size - 1; i >= index; i--) {
			Object movingValue = map.get(Fun.t3(PersistentEObject.id(),  feature.getName(), i));
			map.put(Fun.t3(PersistentEObject.id(), feature.getName(), i + 1), movingValue);
		}
		sizesMap.put(Fun.t2(PersistentEObject.id(), feature.getName()), size + 1);
		
		// add element
		if (feature instanceof EAttribute) {
			map.put(Fun.t3(PersistentEObject.id(), feature.getName(), index), value);
		} else if (feature instanceof EReference) {
			PersistentEObject referencedEObject = NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class);
			updateContainment(PersistentEObject, (EReference) feature, referencedEObject);
			updateInstanceOf(referencedEObject);
			map.put(Fun.t3(PersistentEObject.id(), feature.getName(), index), referencedEObject.id());
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);

		Integer size = sizesMap.get(Fun.t2(PersistentEObject.id(), feature.getName()));
		// Gete element to remove
		Object returnValue = map.get(Fun.t3(PersistentEObject.id(),feature.getName(), index));
		// Update indexes (element to remove is overwriten)
		for (int i = index + 1; i < size; i++) {
			Object movingValue = map.get(Fun.t3(PersistentEObject.id(), feature.getName(), i));
			map.put(Fun.t3(PersistentEObject.id(), feature.getName(), i - 1), movingValue);
		}
		sizesMap.put(Fun.t2(PersistentEObject.id(), feature.getName()), size - 1);
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
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		sizesMap.remove(Fun.t2(PersistentEObject.id(), feature.getName()));
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0; 
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		Integer size = sizesMap.get(Fun.t2(PersistentEObject.id(), feature.getName()));
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
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		Integer size = sizesMap.remove(Fun.t2(PersistentEObject.id(), feature.getName()));
//		for (int i = 0; i < size; i++) {
//			map.remove(Fun.t3(PersistentEObject.id(), feature.getName(), i));
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
		T[] result = null;
		if (array.length < size) {
			result = Arrays.copyOf(array, size);
		} else {
			result = array;
		}
		for (int index = 0; index < size; index++) {
			result[index] = (T) get(object, feature, index);
		}
		return result;
	}


	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		return toArray(object, feature).hashCode();
	}


	@Override
	public InternalEObject getContainer(InternalEObject object) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		ContainerInfo info = containersMap.get(PersistentEObject.id());
		if (info != null) {
			return (InternalEObject) eObject(info.containerId);
		}
		return null;
	}


	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		ContainerInfo info = containersMap.get(PersistentEObject.id());
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
		if (id == null) {
			return null;
		}
		InternalPersistentEObject PersistentEObject = loadedEObjects.get(id);
		if (PersistentEObject == null) {
			EClass eClass = resolveInstanceOf(id);
			if (eClass != null) {
			    EObject eObject = null;
                if(eClass.getEPackage().getClass().equals(EPackageImpl.class)) {
                    // Dynamic EMF
                    eObject = PersistenceFactory.eINSTANCE.create(eClass);
                } else {
                    eObject = EcoreUtil.create(eClass);
                }
				if (eObject instanceof InternalPersistentEObject) {
					PersistentEObject = (InternalPersistentEObject) eObject;
				} else {
					PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(eObject, InternalPersistentEObject.class);
				}
				PersistentEObject.id(id);
			} else {
				NeoLogger.log(NeoLogger.SEVERITY_ERROR, 
						MessageFormat.format("Element {0} does not have an associated EClass", id));
			}
			loadedEObjects.put(id, PersistentEObject);
		}
		if (PersistentEObject.resource() != resource()) {
			PersistentEObject.resource(resource());
		}
		return PersistentEObject;
	}
	

	protected EClass resolveInstanceOf(Id id) {
		EClassInfo eClassInfo = instanceOfMap.get(id);
		if (eClassInfo != null) {
			EClass eClass = (EClass) Registry.INSTANCE.getEPackage(eClassInfo.nsURI).getEClassifier(eClassInfo.className);
			return eClass;
		}
		return null;
	}
	
	protected void updateContainment(PersistentEObject object, EReference eReference, PersistentEObject referencedObject) {
		if (eReference.isContainment()) {
			ContainerInfo info = containersMap.get(referencedObject.id());
			if (info == null || !(info.containerId.equals(object.id()))) {
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
