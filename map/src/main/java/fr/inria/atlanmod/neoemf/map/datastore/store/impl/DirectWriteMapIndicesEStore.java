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

package fr.inria.atlanmod.neoemf.map.datastore.store.impl;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.store.impl.cache.FeatureKey;
import fr.inria.atlanmod.neoemf.datastore.store.impl.cache.MultivaluedFeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;

import static java.util.Objects.isNull;


public class DirectWriteMapIndicesEStore extends DirectWriteMapEStore {

    public DirectWriteMapIndicesEStore(Resource.Internal resource, MapPersistenceBackend persistenceBackend) {
        super(resource, persistenceBackend);
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        // Make space for the new element
        Integer size = (Integer) persistenceBackend.valueOf(new FeatureKey(persistentEObject.id(), feature.getName()));
        if (isNull(size)) {
            size = 0;
        }
        for (int i = size - 1; i >= index; i--) {
            Object movingValue = persistenceBackend.valueAtIndex(new MultivaluedFeatureKey(persistentEObject.id(), feature.getName(), i));
            persistenceBackend.storeValueAtIndex(new MultivaluedFeatureKey(persistentEObject.id(), feature.getName(), i + 1), movingValue);
        }
        persistenceBackend.storeValue(new FeatureKey(persistentEObject.id(), feature.getName()), size + 1);

        // add element
        if (feature instanceof EAttribute) {
            persistenceBackend.storeValueAtIndex(new MultivaluedFeatureKey(persistentEObject.id(), feature.getName(), index), value);
        }
        else if (feature instanceof EReference) {
            PersistentEObject referencedEObject = PersistentEObject.from(value);
            updateContainment(persistentEObject, (EReference) feature, referencedEObject);
            updateInstanceOf(referencedEObject);
            persistenceBackend.storeValueAtIndex(new MultivaluedFeatureKey(persistentEObject.id(), feature.getName(), index), referencedEObject.id());
        }
        else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        Integer size = (Integer) persistenceBackend.valueOf(new FeatureKey(persistentEObject.id(), feature.getName()));
        // Get element to remove
        Object returnValue = persistenceBackend.valueAtIndex(new MultivaluedFeatureKey(persistentEObject.id(), feature.getName(), index));
        // Update indexes (element to remove is overwriten)
        for (int i = index + 1; i < size; i++) {
            Object movingValue = persistenceBackend.valueAtIndex(new MultivaluedFeatureKey(persistentEObject.id(), feature.getName(), i));
            persistenceBackend.storeValueAtIndex(new MultivaluedFeatureKey(persistentEObject.id(), feature.getName(), i - 1), movingValue);
        }
        persistenceBackend.storeValue(new FeatureKey(persistentEObject.id(), feature.getName()), size - 1);
        return returnValue;
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
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        persistenceBackend.removeFeature(new FeatureKey(persistentEObject.id(), feature.getName()));
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        Object value = persistenceBackend.valueAtIndex(new MultivaluedFeatureKey(object.id(), eAttribute.getName(), index));
        return parseProperty(eAttribute, value);
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference eReference, int index) {
        Object value = persistenceBackend.valueAtIndex(new MultivaluedFeatureKey(object.id(), eReference.getName(), index));
        return eObject((Id) value);
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        if (!eAttribute.isMany()) {
            persistenceBackend.storeValue(new FeatureKey(object.id(), eAttribute.getName()), EStore.NO_INDEX);
        }
        Object oldValue = persistenceBackend.storeValueAtIndex(new MultivaluedFeatureKey(object.id(), eAttribute.getName(), index), serializeToProperty(eAttribute, value));
        return parseProperty(eAttribute, oldValue);
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        if (!eReference.isMany()) {
            persistenceBackend.storeValue(new FeatureKey(object.id(), eReference.getName()), EStore.NO_INDEX);
        }
        Object returnValue = null;
        updateContainment(object, eReference, value);
        updateInstanceOf(value);
        Object oldId = persistenceBackend.storeValueAtIndex(new MultivaluedFeatureKey(object.id(), eReference.getName(), index), value.id());
        if (!isNull(oldId)) {
            returnValue = eObject((Id) oldId);
        }
        return returnValue;
    }

    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        Integer size = (Integer) persistenceBackend.valueOf(new FeatureKey(persistentEObject.id(), feature.getName()));

        return isNull(size) ? 0 : size;
    }
}
