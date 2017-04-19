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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.StreamSupport;

import javax.annotation.Nullable;
import javax.swing.text.html.ListView;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.collect.Iterables;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractDirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

/**
 * A {@link DirectWriteStore} that translates model-level operations to
 * Blueprints calls.
 * <p>
 * This class implements the {@link PersistentStore} interface that defines a
 * set of operations to implement in order to allow EMF persistence delegation.
 * If this store is used, every method call and property access on
 * {@link PersistentEObject} is forwarded to this class, that takes care of the
 * database serialization and deserialization using its embedded
 * {@link BlueprintsPersistenceBackend}.
 * <p>
 * This store can be used as a base store that can be complemented by plugging
 * decorator stores on top of it (see {@link AbstractPersistentStoreDecorator}
 * subclasses) to provide additional features such as caching or logging.
 *
 * @see PersistentEObject
 * @see BlueprintsPersistenceBackend
 * @see AbstractPersistentStoreDecorator
 */
public class DirectWriteBlueprintsStore extends
        AbstractDirectWriteStore<BlueprintsPersistenceBackend> {

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
     * The property key used to define the opposite containing feature in
     * container {@link Edge}s.
     */
    protected static final String CONTAINING_FEATURE = "containingFeature";

    /**
     * The property key used to define the number of edges with a specific
     * label.
     */
    protected static final String SIZE_LITERAL = "size";

    /**
     * Constructs a new {@code DirectWriteBlueprintsStore} between the given
     * {@code resource} and the {@code backend}.
     *
     * @param resource
     *            the resource to persist and access
     * @param backend
     *            the persistence back-end used to store the model
     */
    public DirectWriteBlueprintsStore(Resource.Internal resource,
            BlueprintsPersistenceBackend backend) {
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
        
        VertexList list = listFor(vertex, reference);
        Vertex referencedVertex = list.get(index);

        // if (!reference.isMany()) {
        // referencedVertex = Iterables.getOnlyElement(
        // vertex.getVertices(Direction.OUT, reference.getName()), null);
        // }
        // else {
        // checkElementIndex(index, getSize(vertex, reference),
        // "Invalid get index " + index);
        // referencedVertex = Iterables.getOnlyElement(
        // vertex.query()
        // .labels(reference.getName())
        // .direction(Direction.OUT)
        // .has(POSITION, index)
        // .vertices(),
        // null);
        // }
        if (nonNull(referencedVertex)) {
            soughtReference = reifyVertex(referencedVertex);
        }
        return soughtReference;
    }

    @Override
    protected Object setAttribute(PersistentEObject object, EAttribute attribute, int index,
            Object value) {
        Object old;
        if (isNull(value)) {
            old = get(object, attribute, index);
            clear(object, attribute);
        } else {
            Vertex vertex = backend.getOrCreateVertex(object);
            String propertyName = attribute.getName();
            if (!attribute.isMany()) {
                Object property = vertex.getProperty(propertyName);
                old = parseProperty(attribute, property);
            } else {
                checkElementIndex(index, getSize(vertex, attribute));
                propertyName += SEPARATOR + index;
                old = vertex.getProperty(propertyName);
            }
            vertex.setProperty(propertyName, serializeToProperty(attribute, value));
        }
        return old;
    }

    @Override
    protected Object setReference(PersistentEObject object, EReference reference, int index,
            PersistentEObject value) {

        Vertex vertex = backend.getOrCreateVertex(object);
        Vertex newReferencedVertex = backend.getOrCreateVertex(value);

        if (reference.isContainment()) {
            updateContainment(reference, vertex, newReferencedVertex);
        }
        
        VertexList list = listFor(vertex, reference);
        Vertex oldVertex = list.set(newReferencedVertex, index);
        
        Object old = null;
        if (nonNull(oldVertex)) {
            old = reifyVertex(oldVertex);
        }

        return old;

        // if (isNull(value)) {
        // old = get(object, reference, index);
        // clear(object, reference);
        // } else {
        // Vertex vertex = backend.getOrCreateVertex(object);
        // Vertex newReferencedVertex = backend.getOrCreateVertex(value);
        //
        // // Update the containment reference if needed
        // if (reference.isContainment()) {
        // updateContainment(reference, vertex, newReferencedVertex);
        // }
        //
        // // retrouver le vertex index et changer sa LIST_VALUE
        // if (!reference.isMany()) {
        // Edge edge = Iterables.getOnlyElement(
        // vertex.getEdges(Direction.OUT, reference.getName()), null);
        // if (nonNull(edge)) {
        // Vertex referencedVertex = edge.getVertex(Direction.IN);
        // old = reifyVertex(referencedVertex);
        // edge.remove();
        // }
        // vertex.addEdge(reference.getName(), newReferencedVertex);
        // } else {
        // checkElementIndex(index, getSize(vertex, reference));
        // Iterable<Edge> edges = vertex.query().labels(reference.getName())
        // .direction(Direction.OUT).has(POSITION, index).edges();
        //
        // for (Edge edge : edges) {
        // Vertex referencedVertex = edge.getVertex(Direction.IN);
        // old = reifyVertex(referencedVertex);
        // edge.remove();
        // }
        // Edge edge = vertex.addEdge(reference.getName(), newReferencedVertex);
        // edge.setProperty(POSITION, index);
        // }
        // }
        // return old;
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

        VertexList list = listFor(vertex, reference);
        list.clear();
        // if (!reference.isMany()) {
        // Edge edge = Iterables.getOnlyElement(
        // vertex.getEdges(Direction.OUT, reference.getName()), null);
        // if (nonNull(edge)) {
        // edge.remove();
        // }
        // } else {
        // for (Edge edge :
        // vertex.query().labels(reference.getName()).direction(Direction.OUT)
        // .edges()) {
        // edge.remove();
        // }
        // vertex.removeProperty(reference.getName() + SEPARATOR +
        // SIZE_LITERAL);
        // }
    }

    @Override
    protected boolean containsAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        return ArrayUtils.contains(toArray(object, attribute), value);
    }

    @Override
    protected boolean containsReference(PersistentEObject object, EReference reference,
            PersistentEObject value) {
        Vertex vertex = backend.getOrCreateVertex(object);
        Vertex referencedVertex = backend.getOrCreateVertex(object);
        VertexList list = listFor(vertex, reference);
        return list.contains(referencedVertex);

        // for (Vertex vOut : v.getVertices(Direction.OUT, reference.getName()))
        // {
        // if (Objects.equals(vOut.getId(), value.id().toString())) {
        // return true;
        // }
        // }
        // return false;
    }

    @Override
    protected int indexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        return ArrayUtils.indexOf(toArray(object, attribute), value);
    }

    @Override
    protected int indexOfReference(PersistentEObject object, EReference reference,
            PersistentEObject value) {

        Vertex vertex = backend.getVertex(object.id());
        VertexList list = listFor(vertex, reference);
        return list.indexOf(value.id());

        // int index = ArrayUtils.INDEX_NOT_FOUND;
        // if (nonNull(value)) {
        // Vertex inVertex = backend.getVertex(object.id());
        // Vertex outVertex = backend.getVertex(value.id());
        // for (Edge e : outVertex.getEdges(Direction.IN, reference.getName()))
        // {
        // if (Objects.equals(e.getVertex(Direction.OUT), inVertex)) {
        // return e.getProperty(POSITION);
        // }
        // }
        // }
        // return index;
    }

    @Override
    protected int lastIndexOfAttribute(PersistentEObject object, EAttribute attribute, Object value) {
        return ArrayUtils.lastIndexOf(toArray(object, attribute), value);
    }

    @Override
    protected int lastIndexOfReference(PersistentEObject object, EReference reference,
            PersistentEObject value) {

        Vertex vertex = backend.getVertex(object.id());
        VertexList list = listFor(vertex, reference);
        return list.lastIndexOf(value.id());
        // int index = ArrayUtils.INDEX_NOT_FOUND;
        // if (nonNull(value)) {
        // Vertex inVertex = backend.getVertex(object.id());
        // Vertex outVertex = backend.getVertex(value.id());
        // Edge lastPositionEdge = null;
        // for (Edge e : outVertex.getEdges(Direction.IN, reference.getName()))
        // {
        // if (Objects.equals(e.getVertex(Direction.OUT), inVertex)
        // && (isNull(lastPositionEdge) || (int) e.getProperty(POSITION) > (int)
        // lastPositionEdge
        // .getProperty(POSITION))) {
        // lastPositionEdge = e;
        // }
        // }
        // if (nonNull(lastPositionEdge)) {
        // index = lastPositionEdge.getProperty(POSITION);
        // }
        // }
        // return index;
    }

    @Override
    protected void addAttribute(PersistentEObject object, EAttribute attribute, int index,
            Object value) {
        if (index == PersistentStore.NO_INDEX) {
            /*
             * Handle NO_INDEX index, which represent direct-append feature. The
             * call to size should not cause an overhead because it would have
             * been done in regular addUnique() otherwise.
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
        vertex.setProperty(attribute.getName() + SEPARATOR + index,
                serializeToProperty(attribute, value));
    }

    @Override
    protected void addReference(PersistentEObject object, EReference reference, int index,
            PersistentEObject value) {
        if (index == PersistentStore.NO_INDEX) {
            /*
             * Handle NO_INDEX index, which represent direct-append feature. The
             * call to size should not cause an overhead because it would have
             * been done in regular addUnique() otherwise.
             */
            index = size(object, reference);
        }
        Vertex vertex = backend.getOrCreateVertex(object);

        Vertex referencedVertex = backend.getOrCreateVertex(value);
        // Update the containment reference if needed
        if (reference.isContainment()) {
            updateContainment(reference, vertex, referencedVertex);
        }

        VertexList list = listFor(vertex, reference);
        list.add(referencedVertex, index);

        // Integer size = getSize(vertex, reference);
        // int newSize = size + 1;
        // checkPositionIndex(index, newSize, "Invalid add index");
        //
        // // Create the list item to put in the graph
        // Vertex listItem = backend.addVertex(StringId.generate());
        // listItem.addEdge(LIST_VALUE, referencedVertex);
        //
        // if (size == 0) {
        // // The list was empty, add the listItem as the head of the list
        // vertex.addEdge(reference.getName(), listItem);
        // } else {
        // Edge oldHeadLink =
        // Iterables.getOnlyElement(vertex.getEdges(Direction.OUT,
        // reference.getName()));
        // Vertex oldHead = oldHeadLink.getVertex(Direction.IN);
        // // There is already a head for this list, find the element to move
        // if (index == 0) {
        // oldHeadLink.remove();
        // vertex.addEdge(reference.getName(), listItem);
        // listItem.addEdge(LIST_NEXT, oldHead);
        // } else {
        // // Find the item in the list at the index - 1
        // Vertex previousInList = getListItem(oldHead, index - 1);
        //
        // // Can be null if this is the last item in the list
        // Edge previousNextEdge = Iterables.getOnlyElement(
        // previousInList.getEdges(Direction.OUT, LIST_NEXT), null);
        // previousInList.addEdge(LIST_NEXT, listItem);
        // if (nonNull(previousNextEdge)) {
        // listItem.addEdge(LIST_NEXT,
        // previousNextEdge.getVertex(Direction.IN));
        // previousNextEdge.remove();
        // }
        // }
        // }

        // if (index != size) {
        // Iterable<Edge> edges = vertex.query()
        // .labels(reference.getName())
        // .direction(Direction.OUT)
        // .interval(POSITION, index, newSize)
        // .edges();
        //
        // // Avoid unnecessary database access
        // for (Edge edge : edges) {
        // int position = edge.getProperty(POSITION);
        // edge.setProperty(POSITION, position + 1);
        // }
        // }
        // Edge edge = vertex.addEdge(reference.getName(), referencedVertex);
        // edge.setProperty(POSITION, index);

        // setSize(vertex, reference, newSize);
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

    public static int setPropertyCount = 0;

    @Override
    protected Object removeReference(PersistentEObject object, EReference reference, int index) {
        Vertex vertex = backend.getVertex(object.id());
        VertexList list = listFor(vertex, reference);
        Vertex oldVertex = list.remove(index);
        checkNotNull(oldVertex);
        InternalEObject oldObject = reifyVertex(oldVertex);
        if (reference.isContainment()) {
            oldObject.eBasicSetContainer(null, -1, null);
            ((PersistentEObject) oldObject).resource(null);
        }
        return oldObject;

        // String referenceName = reference.getName();
        // Integer size = getSize(vertex, reference);
        // InternalEObject old = null;
        // checkPositionIndex(index, size, "Invalid remove index");
        //
        // Iterable<Edge> edges =
        // vertex.query().labels(referenceName).direction(Direction.OUT)
        // .interval(POSITION, index, size).edges();
        //
        // for (Edge edge : edges) {
        // int position = edge.getProperty(POSITION);
        // if (position == index) {
        // Vertex referencedVertex = edge.getVertex(Direction.IN);
        // old = reifyVertex(referencedVertex);
        // edge.remove();
        // if (reference.isContainment()) {
        // for (Edge conEdge : referencedVertex.getEdges(Direction.OUT,
        // CONTAINER)) {
        // conEdge.remove();
        // }
        // }
        // } else {
        // setPropertyCount++;
        // edge.setProperty(POSITION, position - 1);
        // }
        // }
        // setPropertyCount++;
        // setSize(vertex, reference, size - 1); // Update size
        // checkNotNull(old);
        // if (reference.isContainment()) {
        // old.eBasicSetContainer(null, -1, null);
        // ((PersistentEObject) old).resource(null);
        // }
        // return old;
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

        VertexList list = listFor(vertex, reference);
        list.clear();

        // TODO remove all the elements in the list
        // Edge listHeadEdge = Iterables.getOnlyElement(
        // vertex.getEdges(Direction.OUT, reference.getName()), null);
        // if (nonNull(listHeadEdge)) {
        // listHeadEdge.remove();
        // }
        //
        // for (Edge edge :
        // vertex.query().labels(reference.getName()).direction(Direction.OUT).edges())
        // {
        // edge.remove();
        // }
        // setSize(vertex, reference, 0);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is an efficient implementation of
     * {@link AbstractDirectWriteStore#toArray(InternalEObject, EStructuralFeature)}
     * that takes benefit of the underlying backend to get all the linked
     * elements once and return it as an array, avoiding multiple {@code get()}
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
     * that takes benefit of the underlying backend to get all the linked
     * elements once and return it as an array, avoiding multiple {@code get()}
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

            VertexList list = listFor(vertex, (EReference) feature);
            return list.toArray(array);

            // if (feature.isMany()) {
            // Comparator<Edge> byPosition = (e1, e2) ->
            // Integer.compare(e1.getProperty(POSITION),
            // e2.getProperty(POSITION));
            // Object[] result = StreamSupport
            // .stream(vertex.query().labels(feature.getName()).direction(Direction.OUT)
            // .edges().spliterator(), false).sorted(byPosition)
            // .map(ee -> reifyVertex(ee.getVertex(Direction.IN))).toArray();
            // if (isNull(array)) {
            // return (T[]) result;
            // } else {
            // System.arraycopy(result, 0, array, 0, result.length);
            // return array;
            // }
            // } else {
            // Vertex referencedVertex = Iterables.getOnlyElement(
            // vertex.getVertices(Direction.OUT, feature.getName()), null);
            // InternalEObject referencedEObject =
            // reifyVertex(referencedVertex);
            // if (isNull(array)) {
            // return (T[]) new Object[] { referencedEObject };
            // } else {
            // array[0] = (T) referencedEObject;
            // return array;
            // }
            // }
        } else {
            String propertyName = feature.getName();
            if (feature.isMany()) {
                int size = getSize(vertex, feature);
                T[] output = array;
                if (isNull(array)) {
                    output = (T[]) new Object[size];
                }
                for (int i = 0; i < size; i++) {
                    Object parsedProperty = parseProperty((EAttribute) feature,
                            vertex.getProperty(propertyName + SEPARATOR + i));
                    output[i] = (T) parsedProperty;
                }
                // Return array if it as been provided to ensure the reference
                // does not change
                return isNull(array) ? output : array;
            } else {
                Object property = vertex.getProperty(propertyName);
                if (isNull(array)) {
                    return (T[]) new Object[] { parseProperty((EAttribute) feature, property) };
                } else {
                    array[0] = (T) parseProperty((EAttribute) feature, property);
                    return array;
                }
            }
        }
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
        Vertex containerVertex = Iterables.getOnlyElement(
                vertex.getVertices(Direction.OUT, CONTAINER), null);
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
     * @param vertex
     *            the input {@link Vertex} of the {@code feature}
     * @param feature
     *            the {@link EStructuralFeature} describing the feature to
     *            access
     *
     * @return the size of the {@code feature} if it is multi-valued, {@code 0}
     *         otherwise
     */
    protected Integer getSize(Vertex vertex, EStructuralFeature feature) {
        if (feature instanceof EReference) {
            VertexList list = listFor(vertex, (EReference) feature);
            return list.size();
        } else {
            Integer size = vertex.getProperty(feature.getName() + SEPARATOR + SIZE_LITERAL);
            return isNull(size) ? 0 : size;
        }
    }

    /**
     * Defines the size property of the given {@code feature} in the given
     * {@code vertex} to {@code size}.
     *
     * @param vertex
     *            the input {@link Vertex} of the {@code feature}
     * @param feature
     *            the {@link EStructuralFeature} describing the feature to
     *            access
     * @param size
     *            the new size
     */
    private void setSize(Vertex vertex, EStructuralFeature feature, int size) {
        vertex.setProperty(feature.getName() + SEPARATOR + SIZE_LITERAL, size);
    }

    /**
     * Creates a new container edge between {@code parentVertex} and
     * {@code childVertex}, and deletes any container edge previously linked to
     * {@code childVertex}.
     *
     * @param reference
     *            the containment {@link EReference}. This parameter is used to
     *            set the containing feature property in the create edge.
     * @param parentVertex
     *            the {@link Vertex} representing the container element
     * @param childVertex
     *            the {@link Vertex} representing the contained element
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
     * @param vertex
     *            the {@link Vertex} to reify
     *
     * @return an {@link InternalEObject} built from the provided {@link Vertex}
     *
     * @see DirectWriteBlueprintsStore#reifyVertex(Vertex, EClass)
     */
    protected InternalEObject reifyVertex(Vertex vertex) {
        return reifyVertex(vertex, null);
    }

    /**
     * Creates an {@link InternalEObject} from the given {@code vertex}, and
     * sets its {@link EClass} with the given {@link EClass}.
     * <p>
     * This method speeds-up the reification for objects with a known
     * {@link EClass} by avoiding unnecessary database accesses.
     *
     * @param vertex
     *            the {@link Vertex} to reify
     * @param eClass
     *            the {@link EClass} representing the type of the element to
     *            create
     *
     * @return an {@link InternalEObject} build from the provided {@link Vertex}
     */
    protected InternalEObject reifyVertex(Vertex vertex, @Nullable EClass eClass) {
        PersistentEObject internalEObject = backend.reifyVertex(vertex, eClass);
        if (internalEObject.resource() != resource()) {
            if (Iterables.isEmpty(vertex.getEdges(Direction.OUT, CONTAINER))) {
                // New format for eContents reference (used in VertexList)
                if (!Iterables.isEmpty(vertex.getVertices(Direction.IN, VertexList.VALUE + "_" + CONTENTS))) {
                    internalEObject.resource(resource());
                }
                // else : not part of the resource
            } else {
                internalEObject.resource(resource());
            }
        }
        return internalEObject;
    }

    /**
     * Search in the database the {@link Vertex} corresponding to the given
     * {@code id} and reifies it.
     *
     * @param id
     *            the {@link Id} to search
     *
     * @return a reified {@link EObject} if the corresponding {@link Vertex} has
     *         been found in the database, {@code null} otherwise
     */
    @Override
    public EObject eObject(Id id) {
        Vertex vertex = backend.getVertex(id);
        return isNull(vertex) ? null : reifyVertex(vertex);
    }

    /**
     * Computes efficiently {@code allInstances} operation by using underlying
     * graph facilities. This method uses database indices to avoid costly
     * traversal of the entire model.
     *
     * @param eClass
     *            the {@link EClass} to get the instances of
     * @param strict
     *            set to {@code true} if the method should look for instances of
     *            {@code eClass} only, set to {@code false} if the method should
     *            also return elements that are subclasses of {@code eClass}
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

    public VertexList listFor(Vertex from, EReference reference) {
        VertexList list = null;
        Vertex listBase = Iterables.getOnlyElement(
                from.getVertices(Direction.OUT, reference.getName()), null);
        if (isNull(listBase)) {
            listBase = backend.addVertex(StringId.generate());
            from.addEdge(reference.getName(), listBase);
            list = new VertexList(listBase, backend, reference);
        } else {
            Vertex head = Iterables.getOnlyElement(
                    listBase.getVertices(Direction.OUT, VertexList.HEAD), null);
            if (nonNull(head)) {
                Vertex tail = Iterables.getOnlyElement(
                        listBase.getVertices(Direction.OUT, VertexList.TAIL), null);
                list = new VertexList(listBase, head, tail, backend, reference);
            } else {
                list = new VertexList(listBase, backend, reference);
            }
        }
        return list;
    }

    private static class VertexList {

        protected static final String HEAD = "head";

        protected static final String TAIL = "tail";

        private static final String NEXT = "next";

        protected static final String VALUE = "value";

        private BlueprintsPersistenceBackend backend;

        private String refLabel;

        private Vertex base;

        private Vertex head;

        private Vertex tail;

        private VertexList(Vertex base, Vertex head, Vertex tail,
                BlueprintsPersistenceBackend backend, EReference reference) {
            this.base = base;
            this.head = head;
            this.tail = tail;
            this.backend = backend;
            this.refLabel = VALUE + "_" + reference.getName();
        }

        private VertexList(Vertex base, BlueprintsPersistenceBackend backend, EReference reference) {
            this(base, null, null, backend, reference);
            setSize(0);
        }

        public Vertex get(int index) {
            int size = size();
            if(size == 0) {
                return null;
            }
            Vertex node = null;
            if(index == EStore.NO_INDEX) {
                // The list represents a single-valued reference
                node = getNodeAtIndex(0, size);
            }
            else {
                node = getNodeAtIndex(index, size);
            }
            return getValue(node);
        }

        public void add(Vertex v, int index) {
            /*
             * Create the list node and associate it to its value
             */
            Vertex newNode = backend.addVertex(StringId.generate());
            newNode.addEdge(refLabel, v);
            int size = size();

            if (index == 0) {
                setHead(newNode);
            } else if (index == size) {
                setTail(newNode);
            } else {
                Vertex oldNode = getNodeAtIndex(index, size);
                Edge inNext = Iterables.getOnlyElement(oldNode.getEdges(Direction.IN, NEXT), null);
                Vertex inNode = inNext.getVertex(Direction.OUT);
                if (inNode.equals(oldNode)) {
                    // Debug
                    throw new RuntimeException("in/out error");
                }
                inNext.remove();
                inNode.addEdge(NEXT, newNode);
                newNode.addEdge(NEXT, oldNode);
            }

            setSize(size + 1);
        }

        public Vertex set(Vertex v, int index) {
            int size = size();
            Vertex oldNode = null;
            if(index == EStore.NO_INDEX) {
                // The list represents a single-valued reference
                if(size == 0) {
                    add(v, 0);
                    return null;
                }
                else {
                    oldNode = getNodeAtIndex(0, size);
                }
            }
            else {
                oldNode = getNodeAtIndex(index, size);
            }
            Vertex oldNodeValue = getValue(oldNode);
            oldNode.getEdges(Direction.OUT, refLabel).forEach(e -> e.remove());
            oldNode.addEdge(refLabel, v);
            return oldNodeValue;
        }

        public Vertex remove(int index) {
            int size = size();
            Vertex oldNode = getNodeAtIndex(index, size);
            Vertex oldNodeValue = getValue(oldNode);
            Vertex prevNode = getPrev(oldNode);
            Vertex nextNode = getNext(oldNode);
            if (isNull(prevNode)) {
                // removing the head
                setHead(nextNode);
            } else if (isNull(nextNode)) {
                // removing the tail
                setTail(prevNode);
            } else {
                prevNode.addEdge(NEXT, nextNode);
            }
            oldNode.getEdges(Direction.BOTH, NEXT, refLabel, HEAD, TAIL).forEach(e -> e.remove());
            oldNode.remove();
            setSize(size - 1);
            return oldNodeValue;
        }

        public int indexOf(Id id) {
            int index = 0;
            Vertex node = head;
            while (nonNull(node)) {
                if (Objects.equals(getValue(node).getId(), id)) {
                    return index;
                }
                index++;
            }
            return ArrayUtils.INDEX_NOT_FOUND;
        }

        public int lastIndexOf(Id id) {
            int size = size();
            int index = size - 1;
            Vertex node = tail;
            while (nonNull(tail)) {
                if (Objects.equals(getValue(node).getId(), id)) {
                    return index;
                }
                index--;
            }
            return ArrayUtils.INDEX_NOT_FOUND;
        }

        public boolean contains(Vertex vertex) {
            Iterable<Vertex> refNodes = vertex.getVertices(Direction.IN, refLabel);
            if (Iterables.isEmpty(refNodes)) {
                return false;
            } else {
                for (Vertex refNode : refNodes) {
                    /*
                     * This iterates multiple times only if references with the
                     * same name point to this object, this should not happen
                     * often in real-life metamodels.
                     */
                    Vertex node = refNode;
                    while (nonNull(node)) {
                        if (node.equals(tail)) {
                            /*
                             * Comparing tail and head should be equivalent: we
                             * don't know if the element is at the beginning or
                             * the end of the list
                             */
                            return true;
                        }
                        node = getNext(node);
                    }
                }
            }
            throw new RuntimeException("Should never happen");
            // Vertex node = head;
            // while(nonNull(node)) {
            // Vertex nodeValue = getValue(node);
            // if(Objects.equals(nodeValue.getId(), vertex.getId())) {
            // return true;
            // }
            // node = getNext(node);
            // }
            // return false;
        }

        public <T> T[] toArray(T[] array) {
            int size = size();
            Object[] result = new Object[size];

            int index = 0;
            Vertex node = head;
            while (nonNull(node)) {
                // Reify here to avoid multiple iterations on the array
                // Not clean, we should not manipulate EObjects in this class
                result[index] = backend.reifyVertex(getValue(node));
                index++;
            }
            if (isNull(array)) {
                return (T[]) result;
            } else {
                System.arraycopy(result, 0, array, 0, result.length);
                return array;
            }
        }

        public void clear() {
            base.getEdges(Direction.BOTH, HEAD, TAIL).forEach(e -> e.remove());
            setSize(0);
        }

        public int size() {
            return base.getProperty(DirectWriteBlueprintsStore.SIZE_LITERAL);
        }

        private void setSize(int newSize) {
            base.setProperty(DirectWriteBlueprintsStore.SIZE_LITERAL, newSize);
        }

        private void setHead(Vertex newHead) {
            if (isNull(head)) {
                head = newHead;
                tail = newHead;
                Edge previousTail = Iterables.getOnlyElement(base.getEdges(Direction.OUT, TAIL),
                        null);
                if (nonNull(previousTail)) {
                    previousTail.remove();
                }
                base.addEdge(TAIL, tail);
            } else {
                newHead.addEdge(NEXT, head);
                head = newHead;
            }
            Edge previousHead = Iterables.getOnlyElement(base.getEdges(Direction.OUT, HEAD), null);
            if (nonNull(previousHead)) {
                previousHead.remove();
            }
            base.addEdge(HEAD, head);

        }

        private void setTail(Vertex newTail) {
            if (isNull(tail)) {
                tail = newTail;
                head = newTail;
                Edge previousHead = Iterables.getOnlyElement(base.getEdges(Direction.OUT, HEAD),
                        null);
                if (nonNull(previousHead)) {
                    previousHead.remove();
                }
                base.addEdge(HEAD, head);
            } else {
                tail.addEdge(NEXT, newTail);
                Edge previousTail = Iterables.getOnlyElement(base.getEdges(Direction.OUT, TAIL),
                        null);
                if (nonNull(previousTail)) {
                    previousTail.remove();
                }
                tail = newTail;
            }
            base.addEdge(TAIL, tail);
        }

        private Vertex getNext(Vertex from) {
            return Iterables.getOnlyElement(from.getVertices(Direction.OUT, NEXT), null);
        }

        private Vertex getPrev(Vertex from) {
            return Iterables.getOnlyElement(from.getVertices(Direction.IN, NEXT), null);
        }

        private Vertex getValue(Vertex from) {
            return Iterables.getOnlyElement(from.getVertices(Direction.OUT, refLabel), null);
        }

        private Vertex getNodeAtIndex(int index, int size) {
            checkElementIndex(index, size);
            Vertex node = null;
            if (index < size / 2) {
                node = head;
                for (int i = 0; i < index; i++) {
                    node = getNext(node);
                }
            } else {
                node = tail;
                for (int i = 0; i < (size - 1) - index; i++) {
                    node = getPrev(node);
                }
            }
            return node;
        }

    }
}
