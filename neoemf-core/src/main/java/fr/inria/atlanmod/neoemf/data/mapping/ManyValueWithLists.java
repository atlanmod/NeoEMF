/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

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

        Consumer<List<V>> replaceFunc = vs -> {
            vs.set(key.position(), value);
            valueFor(key.withoutPosition(), vs).subscribe();
        };

        return this.<List<V>>valueOf(key.withoutPosition())
                .filter(vs -> key.position() < vs.size())
                .toSingle()
                .doOnSuccess(replaceFunc)
                .toCompletable()
                .cache();
    }

    @Nonnull
    @Override
    default <V> Completable addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .orElseGet(() -> getOrCreateList(key.withoutPosition()));

        checkPositionIndex(key.position(), values.size());

        values.add(key.position(), value);

        return valueFor(key.withoutPosition(), values);
    }

    @Nonnull
    @Override
    default <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values) {
        checkNotNull(key, "key");
        checkNotNull(values, "collection");

        if (values.isEmpty()) {
            return Completable.complete();
        }

        if (values.stream().anyMatch(Objects::isNull)) {
            throw new NullPointerException();
        }

        List<V> vs = this.<List<V>>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .orElseGet(() -> getOrCreateList(key.withoutPosition()));

        checkPositionIndex(key.position(), vs.size());

        vs.addAll(key.position(), values);

        return valueFor(key.withoutPosition(), vs);
    }

    @Nonnull
    @Override
    default <V> Maybe<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .orElse(null);

        if (isNull(values)) {
            return Maybe.empty();
        }

        Optional<V> previousValue = Optional.empty();

        if (key.position() < values.size()) {
            previousValue = Optional.of(values.remove(key.position()));

            if (values.isEmpty()) {
                removeAllValues(key.withoutPosition()).blockingAwait();
            }
            else {
                valueFor(key.withoutPosition(), values).blockingAwait();
            }
        }

        return previousValue
                .map(Maybe::just)
                .orElseGet(Maybe::empty);
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
