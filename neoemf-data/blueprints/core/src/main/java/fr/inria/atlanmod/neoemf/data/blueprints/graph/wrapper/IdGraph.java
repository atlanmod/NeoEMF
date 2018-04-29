/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Features;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.IndexableGraph;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Parameter;
import com.tinkerpop.blueprints.TransactionalGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.StringFactory;
import com.tinkerpop.blueprints.util.wrappers.WrapperGraph;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.log.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkInstanceOf;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link com.tinkerpop.blueprints.KeyIndexableGraph} able to map the result of each method call to a dedicated implementation.
 * <p>
 * Re-implemented from {@link com.tinkerpop.blueprints.util.wrappers.id.IdGraph}.
 *
 * @param <G> the "self"-type of this graph
 */
@ParametersAreNonnullByDefault
public class IdGraph<G extends IdGraph<G>> implements WrapperGraph<KeyIndexableGraph>, KeyIndexableGraph, IndexableGraph, TransactionalGraph {

    /**
     * The property key used to identify {@link Vertex} and {@link Edge} instances.
     *
     * <b>NOTE:</b> using "__id" instead of "_id" avoids collision with Rexster's "_id".
     */
    @Nonnull
    protected static final String ID = "__id";

    /**
     * The base graph which does not necessarily support custom IDs.
     */
    @Nonnull
    private final KeyIndexableGraph baseGraph;

    /**
     * {@code true} if this graph support custom vertex IDs.
     */
    private final boolean supportVertexIds;

    /**
     * {@code true} if this graph support custom edge IDs.
     */
    private final boolean supportEdgeIds;

    /**
     * The features supported by this graph.
     */
    @Nonnull
    private final Features features;

    /**
     * Adds custom ID functionality to the given graph, supporting either custom vertex IDs, custom edge IDs, or both.
     *
     * @param baseGraph        the base graph which does not necessarily support custom IDs
     * @param supportVertexIds whether to support custom vertex IDs
     * @param supportEdgeIds   whether to support custom edge IDs
     */
    public IdGraph(KeyIndexableGraph baseGraph, boolean supportVertexIds, boolean supportEdgeIds) {
        this.baseGraph = checkNotNull(baseGraph, "baseGraph");

        this.supportVertexIds = supportVertexIds;
        this.supportEdgeIds = supportEdgeIds;

        checkArgument(supportVertexIds || supportEdgeIds, "If neither custom vertex IDs nor custom edge IDs are supported, IdGraph can't help you!");

        // Create indices
        if (supportVertexIds && !baseGraph.getIndexedKeys(Vertex.class).contains(ID)) {
            baseGraph.createKeyIndex(ID, Vertex.class);
        }

        if (supportEdgeIds && !baseGraph.getIndexedKeys(Edge.class).contains(ID)) {
            baseGraph.createKeyIndex(ID, Edge.class);
        }

        features = baseGraph.getFeatures().copyFeatures();
        features.isWrapper = true;
        features.ignoresSuppliedIds = false;
    }

    /**
     * Returns {@code true} if this graph support custom vertex IDs.
     *
     * @return {@code true} if this graph support custom vertex IDs.
     */
    public boolean isSupportVertexIds() {
        return supportVertexIds;
    }

    /**
     * Returns {@code true} if this graph support custom edge IDs.
     *
     * @return {@code true} if this graph support custom edge IDs.
     */
    public boolean isSupportEdgeIds() {
        return supportEdgeIds;
    }

    @Nonnull
    @Override
    public Features getFeatures() {
        return features;
    }

    // region Vertices

    @Nonnull
    @Override
    public Vertex addVertex(@Nullable Object id) {
        return addVertex(id, IdVertex::new);
    }

    @Override
    public Vertex getVertex(Object id) {
        return getVertex(id, IdVertex::new).orElse(null);
    }

    @Override
    public void removeVertex(Vertex vertex) {
        checkNativeElement(vertex);

        baseGraph.removeVertex(getBaseElement(vertex));
    }

    @Nonnull
    @Override
    public Iterable<Vertex> getVertices() {
        return getVertices(IdVertex::new);
    }

    @Nonnull
    @Override
    public Iterable<Vertex> getVertices(String key, Object value) {
        return getVertices(key, value, IdVertex::new);
    }

    // endregion

    // region Edges

    @Nonnull
    @Override
    public Edge addEdge(@Nullable Object id, Vertex outVertex, Vertex inVertex, String label) {
        return addEdge(id, outVertex, inVertex, label, IdEdge::new);
    }

    @Override
    public Edge getEdge(Object id) {
        return getEdge(id, IdEdge::new).orElse(null);
    }

    @Override
    public void removeEdge(Edge edge) {
        checkNativeElement(edge);

        baseGraph.removeEdge(getBaseElement(edge));
    }

