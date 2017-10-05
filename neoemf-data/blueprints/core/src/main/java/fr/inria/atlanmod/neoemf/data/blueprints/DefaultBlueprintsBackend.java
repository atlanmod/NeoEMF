/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;

/**
 * The default {@link BlueprintsBackend} mapping.
 *
 * @see BlueprintsBackendFactory
 */
@ParametersAreNonnullByDefault
class DefaultBlueprintsBackend extends AbstractBlueprintsBackend {

    /**
     * Constructs a new {@code DefaultBlueprintsBackend} wrapping the provided {@code baseGraph}.
     *
     * @param baseGraph the base {@link KeyIndexableGraph} used to access the database
     *
     * @see BlueprintsBackendFactory
     */
    protected DefaultBlueprintsBackend(KeyIndexableGraph baseGraph) {
        super(baseGraph);
    }

    //region Single-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key);

        return get(key.owner()).map(v -> v.<V>getProperty(formatLabel(key)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Vertex vertex = getOrCreate(key.owner());

        Optional<V> previousValue = Optional.ofNullable(vertex.<V>getProperty(formatLabel(key)));
        vertex.<V>setProperty(formatLabel(key), value);

        return previousValue;
    }

    @Override
    public <V> void removeValue(SingleFeatureBean key) {
        checkNotNull(key);

        get(key.owner()).ifPresent(v -> v.<V>removeProperty(formatLabel(key)));
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        Iterable<Vertex> referencedVertices = vertex.get().query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .limit(1)
                .vertices();

        return MoreIterables.onlyElement(referencedVertices)
                .map(v -> AS_LONG_OBJECT.revert(v.getId()));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        Vertex vertex = getOrCreate(key.owner());

        Iterable<Edge> referenceEdges = vertex.query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .limit(1)
                .edges();

        Optional<Edge> referenceEdge = MoreIterables.onlyElement(referenceEdges);

        Optional<Id> previousId = Optional.empty();
        if (referenceEdge.isPresent()) {
            Vertex previousVertex = referenceEdge.get().getVertex(Direction.IN);
            previousId = Optional.of(AS_LONG_OBJECT.revert(previousVertex.getId()));
            referenceEdge.get().remove();
        }

        Vertex referencedVertex = getOrCreate(reference);
        vertex.addEdge(formatLabel(key), referencedVertex);

        return previousId;
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return;
        }

        Iterable<Edge> edges = vertex.get().query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .limit(1)
                .edges();

        edges.forEach(Edge::remove);
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key);

