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
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;
import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link MapStore} that persists {@link Collection} indices instead of serialized arrays.
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
 * @see MapStore
 * @see MapBackend
 * @see AbstractPersistentStoreDecorator
 */
public class MapStoreWithIndices<P extends MapBackend> extends MapStore<P> {
    /**
     * Constructs a new {@code MapStore} between the given {@code resource} and the {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public MapStoreWithIndices(Resource.Internal resource, P backend) {
        super(resource, backend);
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        PersistentEObject object = PersistentEObject.from(internalObject);

        NeoLogger.debug("add({0}, {1}, {2}, {3})", new Object[]{object.id(), feature.getName(), index, value});

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
        backend.storeValue(featureKey, new Integer(size + 1));

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
        NeoLogger.debug("remove({0}, {1})", new Object[] {feature.getName(), index});

        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        Integer size = (Integer) backend.valueOf(featureKey);
        // Get element to remove
        Object old = backend.valueAtIndex(featureKey.withPosition(index));
        // Update indexes (element to remove is overwritten)
        for (int i = index + 1; i < size; i++) {
            Object movingValue = backend.valueAtIndex(featureKey.withPosition(i));
            backend.storeValueAtIndex(featureKey.withPosition(i - 1), movingValue);
        }
        backend.storeValue(featureKey, new Integer(size - 1));
        return old;
    }

    @Override
    public int indexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        NeoLogger.debug("MapStoreWithIndices::indexOf({1}, {2})", new Object[] {
                feature.getName(), value});

        return ArrayUtils.indexOf(toArray(internalObject, feature), value);
    }

    @Override
    public int lastIndexOf(InternalEObject internalObject, EStructuralFeature feature, Object value) {
        NeoLogger.debug("MapStoreWithIndices::lastIndexOf({1}, {2})", new Object[] {
                feature.getName(), value});

        return indexOf(internalObject, feature, value);
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        FeatureKey featureKey = FeatureKey.from(internalObject, feature);
        backend.removeFeature(featureKey);
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        NeoLogger.debug("getAttribute({0}, {1}, {2})", new Object[]{object.id(), attribute.getName(), index});

        Object result;
        FeatureKey featureKey = FeatureKey.from(object, attribute);
        if (attribute.isMany()) {
            result = backend.valueAtIndex(featureKey.withPosition(index));
        } else {
            result = backend.valueOf(featureKey);
        }

        return parseProperty(attribute, result);
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        NeoLogger.debug("setAttribute({0}, {1}, {2}, {3})", new Object[]{object.id(), attribute.getName(), index, value});

        Object old;
        FeatureKey featureKey = FeatureKey.from(object, attribute);
        Object serializedValue = serializeToProperty(attribute, value);

        if (attribute.isMany()) {
            old = backend.storeValueAtIndex(featureKey.withPosition(index), serializedValue);
        } else {
            old = backend.storeValue(featureKey, serializedValue);
        }

        return parseProperty(attribute, old);
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference reference, int index) {
        NeoLogger.debug("getReference({0}, {1}, {2})", new Object[]{object.id(), reference.getName(), index});

        Id result;
        FeatureKey featureKey = FeatureKey.from(object, reference);
        if (reference.isMany()) {
            result = (Id) backend.valueAtIndex(featureKey.withPosition(index));
        } else {
            result = (Id) backend.valueOf(featureKey);
        }

        return nonNull(result) ? eObject(result) : null;
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        NeoLogger.debug("setReference({0}, {1}, {2}, {3})", new Object[]{object.id(), reference.getName(), index, value});

        Id old;
        FeatureKey featureKey = FeatureKey.from(object, reference);
        updateContainment(object, reference, value);
        updateInstanceOf(value);

        if (reference.isMany()) {
            old = (Id) backend.storeValueAtIndex(featureKey.withPosition(index), value.id());
        } else {
            old = (Id) backend.storeValue(featureKey, value.id());
        }

        return nonNull(old) ? eObject(old) : null;
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkNotNull(internalObject);
        checkNotNull(feature);
        checkArgument(feature.isMany(), "Cannot compute size of a single-valued feature");

        PersistentEObject object = PersistentEObject.from(internalObject);
        NeoLogger.debug("size({0}, {1})", new Object[]{object.id(), feature.getName()});

        FeatureKey featureKey = FeatureKey.from(object, feature);
        Object value = backend.valueOf(featureKey);

        return isNull(value) ? 0 : (Integer) value;
    }
}
