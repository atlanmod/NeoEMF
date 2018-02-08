/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.wrappers.id.IdEdge;
import com.tinkerpop.blueprints.util.wrappers.id.IdGraph;

import fr.inria.atlanmod.commons.collect.MoreIterables;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link IdEdge} that automatically removes {@link Vertex} that are no longer referenced.
 */
@ParametersAreNonnullByDefault
class SmartIdEdge extends IdEdge {

    /**
     * Constructs a new {@code SmartIdEdge} on the specified {@code edge}.
     *
     * @param edge the base edge
     */
    public SmartIdEdge(Edge edge, IdGraph<?> graph) {
        super(edge, graph);
    }

    /**
     * {@inheritDoc}
     * <p>
     * If the {@link Edge} references a {@link Vertex} with no more incoming {@link Edge}, the referenced {@link
     * Vertex} is removed as well.
     */
    @Override
    public void remove() {
        // Retrieve the incoming vertex
        Vertex referencedVertex = getVertex(Direction.IN);

        // Remove this edge
        super.remove();

        // If the Vertex has no more incoming edges remove it
        if (MoreIterables.isEmpty(referencedVertex.getEdges(Direction.IN))) {
            referencedVertex.remove();
        }
    }
}
