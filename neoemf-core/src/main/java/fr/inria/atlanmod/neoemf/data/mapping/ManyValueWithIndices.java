/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.collect.SizedIterator;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;

/**
 * A {@link ManyValueMapper} that provides a default behavior to represent the "multi-valued" directly with their
 * position.
 * <p>
 * Indices are persisted with dedicated {@link SingleFeatureBean}s containing the index of the element to store. Using
 * this approach avoid to deserialize entire {@link java.util.Collection}s to retrieve a single element, which can be an
 * important bottleneck in terms of execution time and memory consumption if the underlying model contains very large
 * {@link java.util.Collection}s.
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithIndices extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Flowable<V> allValuesOf(SingleFeatureBean key) {
        int size = sizeOfValue(key).toSingle(0).blockingGet();

        IntFunction<V> mappingFunc = i -> this.<V>valueOf(key.withPosition(i))
                .to(CommonQueries::toOptional)
                .orElse(null);

        return Flowable.fromIterable(() -> new SizedIterator<>(size, mappingFunc));
    }

    @Nonnull
    @Override
    default <V> Completable valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        return this.<V>valueOf(key)
                .toSingle()
                .flatMapCompletable(v -> innerValueFor(key, value))
                .cache();
    }

    @Nonnull
    @Override
    default <V> Completable addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        return sizeOfValue(key.withoutPosition())
                .toSingle(0)
                .filter(s -> {
                    checkPositionIndex(key.position(), s);
                    return true;
                })
                .flatMap(s -> sizeForValue(key.withoutPosition(), s + 1).toSingleDefault(s).toMaybe())
                .flattenAsFlowable(s -> IntStream.range(key.position(), s).map(i -> key.position() - i + s - 1).boxed().collect(Collectors.toList()))
                .flatMapCompletable(i -> valueOf(key.withPosition(i)).flatMapCompletable(v -> innerValueFor(key.withPosition(i + 1), v)), false, 1)
                .concatWith(innerValueFor(key, value))
                .cache();
    }

    @Nonnull
    @Override
    default <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values) {
        checkNotNull(key, "key");
        checkNotNull(values, "collection");

        if (values.contains(null)) {
            throw new NullPointerException();
        }

        return Flowable.range(0, values.size())
                .flatMapCompletable(i -> addValue(key.withPosition(key.position() + i), values.get(i)), false, 1)
                .cache();
    }

    @Nonnull
    @Override
    default Single<Boolean> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        return sizeOfValue(key.withoutPosition())
                .flatMap(s -> sizeForValue(key.withoutPosition(), s - 1).toSingleDefault(s).toMaybe())
                .flatMap(s -> Flowable.range(key.position(), s - key.position() - 1)
                        .flatMapCompletable(i -> valueOf(key.withPosition(i + 1))
                                .toSingle()
                                .flatMapCompletable(cv -> innerValueFor(key.withPosition(i), cv)), false, 1)
                        .concatWith(innerValueFor(key.withPosition(s - 1), null))
                        .toSingleDefault(true).toMaybe())
                .toSingle(false)
                .cache();
    }

    @Nonnull
    @Override
    default Completable removeAllValues(SingleFeatureBean key) {
        return sizeOfValue(key)
                .flattenAsFlowable(s -> IntStream.range(0, s).boxed().collect(Collectors.toList()))
                .flatMapCompletable(i -> innerValueFor(key.withPosition(i), null), false, 1)
                .concatWith(removeValue(key))
                .cache();
    }

    @Nonnull
    @Override
    default Maybe<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        return this.<Integer>valueOf(key)
                .filter(s -> s > 0)
                .cache();
    }

    /**
     * Defines the number of values of the specified {@code key}.
     *
     * @param key  the key identifying the multi-valued attribute
     * @param size the number of values
     *
     * @return the deffered computation
     */
    @Nonnull
    default Completable sizeForValue(SingleFeatureBean key, @Nonnegative int size) {
        checkNotNull(key, "key");
        checkArgument(size >= 0, "size (%d) must not be negative");

        return size > 0
                ? valueFor(key, size)
                : removeValue(key);
    }

    /**
     * Defines the {@code value} of the specified {@code key} at a defined position.
     * <p>
     * This method behaves like: {@link #valueFor(ManyFeatureBean, Object)}, without checking whether the multi-valued
     * feature already exists, in order to replace it. If {@code value == null}, the key is removed.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to set
     * @param <V>   the type of value
     *
     * @return the deffered computation
     */
    @Nonnull
    <V> Completable innerValueFor(ManyFeatureBean key, @Nullable V value);
}
