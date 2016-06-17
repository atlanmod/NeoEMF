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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.AbstractDirectWriteResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackend;
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
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple2;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;

public class DirectWriteMapResourceEStoreImpl extends AbstractDirectWriteResourceEStore<MapPersistenceBackend> {

	private static final String INSTANCE_OF = "neoInstanceOf";
	private static final String CONTAINER = "eContainer";

	private final Cache<Id, InternalPersistentEObject> loadedEObjectsCache;

	protected Map<Tuple2<Id, String>, Object> tuple2Map;

	private Map<Id, EClassInfo> instanceOfMap;

	private Map<Id, ContainerInfo> containersMap;

	public DirectWriteMapResourceEStoreImpl(Resource.Internal resource, MapPersistenceBackend persistenceBackend) {
		this(resource, persistenceBackend, persistenceBackend.getHashMap("NeoEMF"));
	}

	@SuppressWarnings("unchecked")
	protected DirectWriteMapResourceEStoreImpl(Resource.Internal resource, MapPersistenceBackend persistenceBackend, Map<?, ?> map) {
		super(resource, persistenceBackend);
		this.loadedEObjectsCache = CacheBuilder.newBuilder().softValues().build();
		this.tuple2Map = (Map<Tuple2<Id, String>, Object>) map;
		this.instanceOfMap = persistenceBackend.getHashMap(INSTANCE_OF);
		this.containersMap = persistenceBackend.getHashMap(CONTAINER);
		NeoLogger.info("DirectWriteMapResourceEStore Created");
	}

	@Override
	protected Object getWithAttribute(InternalPersistentEObject object, EAttribute eAttribute, int index) {
		Object returnValue;
		Object value = getFromMap(object, eAttribute);
		if (eAttribute.isMany()) {
			Object[] array = (Object[]) value;
			checkPositionIndex(index, array.length, "Invalid get index " + index);
			returnValue = parseProperty(eAttribute, array[index]);
		} else {
			returnValue = parseProperty(eAttribute, value);
		}
		return returnValue;
	}

	@Override
	protected Object getWithReference(InternalPersistentEObject object, EReference eReference, int index) {
		Object returnValue;
		Object value = getFromMap(object, eReference);
		if (eReference.isMany()) {
			Object[] array = (Object[]) value;
			checkPositionIndex(index, array.length, "Invalid get index " + index);
			returnValue = eObject((Id) array[index]);
		} else {
			returnValue = eObject((Id) value);
		}
		return returnValue;
	}

	@Override
	protected Object setWithAttribute(InternalPersistentEObject object, EAttribute eAttribute, int index, Object value) {
		Object returnValue;
		if (!eAttribute.isMany()) {
			Object oldValue = tuple2Map.put(Fun.t2(object.id(), eAttribute.getName()), serializeToProperty(eAttribute, value));
			returnValue = parseProperty(eAttribute, oldValue);
		} else {
			Object[] array = (Object[]) getFromMap(object, eAttribute);
			checkPositionIndex(index, array.length, "Invalid set index " + index);
			Object oldValue = array[index];
			array[index] = serializeToProperty(eAttribute, value);
			tuple2Map.put(Fun.t2(object.id(), eAttribute.getName()), array);
			returnValue = parseProperty(eAttribute, oldValue);
		}
		return returnValue;
	}

