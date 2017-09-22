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

import fr.inria.atlanmod.commons.Converter;
import fr.inria.atlanmod.commons.cache.Cache;
import fr.inria.atlanmod.commons.cache.CacheBuilder;
import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.AbstractPersistentBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link BlueprintsBackend} that provides overall behavior for the management of a Blueprints database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractBlueprintsBackend extends AbstractPersistentBackend implements BlueprintsBackend {

    /**
     * The default converter to use {@link Long} instead of {@link Id}.
     */
    @Nonnull
    protected static final Converter<Id, Object> ID_CONVERTER = Converter.from(
            Id::toLong,
            o -> Id.getProvider().fromLong(Long.class.cast(o)));

    /**
     * The property key used to define the index of an edge.
     */
    protected static final String KEY_POSITION = "p";

    /**
     * The label used to define container {@link Edge}s.
     */
    protected static final String KEY_CONTAINER = "c";

    /**
     * The property key used to define the opposite containing feature in container {@link Edge}s.
     */
    protected static final String KEY_CONTAINING_FEATURE = "f";

    /**
     * The property key used to define the number of edges with a specific label.
     */
    protected static final String KEY_SIZE = "s";

    /**
     * The label of type conformance {@link Edge}s.
     */
    private static final String KEY_INSTANCE_OF = "i";

    /**
     * The name of the index entry holding meta-class {@link Vertex}s.
     */
    private static final String KEY_METACLASSES = "m";

    /**
     * The index key used to retrieve meta-class {@link Vertex}s.
     */
    private static final String KEY_NAME = "n";

    /**
     * The property key used to set the namespace URI of meta-class {@link Vertex}s.
     */
    private static final String KEY_NS_URI = "u";

    /**
     * In-memory cache that holds recently loaded {@link Vertex}s, identified by the associated object {@link Id}.
     */
    @Nonnull
    private final Cache<Id, Vertex> verticesCache = CacheBuilder.builder()
            .softValues()
            .build();

    /**
     * A set that holds indexed {@link ClassBean}.
     *
     * @see #metaClassIndex
     * @see #innerCopyTo(DataMapper)
     */
    @Nonnull
    private final Set<ClassBean> metaClassSet;

    /**
     * Index containing meta-classes.
     */
    @Nonnull
    private final Index<Vertex> metaClassIndex;

    /**
     * The Blueprints graph.
     */
    @Nonnull
    private final IdGraph<KeyIndexableGraph> graph;

    /**
     * Constructs a new {@code AbstractBlueprintsBackend} wrapping the provided {@code baseGraph}.
     *
     * @param baseGraph the base {@link KeyIndexableGraph} used to access the database
     *
     * @see BlueprintsBackendFactory
     */
    protected AbstractBlueprintsBackend(KeyIndexableGraph baseGraph) {
        checkNotNull(baseGraph);

        graph = new InternalIdGraph(baseGraph);

        metaClassSet = new HashSet<>();
        metaClassIndex = getOrCreateIndex(KEY_METACLASSES);
    }

    /**
     * Builds the {@link Id} used to identify a {@link ClassBean} {@link Vertex}.
     *
     * @param metaClass the {@link ClassBean} to build an {@link Id} from
     *
     * @return the create {@link Id}
     */
    @Nonnull
    private static Id generateClassId(ClassBean metaClass) {
        return Id.getProvider().generate(metaClass.name() + metaClass.uri());
    }

    /**
     * Formats a property as {@code prefix:suffix}.
     *
     * @param prefix the prefix of the property
     * @param suffix the suffix of the property
     *
     * @return the formatted property
     */
    @Nonnull
    protected static String formatProperty(Object prefix, Object suffix) {
        return String.valueOf(prefix) + ':' + String.valueOf(suffix);
    }

    /**
     * Formats a label.
     *
     * @param label the label to format
     *
     * @return the formatted label
     */
    @Nonnull
    protected static String formatLabel(Object label) {
        return String.valueOf(label);
    }

    /**
     * Retrieves or create an index for the given {@code name}.
     *
     * @param name the name of the index
     *
     * @return the index
     */
    @Nonnull
    private Index<Vertex> getOrCreateIndex(String name) {
        return Optional.ofNullable(graph.getIndex(name, Vertex.class))
                .orElseGet(() -> graph.createIndex(name, Vertex.class));
    }

    @Override
    public void save() {
        if (graph.getFeatures().supportsTransactions) {
            graph.commit();
        }
        else {
            graph.shutdown();
        }
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    protected void innerClose() {
        try {
            graph.shutdown();
        }
        catch (Exception ignored) {
        }
    }

    @Override
    protected void innerCopyTo(DataMapper target) {
        AbstractBlueprintsBackend to = AbstractBlueprintsBackend.class.cast(target);

        GraphHelper.copyGraph(graph, to.graph);

        metaClassSet.forEach(m -> {
            Id id = generateClassId(m);
            Vertex vertex = get(id).<IllegalStateException>orElseThrow(IllegalStateException::new);
            to.metaClassIndex.put(KEY_NAME, m.name(), vertex);
        });
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id);

        Optional<Vertex> containmentVertex = get(id);

        if (!containmentVertex.isPresent()) {
            return Optional.empty();
        }

        Iterable<Edge> edges = containmentVertex.get().query()
                .labels(KEY_CONTAINER)
                .direction(Direction.OUT)
                .limit(1)
                .edges();

        return MoreIterables.onlyElement(edges)
                .map(e -> SingleFeatureBean.of(
                        ID_CONVERTER.revert(e.getVertex(Direction.IN).getId()),
                        e.getProperty(KEY_CONTAINING_FEATURE)));
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id);
        checkNotNull(container);

        Vertex containmentVertex = getOrCreate(id);
        Vertex containerVertex = getOrCreate(container.owner());

        Iterable<Edge> containmentEdges = containmentVertex.query()
                .labels(KEY_CONTAINER)
                .direction(Direction.OUT)
                .limit(1)
                .edges();

        containmentEdges.forEach(Edge::remove);

        Edge edge = containmentVertex.addEdge(KEY_CONTAINER, containerVertex);
        edge.setProperty(KEY_CONTAINING_FEATURE, container.id());
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id);

        Optional<Vertex> containmentVertex = get(id);

        if (!containmentVertex.isPresent()) {
            return;
        }

        Iterable<Edge> containmentEdges = containmentVertex.get().query()
                .labels(KEY_CONTAINER)
                .direction(Direction.OUT)
                .limit(1)
                .edges();

        containmentEdges.forEach(Edge::remove);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id);

        Optional<Vertex> vertex = get(id);

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        Iterable<Vertex> metaClassVertices = vertex.get().query()
                .labels(KEY_INSTANCE_OF)
                .direction(Direction.OUT)
                .limit(1)
                .vertices();

        return MoreIterables.onlyElement(metaClassVertices)
                .map(v -> ClassBean.of(
                        v.getProperty(KEY_NAME),
                        v.getProperty(KEY_NS_URI)));
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id);
        checkNotNull(metaClass);

        Vertex vertex = getOrCreate(id);

        // Check the presence of a meta-class
        Iterable<Edge> instanceEdges = vertex.query()
                .labels(KEY_INSTANCE_OF)
                .direction(Direction.OUT)
                .limit(1)
                .edges();

        if (MoreIterables.onlyElement(instanceEdges).isPresent()) {
            return false;
        }

        // Retrieve or create the meta-class and store it in the index
        Iterable<Vertex> instanceVertices = metaClassIndex.get(KEY_NAME, metaClass.name());

        Vertex metaClassVertex = MoreIterables.onlyElement(instanceVertices).orElseGet(() -> {
            Vertex mcv = graph.addVertex(ID_CONVERTER.convert(generateClassId(metaClass)));
            mcv.setProperty(KEY_NAME, metaClass.name());
            mcv.setProperty(KEY_NS_URI, metaClass.uri());

            metaClassIndex.put(KEY_NAME, metaClass.name(), mcv);
            metaClassSet.add(metaClass);

            return mcv;
        });

        // Defines the meta-class
        vertex.addEdge(KEY_INSTANCE_OF, metaClassVertex);

        return true;
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return metaClasses.stream()
                .map(mc -> metaClassIndex.get(KEY_NAME, mc.name()))
                .flatMap(MoreIterables::stream)
                .map(mcv -> mcv.getVertices(Direction.IN, KEY_INSTANCE_OF))
                .flatMap(MoreIterables::stream)
                .map(v -> ID_CONVERTER.revert(v.getId()))
                .collect(Collectors.toSet());
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
        return Optional.ofNullable(verticesCache.get(id, i -> graph.getVertex(ID_CONVERTER.convert(i))));
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
        return verticesCache.get(id, i ->
                Optional.ofNullable(graph.getVertex(ID_CONVERTER.convert(i)))
                        .orElseGet(() -> graph.addVertex(ID_CONVERTER.convert(i))));
    }

    /**
     * Provides a direct access to the underlying graph.
     * <p>
     * This method is public for tool compatibility (see the <a href="https://github.com/atlanmod/Mogwai">Mogwaï</a>)
     * framework, NeoEMF consistency is not guaranteed if the graph is modified manually.
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
    @ParametersAreNonnullByDefault
    private static class InternalIdGraph extends IdGraph<KeyIndexableGraph> {

        /**
         * Constructs a new {@code InternalIdGraph} on the specified {@code baseGraph}.
         *
         * @param baseGraph the base graph
         */
        public InternalIdGraph(KeyIndexableGraph baseGraph) {
            super(baseGraph, true, false);
            enforceUniqueIds(false);
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
        @ParametersAreNonnullByDefault
        private class AutoCleanerIdEdge extends IdEdge {

            /**
             * Constructs a new {@code AutoCleanerIdEdge} on the specified {@code edge}.
             *
             * @param edge the base edge
             */
            public AutoCleanerIdEdge(Edge edge) {
                super(edge, InternalIdGraph.this);
            }

            /**
             * {@inheritDoc}
             * <p>
             * If the {@link Edge} references a {@link Vertex} with no more incoming {@link Edge}, the referenced {@link
             * Vertex} is removed as well.
             */
            @Override
            public void remove() {
                Vertex referencedVertex = getVertex(Direction.IN);
                super.remove();

                Iterable<Edge> edges = referencedVertex.query()
                        .direction(Direction.IN)
                        .limit(1)
                        .edges();

                if (MoreIterables.isEmpty(edges)) {
                    // If the Vertex has no more incoming edges remove it from the DB
                    referencedVertex.remove();
                }
            }
        }
    }
}
