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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.GraphHelper;
import com.tinkerpop.blueprints.util.wrappers.id.IdEdge;
import com.tinkerpop.blueprints.util.wrappers.id.IdGraph;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link PersistenceBackend} that is responsible of low-level access to a Blueprints database.
 * <p>
 * It wraps an existing Blueprints database and provides facilities to create and retrieve elements, map {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}s to {@link Vertex} elements in order to speed up attribute access,
 * and manage a set of lightweight caches to improve access time of {@link Vertex} from  their corresponding {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}.
 *
 * @note This class is used in {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} to access and manipulate the
 * database.
 * @note Instances of {@link BlueprintsBackend} are created by {@link BlueprintsBackendFactory} that provides an usable
 * {@link KeyIndexableGraph} that can be manipulated by this wrapper.
 * @see BlueprintsBackendFactory
 */
class DefaultBlueprintsBackend extends AbstractBlueprintsBackend {

    /**
     * The property key used to set metaclass name in metaclass {@link Vertex}s.
     */
    public static final String KEY_ECLASS_NAME = EcorePackage.eINSTANCE.getENamedElement_Name().getName();

    /**
     * The property key used to set the {@link EPackage} {@code nsURI} in metaclass {@link Vertex}s.
     */
    public static final String KEY_EPACKAGE_NSURI = EcorePackage.eINSTANCE.getEPackage_NsURI().getName();

    /**
     * The label of type conformance {@link Edge}s.
     */
    public static final String KEY_INSTANCE_OF = "kyanosInstanceOf";

    /**
     * The name of the index entry holding metaclass {@link Vertex}s.
     */
    public static final String KEY_METACLASSES = "metaclasses";

    /**
     * The index key used to retrieve metaclass {@link Vertex}s.
     */
    public static final String KEY_NAME = "name";

    /**
     * The property key used to define the index of an edge.
     */
    protected static final String KEY_POSITION = "position";

    /**
     * The label used to define container {@link Edge}s.
     */
    protected static final String KEY_CONTAINER = "eContainer";

    /**
     * The label used to link root vertex to top-level elements.
     */
    protected static final String KEY_CONTENTS = "eContents";

    /**
     * The property key used to define the opposite containing feature in container {@link Edge}s.
     */
    protected static final String KEY_CONTAINING_FEATURE = "containingFeature";

    /**
     * The property key used to define the number of edges with a specific label.
     */
    protected static final String KEY_SIZE = "size";

    /**
     * In-memory cache that holds recently loaded {@link Vertex}s, identified by the associated object {@link Id}.
     */
    private final Cache<Id, Vertex> verticesCache = Caffeine.newBuilder()
            .initialCapacity(1_000)
            .maximumSize(10_000)
            .build();

    /**
     * List that holds indexed {@link MetaclassValue}.
     */
    private final List<MetaclassValue> indexedMetaclasses;

    /**
     * Index containing metaclasses.
     */
    private final Index<Vertex> metaclassIndex;

    /**
     * The Blueprints graph.
     */
    private final IdGraph<KeyIndexableGraph> graph;

    /**
     * Whether the underlying database is closed.
     */
    private boolean isClosed = false;

    /**
     * Constructs a new {@code DefaultBlueprintsBackend} wrapping the provided {@code baseGraph}.
     * <p>
     * This constructor initialize the caches and create the metaclass index.
     *
     * @param baseGraph the base {@link KeyIndexableGraph} used to access the database
     *
     * @note This constructor is protected. To create a new {@code DefaultBlueprintsBackend} use {@link
     * BlueprintsBackendFactory#createPersistentBackend(java.io.File, Map)}.
     * @see BlueprintsBackendFactory
     */
    protected DefaultBlueprintsBackend(KeyIndexableGraph baseGraph) {
        this.graph = new AutoCleanerIdGraph(baseGraph);
        this.indexedMetaclasses = new ArrayList<>();

        Index<Vertex> metaclasses = graph.getIndex(KEY_METACLASSES, Vertex.class);
        if (isNull(metaclasses)) {
            metaclassIndex = graph.createIndex(KEY_METACLASSES, Vertex.class);
        }
        else {
            metaclassIndex = metaclasses;
        }
    }

