/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph;

import com.tinkerpop.blueprints.VertexQuery;

import fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper.IdVertexQuery;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link IdVertexQuery} for querying a {@link ModelGraph} instance and its components.
 */
@ParametersAreNonnullByDefault
class ElementVertexQuery extends IdVertexQuery<ElementVertexQuery, ModelGraph> {

    /**
     * Constructs a new {@code IdVertexQuery}.
     *
     * @param base  the base query
     * @param graph the graph that owns this element
     */
    protected ElementVertexQuery(VertexQuery base, ModelGraph graph) {
        super(base, graph);
    }

    /**
     * Filter out elements that have a different position from the provided value.
     *
     * @param position the expected position
     *
     * @return this query
     */
    @Nonnull
    public ElementVertexQuery hasPosition(int position) {
        return has(ElementEdge.PROPERTY_POSITION, position).limit(1);
    }

    /**
     * Filter out elements that have a position that is not within the provided interval.
     *
     * @param start the start position
     * @param end   the end position
     *
     * @return this query
     */
    @Nonnull
    public ElementVertexQuery hasPositionBetween(int start, int end) {
        return interval(ElementEdge.PROPERTY_POSITION, start, end).limit(end - start);
    }
}
