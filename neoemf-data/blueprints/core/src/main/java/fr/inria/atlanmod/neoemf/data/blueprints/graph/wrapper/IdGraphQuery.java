/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper;

import com.tinkerpop.blueprints.GraphQuery;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link GraphQuery} able to map the result of each method call to a dedicated implementation.
 * <p>
 * Re-implemented from {@link com.tinkerpop.blueprints.util.wrappers.WrappedGraphQuery}.
 *
 * @param <G> the type of the graph where to execute the query
 */
@ParametersAreNonnullByDefault
public class IdGraphQuery<G extends IdGraph<G>> extends AbstractIdQuery<IdGraphQuery<G>, GraphQuery, G> implements GraphQuery {

    /**
     * Constructs a new {@code IdGraphQuery}.
     *
     * @param base  the base query
     * @param graph the graph that owns this element
     */
    public IdGraphQuery(GraphQuery base, G graph) {
        super(base, graph);
    }
}
