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

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

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

        return get(key.owner()).map(v -> v.<V>getProperty(formatLabel(key.id())));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Vertex vertex = getOrCreate(key.owner());

        Optional<V> previousValue = Optional.ofNullable(vertex.<V>getProperty(formatLabel(key.id())));
        vertex.<V>setProperty(formatLabel(key.id()), value);

        return previousValue;
    }

    @Override
    public <V> void unsetValue(SingleFeatureBean key) {
        checkNotNull(key);

        get(key.owner()).ifPresent(v -> v.<V>removeProperty(formatLabel(key.id())));
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

        Iterable<Vertex> referencedVertices = vertex.get().getVertices(Direction.OUT, formatLabel(key.id()));
        Optional<Vertex> referencedVertex = MoreIterables.onlyElement(referencedVertices);

        return referencedVertex.map(v -> StringId.from(v.getId()));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        Vertex vertex = getOrCreate(key.owner());

        Iterable<Edge> referenceEdges = vertex.getEdges(Direction.OUT, formatLabel(key.id()));
        Optional<Edge> referenceEdge = MoreIterables.onlyElement(referenceEdges);

        Optional<Id> previousId = Optional.empty();
        if (referenceEdge.isPresent()) {
            Vertex previousVertex = referenceEdge.get().getVertex(Direction.IN);
            previousId = Optional.of(StringId.from(previousVertex.getId()));
            referenceEdge.get().remove();
        }

        Vertex referencedVertex = getOrCreate(reference);

        vertex.addEdge(formatLabel(key.id()), referencedVertex);

        return previousId;
    }

    @Override
    public void unsetReference(SingleFeatureBean key) {
        checkNotNull(key);

        get(key.owner())
                .map(v -> v.getEdges(Direction.OUT, formatLabel(key.id())))
                .ifPresent(v -> v.forEach(Element::remove));
    }

    @Override
    public boolean hasReference(SingleFeatureBean key) {
        checkNotNull(key);

        return get(key.owner())
                .map(v -> v.getVertices(Direction.OUT, formatLabel(key.id())))
                .map(MoreIterables::notEmpty)
                .orElse(false);
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key);

        return get(key.owner()).map(v -> v.<V>getProperty(formatProperty(key.id(), key.position())));
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(SingleFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return Collections.emptyList();
        }

        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .mapToObj(i -> vertex.get().<V>getProperty(formatProperty(key.id(), i)))
                .collect(Collectors.toList());
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
        vertex.get().<V>setProperty(formatProperty(key.id(), key.position()), value);

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
            V movedValue = vertex.<V>getProperty(formatProperty(key.id(), i));
            vertex.<V>setProperty(formatProperty(key.id(), i + 1), movedValue);
        }

        vertex.<V>setProperty(formatProperty(key.id(), key.position()), value);

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

        for (int i = size - 1; i >= key.position(); i--) {
            V movedValue = vertex.<V>getProperty(formatProperty(key.id(), i));
            vertex.<V>setProperty(formatProperty(key.id(), i + collection.size()), movedValue);
        }

        int i = 0;
        for (V value : collection) {
            vertex.<V>setProperty(formatProperty(key.id(), key.position() + i), value);
            i++;
        }

        sizeForValue(key.withoutPosition(), size + collection.size());
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

        Optional<V> previousValue = Optional.ofNullable(vertex.get().<V>getProperty(formatProperty(key.id(), key.position())));

        for (int i = key.position(); i < size - 1; i++) {
            vertex.get().<V>setProperty(formatProperty(key.id(), i), vertex.get().<V>getProperty(formatProperty(key.id(), i + 1)));
        }

        vertex.get().<V>removeProperty(formatProperty(key.id(), size - 1));

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
                .forEach(i -> vertex.get().<V>removeProperty(formatProperty(key.id(), i)));

        sizeForValue(key, 0);
    }

    @Nonnull
    @Override
    public <V> Optional<Integer> indexOfValue(SingleFeatureBean key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOfValue(key).orElse(0);
        for (int i = 0; i < size; i++) {
            if (Objects.equals(vertex.get().<V>getProperty(formatProperty(key.id(), i)), value)) {
                return Optional.of(i);
            }
        }

        return Optional.empty();
    }

    @Nonnull
    @Override
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureBean key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOfValue(key).orElse(0);
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(vertex.get().<V>getProperty(formatProperty(key.id(), i)), value)) {
                return Optional.of(i);
            }
        }

        return Optional.empty();
    }

    @Nonnull
    @Nonnegative
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key);

        return get(key.owner())
                .map(v -> v.<Integer>getProperty(formatProperty(key.id(), KEY_SIZE)))
                .filter(s -> s != 0);
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
            vertex.get().<Integer>setProperty(formatProperty(key.id(), KEY_SIZE), size);
        }
        else {
            vertex.get().<Integer>removeProperty(formatProperty(key.id(), KEY_SIZE));
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
                .labels(formatLabel(key.id()))
                .direction(Direction.OUT)
                .has(KEY_POSITION, key.position())
                .vertices();

        return MoreIterables.onlyElement(referencedVertices)
                .map(v -> StringId.from(v.getId()));
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(SingleFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return Collections.emptyList();
        }

        Comparator<Edge> byPosition = Comparator.comparingInt(e -> e.<Integer>getProperty(KEY_POSITION));

        Iterable<Edge> edges = vertex.get().getEdges(Direction.OUT, formatLabel(key.id()));

        return MoreIterables.stream(edges)
                .sorted(byPosition)
                .map(e -> StringId.from(e.getVertex(Direction.IN).getId()))
                .collect(Collectors.toList());
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
                .labels(formatLabel(key.id()))
                .direction(Direction.OUT)
                .has(KEY_POSITION, key.position())
                .edges();

        Optional<Edge> previousEdge = MoreIterables.onlyElement(edges);

        Optional<Id> previousId = Optional.empty();
        if (previousEdge.isPresent()) {
            Vertex referencedVertex = previousEdge.get().getVertex(Direction.IN);
            previousId = Optional.of(StringId.from(referencedVertex.getId()));
            previousEdge.get().remove();
        }

        Vertex referencedVertex = getOrCreate(reference);

        Edge edge = vertex.get().addEdge(formatLabel(key.id()), referencedVertex);
        edge.<Integer>setProperty(KEY_POSITION, key.position());

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
            Iterable<Edge> edges = vertex.query()
                    .labels(formatLabel(key.id()))
                    .direction(Direction.OUT)
                    .interval(KEY_POSITION, key.position(), size + 1)
                    .edges();

            edges.forEach(e -> e.<Integer>setProperty(KEY_POSITION, e.<Integer>getProperty(KEY_POSITION) + 1));
        }

        Vertex referencedVertex = getOrCreate(reference);

        Edge edge = vertex.addEdge(formatLabel(key.id()), referencedVertex);
        edge.<Integer>setProperty(KEY_POSITION, key.position());

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

        if (key.position() < size) {
            Iterable<Edge> edges = vertex.query()
                    .labels(formatLabel(key.id()))
                    .direction(Direction.OUT)
                    .interval(KEY_POSITION, key.position(), size + collection.size())
                    .edges();

            edges.forEach(e -> e.<Integer>setProperty(KEY_POSITION, e.<Integer>getProperty(KEY_POSITION) + collection.size()));
        }

        int i = 0;
        for (Id reference : collection) {
            Vertex referencedVertex = getOrCreate(reference);

            Edge edge = vertex.addEdge(formatLabel(key.id()), referencedVertex);
            edge.<Integer>setProperty(KEY_POSITION, key.position() + i);
            i++;
        }

        sizeForReference(key.withoutPosition(), size + collection.size());
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

        Iterable<Edge> edges = vertex.get().query()
                .labels(formatLabel(key.id()))
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
    public void removeAllReferences(SingleFeatureBean key) {
        checkNotNull(key);

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return;
        }

        vertex.get().getEdges(Direction.OUT, formatLabel(key.id())).forEach(Element::remove);

        sizeForReference(key, 0);
    }

    @Nonnull
    @Override
    public Optional<Integer> indexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        checkNotNull(key);

        Optional<Vertex> referencedVertex = get(reference);

        if (!referencedVertex.isPresent()) {
            return Optional.empty();
        }

        int index = allReferencesOf(key).indexOf(reference);
        return index >= 0 ? Optional.of(index) : Optional.empty();
    }

    @Nonnull
    @Override
    public Optional<Integer> lastIndexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        checkNotNull(key);

        Optional<Vertex> referencedVertex = get(reference);

        if (!referencedVertex.isPresent()) {
            return Optional.empty();
        }

        int lastIndex = allReferencesOf(key).lastIndexOf(reference);
        return lastIndex >= 0 ? Optional.of(lastIndex) : Optional.empty();
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