    /**
     * Builds the {@link Id} used to identify a {@link MetaclassValue} {@link Vertex}.
     *
     * @param metaclass the {@link MetaclassValue} to build an {@link Id} from
     *
     * @return the create {@link Id}
     */
    @Nonnull
    private static Id buildId(@Nonnull MetaclassValue metaclass) {
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
    private static String formatProperty(String prefix, Object suffix) {
        return prefix + ':' + suffix;
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
    public void close() {
        try {
            graph.shutdown();
        }
        catch (Exception e) {
            NeoLogger.warn(e);
        }
        isClosed = true;
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    public void create(Id id) {
        Vertex vertex = graph.addVertex(id.toString());
        verticesCache.put(id, vertex);
    }

    @Override
    public boolean has(Id id) {
        return nonNull(vertex(id));
    }

    @Override
    public Optional<ContainerValue> containerOf(Id id) {
        Vertex containmentVertex = vertex(id);

        Iterable<Edge> containerEdges = containmentVertex.getEdges(Direction.OUT, KEY_CONTAINER);
        Optional<Edge> containerEdge = StreamSupport.stream(containerEdges.spliterator(), false).findAny();

        Optional<ContainerValue> container = Optional.empty();
        if (containerEdge.isPresent()) {
            String featureName = containerEdge.get().getProperty(KEY_CONTAINING_FEATURE);
            Vertex containerVertex = containerEdge.get().getVertex(Direction.IN);
            container = Optional.of(ContainerValue.of(StringId.from(containerVertex.getId()), featureName));
        }

        return container;
    }

    @Override
    public void containerFor(Id id, ContainerValue container) {
        Vertex containmentVertex = vertex(id);
        Vertex containerVertex = vertex(container.id());

        containmentVertex.getEdges(Direction.OUT, KEY_CONTAINER).forEach(Element::remove);

        Edge edge = containmentVertex.addEdge(KEY_CONTAINER, containerVertex);
        edge.setProperty(KEY_CONTAINING_FEATURE, container.name());
    }

    @Override
    public Optional<MetaclassValue> metaclassOf(Id id) {
        Vertex vertex = vertex(id);

        Iterable<Vertex> metaclassVertices = vertex.getVertices(Direction.OUT, KEY_INSTANCE_OF);
        Optional<Vertex> metaclassVertex = StreamSupport.stream(metaclassVertices.spliterator(), false).findAny();

        return metaclassVertex.map(v -> MetaclassValue.of(v.getProperty(KEY_ECLASS_NAME), v.getProperty(KEY_EPACKAGE_NSURI)));
    }

    @Override
    public void metaclassFor(Id id, MetaclassValue metaclass) {
        Iterable<Vertex> metaclassVertices = metaclassIndex.get(KEY_NAME, metaclass.name());
        Vertex metaclassVertex = StreamSupport.stream(metaclassVertices.spliterator(), false).findAny().orElse(null);

        if (isNull(metaclassVertex)) {
            metaclassVertex = graph.addVertex(buildId(metaclass).toString());
            metaclassVertex.setProperty(KEY_ECLASS_NAME, metaclass.name());
            metaclassVertex.setProperty(KEY_EPACKAGE_NSURI, metaclass.uri());

            metaclassIndex.put(KEY_NAME, metaclass.name(), metaclassVertex);
            indexedMetaclasses.add(metaclass);
        }

        Vertex vertex = vertex(id);
        vertex.addEdge(KEY_INSTANCE_OF, metaclassVertex);
    }

    @Override
    public Iterable<Id> allInstances(EClass eClass, boolean strict) {
        List<Id> indexHits;

        // There is no strict instance of an abstract class
        if (eClass.isAbstract() && strict) {
            return Collections.emptyList();
        }
        else {
            Set<EClass> eClassToFind = new HashSet<>();
            eClassToFind.add(eClass);

            // Find all the concrete subclasses of the given EClass (the metaclass index only stores concretes EClass)
            if (!strict) {
                eClassToFind.addAll(eClass.getEPackage().getEClassifiers()
                        .stream()
                        .filter(EClass.class::isInstance)
                        .map(EClass.class::cast)
                        .filter(c -> eClass.isSuperTypeOf(c) && !c.isAbstract())
                        .collect(Collectors.toList()));
            }

            // Get all the vertices that are indexed with one of the EClass
            return eClassToFind.stream()
                    .flatMap(ec -> StreamSupport.stream(metaclassIndex.get(KEY_NAME, ec.getName()).spliterator(), false)
                            .flatMap(mcv -> StreamSupport.stream(mcv.getVertices(Direction.IN, KEY_INSTANCE_OF).spliterator(), false)
                                    .map(v -> StringId.from(v.getId()))))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        return Optional.ofNullable(vertex(key.id()).getProperty(key.name()));
    }

    @Override
    public <V> Optional<V> valueOf(MultivaluedFeatureKey key) {
        return Optional.ofNullable(vertex(key.id()).getProperty(formatProperty(key.name(), key.position())));
    }

    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        Iterable<Vertex> referencedVertices = vertex(key.id()).getVertices(Direction.OUT, key.name());
        Optional<Vertex> referencedVertex = StreamSupport.stream(referencedVertices.spliterator(), false).findAny();

        return referencedVertex.map(v -> StringId.from(v.getId()));
    }

    @Override
    public Optional<Id> referenceOf(MultivaluedFeatureKey key) {
        Vertex vertex = vertex(key.id());

        Iterable<Vertex> referencedVertices = vertex.query()
                .labels(key.name())
                .direction(Direction.OUT)
                .has(KEY_POSITION, key.position())
                .vertices();

        Optional<Vertex> referencedVertex = StreamSupport.stream(referencedVertices.spliterator(), false).findAny();

        return referencedVertex.map(v -> StringId.from(v.getId()));
    }

    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        Optional<V> previousValue = valueOf(key);
        vertex(key.id()).setProperty(key.name(), value);
        return previousValue;
    }

    public <V> Optional<V> valueFor(MultivaluedFeatureKey key, V value) {
        Optional<V> previousValue = valueOf(key);
        vertex(key.id()).setProperty(formatProperty(key.name(), key.position()), value);
        return previousValue;
    }

    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id id) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> referenceEdges = vertex.getEdges(Direction.OUT, key.name());
        Optional<Edge> referenceEdge = StreamSupport.stream(referenceEdges.spliterator(), false).findAny();

        Optional<Id> previousId = Optional.empty();
        if (referenceEdge.isPresent()) {
            Vertex previouslyReferencedVertex = referenceEdge.get().getVertex(Direction.IN);
            previousId = Optional.of(StringId.from(previouslyReferencedVertex.getId()));
            referenceEdge.get().remove();
        }

        vertex(key.id()).addEdge(key.name(), vertex(id));

        return previousId;
    }

