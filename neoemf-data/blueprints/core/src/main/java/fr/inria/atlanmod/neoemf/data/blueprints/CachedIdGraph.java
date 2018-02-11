/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Parameter;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.wrappers.id.IdGraph;

import fr.inria.atlanmod.commons.cache.Cache;
import fr.inria.atlanmod.commons.cache.CacheBuilder;
import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdProvider;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link IdGraph} that caches recently processed {@link com.tinkerpop.blueprints.Vertex} and
 * {@link com.tinkerpop.blueprints.Edge} instances.
 */
@ParametersAreNonnullByDefault
// TODO Edge auto-removal
class CachedIdGraph extends IdGraph<KeyIndexableGraph> {

    /**
     * In-memory cache that holds recently loaded {@link Vertex}s.
     * <p>
     * This cache exists only because of the low performance of the Lucene indices.
     */
    @Nonnull
    private final Cache<Object, Vertex> vertexCache = CacheBuilder.builder()
            .softValues()
            .build();

    /**
     * The {@link Converter} to use a primitive representation instead of a complete {@link Id}.
     * <p>
     * This converter is specific to Blueprints which returns each identifier as an {@link Object}.
     */
    @Nonnull
    private final Converter<Id, Object> idConverter;

    /**
     * Constructs a new {@code CachedIdGraph} on the specified {@code baseGraph}.
     *
     * @param baseGraph the base graph
     */
    public CachedIdGraph(KeyIndexableGraph baseGraph, Converter<Id, Object> idConverter) {
        super(baseGraph, true, false);

        // Disable the uniqueness check to avoid full browsing of the index to find the duplicate
        enforceUniqueIds(false);

        this.idConverter = idConverter;

        // Create and define ID factories
        final IdProvider idProvider = Id.getProvider();
        final IdFactory idFactory = () -> idConverter.convert(idProvider.generate());
        setVertexIdFactory(idFactory);
    }

    @Nonnull
    @Override
    public Vertex addVertex(Object id) {
        Vertex vertex = super.addVertex(id);
        vertexCache.put(vertex.getId(), vertex);
        return vertex;
    }

    @Nullable
    @Override
    public Vertex getVertex(Object id) {
        return vertexCache.get(id, super::getVertex);
    }

    @Override
    public void removeVertex(Vertex vertex) {
        vertexCache.invalidate(vertex.getId());
        super.removeVertex(vertex);
    }

    /**
     * Returns the vertex referenced by the provided {@code id}.
     *
     * @param id the identifier of the vertex to retrieve from the graph
     *
     * @return an {@link Optional} containing the vertex referenced by the provided identifier, or
     * {@link Optional#empty()} when no such vertex exists
     *
     * @see #getVertex(Object)
     * @see Converter#convert(Object)
     */
    @Nonnull
    public Optional<Vertex> getVertex(Id id) {
        Object vertexId = idConverter.convert(id);
        return Optional.ofNullable(getVertex(vertexId));
    }

    /**
     * Returns the vertex referenced by the provided {@code id}.
     * If no such vertex exists, it will be created and added to the graph.
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
    public Vertex getOrCreateVertex(Id id) {
        Object vertexId = idConverter.convert(id);
        return Optional.ofNullable(getVertex(vertexId)).orElseGet(() -> addVertex(vertexId));
    }

    /**
     * Returns the index, from the graph, by its name and index class.
     * An index is unique up to name.
     * If no such index exists, it will be created.
     *
     * @param indexName  the name of the manual index
     * @param indexClass the element class that this index is indexing
     *
     * @return the index
     *
     * @see #getIndex(String, Class)
     * @see #createIndex(String, Class, Parameter[])
     */
    @Nonnull
    public <E extends Element> Index<E> getOrCreateIndex(String indexName, Class<E> indexClass) {
        return Optional.ofNullable(getIndex(indexName, indexClass)).orElseGet(() -> createIndex(indexName, indexClass));
    }
}
