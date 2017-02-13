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
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
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
@ParametersAreNonnullByDefault
class DefaultBlueprintsBackend extends AbstractBlueprintsBackend {

    /**
     * Constructs a new {@code DefaultBlueprintsBackend} wrapping the provided {@code baseGraph}.
     * <p>
     * This constructor initialize the caches and create the metaclass index.
     *
     * @param baseGraph the base {@link KeyIndexableGraph} used to access the database
     *
     * @note This constructor is protected. To create a new {@code DefaultBlueprintsBackend} use {@link
     * PersistenceBackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     * @see BlueprintsBackendFactory
     */
    protected DefaultBlueprintsBackend(KeyIndexableGraph baseGraph) {
        super(baseGraph);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        return Optional.ofNullable(vertex(key.id()).getProperty(key.name()));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        Optional<V> previousValue = valueOf(key);
        vertex(key.id()).setProperty(key.name(), value);
        return previousValue;
    }

    @Override
    public void unsetValue(SingleFeatureKey key) {
        vertex(key.id()).removeProperty(key.name());
    }

    @Override
    public boolean hasValue(SingleFeatureKey key) {
        Vertex vertex = vertex(key.id());
        return nonNull(vertex) && Optional.ofNullable(vertex.getProperty(key.name())).isPresent();
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureKey key) {
        Iterable<Vertex> referencedVertices = vertex(key.id()).getVertices(Direction.OUT, key.name());
        Optional<Vertex> referencedVertex = StreamSupport.stream(referencedVertices.spliterator(), false).findAny();

        return referencedVertex.map(v -> StringId.from(v.getId()));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureKey key, Id id) {
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
    public void unsetReference(SingleFeatureKey key) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> referenceEdges = vertex.getEdges(Direction.OUT, key.name());
        Optional<Edge> referenceEdge = StreamSupport.stream(referenceEdges.spliterator(), false).findAny();

        referenceEdge.ifPresent(Element::remove);
    }

    @Override
    public boolean hasReference(SingleFeatureKey key) {
        Vertex vertex = vertex(key.id());
        return nonNull(vertex) && StreamSupport.stream(vertex.getVertices(Direction.OUT, key.name()).spliterator(), false).findAny().isPresent();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultiFeatureKey key) {
        return Optional.ofNullable(vertex(key.id()).getProperty(formatProperty(key.name(), key.position())));
    }

    @Nonnull
    public <V> Optional<V> valueFor(MultiFeatureKey key, V value) {
        Optional<V> previousValue = valueOf(key);
        vertex(key.id()).setProperty(formatProperty(key.name(), key.position()), value);
        return previousValue;
    }

    @Override
    public void unsetAllValues(SingleFeatureKey key) {
        Vertex vertex = vertex(key.id());
        String property = formatProperty(key.name(), KEY_SIZE);

        IntStream.range(0, vertex.getProperty(property))
                .forEach(i -> vertex.removeProperty(formatProperty(key.name(), i)));

        vertex.removeProperty(property);
    }

    public boolean hasAnyValue(SingleFeatureKey key) {
        Vertex vertex = vertex(key.id());
        return nonNull(vertex) && nonNull(vertex.getProperty(formatProperty(key.name(), KEY_SIZE)));
    }

    @Override
    public <V> void addValue(MultiFeatureKey key, V value) {
        int size = sizeOfValue(key).orElse(0);
        int newSize = size + 1;

        Vertex vertex = vertex(key.id());

        // TODO Replace by Stream
        for (int i = size; i > key.position(); i--) {
            vertex.setProperty(formatProperty(key.name(), i), vertex.getProperty(formatProperty(key.name(), (i - 1))));
        }

        vertex.setProperty(formatProperty(key.name(), key.position()), value);

        sizeFor(key, newSize);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultiFeatureKey key) {
        int size = sizeOfValue(key).orElse(0);
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
    public void cleanValues(SingleFeatureKey key) {
        Vertex vertex = vertex(key.id());

        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> vertex.removeProperty(formatProperty(key.name(), i)));

        sizeFor(key, 0);
    }

