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

import com.google.common.cache.Cache;
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

import org.apache.commons.collections4.CollectionUtils;
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
import org.mapdb.DB;
import org.mapdb.Fun.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkNotNull;

public class DirectWriteMapResourceWithListsEStoreImpl implements SearcheableResourceEStore {

	private static final int DEFAULT_CACHE_SIZE = 100;

	private static final String INSTANCE_OF = "neoInstanceOf";
	private static final String CONTAINER = "eContainer";

	private final Cache<Id, InternalPersistentEObject> loadedEObjectsCache;

	private DB db;

	private Map<Tuple2<Id, String>, Object> map;

	private Map<Id, EClassInfo> instanceOfMap;

	private Map<Id, ContainerInfo> containersMap;

	private Resource.Internal resource;

	private final LoadingCache<Tuple2<Id, String>, Object> mapCache;

	@SuppressWarnings("unchecked")
	public DirectWriteMapResourceWithListsEStoreImpl(Resource.Internal resource, DB db) {
		this.loadedEObjectsCache = CacheBuilder.newBuilder().softValues().build();
		this.mapCache = CacheBuilder.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).softValues().build(new Tuple2CacheLoader());
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
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			returnValue = get(persistentEObject, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			returnValue = get(persistentEObject, (EReference) feature, index);
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
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) value;
			returnValue = eObject((Id) list.get(index));
		}
		return returnValue;
	}


	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		Object returnValue;
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
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
		Object returnValue;
		if (!eAttribute.isMany()) {
			Object oldValue = map.put(new Tuple2<>(object.id(), eAttribute.getName()), serializeToMapValue(eAttribute, value));
			returnValue = parseMapValue(eAttribute, oldValue);
		} else {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) getFromMap(object, eAttribute);
			Object oldValue = list.get(index);
			list.set(index, serializeToMapValue(eAttribute, value));
			map.put(new Tuple2<>(object.id(), eAttribute.getName()), list.toArray());
			returnValue = parseMapValue(eAttribute, oldValue);
		}
		return returnValue;
	}

	protected Object set(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		Object returnValue;
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		if (!eReference.isMany()) {
			Object oldId = map.put(new Tuple2<>(object.id(), eReference.getName()), referencedObject.id());
			returnValue = oldId != null ? eObject((Id) oldId) : null;
		} else {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) getFromMap(object, eReference);
			Object oldId = list.get(index);
			list.set(index, referencedObject.id());
			map.put(new Tuple2<>(object.id(), eReference.getName()), list.toArray());
			returnValue = oldId != null ? eObject((Id) oldId) : null;
		}
		return returnValue;
	}


	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		return map.containsKey(new Tuple2<>(persistentEObject.id(), feature.getName()));
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			add(persistentEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			PersistentEObject referencedEObject = NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class);
			add(persistentEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected void add(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(object, eAttribute);
		list.add(index, serializeToMapValue(eAttribute, value));
		map.put(new Tuple2<>(object.id(), eAttribute.getName()), list.toArray());
	}

	protected void add(PersistentEObject object, EReference eReference, int index, PersistentEObject referencedObject) {
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(object, eReference);
		list.add(index, referencedObject.id());
		map.put(new Tuple2<>(object.id(), eReference.getName()), list.toArray());
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		Object returnValue;
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		if (feature instanceof EAttribute) {
			returnValue = remove(persistentEObject, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			returnValue = remove(persistentEObject, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
		return returnValue;
	}

	protected Object remove(PersistentEObject object, EAttribute eAttribute, int index) {
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(object, eAttribute);
		Object oldValue = list.get(index);
		list.remove(index);
		map.put(new Tuple2<>(object.id(), eAttribute.getName()), list.toArray());
		return parseMapValue(eAttribute, oldValue);
	}

	protected Object remove(PersistentEObject object, EReference eReference, int index) {
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(object, eReference);
		Object oldId = list.get(index);
		list.remove(index);
		map.put(new Tuple2<>(object.id(), eReference.getName()), list.toArray());
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
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		map.remove(new Tuple2<>(persistentEObject.id(), feature.getName()));
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0; 
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) getFromMap(persistentEObject, feature);
		return list != null ? list.size() : 0; 
	}


	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return indexOf(object, feature, value) != -1;
	}


	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		int returnValue;
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		@SuppressWarnings("unchecked")
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
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		int returnValue;
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		@SuppressWarnings("unchecked")
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
		map.put(new Tuple2<>(persistentEObject.id(), feature.getName()), new Object[] {});
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
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		ContainerInfo info = containersMap.get(persistentEObject.id());
		if (info != null) {
			returnValue = (InternalEObject) eObject(info.containerId);
		}
		return returnValue;
	}


	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		ContainerInfo info = containersMap.get(persistentEObject.id());
		if (info != null) {
			EObject container = eObject(info.containerId);
			container.eClass().getEStructuralFeature(info.containingFeatureName);
		}
		return null;
	}


	@Override
	public EObject create(EClass eClass) {
		throw new IllegalStateException("This method should not be called");
	}


	@Override
	public EObject eObject(Id id) {
		InternalPersistentEObject neoEObject = null;
		if (id != null) {
			try {
				neoEObject = loadedEObjectsCache.get(id, new PersistentEObjectCacheLoader(id));
				if (neoEObject.resource() != resource()) {
					neoEObject.resource(resource());
				}
			} catch (ExecutionException e) {
				NeoLogger.error(e.getCause());
			}
		}
		return neoEObject;
	}
	

	protected EClass resolveInstanceOf(Id id) {
		EClass returnValue = null;
		EClassInfo eClassInfo = instanceOfMap.get(id);
		if (eClassInfo != null) {
			returnValue = (EClass) Registry.INSTANCE.getEPackage(eClassInfo.nsURI).getEClassifier(eClassInfo.className);
		}
		return returnValue;
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
		Object returnValue = null;
		if (!feature.isMany()) {
			returnValue = map.get(new Tuple2<>(object.id(), feature.getName()));
		} else {
			try {
				returnValue = mapCache.get(new Tuple2<>(object.id(), feature.getName()));
			} catch (ExecutionException e) {
				NeoLogger.warn(e.getCause());
			}
		}
		return returnValue;
	}
	
	@Override
	public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
		throw new UnsupportedOperationException();
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

	private class PersistentEObjectCacheLoader implements Callable<InternalPersistentEObject> {

		private Id id;

		public PersistentEObjectCacheLoader(Id id) {
			this.id = id;
		}

		@Override
        public InternalPersistentEObject call() throws Exception {
            InternalPersistentEObject persistentEObject;
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
                    persistentEObject = checkNotNull(
                            NeoEObjectAdapterFactoryImpl.getAdapter(eObject, InternalPersistentEObject.class));
                }
                persistentEObject.id(id);
            } else {
                // TODO Find a better exception to thrown
                throw new Exception("Element " + id + " does not have an associated EClass");
            }
			return persistentEObject;
        }
	}
}