        return get(key.owner()).map(v -> v.<V>getProperty(formatProperty(key, key.position())));
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return Stream.empty();
        }

        final Iterator<V> iter = new Iterator<V>() {

            /**
             * The size of the iterator.
             */
            @Nonnegative
            final int size = sizeOfValue(key).orElse(0);

            /**
             * The current position.
             */
            @Nonnegative
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public V next() {
                return vertex.get().getProperty(formatProperty(key, currentIndex++));
            }
        };

        return MoreIterables.stream(() -> iter);
    }

    @Nonnull
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            throw new NoSuchElementException();
        }

        Optional<V> previousValue = valueOf(key);

        if (!previousValue.isPresent()) {
            throw new NoSuchElementException();
        }

        vertex.get().<V>setProperty(formatProperty(key, key.position()), value);

        return previousValue;
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        int size = sizeOfValue(key.withoutPosition()).orElse(0);
        checkPositionIndex(key.position(), size);

        Vertex vertex = getOrCreate(key.owner());

        for (int i = size - 1; i >= key.position(); i--) {
            V movedValue = vertex.<V>getProperty(formatProperty(key, i));
            vertex.<V>setProperty(formatProperty(key, i + 1), movedValue);
        }

        vertex.<V>setProperty(formatProperty(key, key.position()), value);

        sizeForValue(key.withoutPosition(), size + 1);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key);
        checkNotNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        int size = sizeOfValue(key.withoutPosition()).orElse(0);
        checkPositionIndex(key.position(), size);

        Vertex vertex = getOrCreate(key.owner());
        int additionCount = collection.size();

        for (int i = size - 1; i >= key.position(); i--) {
            V movedValue = vertex.<V>getProperty(formatProperty(key, i));
            vertex.<V>setProperty(formatProperty(key, i + additionCount), movedValue);
        }

        int i = 0;
        for (V value : collection) {
            vertex.<V>setProperty(formatProperty(key, key.position() + i), value);
            i++;
        }

        sizeForValue(key.withoutPosition(), size + additionCount);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());
        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOfValue(key.withoutPosition()).orElse(0);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<V> previousValue = Optional.ofNullable(vertex.get().<V>getProperty(formatProperty(key, key.position())));

        for (int i = key.position(); i < size - 1; i++) {
            V movedValue = vertex.get().<V>getProperty(formatProperty(key, i + 1));
            vertex.get().<V>setProperty(formatProperty(key, i), movedValue);
        }

        vertex.get().<V>removeProperty(formatProperty(key, size - 1));

        sizeForValue(key.withoutPosition(), size - 1);

        return previousValue;
    }

    @Override
    public <V> void removeAllValues(SingleFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return;
        }

        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> vertex.get().<V>removeProperty(formatProperty(key, i)));

        sizeForValue(key, 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key);

        return get(key.owner())
                .map(v -> v.<Integer>getProperty(formatProperty(key, PROPERTY_SIZE)))
                .filter(s -> s > 0);
    }

    /**
     * Defines the number of value of the specified {@code key}.
     *
     * @param key  the key identifying the multi-valued attribute
     * @param size the number of value
     * @param <V>  the type of value
     */
    protected <V> void sizeForValue(SingleFeatureBean key, @Nonnegative int size) {
        checkNotNull(key);
        checkArgument(size >= 0);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return;
        }

        if (size > 0) {
            vertex.get().<Integer>setProperty(formatProperty(key, PROPERTY_SIZE), size);
        }
        else {
            vertex.get().<Integer>removeProperty(formatProperty(key, PROPERTY_SIZE));
        }
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        Iterable<Vertex> referencedVertices = vertex.get().query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .has(PROPERTY_INDEX, key.position())
                .limit(1)
                .vertices();

        return MoreIterables.onlyElement(referencedVertices)
                .map(v -> AS_LONG_OBJECT.revert(v.getId()));
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return Stream.empty();
        }

        Comparator<Edge> byPosition = Comparator.comparingInt(e -> e.<Integer>getProperty(PROPERTY_INDEX));

        Iterable<Edge> edges = vertex.get().query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .edges();

        Iterator<Edge> sortedEdges = MoreIterables.stream(edges)
                .sorted(byPosition)
                .iterator();

        final Iterator<Id> iter = new Iterator<Id>() {

            @Override
            public boolean hasNext() {
                return sortedEdges.hasNext();
            }

            @Override
            public Id next() {
                return AS_LONG_OBJECT.revert(sortedEdges.next().getVertex(Direction.IN).getId());
            }
        };

        return MoreIterables.stream(() -> iter);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            throw new NoSuchElementException();
        }

        Iterable<Edge> edges = vertex.get().query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .has(PROPERTY_INDEX, key.position())
                .limit(1)
                .edges();

        Optional<Edge> previousEdge = MoreIterables.onlyElement(edges);

        if (!previousEdge.isPresent()) {
            throw new NoSuchElementException();
        }

        Vertex previousReferencedVertex = previousEdge.get().getVertex(Direction.IN);
        Optional<Id> previousId = Optional.of(AS_LONG_OBJECT.revert(previousReferencedVertex.getId()));
        previousEdge.get().remove();

        Vertex referencedVertex = getOrCreate(reference);

        Edge edge = vertex.get().addEdge(formatLabel(key), referencedVertex);
        edge.<Integer>setProperty(PROPERTY_INDEX, key.position());

        return previousId;
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        int size = sizeOfReference(key.withoutPosition()).orElse(0);
        checkPositionIndex(key.position(), size);

        Vertex vertex = getOrCreate(key.owner());

        if (key.position() < size) {
            int interval = size + 1 - key.position();

            Iterable<Edge> edges = vertex.query()
                    .labels(formatLabel(key))
                    .direction(Direction.OUT)
                    .interval(PROPERTY_INDEX, key.position(), size + 1)
                    .limit(interval)
                    .edges();

            edges.forEach(e -> {
                int position = e.<Integer>getProperty(PROPERTY_INDEX);
                e.<Integer>setProperty(PROPERTY_INDEX, position + 1);
            });
        }

        Vertex referencedVertex = getOrCreate(reference);

        Edge edge = vertex.addEdge(formatLabel(key), referencedVertex);
        edge.<Integer>setProperty(PROPERTY_INDEX, key.position());

        sizeForReference(key.withoutPosition(), size + 1);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        checkNotNull(key);
        checkNotNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        int size = sizeOfReference(key.withoutPosition()).orElse(0);
        checkPositionIndex(key.position(), size);

        Vertex vertex = getOrCreate(key.owner());
        int additionCount = collection.size();

        if (key.position() < size) {
            int interval = size + additionCount - key.position();

            Iterable<Edge> edges = vertex.query()
                    .labels(formatLabel(key))
                    .direction(Direction.OUT)
                    .interval(PROPERTY_INDEX, key.position(), size + additionCount)
                    .limit(interval)
                    .edges();

            edges.forEach(e -> {
                int position = e.<Integer>getProperty(PROPERTY_INDEX);
                e.<Integer>setProperty(PROPERTY_INDEX, position + additionCount);
            });
        }

        int i = 0;
        for (Id reference : collection) {
            Vertex referencedVertex = getOrCreate(reference);

            Edge edge = vertex.addEdge(formatLabel(key), referencedVertex);
            edge.<Integer>setProperty(PROPERTY_INDEX, key.position() + i);
            i++;
        }

        sizeForReference(key.withoutPosition(), size + additionCount);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());
        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOfReference(key.withoutPosition()).orElse(0);
        if (size == 0) {
            return Optional.empty();
        }

        int interval = size - key.position();

        Iterable<Edge> edges = vertex.get().query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .interval(PROPERTY_INDEX, key.position(), size)
                .limit(interval)
                .edges();

        Optional<Id> previousId = Optional.empty();
        for (Edge edge : edges) {
            int position = edge.<Integer>getProperty(PROPERTY_INDEX);

            if (position != key.position()) {
                edge.<Integer>setProperty(PROPERTY_INDEX, position - 1);
            }
            else {
                Vertex referencedVertex = edge.getVertex(Direction.IN);
                previousId = Optional.of(AS_LONG_OBJECT.revert(referencedVertex.getId()));
                edge.remove();
            }
        }

        sizeForReference(key.withoutPosition(), size - 1);

        return previousId;
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return;
        }

        Iterable<Edge> edges = vertex.get().query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .edges();

        edges.forEach(Element::remove);

        sizeForReference(key, 0);
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return sizeOfValue(key);
    }

    /**
     * Defines the number of reference of the specified {@code key}.
     *
     * @param key  the key identifying the multi-valued attribute
     * @param size the number of reference
     */
    protected void sizeForReference(SingleFeatureBean key, @Nonnegative int size) {
        sizeForValue(key, size);
    }

    //endregion
}
