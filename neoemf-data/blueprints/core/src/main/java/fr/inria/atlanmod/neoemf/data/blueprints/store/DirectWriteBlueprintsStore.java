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
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

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

import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link DirectWriteStore} that translates model-level operations to Blueprints calls.
 * <p>
 * This class implements the {@link PersistentStore} interface that defines a set of operations to implement in order to
 * allow EMF persistence delegation. If this store is used, every method call and property access on {@link
 * PersistentEObject} is forwarded to this class, that takes care of the database serialization and deserialization
 * using its embedded {@link BlueprintsPersistenceBackend}.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it (see {@link
 * AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 *
 * @see PersistentEObject
 * @see BlueprintsPersistenceBackend
 * @see AbstractPersistentStoreDecorator
 */
public class DirectWriteBlueprintsStore extends AbstractDirectWriteStore<BlueprintsPersistenceBackend> {

    /**
     * The string used as a separator between values of multi-valued attributes.
     */
    protected static final String SEPARATOR = ":";
    /**
     * The property key used to define the index of an edge.
     */
    protected static final String POSITION = "position";
    /**
     * The label used to define container {@link Edge}s.
     */
    protected static final String CONTAINER = "eContainer";
    /**
     * The label used to link root vertex to top-level elements.
     */
    protected static final String CONTENTS = "eContents";
    /**
     * The property key used to define the opposite containing feature in container {@link Edge}s.
     */
    protected static final String CONTAINING_FEATURE = "containingFeature";
    /**
     * The property key used to define the number of edges with a specific label.
     */
    protected static final String SIZE_LITERAL = "size";

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

    @Override
    protected Object getAttribute(PersistentEObject object, EAttribute attribute, int index) {
        Vertex vertex = backend.getVertex(object.id());
        String propertyName = attribute.getName();
        if (attribute.isMany()) {
            checkElementIndex(index, getSize(vertex, attribute), "Invalid get index " + index);
            propertyName += SEPARATOR + index;
        }
        return parseProperty(attribute, vertex.getProperty(propertyName));
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference reference, int index) {
        Object soughtReference = null;
        Vertex vertex = backend.getVertex(object.id());
        Vertex referencedVertex;
        if (!reference.isMany()) {
            referencedVertex = Iterables.getOnlyElement(
                    vertex.getVertices(Direction.OUT, reference.getName()), null);
        }
        else {
            checkElementIndex(index, getSize(vertex, reference), "Invalid get index " + index);
            referencedVertex = Iterables.getOnlyElement(
                    vertex.query()
                            .labels(reference.getName())
                            .direction(Direction.OUT)
                            .has(POSITION, index)
                            .vertices(),
                    null);
        }
        if (nonNull(referencedVertex)) {
            soughtReference = reifyVertex(referencedVertex);
        }
        return soughtReference;
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        Object old;
        if (isNull(value)) {
            old = get(object, attribute, index);
            clear(object, attribute);
        }
        else {
            Vertex vertex = backend.getOrCreateVertex(object);
            String propertyName = attribute.getName();
            if (!attribute.isMany()) {
                Object property = vertex.getProperty(propertyName);
                old = parseProperty(attribute, property);
            }
            else {
                checkElementIndex(index, getSize(vertex, attribute));
                propertyName += SEPARATOR + index;
                old = vertex.getProperty(propertyName);
            }
            vertex.setProperty(propertyName, serializeToProperty(attribute, value));
        }
        return old;
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        Object old = null;
        if (isNull(value)) {
            old = get(object, reference, index);
            clear(object, reference);
        }
        else {
            Vertex vertex = backend.getOrCreateVertex(object);
            Vertex newReferencedVertex = backend.getOrCreateVertex(value);

            // Update the containment reference if needed
            if (reference.isContainment()) {
                updateContainment(reference, vertex, newReferencedVertex);
            }

            if (!reference.isMany()) {
                Edge edge = Iterables.getOnlyElement(vertex.getEdges(Direction.OUT, reference.getName()), null);
                if (nonNull(edge)) {
                    Vertex referencedVertex = edge.getVertex(Direction.IN);
                    old = reifyVertex(referencedVertex);
                    edge.remove();
                }
                vertex.addEdge(reference.getName(), newReferencedVertex);
            }
            else {
                checkElementIndex(index, getSize(vertex, reference));
                Iterable<Edge> edges = vertex.query()
                        .labels(reference.getName())
                        .direction(Direction.OUT)
                        .has(POSITION, index)
                        .edges();

                for (Edge edge : edges) {
                    Vertex referencedVertex = edge.getVertex(Direction.IN);
                    old = reifyVertex(referencedVertex);
                    edge.remove();
                }
                Edge edge = vertex.addEdge(reference.getName(), newReferencedVertex);
                edge.setProperty(POSITION, index);
            }
        }
        return old;
    }

    @Override
    protected boolean isSetAttribute(PersistentEObject object, EAttribute attribute) {
        boolean isSet = false;
        Vertex vertex = backend.getVertex(object.id());
        if (nonNull(vertex)) {
            String propertyName = attribute.getName();
            if (attribute.isMany()) {
                propertyName += SEPARATOR + SIZE_LITERAL;
            }
            isSet = nonNull(vertex.getProperty(propertyName));
        }
        return isSet;
    }

    @Override
    protected boolean isSetReference(PersistentEObject object, EReference reference) {
        boolean isSet = false;
        Vertex vertex = backend.getVertex(object.id());
        if (nonNull(vertex)) {
            isSet = !Iterables.isEmpty(vertex.getVertices(Direction.OUT, reference.getName()));
        }
        return isSet;
    }

    @Override
    protected void unsetAttribute(PersistentEObject object, EAttribute attribute) {
        Vertex vertex = backend.getVertex(object.id());
        String propertyName = attribute.getName();
        if (attribute.isMany()) {
            propertyName += SEPARATOR + SIZE_LITERAL;
            Integer size = vertex.getProperty(propertyName);
            for (int i = 0; i < size; i++) {
                vertex.removeProperty(attribute.getName() + SEPARATOR + i);
            }
        }
        vertex.removeProperty(propertyName);
    }

    @Override
    protected void unsetReference(PersistentEObject object, EReference reference) {
        Vertex vertex = backend.getVertex(object.id());
        if (!reference.isMany()) {
            Edge edge = Iterables.getOnlyElement(vertex.getEdges(Direction.OUT, reference.getName()), null);
            if (nonNull(edge)) {
                edge.remove();
            }
        }
        else {
            for (Edge edge : vertex.query().labels(reference.getName()).direction(Direction.OUT).edges()) {
                edge.remove();
            }
            vertex.removeProperty(reference.getName() + SEPARATOR + SIZE_LITERAL);
        }
    }

    @Override
    protected boolean containsAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        return ArrayUtils.contains(toArray(object, attribute), value);
    }

    @Override
    protected boolean containsReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        Vertex v = backend.getOrCreateVertex(object);
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
        int index = ArrayUtils.INDEX_NOT_FOUND;
        if (nonNull(value)) {
            Vertex inVertex = backend.getVertex(object.id());
            Vertex outVertex = backend.getVertex(value.id());
            for (Edge e : outVertex.getEdges(Direction.IN, reference.getName())) {
                if (Objects.equals(e.getVertex(Direction.OUT), inVertex)) {
                    return e.getProperty(POSITION);
                }
            }
        }
        return index;
    }

    @Override
    protected int lastIndexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        return ArrayUtils.lastIndexOf(toArray(object, attribute), value);
    }

    @Override
    protected int lastIndexOfReference(PersistentEObject object, EReference reference, PersistentEObject value) {
        int index = ArrayUtils.INDEX_NOT_FOUND;
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
                index = lastPositionEdge.getProperty(POSITION);
            }
        }
        return index;
    }

    @Override
    protected void addAttribute(PersistentEObject object, EAttribute attribute, int index, Object value) {
        if (index == PersistentStore.NO_INDEX) {
            /*
             * Handle NO_INDEX index, which represent direct-append feature.
			 * The call to size should not cause an overhead because it would have been done in regular
			 * addUnique() otherwise.
			 */
            index = size(object, attribute);
        }
        Vertex vertex = backend.getOrCreateVertex(object);
        Integer size = getSize(vertex, attribute);
        size++;
        setSize(vertex, attribute, size);
        checkPositionIndex(index, size, "Invalid add index");
        for (int i = size - 1; i > index; i--) {
            Object movingProperty = vertex.getProperty(attribute.getName() + SEPARATOR + (i - 1));
            vertex.setProperty(attribute.getName() + SEPARATOR + i, movingProperty);
        }
        vertex.setProperty(attribute.getName() + SEPARATOR + index, serializeToProperty(attribute, value));
    }

    @Override
    protected void addReference(PersistentEObject object, EReference reference, int index, PersistentEObject value) {
        if (index == PersistentStore.NO_INDEX) {
            /*
             * Handle NO_INDEX index, which represent direct-append feature.
			 * The call to size should not cause an overhead because it would have been done in regular
			 * addUnique() otherwise.
			 */
            index = size(object, reference);
        }
        Vertex vertex = backend.getOrCreateVertex(object);

        Vertex referencedVertex = backend.getOrCreateVertex(value);
        // Update the containment reference if needed
        if (reference.isContainment()) {
            updateContainment(reference, vertex, referencedVertex);
        }

        Integer size = getSize(vertex, reference);
        int newSize = size + 1;
        checkPositionIndex(index, newSize, "Invalid add index");
        if (index != size) {
            Iterable<Edge> edges = vertex.query()
                    .labels(reference.getName())
                    .direction(Direction.OUT)
                    .interval(POSITION, index, newSize)
                    .edges();

            // Avoid unnecessary database access
            for (Edge edge : edges) {
                int position = edge.getProperty(POSITION);
                edge.setProperty(POSITION, position + 1);
            }
        }
        Edge edge = vertex.addEdge(reference.getName(), referencedVertex);
        edge.setProperty(POSITION, index);

        setSize(vertex, reference, newSize);
    }

    @Override
    protected Object removeAttribute(PersistentEObject object, EAttribute attribute, int index) {
        Vertex vertex = backend.getVertex(object.id());
        Integer size = getSize(vertex, attribute);
        Object old;
        checkPositionIndex(index, size, "Invalid remove index");

        old = parseProperty(attribute, vertex.getProperty(attribute.getName() + SEPARATOR + index));
        int newSize = size - 1;
        for (int i = newSize; i > index; i--) {
            Object movingProperty = vertex.getProperty(attribute.getName() + SEPARATOR + i);
            vertex.setProperty(attribute.getName() + SEPARATOR + (i - 1), movingProperty);
        }
        setSize(vertex, attribute, newSize);
        return old;
    }

    @Override
    protected Object removeReference(PersistentEObject object, EReference reference, int index) {
        Vertex vertex = backend.getVertex(object.id());
        String referenceName = reference.getName();
        Integer size = getSize(vertex, reference);
        InternalEObject old = null;
        checkPositionIndex(index, size, "Invalid remove index");

        Iterable<Edge> edges = vertex.query()
                .labels(referenceName)
                .direction(Direction.OUT)
                .interval(POSITION, index, size)
                .edges();

        for (Edge edge : edges) {
            int position = edge.getProperty(POSITION);
            if (position == index) {
                Vertex referencedVertex = edge.getVertex(Direction.IN);
                old = reifyVertex(referencedVertex);
                edge.remove();
                if (reference.isContainment()) {
                    for (Edge conEdge : referencedVertex.getEdges(Direction.OUT, CONTAINER)) {
                        conEdge.remove();
                    }
                }
            }
            else {
                edge.setProperty(POSITION, position - 1);
            }
        }
        setSize(vertex, reference, size - 1); // Update size
        checkNotNull(old);
        if (reference.isContainment()) {
            old.eBasicSetContainer(null, -1, null);
            ((PersistentEObject) old).resource(null);
        }
        return old;
    }

    @Override
    protected void clearAttribute(PersistentEObject object, EAttribute attribute) {
        Vertex vertex = backend.getVertex(object.id());
        Integer size = getSize(vertex, attribute);
        for (int i = 0; i < size; i++) {
            vertex.removeProperty(attribute.getName() + SEPARATOR + i);
        }
        setSize(vertex, attribute, 0);
    }

    @Override
    protected void clearReference(PersistentEObject object, EReference reference) {
        Vertex vertex = backend.getOrCreateVertex(object);
        for (Edge edge : vertex.query().labels(reference.getName()).direction(Direction.OUT).edges()) {
            edge.remove();
        }
        setSize(vertex, reference, 0);
    }

    @Override
    public int size(InternalEObject internalObject, EStructuralFeature feature) {
        checkArgument(feature.isMany(), "Cannot compute size of a single-valued feature");
        PersistentEObject object = PersistentEObject.from(internalObject);
        Vertex vertex = backend.getVertex(object.id());
        return isNull(vertex) ? 0 : getSize(vertex, feature);
    }

    @Override
    public InternalEObject getContainer(InternalEObject internalObject) {
        InternalEObject container = null;
        PersistentEObject object = PersistentEObject.from(internalObject);
        Vertex vertex = backend.getVertex(object.id());
        Vertex containerVertex = Iterables.getOnlyElement(vertex.getVertices(Direction.OUT, CONTAINER), null);
        if (nonNull(containerVertex)) {
            container = reifyVertex(containerVertex);
        }
        return container;
    }

    @Override
    public EStructuralFeature getContainingFeature(InternalEObject internalObject) {
        EStructuralFeature feature = null;
        PersistentEObject object = PersistentEObject.from(internalObject);
        Vertex vertex = backend.getVertex(object.id());
        Edge edge = Iterables.getOnlyElement(vertex.getEdges(Direction.OUT, CONTAINER), null);
        if (nonNull(edge)) {
            String featureName = edge.getProperty(CONTAINING_FEATURE);
            Vertex containerVertex = edge.getVertex(Direction.IN);
            if (nonNull(featureName)) {
                EObject container = reifyVertex(containerVertex);
                feature = container.eClass().getEStructuralFeature(featureName);
            }
        }
        return feature;
    }

    /**
     * Finds the number of elements contained in the given {@code feature}.
     *
     * @param vertex  the input {@link Vertex} of the {@code feature}
     * @param feature the {@link EStructuralFeature} describing the feature to access
     *
     * @return the size of the {@code feature} if it is multi-valued, {@code 0} otherwise
     */
    protected Integer getSize(Vertex vertex, EStructuralFeature feature) {
        Integer size = vertex.getProperty(feature.getName() + SEPARATOR + SIZE_LITERAL);
        return isNull(size) ? 0 : size;
    }

    /**
     * Defines the size property of the given {@code feature} in the given {@code vertex} to {@code size}.
     *
     * @param vertex  the input {@link Vertex} of the {@code feature}
     * @param feature the {@link EStructuralFeature} describing the feature to access
     * @param size    the new size
     */
    private void setSize(Vertex vertex, EStructuralFeature feature, int size) {
        vertex.setProperty(feature.getName() + SEPARATOR + SIZE_LITERAL, size);
    }

    /**
     * Creates a new container edge between {@code parentVertex} and {@code childVertex}, and deletes any
     * container edge previously linked to {@code childVertex}.
     *
     * @param reference    the containment {@link EReference}. This parameter is used to set the containing feature
     *                     property in the create edge.
     * @param parentVertex the {@link Vertex} representing the container element
     * @param childVertex  the {@link Vertex} representing the contained element
     *
     * @see DirectWriteBlueprintsStore#CONTAINER
     * @see DirectWriteBlueprintsStore#CONTAINING_FEATURE
     */
    private void updateContainment(EReference reference, Vertex parentVertex, Vertex childVertex) {
        for (Edge edge : childVertex.getEdges(Direction.OUT, CONTAINER)) {
            edge.remove();
        }
        Edge edge = childVertex.addEdge(CONTAINER, parentVertex);
        edge.setProperty(CONTAINING_FEATURE, reference.getName());
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
    protected InternalEObject reifyVertex(Vertex vertex) {
        return reifyVertex(vertex, null);
    }

    /**
     * Creates an {@link InternalEObject} from the given {@code vertex}, and sets its {@link EClass} with the given
     * {@link EClass}.
     * <p>
     * This method speeds-up the reification for objects with a known {@link EClass} by avoiding unnecessary database
     * accesses.
     *
     * @param vertex the {@link Vertex} to reify
     * @param eClass the {@link EClass} representing the type of the element to create
     *
     * @return an {@link InternalEObject} build from the provided {@link Vertex}
     */
    protected InternalEObject reifyVertex(Vertex vertex, EClass eClass) {
        PersistentEObject internalEObject = backend.reifyVertex(vertex, eClass);
        if (internalEObject.resource() != resource()) {
            if (Iterables.isEmpty(vertex.getEdges(Direction.OUT, CONTAINER))) {
                if (!Iterables.isEmpty(vertex.getVertices(Direction.IN, CONTENTS))) {
                    internalEObject.resource(resource());
                }
                // else : not part of the resource
            }
            else {
                internalEObject.resource(resource());
            }
        }
        return internalEObject;
    }

    /**
     * Search in the database the {@link Vertex} corresponding to the given {@code id} and reifies it.
     *
     * @param id the {@link Id} to search
     *
     * @return a reified {@link EObject} if the corresponding {@link Vertex} has been found in the database, {@code
     * null} otherwise
     */
    @Override
    public EObject eObject(Id id) {
        Vertex vertex = backend.getVertex(id);
        return isNull(vertex) ? null : reifyVertex(vertex);
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
