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
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.KeyIndexableGraph;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

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
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Maybe<V> query = get(key.owner())
                .map(v -> Optional.<V>ofNullable(v.getProperty(formatLabel(key))))
                .filter(Optional::isPresent)
                .map(Optional::get);

        return query.compose(dispatcher().dispatchMaybe());
    }

    @Nonnull
    @Override
    public <V> Completable valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Completable query = getOrCreate(key.owner())
                .flatMapCompletable(v -> Completable.fromAction(() -> v.setProperty(formatLabel(key), value)));

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Completable query = get(key.owner())
                .flatMapCompletable(v -> Completable.fromAction(() -> v.removeProperty(formatLabel(key))));

        return query.compose(dispatcher().dispatchCompletable());
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Maybe<Id> referenceOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Maybe<Id> query = get(key.owner())
                .flattenAsFlowable(v -> v.getVertices(Direction.OUT, formatLabel(key)))
                .singleElement()
                .map(v -> idConverter.revert(v.getId()));

        return query.compose(dispatcher().dispatchMaybe());
    }

    @Nonnull
    @Override
    public Completable referenceFor(SingleFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Completable query = getOrCreate(key.owner())
                .filter(v -> key.position() < sizeOf(key, v))
                .toSingle()
                .flatMapCompletable(v -> Completable.fromAction(() -> {
                    v.getEdges(Direction.OUT, formatLabel(key)).forEach(Edge::remove);
                    v.addEdge(formatLabel(key), blockingGetOrCreate(reference));
                }));

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Completable removeReference(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Completable query = get(key.owner())
                .flatMapCompletable(v -> Completable.fromAction(() -> v.getEdges(Direction.OUT, formatLabel(key)).forEach(Edge::remove)));

        return query.compose(dispatcher().dispatchCompletable());
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Maybe<V> query = get(key.owner())
                .map(v -> Optional.<V>ofNullable(v.getProperty(formatProperty(key, key.position()))))
                .filter(Optional::isPresent)
                .map(Optional::get);

        return query.compose(dispatcher().dispatchMaybe());
    }

    @Nonnull
    @Override
    public <V> Flowable<V> allValuesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Flowable<V> query = get(key.owner())
                .flattenAsFlowable(v -> IntStream.range(0, sizeOf(key, v))
                        .mapToObj(i -> new AbstractMap.SimpleImmutableEntry<>(v, i))
                        .collect(Collectors.toList()))
                .map(e -> e.getKey().getProperty(formatProperty(key, e.getValue())));

        return query.compose(dispatcher().dispatchFlowable());
    }

    @Nonnull
    public <V> Completable valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Completable query = get(key.owner())
                .filter(v -> key.position() < sizeOf(key.withoutPosition(), v))
                .toSingle()
                .flatMapCompletable(v -> Completable.fromAction(() -> v.setProperty(formatProperty(key, key.position()), value)));

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public <V> Completable addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Completable query = getOrCreate(key.owner())
                .flatMapCompletable(v -> Completable.fromAction(() -> {
                    final int size = sizeOf(key.withoutPosition(), v);
                    checkPositionIndex(key.position(), size);

                    for (int i = size - 1; i >= key.position(); i--) {
                        v.setProperty(formatProperty(key, i + 1), v.getProperty(formatProperty(key, i)));
                    }

                    v.setProperty(formatProperty(key, key.position()), value);
                    sizeFor(key.withoutPosition(), v, size + 1);
                }));

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values) {
        checkNotNull(key, "key");
        checkNotNull(values, "values");

        if (values.isEmpty()) {
            return Completable.complete();
        }

        checkNotContainsNull(values);

        Completable query = getOrCreate(key.owner())
                .flatMapCompletable(v -> Completable.fromAction(() -> {
                    final int size = sizeOf(key.withoutPosition(), v);
                    checkPositionIndex(key.position(), size);

                    int additionCount = values.size();

                    for (int i = size - 1; i >= key.position(); i--) {
                        v.setProperty(formatProperty(key, i + additionCount), v.getProperty(formatProperty(key, i)));
                    }

                    for (int i = 0; i < additionCount; i++) {
                        v.setProperty(formatProperty(key, key.position() + i), values.get(i));
                    }

                    sizeFor(key.withoutPosition(), v, size + additionCount);
                }));

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Single<Boolean> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Single<Boolean> query = get(key.owner())
                .map(v -> {
                    final int size = sizeOf(key.withoutPosition(), v);
                    if (size <= 0) {
                        return false;
                    }

                    for (int i = key.position(); i < size - 1; i++) {
                        v.setProperty(formatProperty(key, i), v.getProperty(formatProperty(key, i + 1)));
                    }

                    v.removeProperty(formatProperty(key, size - 1));
                    sizeFor(key.withoutPosition(), v, size - 1);

                    return true;
                })
                .toSingle(false);

        return query.compose(dispatcher().dispatchSingle());
    }

    @Nonnull
    @Override
    public Completable removeAllValues(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Completable query = get(key.owner())
                .flatMapCompletable(v -> Completable.fromAction(() -> {
                    IntStream.range(0, sizeOf(key, v)).forEach(i -> v.removeProperty(formatProperty(key, i)));
                    sizeFor(key, v, 0);
                }));

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Maybe<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Maybe<Integer> query = get(key.owner())
                .map(v -> sizeOf(key, v))
                .filter(s -> s > 0);

        return query.compose(dispatcher().dispatchMaybe());
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Maybe<Id> referenceOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Maybe<Id> query = get(key.owner())
                .flattenAsFlowable(v -> v.query().direction(Direction.OUT).labels(formatLabel(key))
                        .has(PROPERTY_INDEX, key.position())
                        .vertices())
                .singleElement()
                .map(v -> idConverter.revert(v.getId()));

        return query.compose(dispatcher().dispatchMaybe());
    }

    @Nonnull
    @Override
    public Flowable<Id> allReferencesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Flowable<Id> query = get(key.owner())
                .flattenAsFlowable(v -> v.getEdges(Direction.OUT, formatLabel(key)))
                .sorted(Comparator.comparingInt(e -> e.getProperty(PROPERTY_INDEX)))
                .map(e -> idConverter.revert(e.getVertex(Direction.IN).getId()));

        return query.compose(dispatcher().dispatchFlowable());
    }

    @Nonnull
    @Override
    public Completable referenceFor(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Completable query = get(key.owner())
                .filter(v -> key.position() < sizeOf(key.withoutPosition(), v))
                .toSingle()
                .flatMapCompletable(v -> Completable.fromAction(() -> {
                    v.query().direction(Direction.OUT).labels(formatLabel(key))
                            .has(PROPERTY_INDEX, key.position())
                            .edges()
                            .forEach(Edge::remove);

                    v.addEdge(formatLabel(key), blockingGetOrCreate(reference)).setProperty(PROPERTY_INDEX, key.position());
                }));

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Completable addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Completable query = getOrCreate(key.owner())
                .flatMapCompletable(vp -> Completable.fromAction(() -> {
                    final int size = sizeOf(key.withoutPosition(), vp);
                    checkPositionIndex(key.position(), size);

                    if (key.position() < size) {
                        vp.query().direction(Direction.OUT).labels(formatLabel(key))
                                .interval(PROPERTY_INDEX, key.position(), size + 1)
                                .limit(size + 1 - key.position())
                                .edges()
                                .forEach(e -> e.setProperty(PROPERTY_INDEX, e.<Integer>getProperty(PROPERTY_INDEX) + 1));
                    }

                    vp.addEdge(formatLabel(key), blockingGetOrCreate(reference)).setProperty(PROPERTY_INDEX, key.position());
                    sizeFor(key.withoutPosition(), vp, size + 1);
                }));

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Completable addAllReferences(ManyFeatureBean key, List<Id> references) {
        checkNotNull(key, "key");
        checkNotNull(references, "collection");

        if (references.isEmpty()) {
            return Completable.complete();
        }

        checkNotContainsNull(references);

        Completable query = getOrCreate(key.owner())
                .flatMapCompletable(v -> Completable.fromAction(() -> {
                    int size = sizeOf(key.withoutPosition(), v);
                    checkPositionIndex(key.position(), size);

                    int additionCount = references.size();

                    if (key.position() < size) {
                        v.query().direction(Direction.OUT).labels(formatLabel(key))
                                .interval(PROPERTY_INDEX, key.position(), size + additionCount)
                                .limit(size + additionCount - key.position())
                                .edges()
                                .forEach(e -> e.setProperty(PROPERTY_INDEX, e.<Integer>getProperty(PROPERTY_INDEX) + additionCount));
                    }

                    for (int i = 0; i < additionCount; i++) {
                        v.addEdge(formatLabel(key), blockingGetOrCreate(references.get(i))).setProperty(PROPERTY_INDEX, key.position() + i);
                    }

                    sizeFor(key.withoutPosition(), v, size + additionCount);
                }));

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Single<Boolean> removeReference(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Single<Boolean> query = get(key.owner())
                .map(v -> {
                    final int size = sizeOf(key.withoutPosition(), v);
                    if (size == 0) {
                        return false;
                    }

                    v.query().direction(Direction.OUT).labels(formatLabel(key))
                            .interval(PROPERTY_INDEX, key.position(), size)
                            .limit(size - key.position())
                            .edges()
                            .forEach(e -> {
                                final int position = e.getProperty(PROPERTY_INDEX);
                                if (position != key.position()) {
                                    e.setProperty(PROPERTY_INDEX, position - 1);
                                }
                                else {
                                    e.remove();
                                }
                            });

                    sizeFor(key.withoutPosition(), v, size - 1);

                    return true;
                })
                .toSingle(false);

        return query.compose(dispatcher().dispatchSingle());
    }

    @Nonnull
    @Override
    public Completable removeAllReferences(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Completable query = get(key.owner())
                .flatMapCompletable(v -> Completable.fromAction(() -> {
                    v.getEdges(Direction.OUT, formatLabel(key)).forEach(Element::remove);
                    sizeFor(key, v, 0);
                }));

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Maybe<Integer> sizeOfReference(SingleFeatureBean key) {
        return sizeOfValue(key);
    }

    //endregion
}
