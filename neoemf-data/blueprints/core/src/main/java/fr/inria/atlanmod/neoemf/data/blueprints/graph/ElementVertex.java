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
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.commons.Lazy;
import fr.inria.atlanmod.commons.LazyReference;
import fr.inria.atlanmod.commons.collect.SizedIterator;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper.IdVertex;

import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link com.tinkerpop.blueprints.Vertex} representing an element that can have a meta-class and a container.
 */
@ParametersAreNonnullByDefault
public class ElementVertex extends IdVertex<ModelGraph> {

    /**
     * The delimiter used to separate the property name and its value in a composed property.
     *
     * @see #format(FeatureBean, Object)
     */
    protected static final String DELIMITER = "_";

    /**
     * The property key used to define the number of edges with a specific label.
     */
    protected static final String PROPERTY_SIZE = "s";

    /**
     * The unique identifier of this vertex.
     */
    @Nonnull
    private final Lazy<Object> lazyId = Lazy.with(super::getId);

    /**
     * The meta-class vertex of this element.
     */
    @Nonnull
    private final LazyReference<ClassVertex> lazyClassVertex = LazyReference.soft(() -> getVertex(Direction.OUT, InstanceOfEdge.LABEL, ClassVertex::from).orElse(null));

    /**
     * The containing edge of this element.
     */
    @Nonnull
    private final LazyReference<ContainingEdge> lazyContainingEdge = LazyReference.soft(() -> getEdge(Direction.OUT, ContainingEdge.LABEL, ContainingEdge::from).orElse(null));

    /**
     * Constructs a new {@code ElementVertex}.
     *
     * @param baseVertex the base vertex
     * @param graph      the graph that owns this vertex
     */
    private ElementVertex(Vertex baseVertex, ModelGraph graph) {
        super(baseVertex, graph);
    }

    /**
     * Retrieves the {@code ElementVertex} that wraps the {@code baseVertex}, or creates a new instance on it.
     *
     * @param baseVertex the base vertex
     * @param graph      the graph that owns this vertex
     *
     * @return the element vertex
     */
    @Nonnull
    public static ElementVertex from(Vertex baseVertex, ModelGraph graph) {
        return graph.getOrWrapVertex(baseVertex);
    }

    /**
     * Creates a new instance of {@code ElementVertex} on the {@code baseVertex}.
     * <p>
     * <b>WARNING:</b> This method is intended for use only by the {@link ModelGraph} class.
     *
     * @param baseVertex the base vertex
     * @param graph      the graph that owns this vertex
     *
     * @return the element vertex
     */
    @Nonnull
    static ElementVertex fromNoCache(Vertex baseVertex, ModelGraph graph) {
        return new ElementVertex(baseVertex, graph);
    }

    @Nonnull
    @Override
    public Object getId() {
        return lazyId.get();
    }

    /**
     * Returns the converted identifier of this element vertex.
     *
     * @return the converted identifier
     */
    @Nonnull
    public Id getElementId() {
        return graph.getIdConverter().revert(getId());
    }

    @Nonnull
    @Override
    public ElementVertexQuery query() {
        return new ElementVertexQuery(base.query(), graph);
    }

    // region Meta-class

    /**
     * Returns the meta-class vertex.
     *
     * @return an {@link Optional} containing the meta-class vertex
     */
    @Nonnull
    public Optional<ClassVertex> getClassVertex() {
        return Optional.ofNullable(lazyClassVertex.get());
    }

    /**
     * Defines the meta-class vertex from the specified {@code bean}. If the meta-class is already defined, then this
     * method does nothing.
     *
     * @param bean the simple representation of the meta-class vertex
     *
     * @return {@code true} if the meta-class has been defined for this vertex, {@code false} if the meta-class was
     * already defined
     */
    public boolean setClassVertex(ClassBean bean) {
        if (getClassVertex().isPresent()) {
            // The meta-class is already defined
            return false;
        }

        // Retrieve or create the meta-class vertex
        ClassVertex classVertex = ClassVertex.getOrCreate(graph, bean);
        lazyClassVertex.update(classVertex);

        // Link this vertex with its meta-class
        InstanceOfEdge.create(graph, this, classVertex);

        return true;
    }

    // endregion

    // region Container

    /**
     * Returns the containing edge.
     *
     * @return an {@link Optional} containing the containing edge
     */
    @Nonnull
    public Optional<ContainingEdge> getContainingEdge() {
        return Optional.ofNullable(lazyContainingEdge.get());
    }

