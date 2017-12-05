/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.collect.MoreArrays;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link ManyValueMapper} that provides a default behavior to represent the "multi-valued" characteristic as {@link
 * Object}[].
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithArrays extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Maybe<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        return this.<V[]>valueOf(key.withoutPosition())
                .filter(values -> key.position() < values.length)
                .map(values -> values[key.position()])
                .cache();
    }

    @Nonnull
    @Override
    default <V> Flowable<V> allValuesOf(SingleFeatureBean key) {
        return this.<V[]>valueOf(key)
                .flattenAsFlowable(Arrays::asList)
                .cache();
    }

    @Nonnull
    @Override
    default <V> Single<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Consumer<V[]> replaceFunc = vs -> {
            vs[key.position()] = value;
            valueFor(key.withoutPosition(), vs).ignoreElement().subscribe();
        };

        return this.<V[]>valueOf(key.withoutPosition())
                .toSingle()
                .doAfterSuccess(replaceFunc)
                .map(vs -> vs[key.position()])
                .cache();
    }

    @Override
    default <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .orElseGet(() -> MoreArrays.newArray(Object.class, 0));

        checkPositionIndex(key.position(), values.length);

        values = MoreArrays.add(values, key.position(), value);

        valueFor(key.withoutPosition(), values).ignoreElement().blockingAwait();
    }

    @Override
    default <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .orElseGet(() -> MoreArrays.newArray(Object.class, 0));

        int firstPosition = key.position();
        checkPositionIndex(firstPosition, values.length);

        values = MoreArrays.addAll(values, firstPosition, collection);

        valueFor(key.withoutPosition(), values).ignoreElement().blockingAwait();
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .orElse(null);

        if (isNull(values)) {
            return Optional.empty();
        }

        Optional<V> previousValue = Optional.empty();

        if (key.position() < values.length) {
            previousValue = Optional.of(values[key.position()]);

            values = MoreArrays.remove(values, key.position());

            if (values.length == 0) {
                removeAllValues(key.withoutPosition());
            }
            else {
                valueFor(key.withoutPosition(), values).ignoreElement().blockingAwait();
            }
        }

        return previousValue;
    }

    @Override
    default void removeAllValues(SingleFeatureBean key) {
        this.removeValue(key).blockingAwait();
    }

    @Nonnull
    @Nonnegative
    @Override
    default Maybe<Integer> sizeOfValue(SingleFeatureBean key) {
        return this.<Object[]>valueOf(key)
                .map(a -> a.length)
                .filter(s -> s > 0)
                .cache();
    }
}
