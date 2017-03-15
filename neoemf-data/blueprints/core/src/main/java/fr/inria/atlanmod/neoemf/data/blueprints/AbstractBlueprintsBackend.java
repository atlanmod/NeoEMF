/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.GraphHelper;
import com.tinkerpop.blueprints.util.wrappers.id.IdEdge;
import com.tinkerpop.blueprints.util.wrappers.id.IdGraph;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.util.Iterables;
import fr.inria.atlanmod.neoemf.util.cache.Cache;
import fr.inria.atlanmod.neoemf.util.cache.CacheBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract {@link BlueprintsBackend} that provides overall behavior for the management of a Blueprints database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractBlueprintsBackend implements BlueprintsBackend {

    /**
     * The property key used to define the index of an edge.
     */
    protected static final String KEY_POSITION = "position";

    /**
     * The label used to define container {@link Edge}s.
     */
    protected static final String KEY_CONTAINER = "eContainer";

    /**
     * The property key used to define the opposite containing feature in container {@link Edge}s.
     */
    protected static final String KEY_CONTAINING_FEATURE = "containingFeature";

    /**
     * The property key used to define the number of edges with a specific label.
     */
    protected static final String KEY_SIZE = "size";

    /**
     * The label of type conformance {@link Edge}s.
     */
    private static final String KEY_INSTANCE_OF = "neoInstanceOf";

    /**
     * The name of the index entry holding metaclass {@link Vertex}s.
     */
    private static final String KEY_METACLASSES = "metaclasses";

    /**
     * The index key used to retrieve metaclass {@link Vertex}s.
     */
    private static final String KEY_NAME = "name";

    /**
     * The property key used to set the namespace URI of metaclass {@link Vertex}s.
     */
    private static final String KEY_NS_URI = "nsURI";

    /**
     * In-memory cache that holds recently loaded {@link Vertex}s, identified by the associated object {@link Id}.
     */
    @Nonnull
    private final Cache<Id, Vertex> verticesCache = CacheBuilder.newBuilder()
            .maximumSize()
            .build();

    /**
     * List that holds indexed {@link ClassDescriptor}.
     */
    @Nonnull
    private final List<ClassDescriptor> indexedMetaclasses;

    /**
     * Index containing metaclasses.
     */
    @Nonnull
    private final Index<Vertex> metaclassIndex;

    /**
     * The Blueprints graph.
     */
    @Nonnull
    private final IdGraph<KeyIndexableGraph> graph;

    /**
     * Whether the underlying database is closed.
     */
    private boolean isClosed;

    /**
     * Constructs a new {@code BlueprintsBackendIndices} wrapping the provided {@code baseGraph}.
     * <p>
     * This constructor initialize the caches and create the metaclass index.
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@code BlueprintsBackendIndices} use {@link
     * BackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     *
     * @param baseGraph the base {@link KeyIndexableGraph} used to access the database
     *
     * @see BlueprintsBackendFactory
     */
    protected AbstractBlueprintsBackend(KeyIndexableGraph baseGraph) {
        checkNotNull(baseGraph);

        this.graph = new AutoCleanerIdGraph(baseGraph);

        indexedMetaclasses = new ArrayList<>();
        metaclassIndex = Optional.ofNullable(graph.getIndex(KEY_METACLASSES, Vertex.class))
                .orElseGet(() -> graph.createIndex(KEY_METACLASSES, Vertex.class));

        isClosed = false;
    }

    /**
     * Builds the {@link Id} used to identify a {@link ClassDescriptor} {@link Vertex}.
     *
     * @param metaclass the {@link ClassDescriptor} to build an {@link Id} from
     *
     * @return the create {@link Id}
     */
    @Nonnull
    private static Id buildId(ClassDescriptor metaclass) {
        return StringId.of(metaclass.name() + '@' + metaclass.uri());
    }

    /**
     * Formats a property key as {@code prefix:suffix}.
     *
     * @param prefix the prefix of the property key
     * @param suffix the suffix of the property key
     *
     * @return the formatted property key
     */
    protected static String formatProperty(String prefix, Object suffix) {
        return prefix + ':' + suffix;
    }

    @Override
    public void save() {
        if (graph.getFeatures().supportsTransactions) {
            graph.commit();
        }
        else {
            try {
                graph.shutdown();
            }
            catch (Exception ignore) {
            }
        }
    }

    @Override
    public void close() {
        if (isClosed) {
            return;
        }

        isClosed = true;

        try {
            graph.shutdown();
        }
        catch (Exception ignore) {
        }
    }

    @Override
    public void copyTo(DataMapper target) {
        checkArgument(target instanceof AbstractBlueprintsBackend);
        AbstractBlueprintsBackend to = (AbstractBlueprintsBackend) target;

        GraphHelper.copyGraph(graph, to.graph);

        indexedMetaclasses.forEach(m -> {
            Iterable<Vertex> metaclasses = to.metaclassIndex.get(KEY_NAME, m.name());
            checkArgument(Iterables.isEmpty(metaclasses), "Index is not consistent");
            to.metaclassIndex.put(KEY_NAME, m.name(), get(buildId(m)).<IllegalStateException>orElseThrow(IllegalStateException::new));
        });
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    public boolean exists(Id id) {
        return get(id).isPresent();
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        checkNotNull(id);

        Optional<Vertex> containmentVertex = get(id);

        if (!containmentVertex.isPresent()) {
            return Optional.empty();
        }

        Iterable<Edge> containerEdges = containmentVertex.get().getEdges(Direction.OUT, KEY_CONTAINER);
        Optional<Edge> containerEdge = Iterables.stream(containerEdges).findAny();

        Optional<ContainerDescriptor> container = Optional.empty();
        if (containerEdge.isPresent()) {
            String featureName = containerEdge.get().getProperty(KEY_CONTAINING_FEATURE);
            Vertex containerVertex = containerEdge.get().getVertex(Direction.IN);
            container = Optional.of(ContainerDescriptor.of(StringId.from(containerVertex.getId()), featureName));
        }

        return container;
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        checkNotNull(id);
        checkNotNull(container);

        Vertex containmentVertex = getOrCreate(id);
        Vertex containerVertex = getOrCreate(container.id());

        containmentVertex.getEdges(Direction.OUT, KEY_CONTAINER).forEach(Edge::remove);

        Edge edge = containmentVertex.addEdge(KEY_CONTAINER, containerVertex);
        edge.setProperty(KEY_CONTAINING_FEATURE, container.name());
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        checkNotNull(id);

        Optional<Vertex> vertex = get(id);

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        Iterable<Vertex> metaclassVertices = vertex.get().getVertices(Direction.OUT, KEY_INSTANCE_OF, "kyanosInstanceOf");
        Optional<Vertex> metaclassVertex = Iterables.stream(metaclassVertices).findAny();

        return metaclassVertex.map(v -> ClassDescriptor.of(v.getProperty(KEY_NAME), v.getProperty(KEY_NS_URI)));
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        checkNotNull(id);
        checkNotNull(metaclass);

        Iterable<Vertex> metaclassVertices = metaclassIndex.get(KEY_NAME, metaclass.name());
        Vertex metaclassVertex = Iterables.stream(metaclassVertices).findAny().orElse(null);

        if (isNull(metaclassVertex)) {
            metaclassVertex = graph.addVertex(buildId(metaclass).toString());
            metaclassVertex.setProperty(KEY_NAME, metaclass.name());
            metaclassVertex.setProperty(KEY_NS_URI, metaclass.uri());

            metaclassIndex.put(KEY_NAME, metaclass.name(), metaclassVertex);
            indexedMetaclasses.add(metaclass);
        }

        Vertex vertex = getOrCreate(id);

        // Remove the previous metaclass if present
        vertex.getEdges(Direction.OUT, KEY_INSTANCE_OF).forEach(Edge::remove);

        vertex.addEdge(KEY_INSTANCE_OF, metaclassVertex);

        // Remove old link
        vertex.getEdges(Direction.OUT, "kyanosInstanceOf").forEach(Edge::remove);
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(ClassDescriptor metaclass, boolean strict) {
        List<Id> indexHits;

        // There is no strict instance of an abstract class
        if (metaclass.isAbstract() && strict) {
            return Collections.emptyList();
        }
        else {
            Set<ClassDescriptor> allInstances = strict ? new HashSet<>() : metaclass.inheritedBy();
            allInstances.add(metaclass);

            // Get all vertices that are indexed with one of the metaclass
            return allInstances.stream()
                    .flatMap(mc -> Iterables.stream(metaclassIndex.get(KEY_NAME, mc.name()))
                            .flatMap(mcv -> Iterables.stream(mcv.getVertices(Direction.IN, KEY_INSTANCE_OF, "kyanosInstanceOf"))
                                    .map(v -> StringId.from(v.getId()))))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Retrieves the {@link Vertex} corresponding to the provided {@code id}.
     *
     * @param id the {@link Id} of the element to find
     *
     * @return an {@link Optional} containing the {@link Vertex}, or {@link Optional#empty()} if it doesn't exist
     */
    @Nonnull
    protected Optional<Vertex> get(Id id) {
        return Optional.ofNullable(verticesCache.get(id, key -> graph.getVertex(key.toString())));
    }

    /**
     * Retrieves the {@link Vertex} corresponding to the provided {@code id}. If it doesn't already exist, it will be
     * created.
     *
     * @param id the {@link Id} of the element to find, or create
     *
     * @return the {@link Vertex}
     */
    @Nonnull
    protected Vertex getOrCreate(Id id) {
        return verticesCache.get(id, k ->
                Optional.ofNullable(graph.getVertex(k.toString()))
                        .orElseGet(() -> graph.addVertex(k.toString())));
    }

    /**
     * Provides a direct access to the underlying graph.
     * <p>
     * This method is public for tool compatibility (see the
     * <a href="https://github.com/atlanmod/Mogwai">Mogwaï</a>) framework, NeoEMF consistency is not guaranteed if
     * the graph is modified manually.
     *
     * @return the underlying Blueprints {@link IdGraph}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public <T> T getGraph() {
        return (T) graph;
    }

    /**
     * An {@link IdGraph} that automatically removes unused {@link Vertex}.
     */
    private static class AutoCleanerIdGraph extends IdGraph<KeyIndexableGraph> {

        /**
         * Constructs a new {@code AutoCleanerIdGraph} on the specified {@code baseGraph}.
         *
         * @param baseGraph the base graph
         */
        public AutoCleanerIdGraph(KeyIndexableGraph baseGraph) {
            super(baseGraph);
        }

        @Override
        public Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label) {
            return createFrom(super.addEdge(id, outVertex, inVertex, label));
        }

        @Override
        public Edge getEdge(Object id) {
            return createFrom(super.getEdge(id));
        }

        /**
         * Creates a new {@link AutoCleanerIdEdge} from another {@link Edge}.
         *
         * @param edge the base edge
         *
         * @return an {@link AutoCleanerIdEdge}
         */
        private Edge createFrom(@Nullable Edge edge) {
            return Optional.ofNullable(edge)
                    .map(AutoCleanerIdEdge::new)
                    .orElse(null);
        }

        /**
         * An {@link IdEdge} that automatically removes {@link Vertex} that are no longer referenced.
         */
        private class AutoCleanerIdEdge extends IdEdge {

            /**
             * Constructs a new {@code AutoCleanerIdEdge} on the specified {@code edge}.
             *
             * @param edge the base edge
             */
            public AutoCleanerIdEdge(Edge edge) {
                super(edge, AutoCleanerIdGraph.this);
            }

            /**
             * {@inheritDoc}
             * <p>
             * If the {@link Edge} references a {@link Vertex} with no more incoming {@link Edge}, the referenced
             * {@link Vertex} is removed as well.
             */
            @Override
            public void remove() {
                Vertex referencedVertex = getVertex(Direction.IN);
                super.remove();

                Iterable<Edge> edges = referencedVertex.getEdges(Direction.IN);
                if (Iterables.isEmpty(edges)) {
                    // If the Vertex has no more incoming edges remove it from the DB
                    referencedVertex.remove();
                }
            }
        }
    }
}