    /**
     * Defines the containing edge from the specified {@code bean}.
     *
     * @param bean the simple representation of the containing edge
     */
    public void setContainingEdge(SingleFeatureBean bean) {
        // Remove the existing container, if defined
        removeContainingEdge();

        // Link this vertex with its container
        ContainingEdge edge = ContainingEdge.create(graph, this, bean);
        lazyContainingEdge.update(edge);
    }

    /**
     * Removes the containing edge.
     */
    public void removeContainingEdge() {
        getContainingEdge().ifPresent(ContainingEdge::remove);
        lazyContainingEdge.update(null);
    }

    // endregion

    // region Size

    /**
     * Returns the size of the {@code feature}.
     *
     * @param feature the feature
     *
     * @return the size
     */
    @Nonnegative
    public int getSize(FeatureBean feature) {
        final String property = format(feature, PROPERTY_SIZE);

        final Integer size = getProperty(property);
        return Optional.ofNullable(size).orElse(0);
    }

    /**
     * Defines the {@code size} of the {@code feature}.
     *
     * @param feature the feature
     * @param size    the new size
     */
    public void setSize(FeatureBean feature, @Nonnegative int size) {
        final String property = format(feature, PROPERTY_SIZE);

        if (size > 0) {
            setProperty(property, size);
        }
        else {
            removeProperty(property);
        }
    }

    // endregion

    // region Values

    /**
     * Returns the value of the {@code feature}.
     *
     * @param feature the feature
     * @param <V>     the type of the value
     *
     * @return an {@link Optional} containing the value
     */
    @Nonnull
    public <V> Optional<V> getValue(FeatureBean feature) {
        final String property = format(feature);

        return Optional.ofNullable(getProperty(property));
    }

    /**
     * Replaces the current value of the {@code feature} by the specified {@code value}.
     *
     * @param feature the feature
     * @param value   the new value
     * @param <V>     the type of the value
     *
     * @return an {@link Optional} containing the replaced value
     */
    @Nonnull
    public <V> Optional<V> setValue(FeatureBean feature, V value) {
        final String property = format(feature);

        Optional<V> oldValue = Optional.ofNullable(getProperty(property));
        setProperty(property, value);

        return oldValue;
    }

    /**
     * Removes the current value of the {@code feature}.
     *
     * @param feature the feature
     */
    public void removeValue(FeatureBean feature) {
        final String property = format(feature);

        removeProperty(property);
    }

    /**
     * Returns the value of the {@code feature} at the defined {@code position}.
     *
     * @param feature  the feature
     * @param position the position of the value
     * @param <V>      the type of the value
     *
     * @return an {@link Optional} containing the value
     */
    @Nonnull
    public <V> Optional<V> getValue(FeatureBean feature, @Nonnegative int position) {
        final String property = format(feature, position);

        return Optional.ofNullable(getProperty(property));
    }

    /**
     * Returns all values of the {@code feature}.
     *
     * @param feature the feature
     * @param <V>     the type of the values
     *
     * @return an ordered iterable
     */
    @Nonnull
    public <V> Iterable<V> getValues(FeatureBean feature) {
        return () -> new SizedIterator<>(getSize(feature), i -> this.<V>getValue(feature, i).orElse(null));
    }

    /**
     * Defines the current value of the {@code feature} at the defined {@code position}.
     *
     * @param feature  the feature
     * @param position the position of the value
     * @param value    the new value
     * @param <V>      the type of the value
     */
    public <V> void setValue(FeatureBean feature, @Nonnegative int position, V value) {
        final String property = format(feature, position);

        setProperty(property, value);
    }

    /**
     * Replaces the current value of the {@code feature} at the defined {@code position}. If the current value does not
     * exist, then the new {@code value} is not defined and this method does nothing.
     *
     * @param feature  the feature
     * @param position the position of the value
     * @param value    the new value
     * @param <V>      the type of the value
     *
     * @return an {@link Optional} containing the replaced value
     */
    @Nonnull
    public <V> Optional<V> replaceValue(FeatureBean feature, @Nonnegative int position, V value) {
        final String property = format(feature, position);

        Optional<V> oldValue = Optional.ofNullable(getProperty(property));

        if (oldValue.isPresent()) {
            setProperty(property, value);
        }

        return oldValue;
    }

    /**
     * Moves the current value of the specified {@code feature} from the {@code sourceIndex} to the {@code
     * targetIndex}.
     *
     * @param feature     the feature
     * @param sourceIndex the current position of the value to move
     * @param targetIndex the new position of the value
     */
    public void moveValue(FeatureBean feature, @Nonnegative int sourceIndex, @Nonnegative int targetIndex) {
        getValue(feature, sourceIndex).ifPresent(v -> setValue(feature, targetIndex, v));
    }

