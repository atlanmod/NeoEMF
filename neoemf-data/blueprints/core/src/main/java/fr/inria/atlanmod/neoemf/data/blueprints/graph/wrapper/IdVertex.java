/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.StringFactory;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.collect.MoreIterables.firstElement;

/**
 * Re-implemented from {@link com.tinkerpop.blueprints.util.wrappers.id.IdVertex}.
 *
 * @param <G>
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

    @Nonnull
    public <E extends Edge> Optional<E> getEdge(Direction direction, String label, BiFunction<Edge, G, E> mappingFunc) {
        return firstElement(getEdges(direction, new String[]{label}, mappingFunc));
    }

    @Nonnull
    public <V extends Vertex> Optional<V> getVertex(Direction direction, String label, BiFunction<Vertex, G, V> mappingFunc) {
        return firstElement(getVertices(direction, new String[]{label}, mappingFunc));
    }

    @Nonnull
    public <E extends Edge> Iterable<E> getEdges(Direction direction, String[] labels, BiFunction<Edge, G, E> mappingFunc) {
        return new IdElementIterable<>(base.getEdges(direction, labels), e -> mappingFunc.apply(e, graph));
    }

    @Nonnull
    public <V extends Vertex> Iterable<V> getVertices(Direction direction, String[] labels, BiFunction<Vertex, G, V> mappingFunc) {
        return new IdElementIterable<>(base.getVertices(direction, labels), v -> mappingFunc.apply(v, graph));
    }

    @Nonnull
    public <E extends Edge> E addEdge(String label, Vertex vertex, BiFunction<Edge, G, E> mappingFunc) {
        return graph.addEdge(null, this, vertex, label, mappingFunc);
    }

    // endregion
}
