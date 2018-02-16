/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph;

import com.tinkerpop.blueprints.CloseableIterable;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.wrappers.WrapperGraph;

import fr.inria.atlanmod.commons.Copiable;
import fr.inria.atlanmod.commons.cache.Cache;
import fr.inria.atlanmod.commons.cache.CacheBuilder;
import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper.IdGraph;
import fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper.IdVertex;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link com.tinkerpop.blueprints.Graph} representing a meta-model.
 */
@ParametersAreNonnullByDefault
public class ModelGraph extends IdGraph<ModelGraph> implements Copiable<ModelGraph> {

    /**
     * An index that holds all meta-class vertices, identified by their name.
     * <p>
     * A name can reference several meta-class vertices that have a different URI.
     */
    @Nonnull
    private final Index<ClassVertex> classVerticesByName;

    /**
     * In-memory cache that holds recently loaded {@link ElementVertex}s.
     * <p>
     * This cache exists only because of the low performance of the Lucene indices.
     */
    @Nonnull
    private final Cache<Object, ElementVertex> elementCache = CacheBuilder.builder()
            .softValues()
            .build();

    /**
     * The converter to use a primitive representation instead of a complete {@link Id}.
     */
    @Nonnull
    private final Converter<Id, Object> idConverter;

    /**
     * {@code true} if the base graph requires unique labels and properties.
     * <p>
     * Some {@link Graph} implementations, such as {@link TinkerGraph}, associates each label and property with a type,
     * even if it appears in a different Vertex/Edge. A simple {@link Integer} cause some troubles because they appear
     * in different {@link Element} but don't represent the same thing.
     */
    private final boolean requiresUniqueLabels;

    /**
     * Constructs a new {@code ModelGraph}.
     *
     * @param baseGraph the base graph
     */
    public ModelGraph(KeyIndexableGraph baseGraph) {
        super(baseGraph, true, false);

        classVerticesByName = getOrCreateIndex("instances-all", Vertex.class, ClassVertex::from);

        Graph originGraph = getOrigin(baseGraph);
        if (TinkerGraph.class.isInstance(originGraph)) {
            // Tinkergraph is stored as XML: prefer using String-based identifiers
            idConverter = Converter.compose(IdConverters.withHexString(), Converter.from(Function.identity(), String.class::cast));
            requiresUniqueLabels = true;
        }
        else {
            idConverter = Converter.compose(IdConverters.withLong(), Converter.from(Function.identity(), Long.class::cast));
            requiresUniqueLabels = false;
        }
    }

    /**
     * Retrieves the base graph of the {@code graph}.
     *
     * @param graph the graph from which to retrieve the base graph
     *
     * @return the base graph of the {@code graph}, or {@code graph} is it is not a wrapper.
     *
     * @see com.tinkerpop.blueprints.Features#isWrapper
     * @see WrapperGraph
     */
    @Nonnull
    private static Graph getOrigin(Graph graph) {
        return graph.getFeatures().isWrapper
                ? getOrigin(WrapperGraph.class.cast(graph).getBaseGraph())
                : graph;
    }

    /**
     * Returns the converter to use a primitive representation instead of a complete {@link
     * fr.inria.atlanmod.neoemf.core.Id}.
     *
     * @return the id converter
     */
    @Nonnull
    public Converter<Id, Object> getIdConverter() {
        return idConverter;
    }

    /**
     * Return {@code true} if the base graph requires unique labels and properties.
     *
     * @return {@code true} if the base graph requires unique labels and properties
     */
    public boolean requiresUniqueLabels() {
        return requiresUniqueLabels;
    }

    // region Element

    /**
     * Returns the vertex referenced by the provided {@code id}.
     *
     * @param id the identifier of the vertex to retrieve from the graph
     *
     * @return an {@link Optional} containing the vertex referenced by the provided identifier, or {@link
     * Optional#empty()} when no such vertex exists
     *
     * @see #getVertex(Object)
     * @see Converter#convert(Object)
     */
    @Nonnull
    public Optional<ElementVertex> getVertex(Id id) {
        final Object vid = idConverter.convert(id);
        return Optional.ofNullable(elementCache.get(vid, i -> getElementVertex(i, () -> null)));
    }

    /**
     * Returns the vertex referenced by the provided {@code id}. If no such vertex exists, it will be created and added
     * to the graph.
     *
     * @param id the identifier of the vertex to retrieve, or create, from the graph
     *
     * @return the vertex
     *
     * @see #getVertex(Object)
     * @see #addVertex(Object)
     * @see Converter#convert(Object)
     */
    @Nonnull
    public ElementVertex getOrCreateVertex(Id id) {
        final Object vid = idConverter.convert(id);
        return elementCache.get(vid, i -> getElementVertex(i, () -> addElementVertex(i)));
    }

    @Override
    public void removeVertex(Vertex vertex) {
        elementCache.invalidate(vertex.getId());

        super.removeVertex(vertex);
    }

