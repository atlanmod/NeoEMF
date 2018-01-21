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
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.commons.collect.DelegatedIterator;
import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.collect.SizedIterator;
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

import static fr.inria.atlanmod.commons.Preconditions.checkNotContainsNull;
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
        checkNotNull(key, "key");

        return get(key.owner()).map(v -> v.<V>getProperty(formatLabel(key)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Vertex vertex = getOrCreate(key.owner());

        Optional<V> previousValue = Optional.ofNullable(vertex.<V>getProperty(formatLabel(key)));
        vertex.<V>setProperty(formatLabel(key), value);

        return previousValue;
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        get(key.owner()).ifPresent(v -> v.removeProperty(formatLabel(key)));
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> optVertex = get(key.owner());
        if (!optVertex.isPresent()) {
            return Optional.empty();
        }

        final Vertex vertex = optVertex.get();

        return MoreIterables.onlyElement(vertex.getVertices(Direction.OUT, formatLabel(key)))
                .map(v -> idConverter.revert(v.getId()));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Vertex vertex = getOrCreate(key.owner());

        Optional<Edge> referenceEdge = MoreIterables.onlyElement(vertex.getEdges(Direction.OUT, formatLabel(key)));

        Optional<Id> previousId = Optional.empty();
        if (referenceEdge.isPresent()) {
            Vertex previousVertex = referenceEdge.get().getVertex(Direction.IN);
            previousId = Optional.of(idConverter.revert(previousVertex.getId()));
            referenceEdge.get().remove();
        }

        vertex.addEdge(formatLabel(key), getOrCreate(reference));

        return previousId;
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> optVertex = get(key.owner());
        if (!optVertex.isPresent()) {
            return;
        }

        final Vertex vertex = optVertex.get();

        vertex.getEdges(Direction.OUT, formatLabel(key)).forEach(Edge::remove);
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        return get(key.owner()).map(v -> v.<V>getProperty(formatProperty(key, key.position())));
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> optVertex = get(key.owner());
        if (!optVertex.isPresent()) {
            return Stream.empty();
        }

        final Vertex vertex = optVertex.get();

        final int size = sizeOf(key, vertex);
        final Iterator<V> iter = new SizedIterator<>(size, i -> vertex.getProperty(formatProperty(key, i)));

        return MoreIterables.stream(() -> iter);
    }

    @Nonnull
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Vertex vertex = get(key.owner()).<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = valueOf(key);
        if (!previousValue.isPresent()) {
            throw new NoSuchElementException();
        }

        vertex.<V>setProperty(formatProperty(key, key.position()), value);

        return previousValue;
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Vertex vertex = getOrCreate(key.owner());

        final int firstPosition = key.position();
        final int size = sizeOf(key.withoutPosition(), vertex);
        checkPositionIndex(firstPosition, size);

        for (int i = size - 1; i >= firstPosition; i--) {
            V movedValue = vertex.getProperty(formatProperty(key, i));
            vertex.<V>setProperty(formatProperty(key, i + 1), movedValue);
        }

        vertex.<V>setProperty(formatProperty(key, firstPosition), value);

        sizeFor(key.withoutPosition(), vertex, size + 1);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        Vertex vertex = getOrCreate(key.owner());

        final int firstPosition = key.position();
        final int size = sizeOf(key.withoutPosition(), vertex);
        checkPositionIndex(firstPosition, size);

        final int additionCount = collection.size();

        for (int i = size - 1; i >= firstPosition; i--) {
            V movedValue = vertex.getProperty(formatProperty(key, i));
            vertex.<V>setProperty(formatProperty(key, i + additionCount), movedValue);
        }

        for (int i = 0; i < additionCount; i++) {
            vertex.<V>setProperty(formatProperty(key, firstPosition + i), collection.get(i));
        }

        sizeFor(key.withoutPosition(), vertex, size + additionCount);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> optVertex = get(key.owner());
        if (!optVertex.isPresent()) {
            return Optional.empty();
        }

        final Vertex vertex = optVertex.get();

        final int firstPosition = key.position();
        final int size = sizeOf(key.withoutPosition(), vertex);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<V> previousValue = Optional.ofNullable(vertex.<V>getProperty(formatProperty(key, firstPosition)));

        for (int i = firstPosition; i < size - 1; i++) {
            V movedValue = vertex.getProperty(formatProperty(key, i + 1));
            vertex.<V>setProperty(formatProperty(key, i), movedValue);
        }

        vertex.<V>removeProperty(formatProperty(key, size - 1));

        sizeFor(key.withoutPosition(), vertex, size - 1);

        return previousValue;
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> optVertex = get(key.owner());
        if (!optVertex.isPresent()) {
            return;
        }

        final Vertex vertex = optVertex.get();

        IntStream.range(0, sizeOf(key, vertex)).forEach(i -> vertex.removeProperty(formatProperty(key, i)));

        sizeFor(key, vertex, 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        return get(key.owner())
                .map(v -> sizeOf(key, v))
                .filter(s -> s > 0);
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> optVertex = get(key.owner());
        if (!optVertex.isPresent()) {
            return Optional.empty();
        }

        final Vertex vertex = optVertex.get();

        Iterable<Vertex> referencedVertices = vertex.query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .has(PROPERTY_INDEX, key.position())
                .vertices();

        return MoreIterables.onlyElement(referencedVertices)
                .map(v -> idConverter.revert(v.getId()));
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> optVertex = get(key.owner());
        if (!optVertex.isPresent()) {
            return Stream.empty();
        }

        final Vertex vertex = optVertex.get();

        final Iterator<Edge> sortedEdges = MoreIterables.stream(vertex.getEdges(Direction.OUT, formatLabel(key)))
                .sorted(Comparator.comparingInt(e -> e.getProperty(PROPERTY_INDEX)))
                .iterator();

        final Iterator<Id> iter = new DelegatedIterator<>(sortedEdges, e -> idConverter.revert(e.getVertex(Direction.IN).getId()));

        return MoreIterables.stream(() -> iter);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Vertex vertex = get(key.owner()).<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Iterable<Edge> edges = vertex.query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .has(PROPERTY_INDEX, key.position())
                .edges();

        Edge previousEdge = MoreIterables.onlyElement(edges).<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Vertex previousReferencedVertex = previousEdge.getVertex(Direction.IN);
        Optional<Id> previousId = Optional.of(idConverter.revert(previousReferencedVertex.getId()));
        previousEdge.remove();

        vertex.addEdge(formatLabel(key), getOrCreate(reference)).setProperty(PROPERTY_INDEX, key.position());

        return previousId;
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Vertex vertex = getOrCreate(key.owner());

        final int firstPosition = key.position();
        final int size = sizeOf(key.withoutPosition(), vertex);
        checkPositionIndex(firstPosition, size);

        if (firstPosition < size) {
            vertex.query()
                    .labels(formatLabel(key))
                    .direction(Direction.OUT)
                    .interval(PROPERTY_INDEX, firstPosition, size + 1)
                    .limit(size + 1 - firstPosition)
                    .edges()
                    .forEach(e -> e.setProperty(PROPERTY_INDEX, e.<Integer>getProperty(PROPERTY_INDEX) + 1));
        }

        vertex.addEdge(formatLabel(key), getOrCreate(reference)).setProperty(PROPERTY_INDEX, firstPosition);

        sizeFor(key.withoutPosition(), vertex, size + 1);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        Vertex vertex = getOrCreate(key.owner());

        final int firstPosition = key.position();
        final int size = sizeOf(key.withoutPosition(), vertex);
        checkPositionIndex(firstPosition, size);

        final int additionCount = collection.size();

        if (firstPosition < size) {
            vertex.query()
                    .labels(formatLabel(key))
                    .direction(Direction.OUT)
                    .interval(PROPERTY_INDEX, firstPosition, size + additionCount)
                    .limit(size + additionCount - firstPosition)
                    .edges()
                    .forEach(e -> e.setProperty(PROPERTY_INDEX, e.<Integer>getProperty(PROPERTY_INDEX) + additionCount));
        }

        for (int i = 0; i < additionCount; i++) {
            vertex.addEdge(formatLabel(key), getOrCreate(collection.get(i))).setProperty(PROPERTY_INDEX, firstPosition + i);
        }

        sizeFor(key.withoutPosition(), vertex, size + additionCount);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> optVertex = get(key.owner());
        if (!optVertex.isPresent()) {
            return Optional.empty();
        }

        final Vertex vertex = optVertex.get();

        final int firstPosition = key.position();
        final int size = sizeOf(key.withoutPosition(), vertex);
        if (size == 0) {
            return Optional.empty();
        }

        Iterable<Edge> edges = vertex.query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .interval(PROPERTY_INDEX, firstPosition, size)
                .limit(size - firstPosition)
                .edges();

        Optional<Id> previousId = Optional.empty();
        for (Edge edge : edges) {
            int position = edge.getProperty(PROPERTY_INDEX);

            if (position != firstPosition) {
                edge.setProperty(PROPERTY_INDEX, position - 1);
            }
            else {
                Vertex referencedVertex = edge.getVertex(Direction.IN);
                previousId = Optional.of(idConverter.revert(referencedVertex.getId()));
                edge.remove();
            }
        }

        sizeFor(key.withoutPosition(), vertex, size - 1);

        return previousId;
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> optVertex = get(key.owner());
        if (!optVertex.isPresent()) {
            return;
        }

        final Vertex vertex = optVertex.get();

        vertex.getEdges(Direction.OUT, formatLabel(key)).forEach(Edge::remove);

        sizeFor(key, vertex, 0);
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return sizeOfValue(key);
    }

    //endregion
}
