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

import fr.inria.atlanmod.common.collect.MoreIterables;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * The default {@link BlueprintsBackend} mapping.
 *
 * @see BlueprintsBackendFactory
 */
@ParametersAreNonnullByDefault
class BlueprintsBackendIndices extends AbstractBlueprintsBackend {

    /**
     * Constructs a new {@code BlueprintsBackendIndices} wrapping the provided {@code baseGraph}.
     * <p>
     * This constructor initialize the caches and create the metaclass index.
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@link BlueprintsBackend} use {@link
     * BackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     *
     * @param baseGraph the base {@link KeyIndexableGraph} used to access the database
     *
     * @see BlueprintsBackendFactory
     */
    protected BlueprintsBackendIndices(KeyIndexableGraph baseGraph) {
        super(baseGraph);
    }

    //region Single-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        return get(key.id()).map(v -> v.<V>getProperty(key.name()));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        checkNotNull(value);

        Vertex vertex = getOrCreate(key.id());

        Optional<V> previousValue = Optional.ofNullable(vertex.<V>getProperty(key.name()));
        vertex.<V>setProperty(key.name(), value);
        return previousValue;
    }

    @Override
    public <V> void unsetValue(SingleFeatureKey key) {
        get(key.id()).ifPresent(v -> v.<V>removeProperty(key.name()));
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        Iterable<Vertex> referencedVertices = vertex.get().getVertices(Direction.OUT, key.name());
        Optional<Vertex> referencedVertex = MoreIterables.stream(referencedVertices).findAny();

        return referencedVertex.map(v -> StringId.from(v.getId()));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureKey key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        Vertex vertex = getOrCreate(key.id());

        Iterable<Edge> referenceEdges = vertex.getEdges(Direction.OUT, key.name());
        Optional<Edge> referenceEdge = MoreIterables.stream(referenceEdges).findAny();

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
    public void unsetReference(SingleFeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return;
        }

        Iterable<Edge> referenceEdges = vertex.get().getEdges(Direction.OUT, key.name());
        Optional<Edge> referenceEdge = MoreIterables.stream(referenceEdges).findAny();

        referenceEdge.ifPresent(Element::remove);
    }

    @Override
    public boolean hasReference(SingleFeatureKey key) {
        checkNotNull(key);

        return get(key.id())
                .map(v -> v.getVertices(Direction.OUT, key.name()))
                .map(MoreIterables::notEmpty)
                .orElse(false);
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        checkNotNull(key);

        return get(key.id()).map(v -> v.<V>getProperty(formatProperty(key.name(), key.position())));
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(SingleFeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        //noinspection OptionalIsPresent
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
        vertex.get().<V>setProperty(formatProperty(key.name(), key.position()), value);
        return previousValue;
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        int size = sizeOfValue(key.withoutPosition()).orElse(0);

        Vertex vertex = getOrCreate(key.id());

        if (key.position() >= size || nonNull(vertex.<V>getProperty(formatProperty(key.name(), key.position())))) {
            for (int i = size; i > key.position(); i--) {
                V movedValue = vertex.<V>getProperty(formatProperty(key.name(), i - 1));
                vertex.<V>setProperty(formatProperty(key.name(), i), movedValue);
            }
        }

        vertex.<V>setProperty(formatProperty(key.name(), key.position()), value);

        sizeForValue(key.withoutPosition(), size + 1);
    }

    @Override
    public <V> void addAllValues(ManyFeatureKey key, List<? extends V> collection) {
        checkNotNull(key);
        checkNotNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        int size = sizeOfValue(key.withoutPosition()).orElse(0);

        Vertex vertex = getOrCreate(key.id());

        if (key.position() >= size || nonNull(vertex.<V>getProperty(formatProperty(key.name(), key.position())))) {
            for (int i = size + collection.size(); i > key.position(); i--) {
                V movedValue = vertex.<V>getProperty(formatProperty(key.name(), i - collection.size()));
                if (nonNull(movedValue)) {
                    vertex.<V>setProperty(formatProperty(key.name(), i), movedValue);
                }
            }
        }

        int i = 0;
        for (V value : collection) {
            vertex.<V>setProperty(formatProperty(key.name(), key.position() + i), value);
            i++;
        }

        sizeForValue(key.withoutPosition(), size + collection.size());
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());
        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOfValue(key.withoutPosition()).orElse(0);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<V> previousValue = Optional.ofNullable(vertex.get().<V>getProperty(formatProperty(key.name(), key.position())));

        for (int i = key.position(); i < size - 1; i++) {
            vertex.get().<V>setProperty(formatProperty(key.name(), i), vertex.get().<V>getProperty(formatProperty(key.name(), i + 1)));
        }

        vertex.get().<V>removeProperty(formatProperty(key.name(), size - 1));

        sizeForValue(key.withoutPosition(), size - 1);

        return previousValue;
    }

    @Override
    public <V> void removeAllValues(SingleFeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return;
        }

        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> vertex.get().<V>removeProperty(formatProperty(key.name(), i)));

        sizeForValue(key, 0);
    }

    @Override
    public <V> boolean containsValue(SingleFeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return false;
        }

        checkNotNull(key);

        return get(key.id()).map(v -> IntStream.range(0, sizeOfValue(key).orElse(0))
                .anyMatch(i -> Objects.equals(v.<V>getProperty(formatProperty(key.name(), i)), value)))
                .orElse(false);
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> indexOfValue(SingleFeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOfValue(key).orElse(0);
        for (int i = 0; i < size; i++) {
            if (Objects.equals(vertex.get().<V>getProperty(formatProperty(key.name(), i)), value)) {
                return Optional.of(i);
            }
        }

        return Optional.empty();
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOfValue(key).orElse(0);
        for (int i = size - 1; i > 0; i--) {
            if (Objects.equals(vertex.get().<V>getProperty(formatProperty(key.name(), i)), value)) {
                return Optional.of(i);
            }
        }

        return Optional.empty();
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureKey key) {
        checkNotNull(key);

        return get(key.id())
                .map(v -> v.<Integer>getProperty(formatProperty(key.name(), KEY_SIZE)))
                .filter(s -> s != 0);
    }

    /**
     * Defines the number of value of the specified {@code key}.
     *
     * @param key  the key identifying the multi-valued attribute
     * @param size the number of value
     * @param <V>  the type of value
     */
    protected <V> void sizeForValue(SingleFeatureKey key, @Nonnegative int size) {
        checkNotNull(key);
        checkArgument(size >= 0);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return;
        }

        if (size > 0) {
            vertex.get().<Integer>setProperty(formatProperty(key.name(), KEY_SIZE), size);
        }
        else {
            vertex.get().<Integer>removeProperty(formatProperty(key.name(), KEY_SIZE));
        }
    }

    //endregion

    //region Multi-valued references

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
                .limit(1)
                .vertices();

        return MoreIterables.stream(referencedVertices)
                .findAny()
                .map(v -> StringId.from(v.getId()));
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(SingleFeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return Collections.emptyList();
        }

        Comparator<Edge> byPosition = Comparator.comparingInt(e -> e.<Integer>getProperty(KEY_POSITION));

        Iterable<Edge> edges = vertex.get().query()
                .labels(key.name())
                .direction(Direction.OUT)
                .edges();

        return MoreIterables.stream(edges)
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

        Optional<Edge> previousEdge = MoreIterables.stream(edges).findAny();

        Optional<Id> previousId = Optional.empty();
        if (previousEdge.isPresent()) {
            Vertex referencedVertex = previousEdge.get().getVertex(Direction.IN);
            previousId = Optional.of(StringId.from(referencedVertex.getId()));
            previousEdge.get().remove();
        }

        Edge edge = vertex.get().addEdge(key.name(), getOrCreate(reference));
        edge.<Integer>setProperty(KEY_POSITION, key.position());

        return previousId;
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        int size = sizeOfReference(key.withoutPosition()).orElse(0);

        Vertex vertex = getOrCreate(key.id());

        boolean alreadyExists = MoreIterables.notEmpty(vertex.query()
                .labels(key.name())
                .direction(Direction.OUT)
                .has(KEY_POSITION, key.position())
                .edges());

        if (key.position() >= size || alreadyExists) {
            if (key.position() != size) {
                Iterable<Edge> edges = vertex.query()
                        .labels(key.name())
                        .direction(Direction.OUT)
                        .interval(KEY_POSITION, key.position(), size + 1)
                        .edges();

                edges.forEach(e -> e.<Integer>setProperty(KEY_POSITION, e.<Integer>getProperty(KEY_POSITION) + 1));
            }
        }

        Edge edge = vertex.addEdge(key.name(), getOrCreate(reference));
        edge.<Integer>setProperty(KEY_POSITION, key.position());

        sizeForReference(key.withoutPosition(), size + 1);
    }

    @Override
    public void addAllReferences(ManyFeatureKey key, List<Id> collection) {
        checkNotNull(key);
        checkNotNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        int size = sizeOfReference(key.withoutPosition()).orElse(0);

        Vertex vertex = getOrCreate(key.id());

        boolean alreadyExists = MoreIterables.notEmpty(vertex.query()
                .labels(key.name())
                .direction(Direction.OUT)
                .has(KEY_POSITION, key.position())
                .edges());

        if (key.position() >= size || alreadyExists) {
            if (key.position() != size) {
                Iterable<Edge> edges = vertex.query()
                        .labels(key.name())
                        .direction(Direction.OUT)
                        .interval(KEY_POSITION, key.position(), size + collection.size())
                        .edges();

                edges.forEach(e -> e.<Integer>setProperty(KEY_POSITION, e.<Integer>getProperty(KEY_POSITION) + collection.size()));
            }
        }

        int i = 0;
        for (Id reference : collection) {
            Edge edge = vertex.addEdge(key.name(), getOrCreate(reference));
            edge.<Integer>setProperty(KEY_POSITION, key.position() + i);
            i++;
        }

        sizeForReference(key.withoutPosition(), size + collection.size());
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());
        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOfReference(key.withoutPosition()).orElse(0);
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
            int position = edge.<Integer>getProperty(KEY_POSITION);

            if (position != key.position()) {
                edge.<Integer>setProperty(KEY_POSITION, position - 1);
            }
            else {
                Vertex referencedVertex = edge.getVertex(Direction.IN);
                previousId = Optional.of(StringId.from(referencedVertex.getId()));
                edge.remove();

                referencedVertex.getEdges(Direction.OUT, KEY_CONTAINER).forEach(Element::remove);
            }
        }

        sizeForReference(key.withoutPosition(), size - 1);

        return previousId;
    }

    @Override
    public void removeAllReferences(SingleFeatureKey key) {
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

        sizeForReference(key, 0);
    }

    @Override
    public boolean containsReference(SingleFeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return false;
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());

        if (!vertex.isPresent()) {
            return false;
        }

        Iterable<Vertex> referencedVertices = vertex.get().getVertices(Direction.OUT, key.name());

        return MoreIterables.stream(referencedVertices)
                .anyMatch(v -> Objects.equals(v.getId().toString(), reference.toString()));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> indexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());
        Optional<Vertex> referencedVertex = get(reference);

        if (!vertex.isPresent() || !referencedVertex.isPresent()) {
            return Optional.empty();
        }

        // TODO Don't browse all vertices/edges
        return MoreIterables.stream(referencedVertex.get().getEdges(Direction.IN, key.name()))
                .filter(e -> Objects.equals(e.getVertex(Direction.OUT), vertex.get()))
                .mapToInt(e -> e.<Integer>getProperty(KEY_POSITION))
                .boxed()
                .min(Comparator.comparingInt(i -> i));
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> lastIndexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.id());
        Optional<Vertex> referencedVertex = get(reference);

        if (!vertex.isPresent() || !referencedVertex.isPresent()) {
            return Optional.empty();
        }

        // TODO Don't browse all vertices/edges
        return MoreIterables.stream(referencedVertex.get().getEdges(Direction.IN, key.name()))
                .filter(e -> Objects.equals(e.getVertex(Direction.OUT), vertex.get()))
                .mapToInt(e -> e.<Integer>getProperty(KEY_POSITION))
                .boxed()
                .max(Comparator.comparingInt(i -> i));
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureKey key) {
        return sizeOfValue(key);
    }

    /**
     * Defines the number of reference of the specified {@code key}.
     *
     * @param key  the key identifying the multi-valued attribute
     * @param size the number of reference
     */
    protected void sizeForReference(SingleFeatureKey key, @Nonnegative int size) {
        sizeForValue(key, size);
    }

    //endregion
}
