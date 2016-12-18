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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDBPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class DirectWriteBerkeleyDBIndicesStore extends DirectWriteBerkeleyDBStore {

    public DirectWriteBerkeleyDBIndicesStore(Resource.Internal resource, BerkeleyDBPersistenceBackend persistenceBackend) {
        super(resource, persistenceBackend);
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        PersistentEObject persistentEObject = PersistentEObject.from(object);
        FeatureKey featureKey = FeatureKey.from(persistentEObject, feature);
        // Make space for the new element
        Integer size = (Integer) persistenceBackend.valueOf(featureKey);
        if (isNull(size)) {
            size = 0;
        }
        for (int i = size - 1; i >= index; i--) {
            Object movingValue = persistenceBackend.valueAtIndex(featureKey.withPosition(i));
            persistenceBackend.storeValueAtIndex(featureKey.withPosition(i + 1), movingValue);
        }
        persistenceBackend.storeValue(featureKey, size + 1);

        // Add element
        MultivaluedFeatureKey multivaluedFeatureKey = featureKey.withPosition(index);
        if (feature instanceof EAttribute) {
            persistenceBackend.storeValueAtIndex(multivaluedFeatureKey, value);
        }
        else if (feature instanceof EReference) {
            PersistentEObject referencedEObject = PersistentEObject.from(value);
            updateContainment(persistentEObject, (EReference) feature, referencedEObject);
            updateInstanceOf(referencedEObject);
            persistenceBackend.storeValueAtIndex(multivaluedFeatureKey, referencedEObject.id());
        }
        else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        Integer size = (Integer) persistenceBackend.valueOf(featureKey);
        // Get element to remove
        Object returnValue = persistenceBackend.valueAtIndex(featureKey.withPosition(index));
        // Update indexes (element to remove is overwriten)
        for (int i = index + 1; i < size; i++) {
            Object movingValue = persistenceBackend.valueAtIndex(featureKey.withPosition(i));
            persistenceBackend.storeValueAtIndex(featureKey.withPosition(i - 1), movingValue);
        }
        persistenceBackend.storeValue(featureKey, size - 1);
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
        FeatureKey featureKey = FeatureKey.from(object, feature);
        persistenceBackend.removeFeature(featureKey);
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute eAttribute, int index) {
        MultivaluedFeatureKey featureKey = MultivaluedFeatureKey.from(object, eAttribute, index);
        Object value = persistenceBackend.valueAtIndex(featureKey);
        return parseProperty(eAttribute, value);
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference eReference, int index) {
        MultivaluedFeatureKey featureKey = MultivaluedFeatureKey.from(object, eReference, index);
        Object value = persistenceBackend.valueAtIndex(featureKey);
        return eObject((Id) value);
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute eAttribute, int index, Object value) {
        FeatureKey featureKey = FeatureKey.from(object, eAttribute);
        if (!eAttribute.isMany()) {
            persistenceBackend.storeValue(featureKey, NO_INDEX);
        }
        Object oldValue = persistenceBackend.storeValueAtIndex(featureKey.withPosition(index), serializeToProperty(eAttribute, value));
        return parseProperty(eAttribute, oldValue);
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference eReference, int index, PersistentEObject value) {
        FeatureKey featureKey = FeatureKey.from(object, eReference);
        if (!eReference.isMany()) {
            persistenceBackend.storeValue(featureKey, NO_INDEX);
        }
        Object returnValue = null;
        updateContainment(object, eReference, value);
        updateInstanceOf(value);
        Object oldId = persistenceBackend.storeValueAtIndex(featureKey.withPosition(index), value.id());
        if (nonNull(oldId)) {
            returnValue = eObject((Id) oldId);
        }
        return returnValue;
    }

    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(object, feature);
        Integer size = (Integer) persistenceBackend.valueOf(featureKey);
        return isNull(size) ? 0 : size;
    }
}
