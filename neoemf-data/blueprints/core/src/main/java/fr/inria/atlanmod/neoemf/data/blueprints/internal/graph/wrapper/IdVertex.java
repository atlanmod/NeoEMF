/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.wrapper;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.StringFactory;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.collect.MoreIterables.onlyElement;

/**
 * An {@link Vertex} able to map the result of each method call to a dedicated implementation.
 * <p>
 * Re-implemented from {@link com.tinkerpop.blueprints.util.wrappers.id.IdVertex}.
 *
 * @param <G> the type of the graph that owns this vertex
 */
@ParametersAreNonnullByDefault
public class IdVertex<G extends IdGraph<G>> extends IdElement<Vertex, G> implements Vertex {

    /**
     * Constructs a new {@code IdVertex}.
     *
     * @param baseVertex the base vertex
     * @param graph      the graph that owns this vertex
     */
    public IdVertex(Vertex baseVertex, G graph) {
        super(baseVertex, graph, graph.isSupportVertexIds());
    }

    @Override
    public void remove() {
        graph.removeVertex(this);
    }

    /**
     * Return the only edge incident to the vertex according to the provided direction and edge label.
     *
     * @param direction the direction of the edge to retrieve
     * @param label     the label of the edge to retrieve
     *
     * @return the incident edge
     *
     * @see #getEdges(Direction, String...)
     */
    @Nonnull
    public Optional<Edge> getEdge(Direction direction, String label) {
        return getEdge(direction, label, IdEdge::new);
    }

    /**
     * Return the only vertex adjacent to the vertex according to the provided direction and edge label.
     *
     * @param direction the direction of the edge of the adjacent vertex
     * @param label     the label of the edge of the adjacent vertex
     *
     * @return the adjacent vertex
     *
     * @see #getVertices(Direction, String...)
     */
    @Nonnull
    public Optional<Vertex> getVertex(Direction direction, String label) {
        return getVertex(direction, label, IdVertex::new);
    }

    @Nonnull
    @Override
    public Iterable<Edge> getEdges(Direction direction, String... labels) {
        return getEdges(direction, labels, IdEdge::new);
    }

    @Nonnull
    @Override
    public Iterable<Vertex> getVertices(Direction direction, String... labels) {
        return getVertices(direction, labels, IdVertex::new);
    }

    @Nonnull
    @Override
    public IdVertexQuery<?, G> query() {
        return new IdVertexQuery<>(base.query(), graph);
    }

    @Nonnull
    @Override
    public Edge addEdge(String label, Vertex vertex) {
        return addEdge(label, vertex, IdEdge::new);
    }

    @Nonnull
    @Override
    public String toString() {
        return StringFactory.vertexString(this);
    }

    // region Internal

    /**
     * Return the only edge incident to the vertex according to the provided direction and edge label.
     *
     * @param direction   the direction of the edge to retrieve
     * @param label       the label of the edge to retrieve
     * @param mappingFunc the function to create a new dedicated edge from another
     * @param <E>         the type of the edge after mapping
     *
     * @return the incident edge
     *
     * @see org.atlanmod.commons.collect.MoreIterables#onlyElement(Iterable)
     * @see #getEdges(Direction, String...)
     */
    @Nonnull
    public <E extends Edge> Optional<E> getEdge(Direction direction, String label, BiFunction<Edge, G, E> mappingFunc) {
        return onlyElement(getEdges(direction, new String[]{label}, mappingFunc));
    }

    /**
     * Return the only vertex adjacent to the vertex according to the provided direction and edge label.
     *
     * @param direction   the direction of the edge of the adjacent vertex
     * @param label       the label of the edge of the adjacent vertex
     * @param mappingFunc the function to create a new dedicated vertex from another
     * @param <V>         the type of the vertex after mapping
     *
     * @return the adjacent vertex
     *
     * @see org.atlanmod.commons.collect.MoreIterables#onlyElement(Iterable)
     * @see #getVertices(Direction, String...)
     */
    @Nonnull
    public <V extends Vertex> Optional<V> getVertex(Direction direction, String label, BiFunction<Vertex, G, V> mappingFunc) {
        return onlyElement(getVertices(direction, new String[]{label}, mappingFunc));
    }

    /**
     * Return the edges incident to the vertex according to the provided direction and edge labels.
     *
     * @param direction   the direction of the edges to retrieve
     * @param labels      the labels of the edges to retrieve
     * @param mappingFunc the function to create a new dedicated edge from another
     * @param <E>         the type of the edge after mapping
     *
     * @return an iterable of incident edges
     *
     * @see #getEdges(Direction, String...)
     */
    @Nonnull
    public <E extends Edge> Iterable<E> getEdges(Direction direction, String[] labels, BiFunction<Edge, G, E> mappingFunc) {
        return new IdElementIterable<>(base.getEdges(direction, labels), e -> mappingFunc.apply(e, graph));
    }

    /**
     * Return the vertices adjacent to the vertex according to the provided direction and edge labels.
     * This method does not remove duplicate vertices (i.e. those vertices that are connected by more than one edge).
     *
     * @param direction   the direction of the edges of the adjacent vertices
     * @param labels      the labels of the edges of the adjacent vertices
     * @param mappingFunc the function to create a new dedicated vertex from another
     * @param <V>         the type of the vertex after mapping
     *
     * @return an iterable of adjacent vertices
     *
     * @see #getVertices(Direction, String...)
     */
    @Nonnull
    public <V extends Vertex> Iterable<V> getVertices(Direction direction, String[] labels, BiFunction<Vertex, G, V> mappingFunc) {
        return new IdElementIterable<>(base.getVertices(direction, labels), v -> mappingFunc.apply(v, graph));
    }

    /**
     * Add a new outgoing edge from this vertex to the parameter vertex with provided edge label.
     *
     * @param label       the label of the edge
     * @param vertex      the vertex to connect to with an incoming edge
     * @param mappingFunc the function to create a new dedicated edge from another
     * @param <E>         the type of the edge after mapping
     *
     * @return the newly created edge
     *
     * @see #addEdge(String, Vertex)
     */
    @Nonnull
    public <E extends Edge> E addEdge(String label, Vertex vertex, BiFunction<Edge, G, E> mappingFunc) {
        return graph.addEdge(null, this, vertex, label, mappingFunc);
    }

    // endregion
}
