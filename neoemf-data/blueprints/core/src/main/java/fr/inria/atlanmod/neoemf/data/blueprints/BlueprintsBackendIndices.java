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
import fr.inria.atlanmod.neoemf.data.mapper.MultiValueWithIndices;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link PersistenceBackend} that is responsible of low-level access to a Blueprints database.
 * <p>
 * It wraps an existing Blueprints database and provides facilities to create and retrieve elements, map {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}s to {@link Vertex} elements in order to speed up attribute access,
 * and manage a set of lightweight caches to improve access time of {@link Vertex} from  their corresponding {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}.
 * <p>
 * <b>Note:</b> This class is used in {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} to access and
 * manipulate the database.
 * <p>
 * <b>Note2:</b> Instances of {@link BlueprintsBackend} are created by {@link BlueprintsBackendFactory} that provides an
 * usable {@link KeyIndexableGraph} that can be manipulated by this wrapper.
 *
 * @see BlueprintsBackendFactory
 */
@ParametersAreNonnullByDefault
class BlueprintsBackendIndices extends AbstractBlueprintsBackend implements MultiValueWithIndices {

    /**
     * Constructs a new {@code BlueprintsBackendIndices} wrapping the provided {@code baseGraph}.
     * <p>
     * This constructor initialize the caches and create the metaclass index.
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@code BlueprintsBackendIndices} use {@link
     * PersistenceBackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     *
     * @param baseGraph the base {@link KeyIndexableGraph} used to access the database
     *
     * @see BlueprintsBackendFactory
     */
    protected BlueprintsBackendIndices(KeyIndexableGraph baseGraph) {
        super(baseGraph);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        return get(key.id())
                .map(v -> v.getProperty(key.name()));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        checkNotNull(value);

        Optional<V> previousValue = valueOf(key);
        getOrCreate(key.id()).setProperty(key.name(), value);
        return previousValue;
    }

    @Override
    public void unsetValue(FeatureKey key) {
        get(key.id())
                .ifPresent(v -> v.removeProperty(key.name()));
    }

    @Override
    public boolean hasValue(FeatureKey key) {
        return get(key.id())
                .map(v -> Optional.ofNullable(v.getProperty(key.name())).isPresent())
                .orElse(false);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        Iterable<Vertex> referencedVertices = vertex.get().getVertices(Direction.OUT, key.name());
        Optional<Vertex> referencedVertex = StreamSupport.stream(referencedVertices.spliterator(), false).findAny();

        return referencedVertex.map(v -> StringId.from(v.getId()));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        Vertex vertex = getOrCreate(key.id());

        Iterable<Edge> referenceEdges = vertex.getEdges(Direction.OUT, key.name());
        Optional<Edge> referenceEdge = StreamSupport.stream(referenceEdges.spliterator(), false).findAny();

        Optional<Id> previousId = Optional.empty();
        if (referenceEdge.isPresent()) {
            Vertex previousVertex = referenceEdge.get().getVertex(Direction.IN);
            previousId = Optional.of(StringId.from(previousVertex.getId()));
            referenceEdge.get().remove();
        }

        vertex.addEdge(key.name(), getOrCreate(reference));

        return previousId;
    }

    @Override
    public void unsetReference(FeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return;
        }

        Iterable<Edge> referenceEdges = vertex.get().getEdges(Direction.OUT, key.name());
        Optional<Edge> referenceEdge = StreamSupport.stream(referenceEdges.spliterator(), false).findAny();

        referenceEdge.ifPresent(Element::remove);
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        checkNotNull(key);

        return get(key.id())
                .map(v -> StreamSupport.stream(v.getVertices(Direction.OUT, key.name()).spliterator(), false)
                        .findAny()
                        .isPresent())
                .orElse(false);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        Iterable<Vertex> referencedVertices = vertex.get().query()
                .labels(key.name())
                .direction(Direction.OUT)
                .has(KEY_POSITION, key.position())
                .vertices();

        Optional<Vertex> referencedVertex = StreamSupport.stream(referencedVertices.spliterator(), false).findAny();

