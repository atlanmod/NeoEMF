/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
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
import fr.inria.atlanmod.neoemf.core.impl.PersistentEObjectAdapter;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.AbstractDirectWriteResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackend;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.ContainerInfo;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo.EClassInfo;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

public class DirectWriteMapResourceEStoreImpl extends AbstractDirectWriteResourceEStore<MapPersistenceBackend> {

    /**
     * An in-memory cache for persistent EObjects.
     */
    protected final Cache<Id, PersistentEObject> loadedEObjectsCache;


    public DirectWriteMapResourceEStoreImpl(Resource.Internal resource, MapPersistenceBackend persistenceBackend) {
        super(resource, persistenceBackend);
        this.loadedEObjectsCache = CacheBuilder.newBuilder().softValues().build();

        NeoLogger.info("DirectWriteMapResourceEStore Created");
    }

    @Override
    public boolean isSet(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject persistentEObject = PersistentEObjectAdapter.getAdapter(object);
        return persistenceBackend.isFeatureSet(new FeatureKey(persistentEObject.id(), feature.getName()));
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject persistentEObject = PersistentEObjectAdapter.getAdapter(object);
        persistenceBackend.removeFeature(new FeatureKey(persistentEObject.id(), feature.getName()));
    }

    @Override
    public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
        return indexOf(object, feature, value) != -1;
    }

    @Override
    public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        int resultValue;
        PersistentEObject persistentEObject = PersistentEObjectAdapter.getAdapter(object);
        Object[] array = (Object[]) getFromMap(persistentEObject, feature);
        if (isNull(array)) {
            resultValue = ArrayUtils.INDEX_NOT_FOUND;
        }
        else if (feature instanceof EAttribute) {
            resultValue = ArrayUtils.indexOf(array, serializeToProperty((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObjectAdapter.getAdapter(value);
            resultValue = ArrayUtils.indexOf(array, childEObject.id());
        }
        return resultValue;
    }

    @Override
    public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        int resultValue;
        PersistentEObject persistentEObject = PersistentEObjectAdapter.getAdapter(object);
        Object[] array = (Object[]) getFromMap(persistentEObject, feature);
        if (isNull(array)) {
            resultValue = ArrayUtils.INDEX_NOT_FOUND;
        }
        else if (feature instanceof EAttribute) {
            resultValue = ArrayUtils.lastIndexOf(array, serializeToProperty((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObjectAdapter.getAdapter(value);
            resultValue = ArrayUtils.lastIndexOf(array, childEObject.id());
        }
        return resultValue;
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject persistentEObject = PersistentEObjectAdapter.getAdapter(object);
        persistenceBackend.storeValue(new FeatureKey(persistentEObject.id(), feature.getName()), new Object[]{});
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        Object returnValue;
        Object value = getFromMap(object, eAttribute);
        if (eAttribute.isMany()) {
            Object[] array = (Object[]) value;
            checkPositionIndex(index, array.length, "Invalid get index " + index);
            returnValue = parseProperty(eAttribute, array[index]);
        }
        else {
            returnValue = parseProperty(eAttribute, value);
        }
        return returnValue;
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference eReference, int index) {
        Object returnValue;
        Object value = getFromMap(object, eReference);
        if (eReference.isMany()) {
            Object[] array = (Object[]) value;
            checkPositionIndex(index, array.length, "Invalid get index " + index);
            returnValue = eObject((Id) array[index]);
        }
        else {
            returnValue = eObject((Id) value);
        }
        return returnValue;
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        Object returnValue;
        if (!eAttribute.isMany()) {
            Object oldValue = persistenceBackend.storeValue(new FeatureKey(object.id(), eAttribute.getName()), serializeToProperty(eAttribute, value));
            returnValue = parseProperty(eAttribute, oldValue);
        }
        else {
            Object[] array = (Object[]) getFromMap(object, eAttribute);
            checkPositionIndex(index, array.length, "Invalid set index " + index);
            Object oldValue = array[index];
            array[index] = serializeToProperty(eAttribute, value);
            persistenceBackend.storeValue(new FeatureKey(object.id(), eAttribute.getName()), array);
            returnValue = parseProperty(eAttribute, oldValue);
        }
        return returnValue;
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        Object returnValue;
        updateContainment(object, eReference, value);
        updateInstanceOf(value);
        if (!eReference.isMany()) {
            Object oldId = persistenceBackend.storeValue(new FeatureKey(object.id(), eReference.getName()), value.id());
            returnValue = isNull(oldId) ? null : eObject((Id) oldId);
        }
        else {
            Object[] array = (Object[]) getFromMap(object, eReference);
            checkPositionIndex(index, array.length, "Invalid set index " + index);
            Object oldId = array[index];
            array[index] = value.id();
            persistenceBackend.storeValue(new FeatureKey(object.id(), eReference.getName()), array);
            returnValue = isNull(oldId) ? null : eObject((Id) oldId);
        }
        return returnValue;
    }

    @Override
    protected void addAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        if (index == EStore.NO_INDEX) {
            /*
             * Handle NO_INDEX index, which represent direct-append feature.
			 * The call to size should not cause an overhead because it would have been done in regular
			 * addUnique() otherwise.
			 */
            add(object, eAttribute, size(object, eAttribute), value);
        }
        Object[] array = (Object[]) getFromMap(object, eAttribute);
        if (isNull(array)) {
            array = new Object[]{};
        }
        checkPositionIndex(index, array.length, "Invalid add index " + index);
        array = ArrayUtils.add(array, index, serializeToProperty(eAttribute, value));
        persistenceBackend.storeValue(new FeatureKey(object.id(), eAttribute.getName()), array);
    }

    @Override
    protected void addReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        if (index == EStore.NO_INDEX) {
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
        if (isNull(array)) {
            array = new Object[]{};
        }
        checkPositionIndex(index, array.length, "Invalid add index " + index);
        array = ArrayUtils.add(array, index, value.id());
        persistenceBackend.storeValue(new FeatureKey(object.id(), eReference.getName()), array);
        loadedEObjectsCache.put(value.id(), value);
    }

    @Override
    protected Object removeAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        Object[] array = (Object[]) getFromMap(object, eAttribute);
        checkPositionIndex(index, array.length, "Invalid remove index " + index);
        Object oldValue = array[index];
        array = ArrayUtils.remove(array, index);
        persistenceBackend.storeValue(new FeatureKey(object.id(), eAttribute.getName()), array);
        return parseProperty(eAttribute, oldValue);
    }

    @Override
    protected Object removeReference(PersistentEObject object, EReference eReference, int index) {
        Object[] array = (Object[]) getFromMap(object, eReference);
        checkPositionIndex(index, array.length, "Invalid remove index " + index);
        Object oldId = array[index];
        array = ArrayUtils.remove(array, index);
        persistenceBackend.storeValue(new FeatureKey(object.id(), eReference.getName()), array);
        return eObject((Id) oldId);
    }

    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
        checkArgument(feature.isMany(), "Cannot compute size of a single-valued feature");
        PersistentEObject persistentEObject = PersistentEObjectAdapter.getAdapter(object);
        Object[] array = (Object[]) getFromMap(persistentEObject, feature);
        return isNull(array) ? 0 : array.length;
    }

    @Override
    public InternalEObject getContainer(InternalEObject object) {
        InternalEObject returnValue = null;
        PersistentEObject persistentEObject = PersistentEObjectAdapter.getAdapter(object);
        ContainerInfo info = persistenceBackend.containerFor(persistentEObject.id());
        if (!isNull(info)) {
            returnValue = (InternalEObject) eObject(info.containerId);
        }
        return returnValue;
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject object) {
        PersistentEObject persistentEObject = PersistentEObjectAdapter.getAdapter(object);
        ContainerInfo info = persistenceBackend.containerFor(persistentEObject.id());
        if (!isNull(info)) {
            EObject container = eObject(info.containerId);
            return container.eClass().getEStructuralFeature(info.containingFeatureName);
        }
        return null;
    }

    @Override
    public EObject eObject(Id id) {
        PersistentEObject persistentEObject = null;
        if (!isNull(id)) {
            try {
                persistentEObject = loadedEObjectsCache.get(id, new PersistentEObjectCacheLoader(id));
                if (persistentEObject.resource() != resource()) {
                    persistentEObject.resource(resource());
                }
            }
            catch (ExecutionException e) {
                NeoLogger.error(e.getCause());
            }
        }
        return persistentEObject;
    }

    private EClass resolveInstanceOf(Id id) {
        EClass eClass = null;
        EClassInfo eClassInfo = persistenceBackend.metaclassFor(id);
        if (!isNull(eClassInfo)) {
            eClass = eClassInfo.eClass();
        }
        return eClass;
    }

    protected void updateContainment(PersistentEObject object, EReference eReference, PersistentEObject referencedObject) {
        if (eReference.isContainment()) {
            ContainerInfo info = persistenceBackend.containerFor(referencedObject.id());
            if (isNull(info) || !info.containerId.equals(object.id())) {
                persistenceBackend.storeContainer(referencedObject.id(), new ContainerInfo(object.id(), eReference.getName()));
            }
        }
    }

    protected void updateInstanceOf(PersistentEObject object) {
        EClassInfo info = persistenceBackend.metaclassFor(object.id());
        if (isNull(info)) {
            persistenceBackend.storeMetaclass(object.id(), new EClassInfo(object));
        }
    }

    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        return persistenceBackend.valueOf(new FeatureKey(object.id(), feature.getName()));
    }

    private class PersistentEObjectCacheLoader implements Callable<PersistentEObject> {

        private final Id id;

        public PersistentEObjectCacheLoader(Id id) {
            this.id = id;
        }

        @Override
        public PersistentEObject call() throws Exception {
            PersistentEObject persistentEObject;
            EClass eClass = resolveInstanceOf(id);
            if (!isNull(eClass)) {
                EObject eObject;
                if (eClass.getEPackage().getClass().equals(EPackageImpl.class)) {
                    // Dynamic EMF
                    eObject = PersistenceFactory.getInstance().create(eClass);
                }
                else {
                    eObject = EcoreUtil.create(eClass);
                }
                if (eObject instanceof PersistentEObject) {
                    persistentEObject = (PersistentEObject) eObject;
                }
                else {
                    persistentEObject = PersistentEObjectAdapter.getAdapter(eObject);
                }
                persistentEObject.id(id);
            }
            else {
                // TODO Find a better exception to thrown
                throw new Exception("Element " + id + " does not have an associated EClass");
            }
            return persistentEObject;
        }
    }
}
