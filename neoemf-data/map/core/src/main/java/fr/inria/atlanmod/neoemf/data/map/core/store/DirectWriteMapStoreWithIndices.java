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
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.Collection;
import java.util.Collections;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
public class DirectWriteMapStoreWithIndices extends DirectWriteMapStore {

    /**
     * Constructs a new {@code DirectWriteMapStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteMapStoreWithIndices(PersistentResource resource, PersistenceBackend backend) {
        super(resource, backend);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        PersistentEObject object = PersistentEObject.from(internalObject);

        NeoLogger.debug("add({0}, {1}, {2}, {3})", object.id(), feature.getName(), index, value);

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
        else if (feature instanceof EReference) {
            PersistentEObject referencedObject = PersistentEObject.from(value);
            updateContainment(object, (EReference) feature, referencedObject);
            updateInstanceOf(referencedObject);
            backend.valueFor(multivaluedFeatureKey, referencedObject.id());
        }
        else {
            throw new IllegalArgumentException(feature.toString());
        }
    }


    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        NeoLogger.debug("remove({0}, {1})", feature.getName(), index);

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
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkArgument(feature.isMany(), "Cannot compute size of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        NeoLogger.debug("size({0}, {1})", object.id(), feature.getName());

        FeatureKey featureKey = FeatureKey.from(object, feature);
        Object value = backend.valueOf(featureKey).orElse(null);

        return isNull(value) ? 0 : (Integer) value;
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        NeoLogger.debug("DirectWriteMapStoreWithIndices::indexOf({0}, {1})", feature.getName(), value);

        return ArrayUtils.indexOf(toArray(internalObject, feature), value);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        NeoLogger.debug("DirectWriteMapStoreWithIndices::lastIndexOf({0}, {1})", feature.getName(), value);

        return indexOf(internalObject, feature, value);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        backend.unsetValue(featureKey);
    }

    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        checkArgument(feature instanceof EReference || feature instanceof EAttribute,
                "Cannot compute toArray from feature {0}: unkown EStructuralFeature type {1}",
                feature.getName(), feature.getClass().getSimpleName());
        PersistentEObject object = PersistentEObject.from(internalObject);
        boolean isReference = feature instanceof EReference;

        if (feature.isMany()) {
            int length = (int) getFromMap(object, feature);
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
        checkArgument(feature instanceof EReference || feature instanceof EAttribute,
                "Cannot compute toArray from feature {0}: unkown EStructuralFeature type {1}",
                feature.getName(), feature.getClass().getSimpleName());

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

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        NeoLogger.debug("getAttribute({0}, {1}, {2})", object.id(), attribute.getName(), index);

        Object result;
        FeatureKey featureKey = FeatureKey.from(object, attribute);
        if (attribute.isMany()) {
            result = backend.valueOf(featureKey.withPosition(index)).orElse(null);
        }
        else {
            result = backend.valueOf(featureKey).orElse(null);
        }

        return deserialize(attribute, result);
    }

    @Override
    protected PersistentEObject getReference(PersistentEObject object, EReference reference, int index) {
        NeoLogger.debug("referenceFor({0}, {1}, {2})", object.id(), reference.getName(), index);

        Id result;
        FeatureKey featureKey = FeatureKey.from(object, reference);
        if (reference.isMany()) {
            result = (Id) backend.valueOf(featureKey.withPosition(index)).orElse(null);
        }
        else {
            result = (Id) backend.valueOf(featureKey).orElse(null);
        }

        return nonNull(result) ? eObject(result) : null;
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        NeoLogger.debug("setAttribute({0}, {1}, {2}, {3})", object.id(), attribute.getName(), index, value);

        Object old;
        FeatureKey featureKey = FeatureKey.from(object, attribute);
        Object serializedValue = serialize(attribute, value);

        if (attribute.isMany()) {
            old = backend.valueFor(featureKey.withPosition(index), serializedValue).orElse(null);
        }
        else {
            old = backend.valueFor(featureKey, serializedValue).orElse(null);
        }

        return deserialize(attribute, old);
    }

    @Override
    protected PersistentEObject setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        NeoLogger.debug("referenceFor({0}, {1}, {2}, {3})", object.id(), reference.getName(), index, value);

        Id old;
        FeatureKey featureKey = FeatureKey.from(object, reference);
        updateContainment(object, reference, value);
        updateInstanceOf(value);

        if (reference.isMany()) {
            old = (Id) backend.valueFor(featureKey.withPosition(index), value.id()).orElse(null);
        }
        else {
            old = (Id) backend.valueFor(featureKey, value.id()).orElse(null);
        }

        return nonNull(old) ? eObject(old) : null;
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
