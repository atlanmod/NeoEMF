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

package fr.inria.atlanmod.neoemf.data.map.core.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DefaultDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.Collection;
import java.util.Collections;

/**
 * A {@link DirectWriteMapStore} that persists {@link Collection} indices instead of serialized arrays.
 * <p>
 * Indices are persisted with dedicated {@link FeatureKey}s containing the index of the element to
 * store. Using this approach avoid to deserialize entire {@link Collection}s to retrieve a single
 * element, which can be an important bottleneck in terms of execution time and memory consumption
 * if the underlying model contains very large {@link Collections}.
 * <p>
 * This class re-implements {@link EStructuralFeature} accessors and mutators as well as {@link Collection}
 * operations such as {@code size}, {@code clear}, or {@code indexOf}.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it
 * (see {@link AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 *
 * @see DirectWriteMapStore
 * @see MapBackend
 * @see AbstractPersistentStoreDecorator
 */
public class DirectWriteMapStore extends DefaultDirectWriteStore<PersistenceBackend> {

    /**
     * Constructs a new {@code DirectWriteMapStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteMapStore(PersistentResource resource, PersistenceBackend backend) {
        super(resource, backend);
    }

    @Override
    public boolean contains(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        return indexOf(internalObject, feature, value) != PersistentStore.NO_INDEX;
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        return ArrayUtils.indexOf(toArray(internalObject, feature), value);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        return ArrayUtils.lastIndexOf(toArray(internalObject, feature), value);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        PersistentEObject object = PersistentEObject.from(internalObject);

        FeatureKey featureKey = FeatureKey.from(object, feature);

        // Make space for the new element
        int size = (Integer) backend.valueOf(featureKey).orElse(0);

        for (int i = size - 1; i >= index; i--) {
            Object movingValue = backend.valueOf(featureKey.withPosition(i)).orElse(null);
            backend.valueFor(featureKey.withPosition(i + 1), movingValue);
        }
        backend.valueFor(featureKey, size + 1);

        // Add element
        MultivaluedFeatureKey multivaluedFeatureKey = featureKey.withPosition(index);
        if (feature instanceof EAttribute) {
            backend.valueFor(multivaluedFeatureKey, value);
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            updateContainment(object, (EReference) feature, referencedObject);
            updateInstanceOf(referencedObject);
            backend.valueFor(multivaluedFeatureKey, referencedObject.id());
        }
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        int size = (Integer) backend.valueOf(featureKey).orElse(0);

        // Get element to remove
        Object old = backend.valueOf(featureKey.withPosition(index)).orElse(null);

        // Update indexes (element to remove is overwritten)
        for (int i = index + 1; i < size; i++) {
            Object movingValue = backend.valueOf(featureKey.withPosition(i)).orElse(null);
            backend.valueFor(featureKey.withPosition(i - 1), movingValue);
        }
        backend.valueFor(featureKey, size - 1);
        return old;
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        boolean isReference = feature instanceof EReference;

        if (feature.isMany()) {
            int length = (int) backend.valueOf(FeatureKey.from(object, feature)).orElse(0);
            if (isReference) {
                return multiValuedReferenceToArray(object, (EReference) feature, new PersistentEObject[length]);
            }
            else {
                return multiValuedAttributeToArray(object, (EAttribute) feature, new Object[length]);
            }
        }
        else { //monovalued
            if (isReference) {
                return monoValuedReferenceToArray(object, (EReference) feature, new PersistentEObject[1]);
            }
            else {
                return monoValuedAttributeToArray(object, (EAttribute) feature, new Object[1]);
            }
        }
    }

    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        PersistentEObject object = PersistentEObject.from(internalObject);
        boolean isReference = feature instanceof EReference;

        if (feature.isMany()) {
            //int length = (int) getFromMap(object, feature);
            if (isReference) {
                return multiValuedReferenceToArray(object, (EReference) feature, array);
            }
            else {
                return multiValuedAttributeToArray(object, (EAttribute) feature, array);
            }
        }
        else { //monovalued
            if (isReference) {
                return monoValuedReferenceToArray(object, (EReference) feature, array);
            }
            else {
                return monoValuedAttributeToArray(object, (EAttribute) feature, array);
            }
        }

    }

    @SuppressWarnings("unchecked")
    private <T> T[] monoValuedAttributeToArray(PersistentEObject object, EAttribute attr, T[] output) {
        FeatureKey fk = FeatureKey.from(object, attr);
        output[0] = (T) deserialize(attr, backend.valueOf(fk).orElse(null));
        return output;
    }

    @SuppressWarnings("unchecked")
    private <T> T[] multiValuedAttributeToArray(PersistentEObject object, EAttribute attr, T[] output) {
        FeatureKey fk = FeatureKey.from(object, attr);
        for (int i = 0; i < output.length; i++) {
            output[i] = (T) deserialize(attr, backend.valueOf(fk.withPosition(i)).orElse(null));
        }
        return output;
    }

    @SuppressWarnings("unchecked")
    private <T> T[] monoValuedReferenceToArray(PersistentEObject object, EReference ref, T[] output) {
        FeatureKey fk = FeatureKey.from(object, ref);
        Id id = (Id) backend.valueOf(fk).orElse(null);
        output[0] = (T) eObject(id);
        return output;
    }

    @SuppressWarnings("unchecked")
    private <T> T[] multiValuedReferenceToArray(PersistentEObject object, EReference ref, T[] output) {
        FeatureKey fk = FeatureKey.from(object, ref);
        for (int i = 0; i < output.length; i++) {
            Id id = (Id) backend.valueOf(fk.withPosition(i)).orElse(null);
            output[i] = (T) eObject(id);
        }
        return output;
    }
}
