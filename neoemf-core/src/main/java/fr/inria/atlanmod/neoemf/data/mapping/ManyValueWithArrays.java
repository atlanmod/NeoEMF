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
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
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
    default <V> Completable valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Consumer<V[]> replaceFunc = vs -> {
            vs[key.position()] = value;
            valueFor(key.withoutPosition(), vs).subscribe();
        };

        return this.<V[]>valueOf(key.withoutPosition())
                .filter(vs -> key.position() < vs.length)
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

        V[] vs = this.<V[]>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .orElseGet(() -> MoreArrays.newArray(Object.class, 0));

        checkPositionIndex(key.position(), vs.length);

        vs = MoreArrays.add(vs, key.position(), value);

        return valueFor(key.withoutPosition(), vs);
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

        V[] vs = this.<V[]>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .orElseGet(() -> MoreArrays.newArray(Object.class, 0));

        checkPositionIndex(key.position(), vs.length);

        vs = MoreArrays.addAll(vs, key.position(), values);

        return valueFor(key.withoutPosition(), vs);
    }

    @Nonnull
    @Override
    default <V> Maybe<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        V[] vs = this.<V[]>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .orElse(null);

        if (isNull(vs)) {
            return Maybe.empty();
        }

        Optional<V> previousValue = Optional.empty();

        if (key.position() < vs.length) {
            previousValue = Optional.of(vs[key.position()]);

            vs = MoreArrays.remove(vs, key.position());

            if (vs.length == 0) {
                removeAllValues(key.withoutPosition()).blockingAwait();
            }
            else {
                valueFor(key.withoutPosition(), vs).blockingAwait();
            }
        }

        return previousValue
                .map(Maybe::just)
                .orElseGet(Maybe::empty);
    }

    @Nonnull
    @Override
    default Completable removeAllValues(SingleFeatureBean key) {
        return this.removeValue(key);
    }

    @Nonnull
    @Override
    default Maybe<Integer> sizeOfValue(SingleFeatureBean key) {
        return this.<Object[]>valueOf(key)
                .map(a -> a.length)
                .filter(s -> s > 0)
                .cache();
    }
}