    @Override
    public Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> edges = vertex.query()
                .labels(key.name())
                .direction(Direction.OUT)
                .has(KEY_POSITION, key.position())
                .edges();

        Optional<Edge> previousEdge = StreamSupport.stream(edges.spliterator(), false).findAny();

        Optional<Id> previousId = Optional.empty();
        if (previousEdge.isPresent()) {
            Vertex referencedVertex = previousEdge.get().getVertex(Direction.IN);
            previousId = Optional.of(StringId.from(referencedVertex.getId()));
            previousEdge.get().remove();
        }

        Edge edge = vertex(key.id()).addEdge(key.name(), vertex(id));
        edge.setProperty(KEY_POSITION, key.position());

        return previousId;
    }

    @Override
    public void unsetValue(FeatureKey key) {
        vertex(key.id()).removeProperty(key.name());
    }

    @Override
    public void unsetAllValues(FeatureKey key) {
        Vertex vertex = vertex(key.id());
        String property = formatProperty(key.name(), KEY_SIZE);

        IntStream.range(0, vertex.getProperty(property))
                .forEach(i -> vertex.removeProperty(formatProperty(key.name(), i)));

        vertex.removeProperty(property);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> referenceEdges = vertex.getEdges(Direction.OUT, key.name());
        Optional<Edge> referenceEdge = StreamSupport.stream(referenceEdges.spliterator(), false).findAny();

        referenceEdge.ifPresent(Element::remove);
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> edges = vertex.query()
                .labels(key.name())
                .direction(Direction.OUT)
                .edges();

        StreamSupport.stream(edges.spliterator(), false).forEach(Element::remove);
        vertex.removeProperty(formatProperty(key.name(), KEY_SIZE));
    }

    @Override
    public boolean hasValue(FeatureKey key) {
        Vertex vertex = vertex(key.id());
        return nonNull(vertex) && Optional.ofNullable(vertex.getProperty(key.name())).isPresent();
    }

    public boolean hasAnyValue(FeatureKey key) {
        Vertex vertex = vertex(key.id());
        return nonNull(vertex) && nonNull(vertex.getProperty(formatProperty(key.name(), KEY_SIZE)));
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        Vertex vertex = vertex(key.id());
        return nonNull(vertex) && StreamSupport.stream(vertex.getVertices(Direction.OUT, key.name()).spliterator(), false).findAny().isPresent();
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        return hasReference(key);
    }

    @Override
    public <V> void addValue(MultivaluedFeatureKey key, V value) {
        int size = sizeOf(key).orElse(0);
        int newSize = size + 1;

        Vertex vertex = vertex(key.id());

        // TODO Replace by Stream
        for (int i = size; i > key.position(); i--) {
            vertex.setProperty(formatProperty(key.name(), i), vertex.getProperty(formatProperty(key.name(), (i - 1))));
        }

        vertex.setProperty(formatProperty(key.name(), key.position()), value);

        sizeFor(key, newSize);
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        int size = sizeOf(key).orElse(0);
        int newSize = size + 1;

        Vertex vertex = vertex(key.id());

        if (key.position() != size) {
            Iterable<Edge> edges = vertex.query()
                    .labels(key.name())
                    .direction(Direction.OUT)
                    .interval(KEY_POSITION, key.position(), newSize)
                    .edges();

            edges.forEach(e -> e.setProperty(KEY_POSITION, e.<Integer>getProperty(KEY_POSITION) + 1));
        }

        Edge edge = vertex.addEdge(key.name(), vertex(id));
        edge.setProperty(KEY_POSITION, key.position());

        sizeFor(key, newSize);
    }

    @Override
    public <V> Optional<V> removeValue(MultivaluedFeatureKey key) {
        int size = sizeOf(key).orElse(0);
        int newSize = size - 1;

        Vertex vertex = vertex(key.id());

        Optional<V> previousValue = Optional.ofNullable(vertex.getProperty(formatProperty(key.name(), key.position())));

        // TODO Replace by Stream
        for (int i = newSize; i > key.position(); i--) {
            vertex.setProperty(formatProperty(key.name(), i - 1), vertex.getProperty(formatProperty(key.name(), i)));
        }

        sizeFor(key, newSize);

        return previousValue;
    }

    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        int size = sizeOf(key).orElse(0);
        int newSize = size - 1;

        Vertex vertex = vertex(key.id());

        Iterable<Edge> edges = vertex.query()
                .labels(key.name())
                .direction(Direction.OUT)
                .interval(KEY_POSITION, key.position(), size)
                .edges();

        Optional<Id> previousId = Optional.empty();
        for (Edge edge : edges) {
            int position = edge.getProperty(KEY_POSITION);

            if (position != key.position()) {
                edge.setProperty(KEY_POSITION, position - 1);
            }
            else {
                Vertex referencedVertex = edge.getVertex(Direction.IN);
                previousId = Optional.of(StringId.from(referencedVertex.getId()));
                edge.remove();

                referencedVertex.getEdges(Direction.OUT, KEY_CONTAINER).forEach(Element::remove);
            }
        }

        sizeFor(key, newSize);

        return previousId;
    }

    @Override
    public void cleanValues(FeatureKey key) {
        Vertex vertex = vertex(key.id());

        IntStream.range(0, sizeOf(key).orElse(0))
                .forEach(i -> vertex.removeProperty(formatProperty(key.name(), i)));

        sizeFor(key, 0);
    }

    @Override
    public void cleanReferences(FeatureKey key) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> edges = vertex.query()
                .labels(key.name())
                .direction(Direction.OUT)
                .edges();

        edges.forEach(Element::remove);

        sizeFor(key, 0);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, V value) {
        Vertex vertex = vertex(key.id());

        return IntStream.range(0, sizeOf(key).orElse(0))
                .anyMatch(i -> Objects.equals(vertex.getProperty(formatProperty(key.name(), i)), value));
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        Iterable<Vertex> referencedVertices = vertex(key.id()).getVertices(Direction.OUT, key.name());

        return StreamSupport.stream(referencedVertices.spliterator(), false)
                .anyMatch(v -> Objects.equals(v.getId().toString(), id.toString()));
    }

    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        Vertex vertex = vertex(key.id());

        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> Objects.equals(vertex.getProperty(formatProperty(key.name(), i)), value))
                .min();
    }

    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> edges = vertex(id).getEdges(Direction.IN, key.name());

        return StreamSupport.stream(edges.spliterator(), false)
                .filter(e -> Objects.equals(e.getVertex(Direction.OUT), vertex))
                .mapToInt(e -> e.<Integer>getProperty(KEY_POSITION))
                .min();
    }

    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
        Vertex vertex = vertex(key.id());

        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> Objects.equals(vertex.getProperty(formatProperty(key.name(), i)), value))
                .max();
    }

    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> edges = vertex(id).getEdges(Direction.IN, key.name());

        return StreamSupport.stream(edges.spliterator(), false)
                .filter(e -> Objects.equals(e.getVertex(Direction.OUT), vertex))
                .mapToInt(e -> e.<Integer>getProperty(KEY_POSITION))
                .max();
    }

    @Override
    public <V> Iterable<V> valuesAsList(FeatureKey key) {
        Vertex vertex = vertex(key.id());

        return IntStream.range(0, sizeOf(key).orElse(0))
                .mapToObj(i -> vertex.<V>getProperty(formatProperty(key.name(), i)))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        Iterable<Edge> edges = vertex(key.id()).query()
                .labels(key.name())
                .direction(Direction.OUT)
                .edges();

        Comparator<Edge> byPosition = Comparator.comparingInt(e -> e.getProperty(KEY_POSITION));

        return StreamSupport.stream(edges.spliterator(), false)
                .sorted(byPosition)
                .map(e -> StringId.from(e.getVertex(Direction.IN).getId()))
                .collect(Collectors.toList());
    }

    @Override
    public OptionalInt sizeOf(FeatureKey key) {
        return Optional.ofNullable(vertex(key.id()))
                .map(v -> Optional.<Integer>ofNullable(v.getProperty(formatProperty(key.name(), KEY_SIZE)))
                        .map(OptionalInt::of)
                        .orElse(OptionalInt.empty()))
                .orElse(OptionalInt.empty());
    }

    /**
     * Defines the {@code size} of the property identified by the given {@code key}.
     *
     * @param key  the feature key identifying the property
     * @param size the new size
     */
    protected void sizeFor(FeatureKey key, int size) {
        vertex(key.id()).setProperty(formatProperty(key.name(), KEY_SIZE), size);
    }

    /**
     * Returns the vertex corresponding to the provided {@code id}. If no vertex corresponds to that {@code id}, then
     * return {@code null}.
     *
     * @param id the {@link Id} of the element to find
     *
     * @return the vertex referenced by the provided {@link Id} or {@code null} when no such vertex exists
     */
    private Vertex vertex(Id id) {
        return verticesCache.get(id, key -> graph.getVertex(key.toString()));
    }

    /**
     * Returns the vertex corresponding to the provided {@code id}. If no vertex corresponds to that {@code id}, then
     * return {@code null}.
     *
     * @param id the {@link Id} of the element to find
     *
     * @return the vertex referenced by the provided {@link Id} or {@code null} when no such vertex exists
     */
    @Override
    public Vertex getVertex(Id id) {
        return verticesCache.get(id, key -> graph.getVertex(key.toString()));
    }

    /**
     * Create a new vertex, add it to the graph, and return the newly created vertex.
     *
     * @param id the identifier of the {@link Vertex}
     *
     * @return the newly created vertex
     */
    @Override
    public Vertex addVertex(Id id) {
        return graph.addVertex(id.toString());
    }

    /**
     * Copies all the contents of this {@code PersistenceBackend} to the {@code target} one.
     *
     * @param target the {@code PersistenceBackend} to copy the database contents to
     */
    @Override
    public void copyTo(BlueprintsBackend target) {
        DefaultBlueprintsBackend backend = (DefaultBlueprintsBackend) target;

        GraphHelper.copyGraph(graph, backend.graph);

        for (MetaclassValue metaclass : indexedMetaclasses) {
            Iterable<Vertex> metaclasses = backend.metaclassIndex.get(KEY_NAME, metaclass.name());
            checkArgument(
                    !StreamSupport.stream(metaclasses.spliterator(), false).findAny().isPresent(),
                    "Index is not consistent");

            backend.metaclassIndex.put(KEY_NAME, metaclass.name(), vertex(buildId(metaclass)));
        }
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
    public IdGraph<KeyIndexableGraph> getGraph() {
        return graph;
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
        private Edge createFrom(Edge edge) {
            return isNull(edge) ? null : new AutoCleanerIdEdge(edge);
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
                if (!StreamSupport.stream(edges.spliterator(), false).findAny().isPresent()) {
                    // If the Vertex has no more incoming edges remove it from the DB
                    referencedVertex.remove();
                }
            }
        }
    }
}
