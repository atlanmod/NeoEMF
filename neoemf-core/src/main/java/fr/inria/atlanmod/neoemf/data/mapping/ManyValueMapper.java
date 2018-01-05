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

import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import static fr.inria.atlanmod.commons.Preconditions.checkNotContainsNull;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An object capable of mapping multi-valued attributes represented as a set of key/value pair.
 */
@ParametersAreNonnullByDefault
public interface ManyValueMapper extends ValueMapper {

    /**
     * Retrieves the value of the specified {@code key} at a defined position.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @return the deferred computation that may contains the value
     */
    @Nonnull
    <V> Maybe<V> valueOf(ManyFeatureBean key);

    /**
     * Retrieves all values of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @return the deferred computation that contains all ordered values
     */
    @Nonnull
    <V> Flowable<V> allValuesOf(SingleFeatureBean key);

    /**
     * Defines the {@code value} of the specified {@code key} at a defined position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to set
     * @param <V>   the type of value
     *
     * @return the deferred computation that may contains a {@link NoSuchElementException} if the {@code key} is not defined
     *
     * @see #addValue(ManyFeatureBean, Object)
     * @see #appendValue(SingleFeatureBean, Object)
     */
    @Nonnull
    <V> Completable valueFor(ManyFeatureBean key, V value);

    /**
     * Adds the {@code value} to the specified {@code key} at a defined position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to add
     * @param <V>   the type of value
     *
     * @return the deferred computation
     */
    @Nonnull
    <V> Completable addValue(ManyFeatureBean key, V value);

    /**
     * Adds all the {@code collection} to the specified {@code key} from the position of the {@code key}.
     *
     * @param <V>    the type of value
     * @param key    the key identifying the multi-valued attribute
     * @param values the values to add
     *
     * @return the deferred computation
     */
    @Nonnull
    <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values);

    /**
     * Adds the {@code value} to the specified {@code key} at the last position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to add
     * @param <V>   the type of value
     *
     * @return the deferred computation that contains the position to which the value was added
     *
     * @see #addValue(ManyFeatureBean, Object)
     */
    @Nonnull
    default <V> Single<Integer> appendValue(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        return sizeOfValue(key)
                .toSingle(0)
                .flatMap(s -> addValue(key.withPosition(s), value).toSingleDefault(s))
                .cache();
    }

    /**
     * Adds all the {@code collection} to the specified {@code key} from the last position.
     *
     * @param <V>    the type of collection
     * @param key    the key identifying the multi-valued attribute
     * @param values the values to add
     *
     * @return the deferred computation that contains the position to which the first value was added
     *
     * @see #addValue(ManyFeatureBean, Object)
     * @see #appendValue(SingleFeatureBean, Object)
     */
    @Nonnull
    default <V> Single<Integer> appendAllValues(SingleFeatureBean key, List<? extends V> values) {
        checkNotNull(key, "key");
        checkNotNull(values, "values");
        checkNotContainsNull(values);

        return sizeOfValue(key)
                .toSingle(0)
                .flatMap(s -> addAllValues(key.withPosition(s), values).toSingleDefault(s))
                .cache();
    }

    /**
     * Removes the value of the specified {@code key} at a defined position.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @return the deferred computation that contains {@code true} if a value has been removed
     */
    @Nonnull
    Single<Boolean> removeValue(ManyFeatureBean key);

    /**
     * Removes all values of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @return the deferred computation
     */
    @Nonnull
    Completable removeAllValues(SingleFeatureBean key);

    /**
     * Returns the number of value of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @return the deferred computation that may contains the size
     */
    @Nonnull
    Maybe<Integer> sizeOfValue(SingleFeatureBean key);
}
