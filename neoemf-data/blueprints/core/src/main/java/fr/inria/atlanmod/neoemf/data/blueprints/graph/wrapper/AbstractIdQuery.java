/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Predicate;
import com.tinkerpop.blueprints.Query;
import com.tinkerpop.blueprints.Vertex;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import static fr.inria.atlanmod.commons.collect.MoreIterables.firstElement;

public class AbstractIdQuery<Q extends AbstractIdQuery<Q, T, G>, T extends Query, G extends IdGraph<G>> extends AbstractGraphBasedObject<T, G> implements Query {

    /**
     * Constructs a new {@code AbstractIdQuery}.
     *
     * @param base  the base query
     * @param graph the graph that owns this element
     */
    public AbstractIdQuery(T base, G graph) {
        super(base, graph);
    }

    @Nonnull
    @Override
    public Q has(String key) {
        base.has(key);
        return me();
    }

    @Nonnull
    @Override
    public Q hasNot(String key) {
        base.hasNot(key);
        return me();
    }

    @Nonnull
    @Override
    public Q has(String key, Object value) {
        base.has(key, value);
        return me();
    }

    @Nonnull
    @Override
    public Q hasNot(String key, Object value) {
        base.hasNot(key, value);
        return me();
    }

    @Nonnull
    @Override
    public Q has(String key, Predicate predicate, Object value) {
        base.has(key, predicate, value);
        return me();
    }

    @Nonnull
    @Override
    @Deprecated
    public <C extends Comparable<C>> Q has(String key, C value, Compare compare) {
        base.has(key, value, compare);
        return me();
    }

    @Nonnull
    @Override
    public <C extends Comparable<?>> Q interval(String key, C startValue, C endValue) {
        base.interval(key, startValue, endValue);
        return me();
    }

    @Nonnull
    @Override
    public Q limit(int limit) {
        base.limit(limit);
        return me();
    }

    @Nonnull
    @Override
    public Iterable<Edge> edges() {
        return edges(IdEdge::new);
    }

    @Nonnull
    @Override
    public Iterable<Vertex> vertices() {
        return vertices(IdVertex::new);
    }

    @Nonnull
    public <E extends Edge> Iterable<E> edges(BiFunction<Edge, G, E> mappingFunc) {
        return new IdElementIterable<>(base.edges(), e -> mappingFunc.apply(e, graph));
    }

    @Nonnull
    public <E extends Edge> Optional<E> edge(BiFunction<Edge, G, E> mappingFunc) {
        return firstElement(edges(mappingFunc));
    }

    @Nonnull
    public <V extends Vertex> Iterable<V> vertices(BiFunction<Vertex, G, V> mappingFunc) {
        return new IdElementIterable<>(base.vertices(), v -> mappingFunc.apply(v, graph));
    }

    @Nonnull
    public <V extends Vertex> Optional<V> vertex(BiFunction<Vertex, G, V> mappingFunc) {
        return firstElement(vertices(mappingFunc));
    }

    @SuppressWarnings("unchecked")
    protected Q me() {
        return (Q) this;
    }
}
