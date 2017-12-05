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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;

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
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Function<Vertex, Optional<V>> mapFunc = v -> Optional.ofNullable(v.getProperty(formatLabel(key)));

        Maybe<V> databaseQuery = asyncGet(key.owner())
                .map(mapFunc)
                .filter(Optional::isPresent)
                .map(Optional::get);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Function<Vertex, Optional<V>> mapFunc = v -> Optional.ofNullable(v.getProperty(formatLabel(key)));

        Action setFunc = () -> getOrCreate(key.owner()).setProperty(formatLabel(key), value);

        Consumer<V> replaceFunc = Functions.actionConsumer(setFunc);

        Maybe<V> databaseQuery = asyncGetOrCreate(key.owner())
                .map(mapFunc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .doOnComplete(setFunc)
                .doOnSuccess(replaceFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Consumer<Vertex> removeFunc = v -> v.removeProperty(formatLabel(key));

        Completable databaseQuery = asyncGet(key.owner())
                .doOnSuccess(removeFunc)
                .ignoreElement();

        return dispatcher().submit(databaseQuery);
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Maybe<Id> referenceOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Function<Vertex, Iterable<Vertex>> getFunc = v -> v
                .getVertices(Direction.OUT, formatLabel(key));

        Maybe<Id> databaseQuery = asyncGet(key.owner())
                .flattenAsFlowable(getFunc)
                .singleElement()
                .map(v -> idConverter.revert(v.getId()));

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Maybe<Id> referenceFor(SingleFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Function<Vertex, Iterable<Edge>> getFunc = v -> v
                .getEdges(Direction.OUT, formatLabel(key));

        Action setFunc = () -> getOrCreate(key.owner())
                .addEdge(formatLabel(key), getOrCreate(reference));

        Consumer<Edge> replaceFunc = e -> {
            e.remove();
            setFunc.run();
        };

        Maybe<Id> databaseQuery = asyncGetOrCreate(key.owner())
                .flattenAsFlowable(getFunc)
                .singleElement()
                .doOnComplete(setFunc)
                .doAfterSuccess(replaceFunc)
                .map(e -> e.getVertex(Direction.IN))
                .map(v -> idConverter.revert(v.getId()));

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Completable removeReference(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Consumer<Vertex> removeFunc = v -> v
                .getEdges(Direction.OUT, formatLabel(key))
                .forEach(Edge::remove);

        Completable databaseQuery = asyncGet(key.owner())
                .doOnSuccess(removeFunc)
                .ignoreElement();

        return dispatcher().submit(databaseQuery);
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Function<Vertex, Optional<V>> getFunc = v -> Optional.ofNullable(v.getProperty(formatProperty(key, key.position())));

        Maybe<V> databaseQuery = asyncGet(key.owner())
                .map(getFunc)
                .filter(Optional::isPresent)
                .map(Optional::get);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public <V> Flowable<V> allValuesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Flowable<V> databaseQuery = asyncGet(key.owner())
                .flattenAsFlowable(v -> IntStream.range(0, sizeOf(key, v))
                        .mapToObj(i -> new AbstractMap.SimpleImmutableEntry<>(v, i))
                        .collect(Collectors.toList()))
                .map(e -> e.getKey().getProperty(formatProperty(key, e.getValue())));

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    public <V> Single<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Function<Vertex, Optional<V>> getFunc = v -> Optional.ofNullable(v.getProperty(formatProperty(key, key.position())));

        Consumer<Vertex> replaceFunc = v -> v.setProperty(formatProperty(key, key.position()), value);

        Single<V> databaseQuery = asyncGet(key.owner())
                .toSingle()
                .doAfterSuccess(replaceFunc)
                .map(getFunc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toSingle();

        return dispatcher().submit(databaseQuery);
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Vertex vertex = getOrCreate(key.owner());

        int size = sizeOf(key.withoutPosition(), vertex);
        checkPositionIndex(key.position(), size);

        for (int i = size - 1; i >= key.position(); i--) {
            V movedValue = vertex.getProperty(formatProperty(key, i));
            vertex.setProperty(formatProperty(key, i + 1), movedValue);
        }

        vertex.setProperty(formatProperty(key, key.position()), value);

        sizeFor(key.withoutPosition(), vertex, size + 1);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        Vertex vertex = getOrCreate(key.owner());

        int size = sizeOf(key.withoutPosition(), vertex);
        checkPositionIndex(key.position(), size);

        int additionCount = collection.size();

        for (int i = size - 1; i >= key.position(); i--) {
            V movedValue = vertex.getProperty(formatProperty(key, i));
            vertex.setProperty(formatProperty(key, i + additionCount), movedValue);
        }

        int i = 0;
        for (V value : collection) {
            vertex.setProperty(formatProperty(key, key.position() + i), value);
            i++;
        }

        sizeFor(key.withoutPosition(), vertex, size + additionCount);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> vertex = get(key.owner());
        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOf(key.withoutPosition(), vertex.get());
        if (size == 0) {
            return Optional.empty();
        }

        Optional<V> previousValue = Optional.ofNullable(vertex.get().getProperty(formatProperty(key, key.position())));

        for (int i = key.position(); i < size - 1; i++) {
            V movedValue = vertex.get().getProperty(formatProperty(key, i + 1));
            vertex.get().setProperty(formatProperty(key, i), movedValue);
        }

        vertex.get().<V>removeProperty(formatProperty(key, size - 1));

        sizeFor(key.withoutPosition(), vertex.get(), size - 1);

        return previousValue;
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return;
        }

        IntStream.range(0, sizeOf(key, vertex.get()))
                .forEach(i -> vertex.get().removeProperty(formatProperty(key, i)));

        sizeFor(key, vertex.get(), 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Maybe<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Maybe<Integer> databaseQuery = asyncGet(key.owner())
                .map(v -> sizeOf(key, v))
                .filter(s -> s > 0);

        return dispatcher().submit(databaseQuery);
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Maybe<Id> referenceOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Function<Vertex, Iterable<Vertex>> getFunc = v -> v
                .query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .has(PROPERTY_INDEX, key.position())
                .limit(1)
                .vertices();

        Maybe<Id> databaseFunc = asyncGet(key.owner())
                .flattenAsFlowable(getFunc)
                .singleElement()
                .map(v -> idConverter.revert(v.getId()));

        return dispatcher().submit(databaseFunc);
    }

    @Nonnull
    @Override
    public Flowable<Id> allReferencesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Flowable<Id> databaseQuery = asyncGet(key.owner())
                .flattenAsFlowable(v -> v.getEdges(Direction.OUT, formatLabel(key)))
                .sorted(Comparator.comparingInt(e -> e.getProperty(PROPERTY_INDEX)))
                .map(e -> idConverter.revert(e.getVertex(Direction.IN).getId()));

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Single<Id> referenceFor(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Function<Vertex, Iterable<Edge>> getFunc = v -> v.query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .has(PROPERTY_INDEX, key.position())
                .limit(1)
                .edges();

        Consumer<Vertex> replaceFunc = v -> v
                .addEdge(formatLabel(key), getOrCreate(reference))
                .setProperty(PROPERTY_INDEX, key.position());

        Single<Id> databaseFunc = asyncGet(key.owner())
                .toSingle()
                .doAfterSuccess(replaceFunc)
                .flattenAsFlowable(getFunc)
                .singleOrError()
                .doAfterSuccess(Edge::remove)
                .map(e -> e.getVertex(Direction.IN))
                .map(v -> idConverter.revert(v.getId()));

        return dispatcher().submit(databaseFunc);
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Vertex vertex = getOrCreate(key.owner());

        int size = sizeOf(key.withoutPosition(), vertex);
        checkPositionIndex(key.position(), size);

        if (key.position() < size) {
            int interval = size + 1 - key.position();

            Iterable<Edge> edges = vertex.query()
                    .labels(formatLabel(key))
                    .direction(Direction.OUT)
                    .interval(PROPERTY_INDEX, key.position(), size + 1)
                    .limit(interval)
                    .edges();

            edges.forEach(e -> {
                int position = e.getProperty(PROPERTY_INDEX);
                e.setProperty(PROPERTY_INDEX, position + 1);
            });
        }

        Vertex referencedVertex = getOrCreate(reference);

        Edge edge = vertex.addEdge(formatLabel(key), referencedVertex);
        edge.setProperty(PROPERTY_INDEX, key.position());

        sizeFor(key.withoutPosition(), vertex, size + 1);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        Vertex vertex = getOrCreate(key.owner());

        int size = sizeOf(key.withoutPosition(), vertex);
        checkPositionIndex(key.position(), size);

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
                int position = e.getProperty(PROPERTY_INDEX);
                e.setProperty(PROPERTY_INDEX, position + additionCount);
            });
        }

        int i = 0;
        for (Id reference : collection) {
            Vertex referencedVertex = getOrCreate(reference);

            Edge edge = vertex.addEdge(formatLabel(key), referencedVertex);
            edge.setProperty(PROPERTY_INDEX, key.position() + i);
            i++;
        }

        sizeFor(key.withoutPosition(), vertex, size + additionCount);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> vertex = get(key.owner());
        if (!vertex.isPresent()) {
            return Optional.empty();
        }

        int size = sizeOf(key.withoutPosition(), vertex.get());
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
            int position = edge.getProperty(PROPERTY_INDEX);

            if (position != key.position()) {
                edge.setProperty(PROPERTY_INDEX, position - 1);
            }
            else {
                Vertex referencedVertex = edge.getVertex(Direction.IN);
                previousId = Optional.of(idConverter.revert(referencedVertex.getId()));
                edge.remove();
            }
        }

        sizeFor(key.withoutPosition(), vertex.get(), size - 1);

        return previousId;
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Optional<Vertex> vertex = get(key.owner());

        if (!vertex.isPresent()) {
            return;
        }

        Iterable<Edge> edges = vertex.get().query()
                .labels(formatLabel(key))
                .direction(Direction.OUT)
                .edges();

        edges.forEach(Element::remove);

        sizeFor(key, vertex.get(), 0);
    }

    @Nonnull
    @Override
    public Maybe<Integer> sizeOfReference(SingleFeatureBean key) {
        return sizeOfValue(key);
    }

    //endregion
}
