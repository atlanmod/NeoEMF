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

import com.google.common.collect.Iterables;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.StreamSupport;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
     * This method is an efficient implementation of
     * {@link AbstractDirectWriteStore#toArray(InternalEObject, EStructuralFeature)}
     * that takes benefit of the underlying backend to get all the linked elements once and return
     * it as an array, avoiding multiple {@code get()}
     * operations.
     */
    @Override
    public Object[] toArray(InternalEObject internalObject, EStructuralFeature feature) {
        return toArray(internalObject, feature, null);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is an efficient implementation of
     * {@link AbstractDirectWriteStore#toArray(InternalEObject, EStructuralFeature, Object[])}
     * that takes benefit of the underlying backend to get all the linked elements once and return
     * it as an array, avoiding multiple {@code get()}
     * operations.
     * <p>
     * Returns the given {@code array} reference if it is not {@code null}.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(InternalEObject internalObject, EStructuralFeature feature, T[] array) {
        checkArgument(feature instanceof EReference || feature instanceof EAttribute,
                "Cannot compute toArray from feature {0}: unkown EStructuralFeature type {1}",
                feature.getName(), feature.getClass().getSimpleName());
        PersistentEObject object = PersistentEObject.from(internalObject);
        Vertex vertex = backend.getVertex(object.id());
        if (feature instanceof EReference) {
            if (feature.isMany()) {
                Comparator<Edge> byPosition = Comparator.comparingInt(e -> e.getProperty(POSITION));
                Object[] result = StreamSupport.stream(
                        vertex.query()
                                .labels(feature.getName())
                                .direction(Direction.OUT)
                                .edges()
                                .spliterator(), false)
                        .sorted(byPosition)
                        .map(ee -> reifyVertex(ee.getVertex(Direction.IN)))
                        .toArray();
                if (isNull(array)) {
                    return (T[]) result;
                }
                else {
                    System.arraycopy(result, 0, array, 0, result.length);
                    return array;
                }
            }
            else {
                Vertex referencedVertex = Iterables.getOnlyElement(
                        vertex.getVertices(Direction.OUT, feature.getName()), null);
                InternalEObject referencedEObject = reifyVertex(referencedVertex);
                if (isNull(array)) {
                    return (T[]) new Object[]{referencedEObject};
                }
                else {
                    array[0] = (T) referencedEObject;
                    return array;
                }
            }
        }
        else {
            String propertyName = feature.getName();
            if (feature.isMany()) {
                int size = size(object, feature);
                T[] output = array;
                if (isNull(array)) {
                    output = (T[]) new Object[size];
                }
                for (int i = 0; i < size; i++) {
                    Object parsedProperty = parseProperty((EAttribute) feature, vertex.getProperty(propertyName + ':' + i));
                    output[i] = (T) parsedProperty;
                }
                // Return array if it as been provided to ensure the reference does not change
                return isNull(array) ? output : array;
            }
            else {
                Object property = vertex.getProperty(propertyName);
                if (isNull(array)) {
                    return (T[]) new Object[]{parseProperty((EAttribute) feature, property)};
                }
                else {
                    array[0] = (T) parseProperty((EAttribute) feature, property);
                    return array;
                }
            }
        }
    }

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        FeatureKey featureKey = FeatureKey.from(object, attribute);

        Object value;
        if (!attribute.isMany()) {
            value = backend.getValue(featureKey);
        }
        else {
            value = backend.getValueAtIndex(featureKey.withPosition(index));
        }
        return parseProperty(attribute, value);
    }

    @Override
    protected PersistentEObject getReference(PersistentEObject object, EReference reference, int index) {
        FeatureKey featureKey = FeatureKey.from(object, reference);

        Id value;
        if (!reference.isMany()) {
            value = backend.getReference(featureKey);
        }
        else {
            value = backend.getReferenceAtIndex(featureKey.withPosition(index));
        }

        return eObject(value);
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        if (isNull(value)) {
            Object previousValue = getAttribute(object, attribute, index);
            clear(object, attribute);
            return previousValue;
        }
        else {
            backend.getOrCreateVertex(object);
            persistentObjectsCache.put(object.id(), object);
            updateInstanceOf(object);

            FeatureKey featureKey = FeatureKey.from(object, attribute);

            Object previousValue;
            if (!attribute.isMany()) {
                previousValue = backend.setValue(featureKey, value);
            }
            else {
                previousValue = backend.setValueAtIndex(featureKey.withPosition(index), value);
            }
            return parseProperty(attribute, previousValue);
        }
    }

    @Override
    protected PersistentEObject setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        if (isNull(value)) {
            PersistentEObject previouslyReferencedObject = getReference(object, reference, index);
            clear(object, reference);
            return previouslyReferencedObject;
        }
        else {
            backend.getOrCreateVertex(object);
            persistentObjectsCache.put(object.id(), object);
            updateInstanceOf(object);

            backend.getOrCreateVertex(value);
            persistentObjectsCache.put(value.id(), value);
            updateInstanceOf(value);

            updateContainment(object, reference, value);

            FeatureKey featureKey = FeatureKey.from(object, reference);

            Id previousId;
            if (!reference.isMany()) {
                previousId = backend.setReference(featureKey, value.id());
            }
            else {
                previousId = backend.setReferenceAtIndex(featureKey.withPosition(index), value.id());
            }
            return eObject(previousId);
        }
    }

    @Override
    protected boolean isSetAttribute(PersistentEObject object, EAttribute attribute) {
        FeatureKey featureKey = FeatureKey.from(object, attribute);

        if (!attribute.isMany()) {
            return backend.hasValue(featureKey);
        }
        else {
            return backend.hasValueAtIndex(featureKey);
        }
    }

    @Override
    protected boolean isSetReference(PersistentEObject object, EReference reference) {
        FeatureKey featureKey = FeatureKey.from(object, reference);

        if (!reference.isMany()) {
            return backend.hasReference(featureKey);
        }
        else {
            return backend.hasReferenceAtIndex(featureKey);
        }
    }

    @Override
    protected void unsetAttribute(PersistentEObject object, EAttribute attribute) {
        FeatureKey featureKey = FeatureKey.from(object, attribute);

        if (!attribute.isMany()) {
            backend.unsetValue(featureKey);
        }
        else {
            backend.unsetValueAtIndex(featureKey);
        }
    }

    @Override
    protected void unsetReference(PersistentEObject object, EReference reference) {
        FeatureKey featureKey = FeatureKey.from(object, reference);

        if (!reference.isMany()) {
            backend.unsetReference(featureKey);
        }
        else {
            backend.unsetReferenceAtIndex(featureKey);
        }
    }

    @Override
    protected boolean containsAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        return ArrayUtils.contains(toArray(object, attribute), value);
    }

    @Override
    protected boolean containsReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        Vertex v = backend.getOrCreateVertex(object);
        persistentObjectsCache.put(object.id(), object);
        updateInstanceOf(object);

        for (Vertex vOut : v.getVertices(Direction.OUT, reference.getName())) {
            if (Objects.equals(vOut.getId(), value.id().toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected int indexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        return ArrayUtils.indexOf(toArray(object, attribute), value);
    }

    @Override
    protected int indexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        if (nonNull(value)) {
            Vertex inVertex = backend.getVertex(object.id());
            Vertex outVertex = backend.getVertex(value.id());
            for (Edge e : outVertex.getEdges(Direction.IN, reference.getName())) {
                if (Objects.equals(e.getVertex(Direction.OUT), inVertex)) {
                    return e.getProperty(POSITION);
                }
            }
        }
        return NO_INDEX;
    }

    @Override
    protected int lastIndexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        return ArrayUtils.lastIndexOf(toArray(object, attribute), value);
    }

    @Override
    protected int lastIndexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        if (nonNull(value)) {
            Vertex inVertex = backend.getVertex(object.id());
            Vertex outVertex = backend.getVertex(value.id());
            Edge lastPositionEdge = null;
            for (Edge e : outVertex.getEdges(Direction.IN, reference.getName())) {
                if (Objects.equals(e.getVertex(Direction.OUT), inVertex)
                        && (isNull(lastPositionEdge)
                        || (int) e.getProperty(POSITION) > (int) lastPositionEdge.getProperty(POSITION)))
                {
                    lastPositionEdge = e;
                }
            }
            if (nonNull(lastPositionEdge)) {
                return lastPositionEdge.getProperty(POSITION);
            }
        }
        return NO_INDEX;
    }

    @Override
    protected void addAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        checkArgument(attribute.isMany(), "Cannot compute add() of a single-valued attribute");

        if (index == NO_INDEX) {
            index = size(object, attribute);
        }

        backend.getOrCreateVertex(object);
        persistentObjectsCache.put(object.id(), object);
        updateInstanceOf(object);

        MultivaluedFeatureKey featureKey = MultivaluedFeatureKey.from(object, attribute, index);
        backend.addValue(featureKey, serializeToProperty(attribute, value));
    }

    @Override
    protected void addReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        checkArgument(reference.isMany(), "Cannot compute add() of a single-valued reference");

        if (index == NO_INDEX) {
            index = size(object, reference);
        }
        Vertex vertex = backend.getOrCreateVertex(object);
        persistentObjectsCache.put(object.id(), object);
        updateInstanceOf(object);

        Vertex referencedVertex = backend.getOrCreateVertex(value);
        persistentObjectsCache.put(value.id(), value);
        updateContainment(object, reference, value);
        updateInstanceOf(value);

        MultivaluedFeatureKey featureKey = MultivaluedFeatureKey.from(object, reference, index);
        backend.addReference(featureKey, value.id());
    }

    @Override
    protected Object removeAttribute(PersistentEObject object, EAttribute attribute, int index) {
        checkArgument(attribute.isMany(), "Cannot compute remove() of a single-valued attribute");

        MultivaluedFeatureKey featureKey = MultivaluedFeatureKey.from(object, attribute, index);
        return parseProperty(attribute, backend.removeValue(featureKey));
    }

    @Override
    protected PersistentEObject removeReference(PersistentEObject object, EReference reference, int index) {
        checkArgument(reference.isMany(), "Cannot compute remove() of a single-valued reference");

        MultivaluedFeatureKey featureKey = MultivaluedFeatureKey.from(object, reference, index);
        PersistentEObject previouslyReferencedObject = eObject(backend.removeReference(featureKey));

        checkNotNull(previouslyReferencedObject);
        if (reference.isContainment()) {
            previouslyReferencedObject.eBasicSetContainer(null, -1, null);
            previouslyReferencedObject.resource(null);
        }

        return previouslyReferencedObject;
    }

    @Override
    protected void clearAttribute(PersistentEObject object, EAttribute attribute) {
        checkArgument(attribute.isMany(), "Cannot compute clear() of a single-valued attribute");

        FeatureKey featureKey = FeatureKey.from(object, attribute);
        backend.cleanValue(featureKey);
    }

    @Override
    protected void clearReference(PersistentEObject object, EReference reference) {
        checkArgument(reference.isMany(), "Cannot compute clear() of a single-valued reference");

        Vertex vertex = backend.getOrCreateVertex(object);
        persistentObjectsCache.put(object.id(), object);
        updateInstanceOf(object);

        FeatureKey featureKey = FeatureKey.from(object, reference);
        backend.cleanReference(featureKey);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkArgument(feature.isMany(), "Cannot compute size() of a single-valued feature");

        return backend.sizeOf(FeatureKey.from(internalObject, feature));
    }

    /**
     * Creates an {@link InternalEObject} from the given {@code vertex}.
     *
     * @param vertex the {@link Vertex} to reify
     *
     * @return an {@link InternalEObject} built from the provided {@link Vertex}
     *
     * @see DirectWriteBlueprintsStore#reifyVertex(Vertex, EClass)
     */
    protected PersistentEObject reifyVertex(Vertex vertex) {
        return reifyVertex(vertex, null);
    }

    /**
     * Creates an {@link InternalEObject} from the given {@code vertex}, and sets its {@link EClass} with the given
     * {@link EClass}.
     * <p>
     * This method speeds-up the reification for objects with a known {@link EClass} by avoiding unnecessary database
     * accesses. It guarantees that the same {@link PersistentEObject} is returned for a given {@link Vertex} in
     * subsequent calls, unless the {@link PersistentEObject} returned in previous calls has been already garbage
     * collected.
     *
     * @param vertex the {@link Vertex} to reify
     * @param eClass the {@link EClass} representing the type of the element to create
     *
     * @return an {@link InternalEObject} build from the provided {@link Vertex}
     */
    protected PersistentEObject reifyVertex(Vertex vertex, @Nullable EClass eClass) {
        Id id = new StringId(vertex.getId().toString());
        PersistentEObject object = persistentObjectsCache.get(id);

        if (object.resource() != resource()) {
            object.resource(resource());
        }
        return object;
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
                instances.add(reifyVertex(instanceVertex, entry.getKey()));
            }
        }
        return instances;
    }
}
