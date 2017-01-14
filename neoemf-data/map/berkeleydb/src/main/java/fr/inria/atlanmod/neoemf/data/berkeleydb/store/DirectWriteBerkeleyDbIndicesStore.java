/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
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
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbPersistenceBackend;
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

public class DirectWriteBerkeleyDbIndicesStore extends DirectWriteBerkeleyDbStore {

    /**
     * Constructs a new {@code DirectWriteBerkeleyDbIndicesStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteBerkeleyDbIndicesStore(Resource.Internal resource, BerkeleyDbPersistenceBackend backend) {
        super(resource, backend);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        FeatureKey featureKey = FeatureKey.from(object, feature);
        // Make space for the new element
        Integer size = (Integer) backend.valueOf(featureKey);
        if (isNull(size)) {
            size = 0;
        }
        for (int i = size - 1; i >= index; i--) {
            Object movingValue = backend.valueAtIndex(featureKey.withPosition(i));
            backend.storeValueAtIndex(featureKey.withPosition(i + 1), movingValue);
        }
        backend.storeValue(featureKey, size + 1);

        // Add element
        MultivaluedFeatureKey multivaluedFeatureKey = featureKey.withPosition(index);
        if (feature instanceof EAttribute) {
            backend.storeValueAtIndex(multivaluedFeatureKey, value);
        }
        else if (feature instanceof EReference) {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            updateContainment(object, (EReference) feature, referencedObject);
            updateInstanceOf(referencedObject);
            backend.storeValueAtIndex(multivaluedFeatureKey, referencedObject.id());
        }
        else {
            throw new IllegalArgumentException(feature.toString());
        }
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        Integer size = (Integer) backend.valueOf(featureKey);
        // Get element to remove
        Object old = backend.valueAtIndex(featureKey.withPosition(index));
        // Update indexes (element to remove is overwriten)
        for (int i = index + 1; i < size; i++) {
            Object movingValue = backend.valueAtIndex(featureKey.withPosition(i));
            backend.storeValueAtIndex(featureKey.withPosition(i - 1), movingValue);
        }
        backend.storeValue(featureKey, size - 1);
        return old;
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        return ArrayUtils.indexOf(toArray(internalObject, feature), value);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        return ArrayUtils.indexOf(toArray(internalObject, feature), value);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        backend.removeFeature(featureKey);
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        MultivaluedFeatureKey featureKey = MultivaluedFeatureKey.from(object, attribute, index);
        Object soughtAttribute = backend.valueAtIndex(featureKey);
        return parseProperty(attribute, soughtAttribute);
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference reference, int index) {
        MultivaluedFeatureKey featureKey = MultivaluedFeatureKey.from(object, reference, index);
        Object soughtReference = backend.valueAtIndex(featureKey);
        return eObject((Id) soughtReference);
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        FeatureKey featureKey = FeatureKey.from(object, attribute);
        if (!attribute.isMany()) {
            backend.storeValue(featureKey, NO_INDEX);
        }
        Object old = backend.storeValueAtIndex(featureKey.withPosition(index), serializeToProperty(attribute, value));
        return parseProperty(attribute, old);
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        FeatureKey featureKey = FeatureKey.from(object, reference);
        if (!reference.isMany()) {
            backend.storeValue(featureKey, NO_INDEX);
        }
        Object old = null;
        updateContainment(object, reference, value);
        updateInstanceOf(value);
        Object oldId = backend.storeValueAtIndex(featureKey.withPosition(index), value.id());
        if (nonNull(oldId)) {
            old = eObject((Id) oldId);
        }
        return old;
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        Integer size = (Integer) backend.valueOf(featureKey);
        return isNull(size) ? 0 : size;
    }
}
