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

package fr.inria.atlanmod.neoemf.data.berkeleydb.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Objects;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class DirectWriteBerkeleyDbStore extends AbstractDirectWriteStore<BerkeleyDbPersistenceBackend> {

    /**
     * An in-memory cache for persistent EObjects.
     */
    protected final Cache<Id, PersistentEObject> persistentObjectsCache;

    public DirectWriteBerkeleyDbStore(Resource.Internal resource, BerkeleyDbPersistenceBackend persistenceBackend) {
        super(resource, persistenceBackend);
        this.persistentObjectsCache = Caffeine.newBuilder().maximumSize(10000).build();
    }

    @Override
    public boolean isSet(InternalEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        return persistenceBackend.isFeatureSet(featureKey);
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        persistenceBackend.removeFeature(featureKey);
    }

    @Override
    public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
        return indexOf(object, feature, value) != PersistentStore.NO_INDEX;
    }

    @Override
    public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        int resultValue;
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        Object[] array = (Object[]) getFromMap(persistentEObject, feature);
        if (isNull(array)) {
            resultValue = ArrayUtils.INDEX_NOT_FOUND;
        }
        else if (feature instanceof EAttribute) {
            resultValue = ArrayUtils.indexOf(array, serializeToProperty((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            resultValue = ArrayUtils.indexOf(array, childEObject.id());
        }
        return resultValue;
    }

    @Override
    public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
        int resultValue;
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        Object[] array = (Object[]) getFromMap(persistentEObject, feature);
        if (isNull(array)) {
            resultValue = ArrayUtils.INDEX_NOT_FOUND;
        }
        else if (feature instanceof EAttribute) {
            resultValue = ArrayUtils.lastIndexOf(array, serializeToProperty((EAttribute) feature, value));
        }
        else {
            PersistentEObject childEObject = PersistentEObject.from(value);
            resultValue = ArrayUtils.lastIndexOf(array, childEObject.id());
        }
        return resultValue;
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        persistenceBackend.storeValue(featureKey, new Object[]{});
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
        FeatureKey featureKey = FeatureKey.from(object, eAttribute);
        if (!eAttribute.isMany()) {
            Object oldValue = persistenceBackend.storeValue(featureKey, serializeToProperty(eAttribute, value));
            returnValue = parseProperty(eAttribute, oldValue);
        }
        else {
            Object[] array = (Object[]) getFromMap(featureKey);
            checkPositionIndex(index, array.length, "Invalid set index " + index);
            Object oldValue = array[index];
            array[index] = serializeToProperty(eAttribute, value);
            persistenceBackend.storeValue(featureKey, array);
            returnValue = parseProperty(eAttribute, oldValue);
        }
        return returnValue;
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        Object returnValue;
        FeatureKey featureKey = FeatureKey.from(object, eReference);
        updateContainment(object, eReference, value);
        updateInstanceOf(value);
        if (!eReference.isMany()) {
            Object oldId = persistenceBackend.storeValue(featureKey, value.id());
            returnValue = isNull(oldId) ? null : eObject((Id) oldId);
        }
        else {
            Object[] array = (Object[]) getFromMap(featureKey);
            checkPositionIndex(index, array.length, "Invalid set index " + index);
            Object oldId = array[index];
            array[index] = value.id();
            persistenceBackend.storeValue(featureKey, array);
            returnValue = isNull(oldId) ? null : eObject((Id) oldId);
        }
        return returnValue;
    }

    @Override
    protected void addAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        FeatureKey featureKey = FeatureKey.from(object, eAttribute);
        if (index == PersistentStore.NO_INDEX) {
            /*
             * Handle NO_INDEX index, which represent direct-append feature.
			 * The call to size should not cause an overhead because it would have been done in regular
			 * addUnique() otherwise.
			 */
            index = size(object, eAttribute);
        }
        Object[] array = (Object[]) getFromMap(featureKey);
        if (isNull(array)) {
            array = new Object[]{};
        }
        checkPositionIndex(index, array.length, "Invalid add index");
        array = ArrayUtils.add(array, index, serializeToProperty(eAttribute, value));
        persistenceBackend.storeValue(featureKey, array);
    }

    @Override
    protected void addReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        FeatureKey featureKey = FeatureKey.from(object, eReference);
        if (index == PersistentStore.NO_INDEX) {
            /*
             * Handle NO_INDEX index, which represent direct-append feature.
			 * The call to size should not cause an overhead because it would have been done in regular
			 * addUnique() otherwise.
			 */
            index = size(object, eReference);
        }
        updateContainment(object, eReference, value);
        updateInstanceOf(value);
        Object[] array = (Object[]) getFromMap(featureKey);
        if (isNull(array)) {
            array = new Object[]{};
        }
        checkPositionIndex(index, array.length, "Invalid add index");
        array = ArrayUtils.add(array, index, value.id());
        persistenceBackend.storeValue(featureKey, array);
        persistentObjectsCache.put(value.id(), value);
    }

    @Override
    protected Object removeAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        FeatureKey featureKey = FeatureKey.from(object, eAttribute);
        Object[] array = (Object[]) getFromMap(featureKey);
        checkPositionIndex(index, array.length, "Invalid remove index");
        Object oldValue = array[index];
        array = ArrayUtils.remove(array, index);
        persistenceBackend.storeValue(featureKey, array);
        return parseProperty(eAttribute, oldValue);
    }

    @Override
    protected Object removeReference(PersistentEObject object, EReference eReference, int index) {
        FeatureKey featureKey = FeatureKey.from(object, eReference);
        Object[] array = (Object[]) getFromMap(featureKey);
        checkPositionIndex(index, array.length, "Invalid remove index");
        Object oldId = array[index];
        array = ArrayUtils.remove(array, index);
        persistenceBackend.storeValue(featureKey, array);
        return eObject((Id) oldId);
    }

    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
        checkArgument(feature.isMany(), "Cannot compute size of a single-valued feature");
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        Object[] array = (Object[]) getFromMap(persistentEObject, feature);
        return isNull(array) ? 0 : array.length;
    }

    @Override
    public InternalEObject getContainer(InternalEObject object) {
        InternalEObject returnValue = null;
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        ContainerInfo info = persistenceBackend.containerFor(persistentEObject.id());
        if (nonNull(info)) {
            returnValue = (InternalEObject) eObject(info.id());
        }
        return returnValue;
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject object) {
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        ContainerInfo info = persistenceBackend.containerFor(persistentEObject.id());
        if (nonNull(info)) {
            EObject container = eObject(info.id());
            return container.eClass().getEStructuralFeature(info.name());
        }
        return null;
    }

    @Override
    public EObject eObject(Id id) {
        EClass eClass = resolveInstanceOf(id);
        PersistentEObject persistentEObject = persistentObjectsCache.get(id, new PersistentEObjectCacheLoader(eClass));
        if (persistentEObject.resource() != resource()) {
            persistentEObject.resource(resource());
        }
        return persistentEObject;
    }

    private EClass resolveInstanceOf(Id id) {
        EClass eClass = null;
        ClassInfo classInfo = persistenceBackend.metaclassFor(id);
        if (nonNull(classInfo)) {
            eClass = classInfo.eClass();
        }
        return eClass;
    }

    protected void updateContainment(PersistentEObject object, EReference eReference, PersistentEObject referencedObject) {
        if (eReference.isContainment()) {
            ContainerInfo info = persistenceBackend.containerFor(referencedObject.id());
            if (isNull(info) || !Objects.equals(info.id(), object.id())) {
                persistenceBackend.storeContainer(referencedObject.id(), ContainerInfo.from(object, eReference));
            }
        }
    }

    protected void updateInstanceOf(PersistentEObject object) {
        ClassInfo info = persistenceBackend.metaclassFor(object.id());
        if (isNull(info)) {
            persistenceBackend.storeMetaclass(object.id(), ClassInfo.from(object));
        }
    }

    protected Object getFromMap(FeatureKey featureKey) {
        return persistenceBackend.valueOf(featureKey);
    }

    protected Object getFromMap(PersistentEObject object, EStructuralFeature feature) {
        return getFromMap(FeatureKey.from(object, feature));
    }

    private static class PersistentEObjectCacheLoader implements Function<Id, PersistentEObject> {

        private final EClass eClass;

        private PersistentEObjectCacheLoader(EClass eClass) {
            this.eClass = eClass;
        }

        @Override
        public PersistentEObject apply(Id id) {
            PersistentEObject persistentEObject;
            if (nonNull(eClass)) {
                EObject eObject;
                if (Objects.equals(eClass.getEPackage().getClass(), EPackageImpl.class)) {
                    // Dynamic EMF
                    eObject = PersistenceFactory.getInstance().create(eClass);
                }
                else {
                    eObject = EcoreUtil.create(eClass);
                }
                persistentEObject = PersistentEObject.from(eObject);
                persistentEObject.id(id);
                persistentEObject.setMapped(true);
            }
            else {
                throw new RuntimeException("Element " + id + " does not have an associated EClass");
            }
            return persistentEObject;
        }
    }
}