    /**
     * Removes the current value of the {@code feature} at the defined {@code position}.
     *
     * @param feature  the feature
     * @param position the position of the value
     */
    public void removeValue(FeatureBean feature, @Nonnegative int position) {
        final String property = format(feature, position);

        removeProperty(property);
    }

    // endregion

    // region References

    /**
     * TODO
     *
     * @param feature
     *
     * @return
     */
    @Nonnull
    public Optional<ElementVertex> getReference(FeatureBean feature) {
        final String label = format(feature);

        return getVertex(Direction.OUT, label, ElementVertex::from);
    }

    /**
     * TODO
     *
     * @param feature
     * @param reference
     *
     * @return
     */
    @Nonnull
    public Optional<ElementVertex> setReference(FeatureBean feature, Id reference) {
        final String label = format(feature);

        Optional<ElementVertex> oldReference = getEdge(Direction.OUT, label, ElementEdge::from).map(e -> {
            ElementVertex v = e.getReferencedVertex();
            e.remove();
            return v;
        });

        ElementEdge.create(graph, this, reference, label);

        return oldReference;
    }

    /**
     * TODO
     *
     * @param feature
     */
    public void removeReferences(FeatureBean feature) {
        final String label = format(feature);

        getEdges(Direction.OUT, label).forEach(Edge::remove);
    }

    /**
     * TODO
     *
     * @param feature
     * @param position
     *
     * @return
     */
    @Nonnull
    public Optional<ElementVertex> getReference(FeatureBean feature, @Nonnegative int position) {
        final String label = format(feature);

        return query().labels(label).direction(Direction.OUT).hasPosition(position).vertex(ElementVertex::from);
    }

    /**
     * TODO
     *
     * @param feature
     *
     * @return
     */
    @Nonnull
    public Iterable<ElementEdge> getReferenceEdges(FeatureBean feature) {
        final String label = format(feature);

        return getEdges(Direction.OUT, toArray(label), ElementEdge::from);
    }

    /**
     * TODO
     *
     * @param feature
     * @param startPosition
     * @param endPosition
     *
     * @return
     */
    @Nonnull
    public Iterable<ElementEdge> getReferenceEdges(FeatureBean feature, int startPosition, int endPosition) {
        final String label = format(feature);

        return query().labels(label).direction(Direction.OUT).hasPositionBetween(startPosition, endPosition).edges(ElementEdge::from);
    }

    /**
     * TODO
     *
     * @param feature
     * @param position
     * @param reference
     */
    public void setReference(FeatureBean feature, @Nonnegative int position, Id reference) {
        final String label = format(feature);

        ElementEdge.create(graph, this, reference, label, position);
    }

    /**
     * TODO
     *
     * @param feature
     * @param position
     * @param reference
     *
     * @return
     */
    @Nonnull
    public Optional<ElementVertex> replaceReference(FeatureBean feature, @Nonnegative int position, Id reference) {
        final String label = format(feature);

        Optional<ElementVertex> oldReference = query().labels(label).direction(Direction.OUT).hasPosition(position).edge(ElementEdge::from).map(e -> {
            ElementVertex v = e.getReferencedVertex();
            e.remove();
            return v;
        });

        if (oldReference.isPresent()) {
            ElementEdge.create(graph, this, reference, label, position);
        }

        return oldReference;
    }

    // endregion

    /**
     * Formats a label.
     *
     * @param feature the feature associated with the label
     *
     * @return the formatted label
     */
    @Nonnull
    private String format(FeatureBean feature) {
        StringBuilder sb = new StringBuilder();

        if (graph.requiresUniqueLabels()) {
            final String className = getClassVertex().map(ClassVertex::getName).orElse(DELIMITER);
            sb.append(className).append(DELIMITER);
        }
        sb.append(feature.id());

        return sb.toString();
    }

    /**
     * Formats a property as {@code label_suffix}.
     *
     * @param feature the feature associated with the property
     * @param suffix  the suffix of the property
     *
     * @return the formatted property
     */
    @Nonnull
    private String format(FeatureBean feature, Object suffix) {
        return format(feature) + DELIMITER + suffix;
    }

    /**
     * Returns an array that only contains the specified {@code value}.
     *
     * @param value the value
     *
     * @return an array
     */
    @Nonnull
    private String[] toArray(String value) {
        return new String[]{value};
    }
}
