/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.util.wrappers.id.IdGraph;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link IdGraph} that caches recently processed {@link com.tinkerpop.blueprints.Vertex} and
 * {@link com.tinkerpop.blueprints.Edge} instances.
 */
@ParametersAreNonnullByDefault
// TODO Implement caches + edge auto-removal
class CachedIdGraph<T extends KeyIndexableGraph> extends IdGraph<T> {

    /**
     * Constructs a new {@code CachedIdGraph} on the specified {@code baseGraph}.
     *
     * @param baseGraph the base graph
     */
    public CachedIdGraph(T baseGraph) {
        super(baseGraph, true, false);

        // Disable the uniqueness check to avoid full browsing of the index to find the duplicate
        enforceUniqueIds(false);
    }
}
