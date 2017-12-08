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

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;

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

        return this.<V[]>valueOf(key.withoutPosition())
                .filter(vs -> key.position() < vs.length)
                .toSingle()
                .flatMapCompletable(vs -> {
                    vs[key.position()] = value;
                    return valueFor(key.withoutPosition(), vs);
                })
                .cache();
    }

    @Nonnull
    @Override
    default <V> Completable addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        return this.<V[]>valueOf(key.withoutPosition())
                .filter(vs -> {
                    checkPositionIndex(key.position(), vs.length);
                    return true;
                })
                .switchIfEmpty(Maybe.fromCallable(() -> MoreArrays.newArray(Object.class, 0)))
                .flatMapCompletable(vs -> {
                    V[] newArray = MoreArrays.add(vs, key.position(), value);
                    return valueFor(key.withoutPosition(), newArray);
                })
                .cache();
    }

    @Nonnull
    @Override
    default <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values) {
        checkNotNull(key, "key");
        checkNotNull(values, "collection");

        if (values.isEmpty()) {
            return Completable.complete();
        }

        if (values.contains(null)) {
            throw new NullPointerException();
        }

        return this.<V[]>valueOf(key.withoutPosition())
                .filter(vs -> {
                    checkPositionIndex(key.position(), vs.length);
                    return true;
                })
                .switchIfEmpty(Maybe.fromCallable(() -> MoreArrays.newArray(Object.class, 0)))
                .flatMapCompletable(vs -> {
                    V[] newArray = MoreArrays.addAll(vs, key.position(), values);
                    return valueFor(key.withoutPosition(), newArray);
                })
                .cache();
    }

    @Nonnull
    @Override
    default Single<Boolean> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        return this.<Object[]>valueOf(key.withoutPosition())
                .filter(vs -> key.position() < vs.length)
                .flatMap(vs -> {
                    Completable completable;
                    if (vs.length == 1) {
                        completable = removeAllValues(key.withoutPosition());
                    }
                    else {
                        Object[] newArray = MoreArrays.remove(vs, key.position());
                        completable = valueFor(key.withoutPosition(), newArray);
                    }
                    return completable.toSingleDefault(true).toMaybe();
                })
                .toSingle(false)
                .cache();
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
