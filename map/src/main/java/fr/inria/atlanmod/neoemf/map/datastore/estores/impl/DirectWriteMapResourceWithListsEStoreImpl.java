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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections4.CollectionUtils;
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
import org.mapdb.Fun.Tuple2;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.ContainerInfo;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.EClassInfo;

public class DirectWriteMapResourceWithListsEStoreImpl implements SearcheableResourceEStore {

	protected static final String INSTANCE_OF = "neoInstanceOf";
	protected static final String CONTAINER = "eContainer";

	@SuppressWarnings("unchecked")
	protected Map<Object, InternalPersistentEObject> loadedEObjects = new SoftValueHashMap();
	
	protected DB db;
	
	protected Map<Tuple2<Id, String>, Object> map;
	
	protected Map<Id, EClassInfo> instanceOfMap;

	protected Map<Id, ContainerInfo> containersMap;
	
	protected Resource.Internal resource;
	
	protected LoadingCache<Tuple2<Id, String>, Object> mapCache;

	public DirectWriteMapResourceWithListsEStoreImpl(Resource.Internal resource, DB db) {
		this.db = db;
		this.resource = resource;
		this.map = db.getHashMap("NeoEMF");
		this.instanceOfMap = db.getHashMap(INSTANCE_OF);
		this.containersMap = db.getHashMap(CONTAINER);
		this.mapCache = CacheBuilder.newBuilder().maximumSize(100).softValues().build(new CacheLoader<Tuple2<Id, String>, Object>() {
			@Override
			public Object load(Tuple2<Id, String> key) throws Exception {
				Object value = map.get(key);
				if (value == null) {
					return new ArrayList<>();
				} else if (value instanceof Object[]) {
					Object[] array = (Object[]) value;
					List<Object> list = new ArrayList<>(array.length + 10);
					CollectionUtils.addAll(list, array);
					return list;
				} else {
					return value;
				}
			}
		});
	}


	@Override
	public Resource.Internal resource() {
		return resource;
	}


	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			return get(PersistentEObject, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			return get(PersistentEObject, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}
	
	protected Object get(PersistentEObject object, EAttribute eAttribute, int index) {
		Object value = getFromMap(object, eAttribute);
		if (!eAttribute.isMany()) {
			return parseMapValue(eAttribute, value);
		} else {
			Object[] array = (Object[]) value;
			return parseMapValue(eAttribute, array[index]);
		}
	}

	protected Object get(PersistentEObject object, EReference eReference, int index) {
		Object value = getFromMap(object, eReference);
		if (!eReference.isMany()) {
			return eObject((Id) value);
		} else {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) value;
			return eObject((Id) list.get(index));
		}
	}


	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
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
		if (!eAttribute.isMany()) {
			Object oldValue = map.put(new Tuple2<Id, String>(object.id(), eAttribute.getName()), serializeToMapValue(eAttribute, value));
			return parseMapValue(eAttribute, oldValue);
		} else {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) getFromMap(object, eAttribute);
			Object oldValue = list.get(index); 
			list.set(index, serializeToMapValue(eAttribute, value));
			map.put(new Tuple2<Id, String>(object.id(), eAttribute.getName()), list.toArray());
			return parseMapValue(eAttribute, oldValue);
		}
	}

	protected Object set(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		if (!eReference.isMany()) {
			Object oldId = map.put(new Tuple2<Id, String>(object.id(), eReference.getName()), referencedObject.id());
			return oldId != null ? eObject((Id) oldId) : null;
		} else {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) getFromMap(object, eReference);
			Object oldId = list.get(index);
			list.set(index, referencedObject.id());
			map.put(new Tuple2<Id, String>(object.id(), eReference.getName()), list.toArray());
			return oldId != null ? eObject((Id) oldId) : null;
		}
	}


	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		return map.containsKey(new Tuple2<Id, String>(PersistentEObject.id(), feature.getName()));
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			add(PersistentEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			PersistentEObject referencedEObject = NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class);
			add(PersistentEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected void add(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(object, eAttribute);
		list.add(index, serializeToMapValue(eAttribute, value));
		map.put(new Tuple2<Id, String>(object.id(), eAttribute.getName()), list.toArray());
	}

	protected void add(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(object, eReference);
		list.add(index, referencedObject.id());
		map.put(new Tuple2<Id, String>(object.id(), eReference.getName()), list.toArray());
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			return remove(PersistentEObject, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			return remove(PersistentEObject, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected Object remove(PersistentEObject object, EAttribute eAttribute, int index) {
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(object, eAttribute);
		Object oldValue = list.get(index);
		list.remove(index);
		map.put(new Tuple2<Id, String>(object.id(), eAttribute.getName()), list.toArray());
		return parseMapValue(eAttribute, oldValue);
	}

	protected Object remove(PersistentEObject object, EReference eReference, int index) {
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(object, eReference);
		Object oldId = list.get(index);
		list.remove(index);
		map.put(new Tuple2<Id, String>(object.id(), eReference.getName()), list.toArray());
		return eObject((Id) oldId);

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
		map.remove(new Tuple2<Id, String>(PersistentEObject.id(), feature.getName()));
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0; 
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(PersistentEObject, feature);
		return list != null ? list.size() : 0; 
	}


	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return indexOf(object, feature, value) != -1;
	}


	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(PersistentEObject, feature);
		if (list== null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return list.indexOf(serializeToMapValue((EAttribute) feature, value));
		} else {
			PersistentEObject childEObject = NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class);
			return list.indexOf(childEObject.id());
		}
	}


	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(PersistentEObject, feature);
		if (list == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return list.lastIndexOf(serializeToMapValue((EAttribute) feature, value));
		} else {
			PersistentEObject childEObject = NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class);
			return list.lastIndexOf(childEObject.id());
		}
	}


	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject PersistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		map.put(new Tuple2<Id, String>(PersistentEObject.id(), feature.getName()), new Object[] {});
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
	
	protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
		if (!feature.isMany()) {
			return map.get(new Tuple2<Id, String>(object.id(), feature.getName()));
		} else {
			try {
				return mapCache.get(new Tuple2<Id, String>(object.id(), feature.getName()));
			} catch (ExecutionException e) {
			}
		}
		return null;
	}
	
}