    /**
     * Retrieves the {@code ElementVertex} that wraps the specified {@code vertex}, or creates it.
     *
     * @param baseVertex the base vertex
     *
     * @return an {@code ElementVertex} that wraps the {@code baseVertex}
     */
    @Nonnull
    ElementVertex getOrWrapVertex(Vertex baseVertex) {
        // Don't call directly `baseVertex#getId()`: the native ID may be different from the ID we use.
        final Object vid = baseVertex.getProperty(ID);
        return elementCache.get(vid, id -> ElementVertex.fromNoCache(baseVertex, this));
    }

    /**
     * Retrieves a vertex from the base graph, or executes the function if it does not exists.
     *
     * @param id     the identifier of the vertex to retrieve
     * @param orElse the function to executes if the vertex if not found in the graph
     *
     * @return an {@code ElementVertex} that wraps the vertex; nullable
     */
    @Nullable
    private ElementVertex getElementVertex(Object id, Supplier<ElementVertex> orElse) {
        return getVertex(id, ElementVertex::fromNoCache).orElseGet(orElse); // Don't cache the created vertex
    }

    /**
     * Adds a new vertex in the base graph.
     *
     * @param id the identifier of the vertex to create
     *
     * @return an {@code ElementVertex} that wraps the created vertex
     */
    @Nonnull
    private ElementVertex addElementVertex(Object id) {
        return addVertex(id, ElementVertex::fromNoCache); // Don't cache the created vertex
    }

    // endregion

    // region Meta-class

    /**
     * Returns the meta-class vertex associated to the provided {@code bean}.
     *
     * @param bean the simple representation of the meta-class
     *
     * @return an {@link Optional} containing the meta-class vertex
     */
    @Nonnull
    public Optional<ClassVertex> getClassVertex(ClassBean bean) {
        // Filter vertices by their name, then by their URI
        return MoreIterables.stream(classVerticesByName.get(ClassVertex.PROPERTY_NAME, bean.name()))
                .filter(v -> bean.uri().equals(v.getUri()))
                .findFirst();
    }

    /**
     * Returns all meta-class vertices associated to the given {@code beans}.
     *
     * @param beans a set of simple representation of the meta-classes
     *
     * @return an iterable of meta-class vertices
     */
    @Nonnull
    public Iterable<ClassVertex> getClassVertices(Set<ClassBean> beans) {
        return beans.stream()
                .map(this::getClassVertex)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    /**
     * Updates the meta-classes index with the specified {@code classVertex}. If the index already contains the vertex
     * then this method does nothing.
     *
     * @param classVertex the meta-class vertex to index
     */
    protected void updateIndex(ClassVertex classVertex) {
        final String name = classVertex.getName();

        // Filter vertices by their name, then by their URI
        try (CloseableIterable<ClassVertex> iterable = classVerticesByName.get(ClassVertex.PROPERTY_NAME, name)) {
            if (MoreIterables.stream(iterable).noneMatch(v -> classVertex.getUri().equals(v.getUri()))) {
                classVerticesByName.put(ClassVertex.PROPERTY_NAME, name, classVertex);
            }
        }
    }

    // endregion

    // region

    @Override
    public void copyTo(ModelGraph target) {
        final List<String> classPropertyKeys = Arrays.asList(ClassVertex.PROPERTY_NAME, ClassVertex.PROPERTY_URI);

        // Copy all vertices
        for (Vertex fromVertex : getVertices()) {
            Vertex toVertex;
            final Set<String> propertyKeys = fromVertex.getPropertyKeys();

            // If this vertex is a meta-class vertex: rebuild the meta-classes index
            if (propertyKeys.containsAll(classPropertyKeys)) {
                final ClassBean bean = ClassVertex.from(((IdVertex<?>) fromVertex).getBaseElement(), this).toBean();
                toVertex = ClassVertex.create(target, bean);
                propertyKeys.removeAll(classPropertyKeys);
            }
            else {
                toVertex = target.addVertex(fromVertex.getId());
            }

            // Copy all remaining properties
            copyProperties(fromVertex, toVertex, propertyKeys);
        }

        // Copy all edges
        for (Edge fromEdge : getEdges()) {
            Vertex outVertex = target.getVertex(fromEdge.getVertex(Direction.OUT).getId());
            Vertex inVertex = target.getVertex(fromEdge.getVertex(Direction.IN).getId());

            Edge toEdge = target.addEdge(fromEdge.getId(), outVertex, inVertex, fromEdge.getLabel());

            // Copy all properties
            copyProperties(fromEdge, toEdge, fromEdge.getPropertyKeys());
        }
    }

    /**
     * Copies all {@code propertyKeys} from the {@code source} to the {@code target}.
     *
     * @param source       the source element
     * @param target       the target element
     * @param propertyKeys the property keys to copy
     */
    private void copyProperties(Element source, Element target, Set<String> propertyKeys) {
        propertyKeys.forEach(k -> target.setProperty(k, source.getProperty(k)));
    }

    // endregion
}