	@Override
	protected Object setWithReference(InternalPersistentEObject object, EReference eReference, int index, PersistentEObject value) {
		Object returnValue;
		updateContainment(object, eReference, value);
		updateInstanceOf(value);
		if (!eReference.isMany()) {
			Object oldId = tuple2Map.put(Fun.t2(object.id(), eReference.getName()), value.id());
			returnValue = oldId != null ? eObject((Id) oldId) : null;
		} else {
			Object[] array = (Object[]) getFromMap(object, eReference);
			checkPositionIndex(index, array.length, "Invalid set index " + index);
			Object oldId = array[index];
			array[index] = value.id();
			tuple2Map.put(Fun.t2(object.id(), eReference.getName()), array);
			returnValue = oldId != null ? eObject((Id) oldId) : null;
		}
		return returnValue;
	}


	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		return tuple2Map.containsKey(Fun.t2(persistentEObject.id(), feature.getName()));
	}

	@Override
	protected void addWithAttribute(InternalPersistentEObject object, EAttribute eAttribute, int index, Object value) {
		if(index == EStore.NO_INDEX) {
			/*
			 * Handle NO_INDEX index, which represent direct-append feature.
			 * The call to size should not cause an overhead because it would have been done in regular
			 * addUnique() otherwise.
			 */
			add(object, eAttribute, size(object, eAttribute), value);
		}
		Object[] array = (Object[]) getFromMap(object, eAttribute);
		if (array == null) {
			array = new Object[] {};
		}
		checkPositionIndex(index, array.length, "Invalid add index " + index);
		array = ArrayUtils.add(array, index, serializeToProperty(eAttribute, value));
		tuple2Map.put(Fun.t2(object.id(), eAttribute.getName()), array);
	}

	@Override
	protected void addWithReference(InternalPersistentEObject object, EReference eReference, int index, PersistentEObject value) {
		if(index == EStore.NO_INDEX) {
			/*
			 * Handle NO_INDEX index, which represent direct-append feature.
			 * The call to size should not cause an overhead because it would have been done in regular
			 * addUnique() otherwise.
			 */
			add(object, eReference, size(object, eReference), value);
		}
		updateContainment(object, eReference, value);
		updateInstanceOf(value);
		Object[] array = (Object[]) getFromMap(object, eReference);
		if (array == null) {
			array = new Object[] {};
		}
		checkPositionIndex(index, array.length, "Invalid add index " + index);
		array = ArrayUtils.add(array, index, value.id());
		tuple2Map.put(Fun.t2(object.id(), eReference.getName()), array);
		loadedEObjectsCache.put(value.id(),(InternalPersistentEObject)value);
	}

	@Override
	protected Object removeWithAttribute(InternalPersistentEObject object, EAttribute eAttribute, int index) {
		Object[] array = (Object[]) getFromMap(object, eAttribute);
		checkPositionIndex(index, array.length, "Invalid remove index " + index);
		Object oldValue = array[index];
		array = ArrayUtils.remove(array, index);
		tuple2Map.put(Fun.t2(object.id(), eAttribute.getName()), array);
		return parseProperty(eAttribute, oldValue);
	}

	@Override
	protected Object removeWithReference(InternalPersistentEObject object, EReference eReference, int index) {
		Object[] array = (Object[]) getFromMap(object, eReference);
		checkPositionIndex(index, array.length, "Invalid remove index " + index);
		Object oldId = array[index];
		array = ArrayUtils.remove(array, index);
		tuple2Map.put(Fun.t2(object.id(), eReference.getName()), array);
		return eObject((Id)oldId);
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		tuple2Map.remove(Fun.t2(persistentEObject.id(), feature.getName()));
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		Object[] array = (Object[]) getFromMap(persistentEObject, feature);
		return array != null ? array.length : 0; 
	}

	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return indexOf(object, feature, value) != -1;
	}

	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		int resultValue;
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		Object[] array = (Object[]) getFromMap(persistentEObject, feature);
		if (array == null) {
			resultValue = ArrayUtils.INDEX_NOT_FOUND;
		} else if (feature instanceof EAttribute) {
			resultValue = ArrayUtils.indexOf(array, serializeToProperty((EAttribute) feature, value));
		} else {
			PersistentEObject childEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class));
			resultValue = ArrayUtils.indexOf(array, childEObject.id());
		}
		return resultValue;
	}

	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		int resultValue;
		PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
		Object[] array = (Object[]) getFromMap(persistentEObject, feature);
		if (array == null) {
			resultValue = ArrayUtils.INDEX_NOT_FOUND;
		} else if (feature instanceof EAttribute) {
			resultValue = ArrayUtils.lastIndexOf(array, serializeToProperty((EAttribute) feature, value));
		} else {
			PersistentEObject childEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(value, PersistentEObject.class));
			resultValue = ArrayUtils.lastIndexOf(array, childEObject.id());
		}
		return resultValue;
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class));
		tuple2Map.put(Fun.t2(persistentEObject.id(), feature.getName()), new Object[] {});
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
	public EObject eObject(Id id) {
		InternalPersistentEObject persistentEObject = null;
		if (id != null) {
			try {
				persistentEObject = loadedEObjectsCache.get(id, new PersistentEObjectCacheLoader(id));
				if (persistentEObject.resource() != resource()) {
					persistentEObject.resource(resource());
				}
			} catch (ExecutionException e) {
				NeoLogger.error(e.getCause());
			}
		}
		return persistentEObject;
	}
	
	private EClass resolveInstanceOf(Id id) {
		EClass eClass = null;
		EClassInfo eClassInfo = instanceOfMap.get(id);
		if (eClassInfo != null) {
			eClass = (EClass) Registry.INSTANCE.getEPackage(eClassInfo.nsURI).getEClassifier(eClassInfo.className);
		}
		return eClass;
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
	
	protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
		return tuple2Map.get(Fun.t2(object.id(), feature.getName()));
	}
	
	@Override
	public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
		throw new UnsupportedOperationException();
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
