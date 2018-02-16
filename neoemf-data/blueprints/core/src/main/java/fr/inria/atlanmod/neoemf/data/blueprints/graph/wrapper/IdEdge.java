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

import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * TODO
 * <p>
 * Re-implemented from {@link com.tinkerpop.blueprints.util.wrappers.id.IdEdge}.
 *
 * @param <G> the type of the graph that owns this edge
 */
@ParametersAreNonnullByDefault
public class IdEdge<G extends IdGraph<G>> extends IdElement<Edge, G> implements Edge {

    /**
     * Constructs a new {@code IdEdge}.
     *
     * @param base  the base edge
     * @param graph the graph that owns this edge
     */
    public IdEdge(Edge base, G graph) {
        super(base, graph, graph.isSupportEdgeIds());
    }

    @Override
    public void remove() {
        graph.removeEdge(this);
    }

    @Nonnull
    @Override
    public Vertex getVertex(Direction direction) {
        return getVertex(direction, IdVertex::new);
    }

    @Override
    public String getLabel() {
        return base.getLabel();
    }

    @Override
    public String toString() {
        return StringFactory.edgeString(this);
    }

    // region Internal

    /**
     * TODO
     *
     * @param direction
     * @param mappingFunc
     * @param <E>
     *
     * @return
     */
    @Nonnull
    public <E extends Vertex> E getVertex(Direction direction, BiFunction<Vertex, G, E> mappingFunc) {
        return mappingFunc.apply(base.getVertex(direction), graph);
    }

    // endregion
}