    @Nonnull
    @Override
    public Iterable<Edge> getEdges() {
        return getEdges(IdEdge::new);
    }

    @Nonnull
    @Override
    public Iterable<Edge> getEdges(String key, Object value) {
        return getEdges(key, value, IdEdge::new);
    }

    // endregion

    @Nonnull
    @Override
    public IdGraphQuery<G> query() {
        return new IdGraphQuery<>(baseGraph.query(), me());
    }

    @Override
    public void shutdown() {
        baseGraph.shutdown();
    }

    @Nonnull
    @Override
    public KeyIndexableGraph getBaseGraph() {
        return baseGraph;
    }

    @Nonnull
    @Override
    public String toString() {
        return StringFactory.graphString(this, baseGraph.toString());
    }

    // region Transactions

    /**
     * {@inheritDoc}
     * <p>
     * This is a no-op if the base graph is not an instance of {@link TransactionalGraph}.
     *
     * @deprecated Follow the Blueprints API
     */
    @Override
    @Deprecated
    public void stopTransaction(Conclusion conclusion) {
        if (Conclusion.SUCCESS == conclusion) {
            commit();
        }
        else {
            rollback();
        }
    }

    @Override
    public void commit() {
        if (TransactionalGraph.class.isInstance(baseGraph)) {
            TransactionalGraph.class.cast(baseGraph).commit();
        }
    }

    @Override
    public void rollback() {
        if (TransactionalGraph.class.isInstance(baseGraph)) {
            TransactionalGraph.class.cast(baseGraph).rollback();
        }
    }

    // endregion

    // region Indices

    @Override
    public <E extends Element> void dropKeyIndex(String key, Class<E> elementClass) {
        checkIndexableKey(key, elementClass);

        baseGraph.dropKeyIndex(key, elementClass);
    }

    @Override
    @SuppressWarnings("rawtypes") // indexParameters
    public <E extends Element> void createKeyIndex(String key, Class<E> elementClass, Parameter... indexParameters) {
        checkIndexableKey(key, elementClass);

        baseGraph.createKeyIndex(key, elementClass, indexParameters);
    }

