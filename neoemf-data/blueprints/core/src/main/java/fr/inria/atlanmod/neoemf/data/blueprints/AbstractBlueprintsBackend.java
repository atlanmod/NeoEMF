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
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.GraphHelper;
import com.tinkerpop.blueprints.util.wrappers.WrapperGraph;
import com.tinkerpop.blueprints.util.wrappers.id.IdEdge;
import com.tinkerpop.blueprints.util.wrappers.id.IdGraph;

import fr.inria.atlanmod.commons.cache.Cache;
import fr.inria.atlanmod.commons.cache.CacheBuilder;
import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link BlueprintsBackend} that provides overall behavior for the management of a Blueprints database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractBlueprintsBackend extends AbstractBackend implements BlueprintsBackend {

    /**
     * The property key used to define the index of an edge.
     */
    protected static final String PROPERTY_INDEX = "_p";

    /**
     * The property key used to define the number of edges with a specific label.
     */
    protected static final String PROPERTY_SIZE = "_s";

    /**
     * The label used to define container {@link Edge}s.
     */
    private static final String EDGE_CONTAINER = "_c";

    /**
     * The label of type conformance {@link Edge}s.
     */
    private static final String EDGE_INSTANCE_OF = "_i";

    /**
     * The property key used to define the name of the the opposite containing feature in container {@link Edge}s.
     */
    private static final String PROPERTY_CONTAINER_NAME = "_cn";

    /**
     * The property key used to define the name of meta-class {@link Vertex}s.
     */
    private static final String PROPERTY_INSTANCE_NAME = "_in";

    /**
     * The property key used to define the URI of meta-class {@link Vertex}s.
     */
    private static final String PROPERTY_INSTANCE_URI = "_iu";

    /**
     * The delimiter used to separate the property name and its value in a composed property.
     *
     * @see #formatProperty(FeatureBean, Object)
     */
    private static final String DELIMITER = "_";

    /**
     * The {@link Converter} to use a long representation instead of {@link Id}.
     * <p>
     * This converter is specific to Blueprints which returns each identifier as an {@link Object}.
     */
    @Nonnull
    protected final Converter<Id, Object> idConverter = Converter.from(
            IdConverters.withLong()::convert,
            o -> IdConverters.withLong().revert(Long.class.cast(o)));

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
     * {@code true} if the base {@link Graph} requires unique labels and properties.
     * <p>
     * Some {@link Graph} implementations, as {@link TinkerGraph}, associates each label and property with a type, even
     * if it appears in a different Vertex/Edge. A simple {@link Integer} cause some troubles because they appear in
     * different {@link com.tinkerpop.blueprints.Element} but don't represent the same thing.
     */
    private final boolean requireUniqueLabels;

    /**
     * Constructs a new {@code AbstractBlueprintsBackend} wrapping the provided {@code baseGraph}.
     *
     * @param baseGraph the base {@link KeyIndexableGraph} used to access the database
     *
     * @see BlueprintsBackendFactory
     */
    protected AbstractBlueprintsBackend(KeyIndexableGraph baseGraph) {
        checkNotNull(baseGraph, "baseGraph");

        graph = new SmartIdGraph(baseGraph);

        requireUniqueLabels = TinkerGraph.class.isInstance(getOrigin(baseGraph));

        metaClassSet = new HashSet<>();
        metaClassIndex = getOrCreateIndex("instances");
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
    private Graph getOrigin(Graph graph) {
        return graph.getFeatures().isWrapper
                ? getOrigin(WrapperGraph.class.cast(graph).getBaseGraph())
                : graph;
    }

    /**
     * Formats a property as {@code label:suffix}.
     *
     * @param feature the feature associated with the property
     * @param suffix  the suffix of the property
     *
     * @return the formatted property
     */
    @Nonnull
    protected String formatProperty(FeatureBean feature, Object suffix) {
        return formatLabel(feature) + DELIMITER + String.valueOf(suffix);
    }

    /**
     * Formats a label.
     *
     * @param feature the feature associated with the label
     *
     * @return the formatted label
     */
    @Nonnull
    protected String formatLabel(FeatureBean feature) {
        return requireUniqueLabels
                // TODO Can cause a massive overhead (metaClassNameOf)
                ? metaClassNameOf(feature.owner()) + DELIMITER + Integer.toString(feature.id())
                : Integer.toString(feature.id());
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

    /**
     * Returns the size of the {@code key} for the given {@code vertex}.
     *
     * @param key    the key identifying the multi-valued feature
     * @param vertex the related vertex; {@code vertex.id == key.id}
     *
     * @return the size
     */
    @Nonnegative
    protected int sizeOf(SingleFeatureBean key, Vertex vertex) {
        return Optional.<Integer>ofNullable(vertex.getProperty(formatProperty(key, PROPERTY_SIZE))).orElse(0);
    }

    /**
     * Defines the {@code size} of the {@code key} for the given {@code vertex}.
     *
     * @param key    the key identifying the multi-valued feature
     * @param vertex the related vertex; {@code vertex.id == key.id}
     * @param size   the new size
     */
    protected void sizeFor(SingleFeatureBean key, Vertex vertex, @Nonnegative int size) {
        if (size > 0) {
            vertex.setProperty(formatProperty(key, PROPERTY_SIZE), size);
        }
        else {
            vertex.removeProperty(formatProperty(key, PROPERTY_SIZE));
        }
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
    protected void innerClose() {
        graph.shutdown();
    }

    @Override
    protected void innerCopyTo(DataMapper target) {
        AbstractBlueprintsBackend to = AbstractBlueprintsBackend.class.cast(target);

        GraphHelper.copyGraph(graph, to.graph);

        metaClassSet.forEach(m -> {
            Id id = generateClassId(m);
            Vertex vertex = get(id).<IllegalStateException>orElseThrow(IllegalStateException::new);
            to.metaClassIndex.put(PROPERTY_INSTANCE_NAME, m.name(), vertex);
        });
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        Optional<Vertex> optContainmentVertex = get(id);
        if (!optContainmentVertex.isPresent()) {
            return Optional.empty();
        }

        final Vertex containmentVertex = optContainmentVertex.get();

        return MoreIterables.onlyElement(containmentVertex.getEdges(Direction.OUT, EDGE_CONTAINER))
                .map(e -> SingleFeatureBean.of(
                        idConverter.revert(e.getVertex(Direction.IN).getId()),
                        e.getProperty(PROPERTY_CONTAINER_NAME)));
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        Vertex containmentVertex = getOrCreate(id);

        containmentVertex.getEdges(Direction.OUT, EDGE_CONTAINER).forEach(Edge::remove);
        containmentVertex.addEdge(EDGE_CONTAINER, getOrCreate(container.owner())).setProperty(PROPERTY_CONTAINER_NAME, container.id());
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        Optional<Vertex> optContainmentVertex = get(id);
        if (!optContainmentVertex.isPresent()) {
            return;
        }

        final Vertex containmentVertex = optContainmentVertex.get();

        containmentVertex.getEdges(Direction.OUT, EDGE_CONTAINER).forEach(Edge::remove);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        Optional<Vertex> optVertex = get(id);
        if (!optVertex.isPresent()) {
            return Optional.empty();
        }

        final Vertex vertex = optVertex.get();

        return MoreIterables.onlyElement(vertex.getVertices(Direction.OUT, EDGE_INSTANCE_OF))
                .map(v -> ClassBean.of(
                        v.getProperty(PROPERTY_INSTANCE_NAME),
                        v.getProperty(PROPERTY_INSTANCE_URI)));
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        Vertex vertex = getOrCreate(id);

        // Check the presence of a meta-class
        if (MoreIterables.onlyElement(vertex.getEdges(Direction.OUT, EDGE_INSTANCE_OF)).isPresent()) {
            return false;
        }

        // Retrieve or create the meta-class and store it in the index
        Iterable<Vertex> instanceVertices = metaClassIndex.get(PROPERTY_INSTANCE_NAME, metaClass.name());

        Vertex metaClassVertex = MoreIterables.onlyElement(instanceVertices).orElseGet(() -> {
            Vertex mcv = graph.addVertex(idConverter.convert(generateClassId(metaClass)));
            mcv.setProperty(PROPERTY_INSTANCE_NAME, metaClass.name());
            mcv.setProperty(PROPERTY_INSTANCE_URI, metaClass.uri());

            metaClassIndex.put(PROPERTY_INSTANCE_NAME, metaClass.name(), mcv);
            metaClassSet.add(metaClass);

            return mcv;
        });

        // Defines the meta-class
        vertex.addEdge(EDGE_INSTANCE_OF, metaClassVertex);

        return true;
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return metaClasses.stream()
                .map(mc -> metaClassIndex.get(PROPERTY_INSTANCE_NAME, mc.name()))
                .flatMap(MoreIterables::stream)
                .map(mcv -> mcv.getVertices(Direction.IN, EDGE_INSTANCE_OF))
                .flatMap(MoreIterables::stream)
                .map(v -> idConverter.revert(v.getId()))
                .collect(Collectors.toSet());
    }

    /**
     * Returns the name of the meta-class of the specified {@code id}.
     *
     * @param id the identifier
     *
     * @return the name of the meta-class
     */
    @Nonnull
    private String metaClassNameOf(Id id) {
        // If the meta-class is not defined, the identifier represents the 'ROOT' element
        return metaClassOf(id).map(ClassBean::name).orElse(DELIMITER);
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
        return Optional.ofNullable(verticesCache.get(id, i -> {
            final Object objectId = idConverter.convert(i);
            return graph.getVertex(objectId);
        }));
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
        return verticesCache.get(id, i -> {
            final Object objectId = idConverter.convert(i);
            return Optional.ofNullable(graph.getVertex(objectId)).orElseGet(() -> graph.addVertex(objectId));
        });
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
    public Graph getGraph() {
        return graph;
    }

    /**
     * An {@link IdGraph} that automatically removes unused {@link Vertex}.
     */
    @ParametersAreNonnullByDefault
    private static class SmartIdGraph extends IdGraph<KeyIndexableGraph> {

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
            return createFrom(super.addEdge(id, outVertex, inVertex, label));
        }

        @Override
        public Edge getEdge(Object id) {
            return createFrom(super.getEdge(id));
        }

        /**
         * Creates a new {@link SmartIdEdge} from another {@link Edge}.
         *
         * @param edge the base edge
         *
         * @return an {@link SmartIdEdge}
         */
        private Edge createFrom(@Nullable Edge edge) {
            return Optional.ofNullable(edge)
                    .map(SmartIdEdge::new)
                    .orElse(null);
        }

        /**
         * An {@link IdEdge} that automatically removes {@link Vertex} that are no longer referenced.
         */
        @ParametersAreNonnullByDefault
        private class SmartIdEdge extends IdEdge {

            /**
             * Constructs a new {@code SmartIdEdge} on the specified {@code edge}.
             *
             * @param edge the base edge
             */
            public SmartIdEdge(Edge edge) {
                super(edge, SmartIdGraph.this);
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

                if (MoreIterables.isEmpty(referencedVertex.getEdges(Direction.IN))) {
                    // If the Vertex has no more incoming edges remove it from the DB
                    referencedVertex.remove();
                }
            }
        }
    }
}
