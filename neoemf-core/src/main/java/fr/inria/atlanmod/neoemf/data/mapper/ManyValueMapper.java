/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.mapper;

import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * An object capable of mapping multi-valued attributes represented as a set of key/value pair.
 */
@ParametersAreNonnullByDefault
public interface ManyValueMapper extends ValueMapper {

    /**
     * A value indicating that no index is specified.
     */
    int NO_INDEX = -1;

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
    <V> Optional<V> valueOf(ManyFeatureKey key);

    /**
     * Retrieves all values of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     *
     * @return an immutable {@link List} containing all values
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    <V> List<V> allValuesOf(FeatureKey key);

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
     * @see #addValue(ManyFeatureKey, Object)
     * @see #appendValue(FeatureKey, Object)
     */
    @Nonnull
    <V> Optional<V> valueFor(ManyFeatureKey key, V value);

    /**
     * Checks whether the specified {@code key} has at least one defined value.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of value
     *
     * @return {@code true} if the {@code key} has a value, {@code false} otherwise
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default <V> boolean hasAnyValue(FeatureKey key) {
        return hasValue(key);
    }

    /**
     * Adds the {@code value} to the specified {@code key} at a defined position. If {@code key#position > size} then it
     * creates {@code null} elements to respect the position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to add
     * @param <V>   the type of value
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    <V> void addValue(ManyFeatureKey key, V value);

    /**
     * Adds the {@code value} to the specified {@code key} at the last position.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to add
     * @param <V>   the type of value
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addValue(ManyFeatureKey, Object)
     */
    default <V> void appendValue(FeatureKey key, V value) {
        checkNotNull(key);

        addValue(key.withPosition(sizeOfValue(key).orElse(0)), value);
    }

    /**
     * Adds all {@code values} to the specified {@code key} from the last position.
     *
     * @param key    the key identifying the multi-valued attribute
     * @param values the values to add
     * @param <V>    the type of values
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addValue(ManyFeatureKey, Object)
     * @see #appendValue(FeatureKey, Object)
     */
    default <V> void appendAllValues(FeatureKey key, List<V> values) {
        checkNotNull(key);
        checkNotNull(values);

        int size = sizeOfValue(key).orElse(0);

        IntStream.range(0, values.size())
                .forEach(i -> addValue(key.withPosition(size + i), values.get(i)));
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
    <V> Optional<V> removeValue(ManyFeatureKey key);

    /**
     * Removes all values of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of value
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default <V> void removeAllValues(FeatureKey key) {
        unsetValue(key);
    }

    /**
     * Checks whether the specified {@code key} has the given {@code value}.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to look for
     * @param <V>   the type of value
     *
     * @return {@code true} if the {@code key} has the {@code value}, {@code false} otherwise
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    <V> boolean containsValue(FeatureKey key, @Nullable V value);

    /**
     * Retrieves the first position of the {@code value} of the specified {@code key}.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to look for
     * @param <V>   the type of value
     *
     * @return an {@link OptionalInt} containing the first position of the {@code value}, or {@link OptionalInt#empty()}
     * if the {@code key} hasn't the {@code value}
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value);

    /**
     * Retrieves the last position of the {@code value} of the specified {@code key}.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to look for
     * @param <V>   the type of value
     *
     * @return an {@link OptionalInt} containing the last position of the {@code value}, or {@link OptionalInt#empty()}
     * if the {@code key} hasn't the {@code value}
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value);

    /**
     * Returns the number of value of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of value
     *
     * @return an {@link OptionalInt} containing the number of value of the {@code key}, or {@link OptionalInt#empty()}
     * if the {@code key} hasn't any value, or if {@code size == 0}.
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    <V> OptionalInt sizeOfValue(FeatureKey key);
}
