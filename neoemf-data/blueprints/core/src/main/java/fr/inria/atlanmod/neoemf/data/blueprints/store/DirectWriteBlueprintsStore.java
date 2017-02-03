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

package fr.inria.atlanmod.neoemf.data.blueprints.store;

import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} that translates model-level operations to Blueprints calls.
 * <p>
 * This class implements the {@link fr.inria.atlanmod.neoemf.data.store.PersistentStore} interface that defines a set of operations to implement in order to
 * allow EMF persistence delegation. If this store is used, every method call and property access on {@link
 * PersistentEObject} is forwarded to this class, that takes care of the database serialization and deserialization
 * using its embedded {@link BlueprintsPersistenceBackend}.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it (see {@link
 * fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 *
 * @see PersistentEObject
 * @see BlueprintsPersistenceBackend
 * @see fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator
 */
public class DirectWriteBlueprintsStore extends AbstractDirectWriteStore<BlueprintsPersistenceBackend> {

    /**
     * The property key used to define the index of an edge.
     */
    protected static final String POSITION = "position";

    /**
     * Constructs a new {@code DirectWriteBlueprintsStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteBlueprintsStore(Resource.Internal resource, BlueprintsPersistenceBackend backend) {
        super(resource, backend);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is an efficient implementation that takes benefit of the underlying backend to get all the linked
     * elements once and return it as an array, avoiding multiple {@code get()} operations.
     */
    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        return toArray(internalObject, feature, null);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is an efficient implementation that takes benefit of the underlying backend to get all the linked
     * elements once and return it as an array, avoiding multiple {@code get()} operations.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        FeatureKey key = FeatureKey.from(internalObject, feature);

        Stream<Object> stream;
        if (feature instanceof EReference) {
            Iterable<Id> references;

            if (feature.isMany()) {
                references = backend.referenceAtIndexAsList(key);
            }
            else {
                references = backend.referenceAsList(key);
            }

            stream = StreamSupport.stream(references.spliterator(), false)
                    .map(this::eObject);
        }
        else {
            Iterable<Object> values;

            if (feature.isMany()) {
                values = backend.valueAtIndexAsList(key);
            }
            else {
                values = backend.valueAsList(key);
            }

            stream = StreamSupport.stream(values.spliterator(), false)
                    .map(v -> parseProperty((EAttribute) feature, v));
        }

        if (isNull(array)) {
            return (T[]) stream.toArray();
        }
        else {
            array = stream.collect(Collectors.toList()).toArray(array);
            return array;
        }
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        FeatureKey key = FeatureKey.from(object, attribute);

        Object value;
        if (!attribute.isMany()) {
            value = backend.getValue(key);
        }
        else {
            checkElementIndex(index, size(object, attribute));
            value = backend.getValueAtIndex(key.withPosition(index));
        }
        return parseProperty(attribute, value);
    }

    @Override
    protected PersistentEObject getReference(PersistentEObject object, EReference reference, int index) {
        FeatureKey key = FeatureKey.from(object, reference);

        Id value;
        if (!reference.isMany()) {
            value = backend.getReference(key);
        }
        else {
            checkElementIndex(index, size(object, reference));
            value = backend.getReferenceAtIndex(key.withPosition(index));
        }

        return eObject(value);
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        if (isNull(value)) {
            Object previousValue = getAttribute(object, attribute, index);
            clearAttribute(object, attribute); // FIXME Can remove more than one attribute
            return previousValue;
        }
        else {
            persist(object);

            FeatureKey key = FeatureKey.from(object, attribute);

            Object previousValue;
            if (!attribute.isMany()) {
                previousValue = backend.setValue(key, value);
            }
            else {
                checkElementIndex(index, size(object, attribute));
                previousValue = backend.setValueAtIndex(key.withPosition(index), value);
            }
            return parseProperty(attribute, previousValue);
        }
    }

    @Override
    protected PersistentEObject setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        if (isNull(value)) {
            PersistentEObject previouslyReferencedObject = getReference(object, reference, index);
            clearReference(object, reference); // FIXME Can remove more than one reference
            return previouslyReferencedObject;
        }
        else {
            persist(object, reference, value);

            FeatureKey key = FeatureKey.from(object, reference);

            Id previousId;
            if (!reference.isMany()) {
                previousId = backend.setReference(key, value.id());
            }
            else {
                checkElementIndex(index, size(object, reference));
                previousId = backend.setReferenceAtIndex(key.withPosition(index), value.id());
            }
            return eObject(previousId);
        }
    }

    @Override
    protected boolean isSetAttribute(PersistentEObject object, EAttribute attribute) {
        FeatureKey key = FeatureKey.from(object, attribute);

        if (!attribute.isMany()) {
            return backend.hasValue(key);
        }
        else {
            return backend.hasValueAtIndex(key);
        }
    }

    @Override
    protected boolean isSetReference(PersistentEObject object, EReference reference) {
        FeatureKey key = FeatureKey.from(object, reference);

        if (!reference.isMany()) {
            return backend.hasReference(key);
        }
        else {
            return backend.hasReferenceAtIndex(key);
        }
    }

    @Override
    protected void unsetAttribute(PersistentEObject object, EAttribute attribute) {
        FeatureKey key = FeatureKey.from(object, attribute);

        if (!attribute.isMany()) {
            backend.unsetValue(key);
        }
        else {
            backend.unsetValueAtIndex(key);
        }
    }

    @Override
    protected void unsetReference(PersistentEObject object, EReference reference) {
        FeatureKey key = FeatureKey.from(object, reference);

        if (!reference.isMany()) {
            backend.unsetReference(key);
        }
        else {
            backend.unsetReferenceAtIndex(key);
        }
    }

    @Override
    protected boolean containsAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        checkArgument(attribute.isMany(), "Cannot compute contains() of a single-valued feature");

        persist(object);

        FeatureKey key = FeatureKey.from(object, attribute);
        return backend.containsValue(key, value);
    }

    @Override
    protected boolean containsReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        checkArgument(reference.isMany(), "Cannot compute contains() of a single-valued feature");

        persist(object);

        FeatureKey key = FeatureKey.from(object, reference);
        return backend.containsReference(key, value.id());
    }

    @Override
    protected int indexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        checkArgument(attribute.isMany(), "Cannot compute indexOf() of a single-valued feature");

        if (isNull(value)) {
            return NO_INDEX;
        }

        FeatureKey key = FeatureKey.from(object, attribute);
        return backend.indexOfValue(key, value);
    }

    @Override
    protected int indexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        checkArgument(reference.isMany(), "Cannot compute indexOf() of a single-valued feature");

        if (isNull(value)) {
            return NO_INDEX;
        }

        FeatureKey key = FeatureKey.from(object, reference);
        return backend.indexOfReference(key, value.id());
    }

    @Override
    protected int lastIndexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        checkArgument(attribute.isMany(), "Cannot compute lastIndexOf() of a single-valued feature");

        if (isNull(value)) {
            return NO_INDEX;
        }

        FeatureKey key = FeatureKey.from(object, attribute);
        return backend.lastIndexOfValue(key, value);
    }

    @Override
    protected int lastIndexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        checkArgument(reference.isMany(), "Cannot compute lastIndexOf() of a single-valued feature");

        if (isNull(value)) {
            return NO_INDEX;
        }

        FeatureKey key = FeatureKey.from(object, reference);
        return backend.lastIndexOfReference(key, value.id());
    }

    @Override
    protected void addAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        checkArgument(attribute.isMany(), "Cannot compute add() of a single-valued feature");

        persist(object);

        if (index == NO_INDEX) {
            index = size(object, attribute);
        }
        else {
            checkPositionIndex(index, size(object, attribute));
        }

        MultivaluedFeatureKey key = MultivaluedFeatureKey.from(object, attribute, index);
        backend.addValue(key, serializeToProperty(attribute, value));
    }

    @Override
    protected void addReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        checkArgument(reference.isMany(), "Cannot compute add() of a single-valued feature");

        persist(object, reference, value);

        if (index == NO_INDEX) {
            index = size(object, reference);
        }
        else {
            checkPositionIndex(index, size(object, reference));
        }

        MultivaluedFeatureKey key = MultivaluedFeatureKey.from(object, reference, index);
        backend.addReference(key, value.id());
    }

    @Override
    protected Object removeAttribute(PersistentEObject object, EAttribute attribute, int index) {
        checkArgument(attribute.isMany(), "Cannot compute remove() of a single-valued feature");
        checkElementIndex(index, size(object, attribute));

        MultivaluedFeatureKey key = MultivaluedFeatureKey.from(object, attribute, index);
        return parseProperty(attribute, backend.removeValue(key));
    }

    @Override
    protected PersistentEObject removeReference(PersistentEObject object, EReference reference, int index) {
        checkArgument(reference.isMany(), "Cannot compute remove() of a single-valued feature");
        checkElementIndex(index, size(object, reference));

        MultivaluedFeatureKey key = MultivaluedFeatureKey.from(object, reference, index);
        PersistentEObject previouslyReferencedObject = eObject(backend.removeReference(key));

        checkNotNull(previouslyReferencedObject);
        if (reference.isContainment()) {
            previouslyReferencedObject.eBasicSetContainer(null, -1, null);
            previouslyReferencedObject.resource(null);
        }

        return previouslyReferencedObject;
    }

    @Override
    protected void clearAttribute(PersistentEObject object, EAttribute attribute) {
        checkArgument(attribute.isMany(), "Cannot compute clear() of a single-valued feature");

        FeatureKey key = FeatureKey.from(object, attribute);
        backend.cleanValue(key);
    }

    @Override
    protected void clearReference(PersistentEObject object, EReference reference) {
        checkArgument(reference.isMany(), "Cannot compute clear() of a single-valued feature");

        persist(object);

        FeatureKey key = FeatureKey.from(object, reference);
        backend.cleanReference(key);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkArgument(feature.isMany(), "Cannot compute size() of a single-valued feature");

        FeatureKey key = FeatureKey.from(internalObject, feature);
        return backend.sizeOf(key);
    }

    /**
     * Computes efficiently {@code allInstances} operation by using underlying graph facilities. This method uses
     * database indices to avoid costly traversal of the entire model.
     *
     * @param eClass the {@link EClass} to get the instances of
     * @param strict set to {@code true} if the method should look for instances of {@code eClass} only, set to {@code
     *               false} if the method should also return elements that are subclasses of {@code eClass}
     */
    @Override
    public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
        Map<EClass, Iterable<Vertex>> indexHits = backend.getAllInstances(eClass, strict);
        EList<EObject> instances = new BasicEList<>();
        for (Map.Entry<EClass, Iterable<Vertex>> entry : indexHits.entrySet()) {
            for (Vertex instanceVertex : entry.getValue()) {
                instances.add(eObject(new StringId(instanceVertex.getId().toString())));
            }
        }
        return instances;
    }
}
