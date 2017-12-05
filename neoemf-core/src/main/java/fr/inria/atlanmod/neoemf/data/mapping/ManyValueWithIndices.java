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

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

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
        final Iterator<V> iter = new Iterator<V>() {

            /**
             * The size of the iterator.
             */
            final int size = sizeOfValue(key).orElse(0);

            /**
             * The current position.
             */
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return ManyValueWithIndices.this.<V>valueOf(key.withPosition(currentIndex++))
                        .to(CommonQueries::toOptional)
                        .orElse(null);
            }
        };

        return Flowable.fromIterable(() -> iter);
    }

    @Nonnull
    @Override
    default <V> Single<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Action setFunc = () -> innerValueFor(key, value).subscribe();

        Consumer<V> replaceFunc = Functions.actionConsumer(setFunc);

        return this.<V>valueOf(key)
                .toSingle()
                .doAfterSuccess(replaceFunc)
                .cache();
    }

    @Override
    default <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        int size = sizeOfValue(key.withoutPosition()).orElse(0);
        checkPositionIndex(key.position(), size);

        for (int i = size - 1; i >= key.position(); i--) {
            innerValueFor(key.withPosition(i + 1), valueOf(key.withPosition(i)).toSingle().blockingGet()).blockingAwait();
        }

        sizeForValue(key.withoutPosition(), size + 1);

        innerValueFor(key, value).blockingAwait();
    }

    @Override
    default <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        int firstPosition = key.position();

        IntStream.range(0, collection.size())
                .forEach(i -> addValue(key.withPosition(firstPosition + i), collection.get(i)));
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        int size = sizeOfValue(key.withoutPosition()).orElse(0);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<V> previousValue = this.<V>valueOf(key).to(CommonQueries::toOptional);

        for (int i = key.position(); i < size - 1; i++) {
            innerValueFor(key.withPosition(i), valueOf(key.withPosition(i + 1)).toSingle().blockingGet()).blockingAwait();
        }

        innerValueFor(key.withPosition(size - 1), null).blockingAwait();

        sizeForValue(key.withoutPosition(), size - 1);

        return previousValue;
    }

    @Override
    default void removeAllValues(SingleFeatureBean key) {
        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> innerValueFor(key.withPosition(i), null).blockingAwait());

        removeValue(key).blockingAwait();
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        return this.<Integer>valueOf(key)
                .to(CommonQueries::toOptional)
                .filter(s -> s > 0);
    }

    /**
     * Defines the number of values of the specified {@code key}.
     *
     * @param key  the key identifying the multi-valued attribute
     * @param size the number of values
     *
     * @throws NullPointerException     if the {@code key} is {@code null}
     * @throws IllegalArgumentException if {@code size < 0}
     */
    default void sizeForValue(SingleFeatureBean key, @Nonnegative int size) {
        checkNotNull(key, "key");
        checkArgument(size >= 0, "size (%d) must not be negative");

        if (size > 0) {
            valueFor(key, size).ignoreElement().blockingAwait();
        }
        else {
            removeValue(key).blockingAwait();
        }
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
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    <V> Completable innerValueFor(ManyFeatureBean key, @Nullable V value);
}