    @Override
    public <V> boolean containsValue(SingleFeatureKey key, V value) {
        Vertex vertex = vertex(key.id());

        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .anyMatch(i -> Objects.equals(vertex.getProperty(formatProperty(key.name(), i)), value));
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(SingleFeatureKey key, V value) {
        Vertex vertex = vertex(key.id());

        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .filter(i -> Objects.equals(vertex.getProperty(formatProperty(key.name(), i)), value))
                .min();
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(SingleFeatureKey key, V value) {
        Vertex vertex = vertex(key.id());

        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .filter(i -> Objects.equals(vertex.getProperty(formatProperty(key.name(), i)), value))
                .max();
    }

    @Nonnull
    @Override
    public <V> Iterable<V> valuesAsList(SingleFeatureKey key) {
        Vertex vertex = vertex(key.id());

        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .mapToObj(i -> vertex.<V>getProperty(formatProperty(key.name(), i)))
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfValue(SingleFeatureKey key) {
        return Optional.ofNullable(vertex(key.id()))
                .map(v -> Optional.<Integer>ofNullable(v.getProperty(formatProperty(key.name(), KEY_SIZE)))
                        .map(OptionalInt::of)
                        .orElse(OptionalInt.empty()))
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(MultiFeatureKey key) {
        Vertex vertex = vertex(key.id());

        Iterable<Vertex> referencedVertices = vertex.query()
                .labels(key.name())
                .direction(Direction.OUT)
                .has(KEY_POSITION, key.position())
                .vertices();

        Optional<Vertex> referencedVertex = StreamSupport.stream(referencedVertices.spliterator(), false).findAny();

        return referencedVertex.map(v -> StringId.from(v.getId()));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(MultiFeatureKey key, Id id) {
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
    public void unsetAllReferences(SingleFeatureKey key) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> edges = vertex.query()
                .labels(key.name())
                .direction(Direction.OUT)
                .edges();

        StreamSupport.stream(edges.spliterator(), false).forEach(Element::remove);
        vertex.removeProperty(formatProperty(key.name(), KEY_SIZE));
    }

    @Override
    public boolean hasAnyReference(SingleFeatureKey key) {
        return hasReference(key);
    }

    @Override
    public void addReference(MultiFeatureKey key, Id id) {
        int size = sizeOfValue(key).orElse(0);
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

    @Nonnull
    @Override
    public Optional<Id> removeReference(MultiFeatureKey key) {
        int size = sizeOfValue(key).orElse(0);
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
    public void cleanReferences(SingleFeatureKey key) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> edges = vertex.query()
                .labels(key.name())
                .direction(Direction.OUT)
                .edges();

        edges.forEach(Element::remove);

        sizeFor(key, 0);
    }

    @Override
    public boolean containsReference(SingleFeatureKey key, Id id) {
        Iterable<Vertex> referencedVertices = vertex(key.id()).getVertices(Direction.OUT, key.name());

        return StreamSupport.stream(referencedVertices.spliterator(), false)
                .anyMatch(v -> Objects.equals(v.getId().toString(), id.toString()));
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(SingleFeatureKey key, Id id) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> edges = vertex(id).getEdges(Direction.IN, key.name());

        return StreamSupport.stream(edges.spliterator(), false)
                .filter(e -> Objects.equals(e.getVertex(Direction.OUT), vertex))
                .mapToInt(e -> e.<Integer>getProperty(KEY_POSITION))
                .min();
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(SingleFeatureKey key, Id id) {
        Vertex vertex = vertex(key.id());

        Iterable<Edge> edges = vertex(id).getEdges(Direction.IN, key.name());

        return StreamSupport.stream(edges.spliterator(), false)
                .filter(e -> Objects.equals(e.getVertex(Direction.OUT), vertex))
                .mapToInt(e -> e.<Integer>getProperty(KEY_POSITION))
                .max();
    }

    @Nonnull
    @Override
    public Iterable<Id> referencesAsList(SingleFeatureKey key) {
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

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(SingleFeatureKey key) {
        return sizeOfValue(key);
    }

    /**
     * Defines the {@code size} of the property identified by the given {@code key}.
     *
     * @param key  the feature key identifying the property
     * @param size the new size
     */
    protected void sizeFor(SingleFeatureKey key, @Nonnegative int size) {
        checkArgument(size >= 0);

        vertex(key.id()).setProperty(formatProperty(key.name(), KEY_SIZE), size);
    }
}
