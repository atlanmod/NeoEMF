/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.wrappers.id.IdGraph;

import java.util.Optional;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link IdGraph} that automatically removes unused {@link Vertex}.
 */
@ParametersAreNonnullByDefault
class SmartIdGraph extends IdGraph<KeyIndexableGraph> {

    /**
     * Constructs a new {@code SmartIdGraph} on the specified {@code baseGraph}.
     *
     * @param baseGraph the base graph
     */
    public SmartIdGraph(KeyIndexableGraph baseGraph) {
        super(baseGraph, true, false);
        enforceUniqueIds(false);
    }

    @Override
    public Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label) {
        return mapEdge(super.addEdge(id, outVertex, inVertex, label));
    }

    @Override
    public Edge getEdge(Object id) {
        return mapEdge(super.getEdge(id));
    }

    /**
     * Creates a new {@link SmartIdEdge} from another {@link Edge}.
     *
     * @param baseEdge the base edge
     *
     * @return an edge
     */
    private Edge mapEdge(@Nullable Edge baseEdge) {
        return Optional.ofNullable(baseEdge).map(e -> new SmartIdEdge(e, this)).orElse(null);
    }

}