    @Nonnull
    @Override
    public <E extends Element> Set<String> getIndexedKeys(Class<E> elementClass) {
        checkNotNull(elementClass, "elementClass");

        Set<String> keys = new HashSet<>(baseGraph.getIndexedKeys(elementClass));
        keys.remove(ID);
        return keys;
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"}) // indexParameters
    public <E extends Element> Index<E> createIndex(String indexName, Class<E> indexClass, Parameter... indexParameters) {
        if (nonNull(indexParameters) && indexParameters.length > 0) {
            Log.warn("Index parameters are ignored. {0}", Arrays.toString(indexParameters));
        }

        final BiFunction<E, G, E> mappingFunc = isVertexClass(indexClass)
                ? (v, g) -> (E) new IdVertex<>((Vertex) v, g)
                : (e, g) -> (E) new IdEdge<>((Edge) e, g);

        return createIndex(indexName, indexClass, mappingFunc);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends Element> Index<E> getIndex(String indexName, Class<E> indexClass) {
        final BiFunction<E, G, E> mappingFunc = isVertexClass(indexClass)
                ? (v, g) -> (E) new IdVertex<>((Vertex) v, g)
                : (e, g) -> (E) new IdEdge<>((Edge) e, g);

        return getIndex(indexName, indexClass, mappingFunc);
    }

    @Nonnull
    @Override
    public Iterable<Index<? extends Element>> getIndices() {
        throw new UnsupportedOperationException("sorry, you currently can't get a list of indexes through IdGraph");
    }

    @Override
    public void dropIndex(String indexName) {
        checkIndexableGraph();

        final IndexableGraph indexableBaseGraph = (IndexableGraph) baseGraph;
        indexableBaseGraph.dropIndex(indexName);
    }

    // endregion

    // region Utilities

    /**
     * Returns the base element of the specified element.
     *
     * @param e the element
     *
     * @return the base element
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private <E extends Element> E getBaseElement(E e) {
        return ((IdElement<E, G>) e).getBaseElement();
    }

    /**
     * Ensures that the specified element has been created in the graph.
     *
     * @param e the element to test
     */
    private void checkNativeElement(final Element e) {
        checkInstanceOf(e, IdElement.class, "Given element was not created in this graph");
    }

    /**
     * Ensures that the base graph is indexable.
     */
    private void checkIndexableGraph() {
        checkInstanceOf(baseGraph, IndexableGraph.class, "The base graph is not an indexable graph");
    }

    /**
     * Ensures that the specified {@code key} is valid for indexation.
     *
     * @param key          the key to test
     * @param elementClass the class of the indexed element
     */
    private void checkIndexableKey(String key, Class<? extends Element> elementClass) {
        checkNotNull(elementClass, "elementClass");

        boolean isVertex = isVertexClass(elementClass);
        boolean isSupported = isVertex && supportVertexIds || !isVertex && supportEdgeIds;

        checkArgument(!isSupported || !key.equals(ID), "index key %s is reserved by IdGraph", ID);
    }

    /**
     * Returns {@code true} if the specified class is assignable from {@link Vertex}.
     *
     * @param type the class to test
     *
     * @return {@code true} if the specified class is assignable from {@link Vertex}
     */
    private <E extends Element> boolean isVertexClass(Class<? extends E> type) {
        return Vertex.class.isAssignableFrom(type);
    }

    // endregion

    // region Internal

    /**
     * Create a new vertex, add it to the graph, and return the newly created vertex.
     *
     * @param id          the identifier of the vertex
     * @param mappingFunc the function to create a new dedicated vertex from another
     * @param <V>         the type of the vertex after mapping
     *
     * @return the newly created vertex
     *
     * @see #addVertex(Object)
     */
    @Nonnull
    public <V extends Vertex> V addVertex(@Nullable Object id, BiFunction<Vertex, G, V> mappingFunc) {
        final Vertex base = baseGraph.addVertex(null);

        if (supportVertexIds) {
            Object v = checkNotNull(id, "id");
            base.setProperty(ID, v);
        }

        return mappingFunc.apply(base, me());
    }

    /**
     * Return the vertex referenced by the provided object identifier.
     *
     * @param id          the identifier of the vertex
     * @param mappingFunc the function to create a new dedicated vertex from another
     * @param <V>         the type of the vertex after mapping
     *
     * @return an {@link Optional} containing the vertex referenced by the provided identifier
     *
     * @see #getVertex(Object)
     */
    @Nonnull
    public <V extends Vertex> Optional<V> getVertex(Object id, BiFunction<Vertex, G, V> mappingFunc) {
        checkNotNull(id, "id");

        Optional<Vertex> vertex = supportVertexIds
                ? MoreIterables.onlyElement(baseGraph.getVertices(ID, id))
                : Optional.ofNullable(baseGraph.getVertex(id));

        return vertex.map(v -> mappingFunc.apply(v, me()));
    }

    /**
     * Return an iterable to all the vertices in the graph.
     *
     * @param mappingFunc the function to create a new dedicated vertex from another
     * @param <V>         the type of the vertex after mapping
     *
     * @return an iterable reference to all vertices in the graph
     *
     * @see #getVertices()
     */
    @Nonnull
    public <V extends Vertex> Iterable<V> getVertices(BiFunction<Vertex, G, V> mappingFunc) {
        return new IdElementIterable<>(baseGraph.getVertices(), v -> mappingFunc.apply(v, me()));
    }

    /**
     * Return an iterable to all the vertices in the graph that have a particular key/value property.
     *
     * @param key         the key of vertex
     * @param value       the value of the vertex
     * @param mappingFunc the function to create a new dedicated vertex from another
     * @param <V>         the type of the vertex after mapping
     *
     * @return an iterable of vertices with provided key and value
     *
     * @see #getVertices(String, Object)
     */
    @Nonnull
    public <V extends Vertex> Iterable<V> getVertices(String key, Object value, BiFunction<Vertex, G, V> mappingFunc) {
        checkIndexableKey(key, Vertex.class);

        return new IdElementIterable<>(baseGraph.getVertices(key, value), v -> mappingFunc.apply(v, me()));
    }

    /**
     * Add an edge to the graph. The added edges requires a recommended identifier, a tail vertex, an head vertex, and a label.
     *
     * @param id          the identifier of the edge
     * @param outVertex   the vertex on the tail of the edge
     * @param inVertex    the vertex on the head of the edge
     * @param label       the label associated with the edge
     * @param mappingFunc the function to create a new dedicated edge from another
     * @param <E>         the type of the edge after mapping
     *
     * @return the newly created edge
     *
     * @see #addEdge(Object, Vertex, Vertex, String)
     */
    @Nonnull
    public <E extends Edge> E addEdge(@Nullable Object id, Vertex outVertex, Vertex inVertex, String label, BiFunction<Edge, G, E> mappingFunc) {
        checkNativeElement(outVertex);
        checkNativeElement(inVertex);

        Edge base = baseGraph.addEdge(null, getBaseElement(outVertex), getBaseElement(inVertex), label);

        if (supportEdgeIds) {
            Object v = checkNotNull(id, "id");
            base.setProperty(ID, v);
        }

        return mappingFunc.apply(base, me());
    }

    /**
     * Return the edge referenced by the provided object identifier.
     *
     * @param id          the identifier of the edge to retrieved from the graph
     * @param mappingFunc the function to create a new dedicated edge from another
     * @param <E>         the type of the edge after mapping
     *
     * @return an {@link Optional} containing the edge referenced by the provided identifier
     *
     * @see #getEdge(Object)
     */
    @Nonnull
    public <E extends Edge> Optional<E> getEdge(Object id, BiFunction<Edge, G, E> mappingFunc) {
        checkNotNull(id, "id");

        Optional<Edge> edge = supportEdgeIds ?
                MoreIterables.onlyElement(baseGraph.getEdges(ID, id)) :
                Optional.ofNullable(baseGraph.getEdge(id));

        return edge.map(e -> mappingFunc.apply(e, me()));
    }

    /**
     * Return an iterable to all the edges in the graph.
     *
     * @param mappingFunc the function to create a new dedicated edge from another
     * @param <E>         the type of the edge after mapping
     *
     * @return an iterable reference to all edges in the graph
     *
     * @see #getEdges()
     */
    @Nonnull
    public <E extends Edge> Iterable<E> getEdges(BiFunction<Edge, G, E> mappingFunc) {
        return new IdElementIterable<>(baseGraph.getEdges(), e -> mappingFunc.apply(e, me()));
    }

    /**
     * Return an iterable to all the edges in the graph that have a particular key/value property.
     *
     * @param key         the key of the edge
     * @param value       the value of the edge
     * @param mappingFunc the function to create a new dedicated edge from another
     * @param <E>         the type of the edge after mapping
     *
     * @return an iterable of edges with provided key and value
     *
     * @see #getEdges(String, Object)
     */
    @Nonnull
    public <E extends Edge> Iterable<E> getEdges(String key, Object value, BiFunction<Edge, G, E> mappingFunc) {
        checkIndexableKey(key, Edge.class);

        return new IdElementIterable<>(baseGraph.getEdges(key, value), e -> mappingFunc.apply(e, me()));
    }

    /**
     * Generate an index with a particular name for a particular class.
     *
     * @param indexName   the name of the manual index
     * @param indexClass  the element class that this index is indexing (can be base class)
     * @param mappingFunc the function to create a new dedicated element from another
     * @param <T>         the element class that this index is indexing (can be base class)
     * @param <U>         the type of the edge indexed element mapping
     *
     * @return the index created
     *
     * @see #createIndex(String, Class, Parameter[])
     */
    @Nonnull
    public <T extends Element, U extends T> Index<U> createIndex(String indexName, Class<T> indexClass, BiFunction<T, G, U> mappingFunc) {
        checkIndexableGraph();

        final IndexableGraph indexableBaseGraph = (IndexableGraph) baseGraph;
        final Index<T> baseIndex = indexableBaseGraph.createIndex(indexName, indexClass);

        return new IdElementIndex<>(baseIndex, e -> mappingFunc.apply(e, me()));
    }

    /**
     * Get an index from the graph by its name and index class. An index is unique up to name.
     *
     * @param indexName   the name of the index to retrieve
     * @param indexClass  the class of the elements being indexed (can be base class)
     * @param mappingFunc the function to create a new dedicated element from another
     * @param <T>         the class of the elements being indexed (can be base class)
     * @param <U>         the type of the edge indexed element mapping
     *
     * @return the retrieved index
     *
     * @see #getIndex(String, Class)
     */
    @Nullable
    public <T extends Element, U extends T> Index<U> getIndex(String indexName, Class<T> indexClass, BiFunction<T, G, U> mappingFunc) {
        checkIndexableGraph();

        final IndexableGraph indexableBaseGraph = (IndexableGraph) baseGraph;
        final Index<T> baseIndex = indexableBaseGraph.getIndex(indexName, indexClass);

        return nonNull(baseIndex)
                ? new IdElementIndex<>(baseIndex, e -> mappingFunc.apply(e, me()))
                : null;
    }

    /**
     * Returns the index, from the graph, by its name and index class. An index is unique up to name. If no such index
     * exists, it will be created.
     *
     * @param indexName   the name of the manual index
     * @param indexClass  the element class that this index is indexing (can be base class)
     * @param mappingFunc the function to create a new dedicated element from another
     * @param <T>         the element class that this index is indexing (can be base class)
     * @param <U>         the type of the edge indexed element mapping
     *
     * @return the index
     *
     * @see #getIndex(String, Class, BiFunction)
     * @see #createIndex(String, Class, BiFunction)
     */
    @Nonnull
    public <T extends Element, U extends T> Index<U> getOrCreateIndex(String indexName, Class<T> indexClass, BiFunction<T, G, U> mappingFunc) {
        return Optional.ofNullable(getIndex(indexName, indexClass, mappingFunc))
                .orElseGet(() -> createIndex(indexName, indexClass, mappingFunc));
    }

    // endregion

    /**
     * Returns this instance, casted as a {@code G}.
     *
     * @return this instance
     */
    @SuppressWarnings("unchecked")
    private G me() {
        return (G) this;
    }
}
