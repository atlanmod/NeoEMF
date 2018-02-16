/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;

import fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper.IdEdge;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link com.tinkerpop.blueprints.Edge} representing the relation between an element and its meta-class.
 */
@ParametersAreNonnullByDefault
public class InstanceOfEdge extends IdEdge<ModelGraph> {

    /**
     * The label of this edge.
     */
    protected static final String LABEL = "_i";

    /**
     * Constructs a new {@code InstanceOfEdge}.
     *
     * @param base  the base edge
     * @param graph the graph that owns this edge
     */
    private InstanceOfEdge(Edge base, ModelGraph graph) {
        super(base, graph);
    }

    /**
     * Creates a new instance of {@code InstanceOfEdge} on the {@code baseEdge}.
     *
     * @param baseEdge the base edge
     * @param graph    the graph that owns the edge
     *
     * @return the instance-of edge
     */
    @Nonnull
    public static InstanceOfEdge from(Edge baseEdge, ModelGraph graph) {
        return new InstanceOfEdge(baseEdge, graph);
    }

    /**
     * Creates a new {@code InstanceOfEdge} between the {@code instance} vertex and its {@code metaClass}.
     *
     * @param graph     the graph that owns the edge
     * @param instance  the instance vertex
     * @param metaClass the meta-class vertex
     *
     * @return the instance-of edge that has been added in the graph
     */
    @Nonnull
    protected static InstanceOfEdge create(ModelGraph graph, ElementVertex instance, ClassVertex metaClass) {
        return graph.addEdge(null, instance, metaClass, InstanceOfEdge.LABEL, InstanceOfEdge::new);
    }

    /**
     * Returns the meta-class vertex of this relation.
     *
     * @return the meta-class vertex
     */
    @Nonnull
    public ClassVertex getClassVertex() {
        return getVertex(Direction.IN, ClassVertex::from);
    }

    /**
     * Returns the instance vertex of this relation.
     *
     * @return the instance vertex
     */
    @Nonnull
    public ElementVertex getInstanceVertex() {
        return getVertex(Direction.OUT, ElementVertex::from);
    }
}
