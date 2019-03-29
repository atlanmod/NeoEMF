/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.wrapper;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.VertexQuery;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link VertexQuery} able to map the result of each method call to a dedicated implementation.
 * <p>
 * Re-implemented from {@link com.tinkerpop.blueprints.util.wrappers.WrapperVertexQuery}.
 *
 * @param <Q> the "self"-type of this query
 * @param <G> the type of the graph where to execute the query
 */
@ParametersAreNonnullByDefault
public class IdVertexQuery<Q extends IdVertexQuery<Q, G>, G extends IdGraph<G>> extends AbstractIdQuery<Q, VertexQuery, G> implements VertexQuery {

    /**
     * Constructs a new {@code IdVertexQuery}.
     *
     * @param base  the base query
     * @param graph the graph that owns this element
     */
    public IdVertexQuery(VertexQuery base, G graph) {
        super(base, graph);
    }

    @Nonnull
    @Override
    public Q direction(Direction direction) {
        base.direction(direction);
        return me();
    }

    @Nonnull
    @Override
    public Q labels(String... labels) {
        base.labels(labels);
        return me();
    }

    @Nonnegative
    @Override
    public long count() {
        return base.count();
    }

    @Nonnull
    @Override
    public Object vertexIds() {
        return base.vertexIds();
    }
}
