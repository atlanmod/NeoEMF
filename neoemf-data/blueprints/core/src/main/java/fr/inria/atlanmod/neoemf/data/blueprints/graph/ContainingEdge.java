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

import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper.IdEdge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 * An {@link Edge} representing the relation between an element and its container.
 */
@ParametersAreNonnullByDefault
public class ContainingEdge extends IdEdge<ModelGraph> {

    /**
     * The label of this edge.
     */
    protected static final String LABEL = "_c";

    /**
     * The property key used to define the name of the opposite containing feature in container edges.
     */
    protected static final String PROPERTY_FID = "_cn";

    /**
     * Constructs a new {@code ContainingEdge}.
     *
     * @param base  the base edge
     * @param graph the graph that owns this edge
     */
    private ContainingEdge(Edge base, ModelGraph graph) {
        super(base, graph);
    }

    /**
     * Creates a new instance of {@code ContainingEdge} on the {@code baseEdge}.
     *
     * @param baseEdge the base edge
     * @param graph    the graph that owns the edge
     *
     * @return the containing edge
     */
    @Nonnull
    public static ContainingEdge from(Edge baseEdge, ModelGraph graph) {
        return new ContainingEdge(baseEdge, graph);
    }

    /**
     * Creates a new {@code ContainingEdge} between the {@code containment} vertex and its container.
     *
     * @param graph       the graph that owns the edge
     * @param containment the containment vertex
     * @param bean        the simple representation of the container
     *
     * @return the containing edge that has been added in the graph
     */
    @Nonnull
    protected static ContainingEdge create(ModelGraph graph, ElementVertex containment, SingleFeatureBean bean) {
        ElementVertex containerVertex = graph.getOrCreateVertex(bean.owner());

        return graph.addEdge(null, containment, containerVertex, ContainingEdge.LABEL, ContainingEdge::new).setFeatureId(bean.id());
    }

    /**
     * Returns the container vertex of this relation.
     *
     * @return the container vertex
     */
    @Nonnull
    public ElementVertex getContainerVertex() {
        return getVertex(Direction.IN, ElementVertex::from);
    }

    /**
     * Returns the containment vertex of this relation.
     *
     * @return the containment vertex
     */
    @Nonnull
    public ElementVertex getContainmentVertex() {
        return getVertex(Direction.OUT, ElementVertex::from);
    }

    /**
     * Returns the identifier of the containing feature.
     *
     * @return the identifier of the containing feature
     */
    @Nonnull
    public Integer getFeatureId() {
        return check(getProperty(PROPERTY_FID));
    }

    /**
     * Defines the identifier of the containing feature.
     *
     * @param featureId the identifier of the containing feature
     *
     * @return this edge (for chaining)
     */
    @Nonnull
    private ContainingEdge setFeatureId(Integer featureId) {
        setProperty(PROPERTY_FID, featureId);
        return this;
    }

    /**
     * Returns the simple representation of this containing edge.
     *
     * @return the simple representation of this containing edge
     */
    @Nonnull
    public SingleFeatureBean toBean() {
        return SingleFeatureBean.of(getContainerVertex().getElementId(), getFeatureId());
    }

    /**
     * Ensures that this edge is a containing relation by checking the specified value.
     *
     * @param value the property value
     *
     * @return the value
     */
    @Nonnull
    private <T> T check(@Nullable T value) {
        if (isNull(value)) {
            throw new IllegalStateException("This vertex is not a meta-class representation");
        }
        return value;
    }
}
