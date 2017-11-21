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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

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
     * @return an {@link Optional} containing the value, or {@link Optional#empty()} if the key hasn't any value or
     * doesn't exist
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    <V> Optional<V> valueOf(ManyFeatureBean key);

    /**
     * Retrieves all values of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @return an immutable ordered {@link Stream} containing all values
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    <V> Stream<V> allValuesOf(SingleFeatureBean key);

    /**
     * Defines the {@code value} of the specified {@code key} at a defined position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to set
     * @param <V>   the type of value
     *
     * @return an {@link Optional} containing the previous value of the {@code key}, or {@link Optional#empty()} if the
     * key has no value before
     *
     * @throws NoSuchElementException if the {@code key} doesn't exist
     * @throws NullPointerException   if any parameter is {@code null}
     * @implSpec This method is intended to modify an existing value. If the {@code key} is not defined, implementations
     * should not add the value, but throw a {@link NoSuchElementException}.
     * @see #addValue(ManyFeatureBean, Object)
     * @see #appendValue(SingleFeatureBean, Object)
     */
    @Nonnull
    <V> Optional<V> valueFor(ManyFeatureBean key, V value);

    /**
     * Adds the {@code value} to the specified {@code key} at a defined position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to add
     * @param <V>   the type of value
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code key#position() > size}
     */
    <V> void addValue(ManyFeatureBean key, V value);

    /**
     * Adds all the {@code collection} to the specified {@code key} from the position of the {@code key}.
     *
     * @param key        the key identifying the multi-valued attribute
     * @param collection the values to add
     * @param <V>        the type of value
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code key#position() > size}
     */
    <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection);

    /**
     * Adds the {@code value} to the specified {@code key} at the last position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to add
     * @param <V>   the type of value
     *
     * @return the position to which the value was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addValue(ManyFeatureBean, Object)
     */
    @Nonnegative
    default <V> int appendValue(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        int position = sizeOfValue(key).orElse(0);

        addValue(key.withPosition(position), value);

        return position;
    }

    /**
     * Adds all the {@code collection} to the specified {@code key} from the last position.
     *
     * @param key        the key identifying the multi-valued attribute
     * @param collection the values to add
     * @param <V>        the type of collection
     *
     * @return the position to which the first value was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addValue(ManyFeatureBean, Object)
     * @see #appendValue(SingleFeatureBean, Object)
     */
    @Nonnegative
    default <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");

        int firstPosition = sizeOfValue(key).orElse(0);

        addAllValues(key.withPosition(firstPosition), collection);

        return firstPosition;
    }

    /**
     * Removes the value of the specified {@code key} at a defined position.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of value
     *
     * @return an {@link Optional} containing the removed value, or {@link Optional#empty()} if the key has no value
     * before
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    <V> Optional<V> removeValue(ManyFeatureBean key);

    /**
     * Removes all values of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    void removeAllValues(SingleFeatureBean key);

    /**
     * Returns the number of value of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @return an {@link Optional} containing the number of value of the {@code key}, or {@link Optional#empty()} if the
     * {@code key} hasn't any value, or if {@code size == 0}.
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    Optional<Integer> sizeOfValue(SingleFeatureBean key);
}
