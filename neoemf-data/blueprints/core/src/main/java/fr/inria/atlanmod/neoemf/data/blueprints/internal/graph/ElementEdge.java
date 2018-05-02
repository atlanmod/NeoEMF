/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.internal.graph;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;

import fr.inria.atlanmod.commons.LazyInt;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.wrapper.IdEdge;

import java.util.function.IntUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link com.tinkerpop.blueprints.Edge} representing the relation between an element and another.
 */
@ParametersAreNonnullByDefault
public class ElementEdge extends IdEdge<ModelGraph> {

    /**
     * The property key used to define the position of an edge.
     */
    protected static final String PROPERTY_POSITION = "_p";

    /**
     * The position of this edge.
     */
    @Nonnull
    private final LazyInt lazyPosition = LazyInt.with(() -> getProperty(PROPERTY_POSITION));

    /**
     * Constructs a new {@code ElementEdge}.
     *
     * @param base  the base edge
     * @param graph the graph that owns this edge
     */
    protected ElementEdge(Edge base, ModelGraph graph) {
        super(base, graph);
    }

    /**
     * Creates a new instance of {@code ElementEdge} on the {@code baseEdge}.
     *
     * @param baseEdge the base edge
     * @param graph    the graph that owns the edge
     *
     * @return the element edge
     */
    @Nonnull
    public static ElementEdge from(Edge baseEdge, ModelGraph graph) {
        return new ElementEdge(baseEdge, graph);
    }

    /**
     * Creates a new {@code ElementEdge} between the {@code referencing} vertex and its reference.
     *
     * @param graph       the graph that owns the edge
     * @param referencing the referencing vertex
     * @param referenced  the identifier of the referenced vertex
     * @param label       the label to identify the relation
     *
     * @return the element edge that has been added in the graph
     */
    @Nonnull
    protected static ElementEdge create(ModelGraph graph, ElementVertex referencing, Id referenced, String label) {
        ElementVertex newReferenceVertex = graph.getOrCreateVertex(referenced);
        return graph.addEdge(null, referencing, newReferenceVertex, label, ElementEdge::new);
    }

    /**
     * Creates a new {@code ElementEdge} between the {@code referencing} vertex and its positionned reference.
     *
     * @param graph       the graph that owns the edge
     * @param referencing the referencing vertex
     * @param referenced  the identifier of the referenced vertex
     * @param label       the label to identify the relation
     * @param position    the index of the relation for the referencing vertex (for multi-valud references)
     *
     * @return the element edge that has been added in the graph
     */
    @Nonnull
    protected static ElementEdge create(ModelGraph graph, ElementVertex referencing, Id referenced, String label, @Nonnegative int position) {
        return create(graph, referencing, referenced, label).setPosition(position);
    }

    /**
     * Returns the referenced vertex of this relation.
     *
     * @return the referenced vertex
     */
    @Nonnull
    public ElementVertex getReferencedVertex() {
        return getVertex(Direction.IN, ElementVertex::from);
    }

    /**
     * Returns the referencing vertex of this relation.
     *
     * @return the referencing vertex
     */
    @Nonnull
    public ElementVertex getReferencingVertex() {
        return getVertex(Direction.OUT, ElementVertex::from);
    }

    /**
     * Returns the position of this relation for the referencing vertex.
     *
     * @return the position
     */
    @Nonnegative
    public int getPosition() {
        return lazyPosition.getAsInt();
    }

    /**
     * Defines the {@code position} of this relation for the referencing vertex.
     *
     * @param position the new position
     *
     * @return this edge (for chaining)
     */
    @Nonnegative
    private ElementEdge setPosition(@Nonnegative int position) {
        setProperty(PROPERTY_POSITION, position);
        lazyPosition.update(position);
        return this;
    }

    /**
     * Updates the position of this relation for the referencing vertex with the specified function.
     *
     * @param updateFunc the function to update the current position
     */
    public void updatePosition(IntUnaryOperator updateFunc) {
        setPosition(updateFunc.applyAsInt(getPosition()));
    }
}
