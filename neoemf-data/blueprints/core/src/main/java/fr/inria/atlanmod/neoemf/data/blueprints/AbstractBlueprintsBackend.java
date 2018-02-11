/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.CloseableIterable;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.ElementHelper;
import com.tinkerpop.blueprints.util.GraphHelper;
import com.tinkerpop.blueprints.util.wrappers.WrapperGraph;
import com.tinkerpop.blueprints.util.wrappers.id.IdGraph;

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
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.collect.MoreIterables.firstElement;

/**
 * An abstract {@link BlueprintsBackend} that provides overall behavior for the management of a Blueprints database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractBlueprintsBackend extends AbstractBackend implements BlueprintsBackend {

    // region Keys

    /**
     * The delimiter used to separate the property name and its value in a composed property.
     *
     * @see #formatProperty(FeatureBean, Object)
     */
    protected static final String DELIMITER = "_";

    /**
     * The label used to define container {@link Edge}s.
     */
    private static final String EDGE_CONTAINER = DELIMITER + "c";

    /**
     * The label of type conformance {@link Edge}s.
     */
    private static final String EDGE_INSTANCE_OF = DELIMITER + "i";

    /**
     * The property key used to define the name of the the opposite containing feature in container {@link Edge}s.
     */
    private static final String PROPERTY_CONTAINER_NAME = DELIMITER + "cn";

    /**
     * The property key used to define the name of meta-class {@link Vertex}s.
     */
    private static final String PROPERTY_INSTANCE_NAME = DELIMITER + "in";

    /**
     * The property key used to define the URI of meta-class {@link Vertex}s.
     */
    private static final String PROPERTY_INSTANCE_URI = DELIMITER + "iu";

    /**
     * The property key used to define the number of edges with a specific label.
     */
    private static final String PROPERTY_SIZE = DELIMITER + "s";

    // endregion

    /**
     * The {@link Converter} to use a long representation instead of {@link Id}.
     * <p>
     * This converter is specific to Blueprints which returns each identifier as an {@link Object}.
     */
    @Nonnull
    protected final Converter<Id, Object> idConverter;

    /**
     * The Blueprints graph.
     */
    @Nonnull
    protected final CachedIdGraph graph;

    /**
     * The index that holds all meta-class vertices.
     *
     * @see #allInstancesOf(Set)
     */
    @Nonnull
    private final Index<Vertex> allMetaClassesIndex;

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

        // Pre-process arguments
        Graph originGraph = getOrigin(baseGraph);
        if (TinkerGraph.class.isInstance(originGraph)) {
            // Tinkergraph is stored as XML: prefer using String-based identifiers
            idConverter = Converter.compose(IdConverters.withHexString(), Converter.from(Function.identity(), String.class::cast));
            requireUniqueLabels = true;
        }
        else {
            idConverter = Converter.compose(IdConverters.withLong(), Converter.from(Function.identity(), Long.class::cast));
            requireUniqueLabels = false;
        }

        // Build graph and components
        graph = new CachedIdGraph(baseGraph, idConverter);
        allMetaClassesIndex = graph.getOrCreateIndex("instances-all", Vertex.class);
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

    /**
     * {@inheritDoc}
     *
     * @see GraphHelper#copyGraph(Graph, Graph)
     * @see ElementHelper#copyProperties(Element, Element)
     */
    @Override
    protected void innerCopyTo(DataMapper target) {
        AbstractBlueprintsBackend to = AbstractBlueprintsBackend.class.cast(target);

        Graph fromGraph = this.graph;
        Graph toGraph = to.graph;

        // Copy all vertices
        final Set<Object> reindexedMetaClassNames = new HashSet<>();

        for (Vertex fromVertex : fromGraph.getVertices()) {
            Vertex toVertex = toGraph.addVertex(fromVertex.getId());

            for (String key : fromVertex.getPropertyKeys()) {
                Object value = fromVertex.getProperty(key);
                toVertex.setProperty(key, value);

                // Rebuild meta-classes index
                if (PROPERTY_INSTANCE_NAME.equals(key) && reindexedMetaClassNames.add(value)) { // value == metaClass.name
                    to.allMetaClassesIndex.put(PROPERTY_INSTANCE_NAME, value, toVertex);
                }
            }
        }

        // Copy all edges
        for (Edge fromEdge : fromGraph.getEdges()) {
            Vertex outVertex = toGraph.getVertex(fromEdge.getVertex(Direction.OUT).getId());
            Vertex inVertex = toGraph.getVertex(fromEdge.getVertex(Direction.IN).getId());
            Edge toEdge = toGraph.addEdge(fromEdge.getId(), outVertex, inVertex, fromEdge.getLabel());

            ElementHelper.copyProperties(fromEdge, toEdge);
        }
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        // Try to retrieve the container edge from the vertex
        return graph.getVertex(id)
                .flatMap(v -> firstElement(v.getEdges(Direction.OUT, EDGE_CONTAINER)))
                .map(this::createContainer);
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        Vertex containmentVertex = graph.getOrCreateVertex(id);

        // Remove the existing container
        removeContainer(containmentVertex);

        // Create and define the new container
        setContainer(containmentVertex, container);
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        graph.getVertex(id).ifPresent(this::removeContainer);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        // Try to retrieve the meta-class vertex from the vertex
        return graph.getVertex(id)
                .flatMap(v -> firstElement(v.getVertices(Direction.OUT, EDGE_INSTANCE_OF)))
                .map(this::createMetaClass);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        // Try to retrieve the meta-class vertex from the vertex
        Vertex vertex = graph.getOrCreateVertex(id);

        Optional<Edge> instanceOfEdge = firstElement(vertex.getEdges(Direction.OUT, EDGE_INSTANCE_OF));
        if (!instanceOfEdge.isPresent()) {
            setMetaClass(vertex, metaClass);
            return true;
        }

        return false;
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return metaClasses.stream()
                .map(mc -> allMetaClassesIndex.get(PROPERTY_INSTANCE_NAME, mc.name()))
                .flatMap(MoreIterables::stream) // Only one vertex
                .map(mcv -> mcv.getVertices(Direction.IN, EDGE_INSTANCE_OF))
                .flatMap(MoreIterables::stream)
                .map(Vertex::getId)
                .map(idConverter::revert)
                .collect(Collectors.toSet());
    }

    // region Bean transformation

    /**
     * Creates a {@link SingleFeatureBean} from an {@link Edge}.
     *
     * @param edge the edge
     *
     * @return the created container
     */
    @Nonnull
    protected SingleFeatureBean createContainer(Edge edge) {
        Vertex vertex = edge.getVertex(Direction.IN);

        Id id = idConverter.revert(vertex.getId());
        int name = edge.getProperty(PROPERTY_CONTAINER_NAME);

        return SingleFeatureBean.of(id, name);
    }

    /**
     * Creates a container edge between the {@code vertex} and its container.
     *
     * @param vertex    the containment vertex
     * @param container information about the container
     */
    protected void setContainer(Vertex vertex, SingleFeatureBean container) {
        Vertex containerVertex = graph.getOrCreateVertex(container.owner());

        // TODO Add an identifier for this edge
        Edge containerEdge = graph.addEdge(null, vertex, containerVertex, EDGE_CONTAINER);
        containerEdge.setProperty(PROPERTY_CONTAINER_NAME, container.id());
    }

    /**
     * Remove the container associated to the specified {@code vertex}.
     *
     * @param vertex the vertex
     */
    protected void removeContainer(Vertex vertex) {
        firstElement(vertex.getEdges(Direction.OUT, EDGE_CONTAINER)).ifPresent(Element::remove);
    }

    /**
     * Creates a {@link ClassBean} from a {@link Vertex}.
     *
     * @param vertex the vertex
     *
     * @return the created meta-class
     */
    @Nonnull
    protected ClassBean createMetaClass(Vertex vertex) {
        String name = vertex.getProperty(PROPERTY_INSTANCE_NAME);
        String uri = vertex.getProperty(PROPERTY_INSTANCE_URI);

        return ClassBean.of(name, uri);
    }

    /**
     * Retrieves or creates the meta-class vertex, and associate it to the specified {@code vertex}.
     *
     * @param vertex    the vertex that is instance of the meta-class
     * @param metaClass information about the meta-class
     */
    protected void setMetaClass(Vertex vertex, ClassBean metaClass) {
        String name = metaClass.name();
        String uri = metaClass.uri();

        Id id = Id.getProvider().generate(name + uri);

        Vertex metaClassVertex = graph.getOrCreateVertex(id);
        metaClassVertex.setProperty(PROPERTY_INSTANCE_NAME, name);
        metaClassVertex.setProperty(PROPERTY_INSTANCE_URI, uri);

        // TODO Add an identifier for this edge
        graph.addEdge(null, vertex, metaClassVertex, EDGE_INSTANCE_OF);

        // Update the index if necessary
        try (CloseableIterable<Vertex> iterable = allMetaClassesIndex.get(PROPERTY_INSTANCE_NAME, name)) {
            if (!firstElement(iterable).isPresent()) {
                allMetaClassesIndex.put(PROPERTY_INSTANCE_NAME, name, metaClassVertex);
            }
        }
    }

    /**
     * Returns the size of the {@code feature} for the given {@code vertex}.
     *
     * @param vertex  the related vertex; {@code vertex.id == feature.id}
     * @param feature the related feature
     *
     * @return the size
     */
    @Nonnegative
    protected int getSize(Vertex vertex, SingleFeatureBean feature) {
        String property = formatProperty(feature, PROPERTY_SIZE);

        return Optional.ofNullable(vertex.<Integer>getProperty(property)).orElse(0);
    }

    /**
     * Defines the {@code size} of the {@code feature} for the given {@code vertex}.
     *
     * @param vertex  the related vertex; {@code vertex.id == feature.id}
     * @param feature the related feature
     * @param size    the new size
     */
    protected void setSize(Vertex vertex, SingleFeatureBean feature, @Nonnegative int size) {
        String property = formatProperty(feature, PROPERTY_SIZE);

        if (size > 0) {
            vertex.setProperty(property, size);
        }
        else {
            vertex.removeProperty(property);
        }
    }

    // endregion

    // region Format

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
        String label = Integer.toString(feature.id());

        if (requireUniqueLabels) {
            // TODO May cause performance loss
            String name = metaClassOf(feature.owner())
                    .map(ClassBean::name)
                    .orElse(DELIMITER);

            label = name + DELIMITER + label;
        }

        return label;
    }

    // endregion

    /**
     * Provides a direct access to the underlying graph.
     * <p>
     * This method is public for tool compatibility (see the <a href="https://github.com/atlanmod/Mogwai">Mogwaï</a>)
     * framework, NeoEMF consistency is not guaranteed if the graph is modified manually.
     *
     * @return the underlying Blueprints {@link IdGraph}
     */
    @Nonnull
    public IdGraph<KeyIndexableGraph> getGraph() {
        return graph;
    }
}