        return referencedVertex.map(v -> StringId.from(v.getId()));
    }

    @Nonnull
    @Override
    public Iterable<Id> allReferencesOf(FeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return Collections.emptyList();
        }

        Iterable<Edge> edges = vertex.get().query()
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
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            throw new NoSuchElementException();
        }

        Iterable<Edge> edges = vertex.get().query()
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

        Edge edge = vertex.get().addEdge(key.name(), getOrCreate(reference));
        edge.setProperty(KEY_POSITION, key.position());

        return previousId;
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return;
        }

        Iterable<Edge> edges = vertex.get().query()
                .labels(key.name())
                .direction(Direction.OUT)
                .edges();

        StreamSupport.stream(edges.spliterator(), false).forEach(Element::remove);
        vertex.get().removeProperty(formatProperty(key.name(), KEY_SIZE));
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        checkNotNull(key);

        return hasReference(key);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        int size = sizeOfValue(key).orElse(0);

        Vertex vertex = getOrCreate(key.id());

        if (key.position() != size) {
            Iterable<Edge> edges = vertex.query()
                    .labels(key.name())
                    .direction(Direction.OUT)
                    .interval(KEY_POSITION, key.position(), size + 1)
                    .edges();

            edges.forEach(e -> e.setProperty(KEY_POSITION, e.<Integer>getProperty(KEY_POSITION) + 1));
        }

        Edge edge = vertex.addEdge(key.name(), getOrCreate(reference));
        edge.setProperty(KEY_POSITION, key.position());

        sizeFor(key, size + 1);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());
        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOfValue(key).orElse(0);
        if (size == 0) {
            return Optional.empty();
        }

        Iterable<Edge> edges = vertex.get().query()
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

        sizeFor(key, size - 1);

        return previousId;
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return;
        }

        vertex.get().query()
                .labels(key.name())
                .direction(Direction.OUT)
                .edges()
                .forEach(Element::remove);

        sizeFor(key, 0);
    }

    @Override
    public boolean containsReference(FeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return false;
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return false;
        }

        Iterable<Vertex> referencedVertices = vertex.get().getVertices(Direction.OUT, key.name());

        return StreamSupport.stream(referencedVertices.spliterator(), false)
                .anyMatch(v -> Objects.equals(v.getId().toString(), reference.toString()));
    }

    @Nonnull
    @Override
    public OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return OptionalInt.empty();
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());
        Optional<Vertex> referencedVertex = get(reference);

        if (!vertex.isPresent() || !referencedVertex.isPresent()) {
            return OptionalInt.empty();
        }

        Iterable<Edge> edges = referencedVertex.get().getEdges(Direction.IN, key.name());

        if (edges instanceof List) {
            List<Edge> edgesList = (List<Edge>) edges;

            int size = edgesList.size();
            for (int i = 0; i < size; i++) {
                if (Objects.equals(edgesList.get(i).getVertex(Direction.OUT), vertex.get())) {
                    return OptionalInt.of(i);
                }
            }

            return OptionalInt.empty();
        }
        else {
            return StreamSupport.stream(edges.spliterator(), false)
                    .filter(e -> Objects.equals(e.getVertex(Direction.OUT), vertex.get()))
                    .mapToInt(e -> e.<Integer>getProperty(KEY_POSITION))
                    .min();
        }
    }

    @Nonnull
    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return OptionalInt.empty();
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());
        Optional<Vertex> referencedVertex = get(reference);

        if (!vertex.isPresent() || !referencedVertex.isPresent()) {
            return OptionalInt.empty();
        }

        Iterable<Edge> edges = referencedVertex.get().getEdges(Direction.IN, key.name());

        if (edges instanceof List) {
            List<Edge> edgesList = (List<Edge>) edges;

            int size = edgesList.size();
            for (int i = size - 1; i > 0; i--) {
                if (Objects.equals(edgesList.get(i).getVertex(Direction.OUT), vertex.get())) {
                    return OptionalInt.of(i);
                }
            }

            return OptionalInt.empty();
        }
        else {
            return StreamSupport.stream(edges.spliterator(), false)
                    .filter(e -> Objects.equals(e.getVertex(Direction.OUT), vertex.get()))
                    .mapToInt(e -> e.<Integer>getProperty(KEY_POSITION))
                    .max();
        }
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        checkNotNull(key);

        return get(key.id())
                .map(v -> v.getProperty(formatProperty(key.name(), key.position())));
    }

    @Nonnull
    @Override
    public <V> Iterable<V> allValuesOf(FeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return Collections.emptyList();
        }

        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .mapToObj(i -> vertex.get().<V>getProperty(formatProperty(key.name(), i)))
                .collect(Collectors.toList());
    }

    @Nonnull
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            throw new NoSuchElementException();
        }

        Optional<V> previousValue = valueOf(key);
        vertex.get().setProperty(formatProperty(key.name(), key.position()), value);
        return previousValue;
    }

    @Override
    public void unsetAllValues(FeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return;
        }

        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> vertex.get().removeProperty(formatProperty(key.name(), i)));

        sizeFor(key, 0);
    }

    public boolean hasAnyValue(FeatureKey key) {
        checkNotNull(key);

        return get(key.id())
                .map(v -> sizeOfValue(key).isPresent())
                .orElse(false);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        int size = sizeOfValue(key.withoutPosition()).orElse(0);

        Vertex vertex = getOrCreate(key.id());

        if (key.position() >= size || nonNull(vertex.getProperty(formatProperty(key.name(), key.position())))) {
            for (int i = size; i > key.position(); i--) {
                vertex.setProperty(formatProperty(key.name(), i), vertex.getProperty(formatProperty(key.name(), i - 1)));
            }
        }

        sizeFor(key, size + 1);

        vertex.setProperty(formatProperty(key.name(), key.position()), value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());
        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOfValue(key).orElse(0);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<V> previousValue = Optional.ofNullable(vertex.get().getProperty(formatProperty(key.name(), key.position())));

        for (int i = key.position(); i < size - 1; i++) {
            vertex.get().setProperty(formatProperty(key.name(), i), vertex.get().getProperty(formatProperty(key.name(), i + 1)));
        }

        vertex.get().removeProperty(formatProperty(key.name(), size - 1));

        sizeFor(key, size - 1);

        return previousValue;
    }

    @Override
    public void removeAllValues(FeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return;
        }

        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> vertex.get().removeProperty(formatProperty(key.name(), i)));

        sizeFor(key, 0);
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return false;
        }

        checkNotNull(key);

        return get(key.id()).map(v -> IntStream.range(0, sizeOfValue(key).orElse(0))
                .anyMatch(i -> Objects.equals(v.getProperty(formatProperty(key.name(), i)), value)))
                .orElse(false);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return OptionalInt.empty();
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return OptionalInt.empty();
        }

        int size = sizeOfValue(key).orElse(0);
        for (int i = 0; i < size; i++) {
            if (Objects.equals(vertex.get().getProperty(formatProperty(key.name(), i)), value)) {
                return OptionalInt.of(i);
            }
        }

        return OptionalInt.empty();
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return OptionalInt.empty();
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return OptionalInt.empty();
        }

        int size = sizeOfValue(key).orElse(0);
        for (int i = size - 1; i > 0; i--) {
            if (Objects.equals(vertex.get().getProperty(formatProperty(key.name(), i)), value)) {
                return OptionalInt.of(i);
            }
        }

        return OptionalInt.empty();
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfValue(FeatureKey key) {
        checkNotNull(key);

        return get(key.id())
                .map(v -> Optional.<Integer>ofNullable(v.getProperty(formatProperty(key.name(), KEY_SIZE)))
                        .map(OptionalInt::of)
                        .orElse(OptionalInt.empty()))
                .orElse(OptionalInt.empty());
    }

    @Override
    public void sizeFor(FeatureKey key, @Nonnegative int size) {
        checkNotNull(key);
        checkArgument(size >= 0);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return;
        }

        if (size > 0) {
            vertex.get().setProperty(formatProperty(key.name(), KEY_SIZE), size);
        }
        else {
            vertex.get().removeProperty(formatProperty(key.name(), KEY_SIZE));
        }
    }
}
