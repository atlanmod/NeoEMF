/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkNotContainsNull;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;

/**
 * A {@link ManyValueMapper} that provides a default behavior to represent the "multi-valued" characteristic as {@link
 * List}s. The implementation used is specified by the {@link #getOrCreateList(SingleFeatureBean)} method.
 * <p>
 * Using a {@link List}-based implementation allows to benefit from the rich Java {@link java.util.Collection} API, with
 * the cost of a small memory overhead compared to raw arrays.
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithLists extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Maybe<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        return this.<List<V>>valueOf(key.withoutPosition())
                .filter(values -> key.position() < values.size())
                .map(values -> values.get(key.position()))
                .cache();
    }

    @Nonnull
    @Override
    default <V> Flowable<V> allValuesOf(SingleFeatureBean key) {
        return this.<List<V>>valueOf(key)
                .flattenAsFlowable(Functions.identity())
                .cache();
    }

    @Nonnull
    @Override
    default <V> Completable valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        return this.<List<V>>valueOf(key.withoutPosition())
                .filter(vs -> key.position() < vs.size())
                .toSingle()
                .flatMapCompletable(vs -> {
                    vs.set(key.position(), value);
                    return valueFor(key.withoutPosition(), vs);
                })
                .cache();
    }

    @Nonnull
    @Override
    default <V> Completable addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        return this.<List<V>>valueOf(key.withoutPosition())
                .filter(vs -> {
                    checkPositionIndex(key.position(), vs.size());
                    return true;
                })
                .switchIfEmpty(Maybe.fromCallable(() -> getOrCreateList(key.withoutPosition())))
                .flatMapCompletable(vs -> {
                    vs.add(key.position(), value);
                    return valueFor(key.withoutPosition(), vs);
                })
                .cache();
    }

    @Nonnull
    @Override
    default <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values) {
        checkNotNull(key, "key");
        checkNotNull(values, "values");

        if (values.isEmpty()) {
            return Completable.complete();
        }

        checkNotContainsNull(values);

        return this.<List<V>>valueOf(key.withoutPosition())
                .filter(vs -> {
                    checkPositionIndex(key.position(), vs.size());
                    return true;
                })
                .switchIfEmpty(Maybe.fromCallable(() -> getOrCreateList(key.withoutPosition())))
                .flatMapCompletable(vs -> {
                    vs.addAll(key.position(), values);
                    return valueFor(key.withoutPosition(), vs);
                })
                .cache();
    }

    @Nonnull
    @Override
    default Single<Boolean> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        return this.<List<Object>>valueOf(key.withoutPosition())
                .filter(vs -> key.position() < vs.size())
                .concatMap(vs -> {
                    Completable completable;
                    if (vs.size() == 1) {
                        completable = removeAllValues(key.withoutPosition());
                    }
                    else {
                        vs.remove(key.position());
                        completable = valueFor(key.withoutPosition(), vs);
                    }
                    return completable.toSingleDefault(true).toMaybe();
                })
                .toSingle(false)
                .cache();
    }

    @Nonnull
    @Override
    default Completable removeAllValues(SingleFeatureBean key) {
        return removeValue(key);
    }

    @Nonnull
    @Override
    default Maybe<Integer> sizeOfValue(SingleFeatureBean key) {
        return this.<List<Object>>valueOf(key)
                .map(List::size)
                .filter(s -> s > 0)
                .cache();
    }

    /**
     * Gets or creates a new {@link List} to store the multi-valued features identified by the {@code key}.
     * <p>
     * By default, this method creates an {@link ArrayList} which favor random read access method, to the detriment of
     * insertions and deletions.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of elements in this list
     *
     * @return a new {@link List}
     */
    default <V> List<V> getOrCreateList(SingleFeatureBean key) {
        return new ArrayList<>();
    }
}
